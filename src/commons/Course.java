package commons;

public class Course {
  
    private int id;
    
    private String course_code;
    
    public Course(int id , String course_code){
      this.id=id;
      this.course_code=course_code;
    }
    
    public int getId() {
      return id;
    }


    public String getCourse_code() {
      return course_code;
    }
}
