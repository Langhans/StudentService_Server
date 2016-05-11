package outputWriter;

import static application.Settings.ERROR_JSON_ERRORCONTENT_STUB;
import static application.Settings.JSON_TAG_COURSECODE;
import static application.Settings.JSON_TAG_COURSES;
import static application.Settings.JSON_TAG_ID;
import static application.Settings.JSON_TAG_NAME;
import static application.Settings.JSON_TAG_STUDENT;
import static application.Settings.JSON_TAG_STUDENTS;

import java.sql.SQLException;
import java.util.List;

import application.Settings;
import commons.Course;
import commons.Student;
import commons.StudentsRegistrations;

public class JsonWriter implements IContentWriter {

  @Override
  public String writeAllStudents(List<Student> students) throws WriterException{
    /*
     * e.g.: { "students":[ { "studentName":"Anders Andersson", "studentID":1 },
     * { "studentName":"Beata Bengtsson", "studentID":2 }, { "studentName":
     * "Charlie Christensen", "studentID":3 }, { "studentName":"Dick Dale",
     * "studentID":4 }, { "studentName":"Edward Eriksson", "studentID":5 } ] }
     * 
     */
    if (students == null ){
      throw new WriterException("null as argument");
    }

    StringBuilder builder = new StringBuilder();
    builder.append("{\"" + JSON_TAG_STUDENTS + "\" : [ ");

    for (Student st : students) {
      builder.append("{\"" + JSON_TAG_NAME + "\" : \"").append(st.getName())
          .append("\", \"" + JSON_TAG_ID + "\" : ").append(st.getId())
          .append("},");
    }
    builder.deleteCharAt(builder.length() - 1);
    builder.append("]}");
    return builder.toString();
  }

  /**
   * 
   * @param regs
   * @return
   */
  @Override
  public String writeAStudentsRegistrations(StudentsRegistrations reg) throws WriterException{
    /*
     * 
     * e.g.: { "studentName":"Beata Bengtsson", "studentID":"2", "courses":[ {
     * "courseCode":"TIG058" }, { "courseCode":"TIG059" } ] }
     * 
     */
    
    if (reg == null ){
      throw new WriterException("null as argument");
    }
    
    StringBuilder builder = new StringBuilder();
    builder.append("{\"" + JSON_TAG_STUDENT + "\" : \"")
        .append(reg.getStudent().getName()).append("\",")
        .append("\"" + JSON_TAG_ID + "\" : ").append(reg.getStudent().getId())
        .append(", ").append("\"" + JSON_TAG_COURSES + "\" : [");
    
    List<Course> courses = reg.getCourses();
    
    for (Course course : courses) {
      builder.append("{\"" + JSON_TAG_COURSECODE + "\" : \"")
          .append(course.getCourse_code()).append("\" , \"")
          .append(Settings.JSON_TAG_COURSEID + "\" : ")
          .append(course.getId())
          .append(" },");
    }
    builder.deleteCharAt(builder.length() - 1);
    builder.append(" ] }");
    return builder.toString();
  }

  @Override
  public String writeErrorContent(String errorName, String errorMessage) {
    return String.format(ERROR_JSON_ERRORCONTENT_STUB, errorName, errorMessage);
  }

}
