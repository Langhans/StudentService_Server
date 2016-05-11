package servlets;

import static application.MainLogger.getLogger;
import static application.Settings.ERROR_TYPE_EMPTY_DATASET;
import static application.Settings.ERROR_TYPE_REQUEST_ERROR;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import application.ErrorType;
import application.Settings;
import application.StorageException;
import commons.Format;
import commons.Student;
import commons.StudentsRegistrations;
import outputWriter.ContentWriterFactory;
import outputWriter.IContentWriter;
import outputWriter.WriterException;
import outputWriter.WriterTools;
import storageHandler.IStorageManager;
import storageHandler.StorageManagerFactory;

@WebServlet("/serviceOLD")
public class DBServlet_original extends HttpServlet {

  private static final long   serialVersionUID = 1L;
  private static final String LOG_TAG          = DBServlet_original.class.getName();

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String param_format = request.getParameter(Settings.PARAM_FORMAT);
    String param_id = request.getParameter(Settings.PARAM_ID);
    String errorMessage;

    //
    // VALIDATION OF USER INPUT
    //
    // TODO better way? Separate validation from request-handling!

    // check for empty parameters
    if (param_format == null || param_id == null) {
      errorMessage = "BAD_REQUEST_MISSING_PARAMETER";
      response = returnBadRequest(errorMessage, response);
      getLogger().log(Level.SEVERE, LOG_TAG + errorMessage);
      return;
    }

    Format format = VerificationTools.parseFormatParameter(param_format);

    // check for correct, supported format
    if (format == Format.NOT_SUPPORTED) {
      errorMessage = "BAD_REQUEST_FORMAT_NOT_SUPPORTED";
      response = returnBadRequest(errorMessage, response);
      getLogger().log(Level.SEVERE, LOG_TAG + errorMessage);
      return;
    }

    // check for correct id
    if (!VerificationTools.isCorrectIdParameter(param_id)) {
      errorMessage = "BAD_REQUEST_BAD_ID_PARAMETER";
      response = returnBadRequest(errorMessage, response);
      getLogger().log(Level.SEVERE, LOG_TAG + errorMessage);
      return;
    }

    int id = VerificationTools.parseIdParameter(param_id);

    //
    // GET CONTENT and translate it to correct format using an IStorageManager
    // and an IContentWriter object
    //
    IContentWriter writer = ContentWriterFactory.getContentWriter(format);
    String content;

    try {
      IStorageManager handler = StorageManagerFactory.getStorageManager();

      if (Settings.PARAM_ID_ALL.equals(param_id.toLowerCase())) {
        List<Student> students = handler.getAllStudents();
        content = writer.writeAllStudents(students);
      } else {
        StudentsRegistrations reg = handler.getRegistrationsForAStudent(id);
        content = writer.writeAStudentsRegistrations(reg);
      }

      // RETURN SUCCESS-RESPONSE
      response.setStatus(200);
      response.setContentType(format.getContentType());
      response.getWriter().append(content);
      response.getWriter().close();
      getLogger().log(Level.INFO,
          LOG_TAG + "Returned successfully: " + content);
      return;

      //
      // HANDLE DIFFERENT DB-EXCEPTIONS
      //
      
    } catch (WriterException e){
   // send other Error Response, WRITER ERROR
      String errorContent = "CANNOT FORMAT CONTENT";
      response.setStatus(500);
      response.setContentType("text/html");
      response.getWriter().append(errorContent);
      response.getWriter().close();
      getLogger().log(Level.SEVERE, LOG_TAG + errorContent , e);
      return;
      
    } catch (StorageException e) {
      String errorContent;

      // QUERY GIVES AN EMPTY RESULTSET,NO DATA
      if (e.getErrorType() == ErrorType.DATABASE_ERROR) {
        // send 404 NOT FOUND RESPONSE
        errorContent = writer.writeErrorContent(ERROR_TYPE_EMPTY_DATASET,
            e.getMessage());
        response = returnDataNotFoundResponse(errorContent, format, response);
        getLogger().log(Level.SEVERE, LOG_TAG + errorContent , e);
        return;

        // QUERY FETCH_ONE, NO DATA FOR THIS STUDENT ID
      } else if (e.getErrorType() == ErrorType.NO_SUCH_ID) {
        String errorMes = WriterTools.getNoSuchStudentIdMessage(id);
        errorContent = writer.writeErrorContent(ERROR_TYPE_REQUEST_ERROR,
            errorMes);
        response = returnDataNotFoundResponse(errorContent, format, response);
        getLogger().log(Level.SEVERE, LOG_TAG + errorContent , e);
        return;

        // ANY OTHER DATABASE ERROR(SQLEXCEPTIONS RETHROWS!)
      } else {
        // send other Error Response, database error type
        errorContent = "NOT SPECIFIED ERROR";
        response.setStatus(500);
        response.setContentType("text/html");
        response.getWriter().append(errorContent);
        response.getWriter().close();
        getLogger().log(Level.SEVERE, LOG_TAG + errorContent ,e );
        return;
      }
    } catch (Exception e) {
      getLogger().log(Level.SEVERE, LOG_TAG + "NOT SPECIFIED EXCEPTION");
      return;
    }
  }

  @Override
  protected void doPost(HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }

  //
  // HELP FUNCTIONS
  //

  // help function - returns a status 400 - Bad Request HttpServletResponse
  private HttpServletResponse returnBadRequest(String errorMessage,
      HttpServletResponse response) {
    try {
      response.setStatus(400);
      response.setContentType("text/html");
      response.getWriter().append(errorMessage).close();
    } catch (IOException e) {
      getLogger().log(Level.SEVERE,
          LOG_TAG + "IOException in returnBadRequest!" );
    }
    return response;
  }

  // help function - returns a status 404 - Data Not Found HttpServletResponse
  private HttpServletResponse returnDataNotFoundResponse(String errorContent,
      Format format, HttpServletResponse response) {
    try {
      response.setStatus(404);
      response.setContentType(format.getContentType());
      response.getWriter().append(errorContent).close();
    } catch (IOException e) {
      getLogger().log(Level.SEVERE,
          LOG_TAG + "IOException in returnDataNotFoundRequest!");
    }
    return response;
  }
  
  private HttpServletResponse returnUnspecifiedErrorResponse(
      HttpServletResponse response){
    String errorContent = "NOT SPECIFIED ERROR";
    try{
    response.setStatus(500);
    response.setContentType("text/html");
    response.getWriter().append(errorContent).close();
    return response;
    } catch (IOException e){
      getLogger().log(Level.SEVERE, LOG_TAG + errorContent);
    }
    return response;
  }
  
}
