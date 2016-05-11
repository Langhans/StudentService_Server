package outputWriter;

import java.util.List;

import commons.Student;
import commons.StudentsRegistrations;

public interface IContentWriter {

  public String writeAllStudents(List<Student> students) throws WriterException;
  
  public String writeAStudentsRegistrations(StudentsRegistrations registration) throws WriterException;
  
  public String writeErrorContent(String errorName, String errorMessage);
  
}
