package storageHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;

import application.ErrorType;
import application.MainLogger;
import application.Settings;
import application.StorageException;
import commons.Course;
import commons.Student;
import commons.StudentsRegistrations;

public class DbHandler implements IDbHandler {

  // Singleton object
  private static DbHandler handler = new DbHandler();

  private DBConnectionPool conPool = DBConnectionPool.getInstance();
  
  private static final String LOG_TAG = DbHandler.class.getName(); 
  
  private DbHandler() {
  }

  // USAGE OF SINGLETON OR NOT DOES NOT MAKE A DIFFERENCE?!
  public static DbHandler getInstance() {
    return handler;
//    return new DbHandler();
  }

  @Override
  public List<Student> getAllStudents() throws StorageException {
    List<Student> students;
    Connection con = null;
    PreparedStatement stmt = null;
    ResultSet resultSet = null;
    try {
       con = conPool.getConnection();
      String sql = Settings.SQL_FETCH_ALL;
      stmt = con.prepareStatement(sql);
      resultSet = stmt.executeQuery();

      students = new ArrayList<>();

      while (resultSet.next()) {
        students.add(new Student(
            resultSet.getInt(Settings.DB_FLD_STUDENTS_ID),
            resultSet.getString(Settings.DB_FLD_STUDENTS_NAME)
            ));
      }
      return Collections.unmodifiableList(students);
    } catch (SQLException e) {
      // uses the SQL-statecode as message
      throw new StorageException("SQL State code: " + e.getSQLState(),
          ErrorType.DATABASE_ERROR);
    } catch (Exception e){
      throw new StorageException(e.getMessage() , ErrorType.UNSPECIFIED);
    }finally {
     close(resultSet);
     close(stmt);
     close(con);
    }
  }

  @Override
  public StudentsRegistrations getAStudentsRegistrations(int id)
      throws StorageException {

    StudentsRegistrations registrations;
    Connection con = null;
    PreparedStatement stmt = null;
    ResultSet resultSet = null;
    
    try {
      String sql = Settings.SQL_FETCH_ONE;
      con = conPool.getConnection();
      stmt = con.prepareStatement(sql);
      stmt.setInt(1, id);
      resultSet = stmt.executeQuery();
      Student student = null;
      List<Course> courses;
      courses = new ArrayList<>();

      while (resultSet.next()) {
        // to avoid hopping around with the resultSets pointer!
        if (student == null) {
          student = new Student(
              resultSet.getInt(Settings.DB_FLD_STUDENTS_ID),
              resultSet.getString(Settings.DB_FLD_STUDENTS_NAME));
        }
        courses.add(new Course(
            resultSet.getInt(Settings.DB_FLD_COURSES_ID),
            resultSet.getString(Settings.DB_FLD_COURSES_CODE)));
      }
      // empty ResultSet
      if (student == null){
        throw new StorageException(Settings.ERROR_NO_DATA_ID_MESSAGE, ErrorType.NO_SUCH_ID);
      }
      return new StudentsRegistrations(student, courses);
    } catch (StorageException e) {
      throw e;
    } catch (SQLException e){
      throw new StorageException("SQL State code: " + e.getSQLState(), ErrorType.DATABASE_ERROR);
    } catch (Exception e) {
      throw new StorageException(e.getMessage(), ErrorType.UNSPECIFIED);
    }finally {
      close(resultSet);
      close(stmt);
      close(con);
    }
  }
  
  
  // help function for closing java.sql.Statement, ResultSet, Connection, etc.
  private  void close(AutoCloseable closable){
    try{
      if (closable != null) closable.close();
    } catch (Exception e){
      MainLogger.getLogger().log(Level.SEVERE ,LOG_TAG +  " error on close()" , e);      
    }
  }
}
