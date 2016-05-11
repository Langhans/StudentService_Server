package commons;

import java.util.List;

public class StudentsRegistrations {

  private Student student;
  
  private List<Course> courses;
  
  
  public StudentsRegistrations(Student student , List<Course> courses){
    this.student = student;
    this.courses = courses;
  }


  public Student getStudent() {
    return student;
  }


  public List<Course> getCourses() {
    return courses;
  }
  
  
}
