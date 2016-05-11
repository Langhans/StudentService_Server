package outputWriter;

import static application.Settings.ERROR_JSON_ERRORCONTENT_STUB;
import static application.Settings.ERROR_NO_SUCH_STUDENT_ID_STUB;
import static application.Settings.ERROR_XML_ERRORCONTENT_STUB;

import java.util.List;

import application.ErrorType;
import application.StorageException;
import commons.Format;
import commons.Service;

public class WriterSelector {

  /**
   * class to select the correct Writer for translating the data input from
   * the database-layer into the required request content format.
   * 
   */
  
  
  /**
   * choses the correct Writer to translate a java.sql.result directly into the 
   * 
   * @param result
   * @param service
   * @param format
   * @return
   * @throws StorageException
   */
//  public static String writeInChosenFormat(List result, Service service,
//      Format format) {
//
//    switch (format) {
//      case JSON:
//        if (service == Service.GET_COURSES_BY_ID)
//          return JsonWriter.writeJSON_courses(result);
//        else
//          return JsonWriter.writeJSON_students(result);
//      case XML:
//        if (service == Service.GET_COURSES_BY_ID)
//          return XmlWriter.writeXML_courses(result);
//        else
//          return XmlWriter.writeXML_students(result);
//      default:
//        return "Format not implemented yet (may never happen)";
//    }
//  }
//
//  
//  /**
//   * writes an error content-message in the specified format. 
//   * 
//   *  e.g. in JSON: 
//   *        {"errorType" : "JSONApiError" , "message" : "No such StudentID" }
//   * @param errorName
//   * @param errorMessage
//   * @param format
//   * @return formatted content as java.lang.String
//   */
//  public static String writeErrorContent(String errorName, String errorMessage,
//      Format format) {
//
//    switch (format) {
//      case XML:
//        return String.format(ERROR_XML_ERRORCONTENT_STUB, errorName,
//            errorMessage);
//
//      case JSON:
//        return String.format(ERROR_JSON_ERRORCONTENT_STUB, errorName,
//            errorMessage);
//      default:
//        return "Format not implemented yet (may never happen)";
//    }
//  }
//
//  /**
//   * just a String for making a correct error response content
//   * using the defined NO-SUCH_STUDENT_ID_STUB from the global
//   * settings
//   * 
//   * e.g.: 'No data for Student with id: 666'
//   * 
//   * @param id - Student ID as int
//   * @return java.lang.String of error message 
//   */
//  public static String getNoSuchStudentIdMessage(int id) {
//    return String.format(ERROR_NO_SUCH_STUDENT_ID_STUB, id);
//}
}
