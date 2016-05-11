package storageHandler;

import java.util.List;

import application.StorageException;
import commons.Student;
import commons.StudentsRegistrations;

public interface IDbHandler {

  public List<Student> getAllStudents() throws StorageException;
  
  public StudentsRegistrations getAStudentsRegistrations(int id) throws StorageException;
  
  
  
  /*
   * TODO easier DB interface? => problem: more Connections needed!
   * public List<Course> getCoursesForStudentById(int id) throws StorageException;
   * 
   * public Student getOneStudentById(int  id) throws StorageException;
   * 
   * 
   */
}
