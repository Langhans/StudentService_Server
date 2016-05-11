package application;


public class Settings {

	// LOGGER
	public static final String LOG_NAME="MainLog";
	public static final String LOG_TAG="SERVER_LOG: ";
	
	// DB SETTINGS
	public static final String DB_DRIVERCLASS = "com.mysql.jdbc.Driver";
	public static final String DB_URL = "jdbc:mysql://localhost:3306/dbStudents";
	public static final String DB_USER = "userStudents";
	public static final String DB_PASS = "1234";
	
	// DB TAGS
	public static final String DB_TABLE_STUDENTS="students";
	public static final String DB_TABLE_COURSES="courses";
	public static final String DB_TABLE_REGISTRATIONS="registrations";
	public static final String DB_FLD_STUDENTS_ID = "students.id";
	public static final String DB_FLD_STUDENTS_NAME = "name"; 
	public static final String DB_FLD_COURSES_ID = "courses.id";
	public static final String DB_FLD_COURSES_CODE = "course_code";
	public static final String DB_FLD_REGISTRATIONS_COURSEID="course_id";
	public static final String DB_FLD_REGISTRATIONS_STUDENTID="student_id";
	
	// SQL STATEMENTS
	public static final String SQL_FETCH_ALL = 
			"SELECT students.id , students.name FROM students "
			+ "ORDER BY students.id ASC";
	
	public static final String SQL_FETCH_ONE = 
			"SELECT students.name, students.id, courses.course_code, courses.id FROM students"
			+ " INNER JOIN registrations ON students.id=registrations.student_id"
			+ " INNER JOIN courses ON courses.id=registrations.course_id"
			+ " WHERE registrations.student_id=?";
	
	// XML TAGS
	public static final String XML_TAG_STUDENTS = "STUDENTS";
	public static final String XML_TAG_STUDENT = "STUDENT";
	public static final String XML_TAG_COURSES = "COURSES";
	 public static final String XML_TAG_ATTR_COURSEID = "courseID";
	public static final String XML_TAG_COURSE = "COURSE";
	public static final String XML_TAG_ATTR_COURSECODE = "courseCode";
	public static final String XML_TAG_ATTR_ID = "studentID";
	public static final String XML_TAG_ID = "id";
	public static final String XML_TAG_ATTR_NAME = "studentName";
	public static final String ERROR_XML_ERRORCONTENT_STUB =
	    "<ERROR name = \"%s\"> %s </ERROR>";

	// JSON TAGS
	public static final String JSON_TAG_STUDENTS= "students";
	public static final String JSON_TAG_STUDENT="studentName";
	public static final String JSON_TAG_COURSES="courses";
	public static final String JSON_TAG_ID = "studentID";
	public static final String JSON_TAG_NAME = "studentName";
	public static final String JSON_TAG_STUDENTSREGISTRATIONS = "registrations";
	public static final String JSON_TAG_COURSECODE = "courseCode";
	public static final String JSON_TAG_COURSEID = "courseID";
	public static final String ERROR_JSON_ERRORCONTENT_STUB =
	    "{\"name\" : \"%s\" , \"message\" : \"%s\"}";

	// HTTP PARAMETER
	public static final String PARAM_ID_ALL = "all";
	public static final String PARAM_FORMAT = "format";
  public static final String PARAM_ID = "id";

	// ERROR MESSAGES
	public static final String ERROR_TYPE_REQUEST_ERROR="Request-Error";
	public static final String ERROR_TYPE_EMPTY_DATASET="Data-Error";
	public static final String ERROR_EMPTY_DATASET_MESSAGE ="No data found";
	public static final String ERROR_NO_SUCH_STUDENT_ID_STUB = "No registration-data for ID: %d";
	public static final String ERROR_IDMISSING_MESS = "missing id parameter";
	public static final String ERROR_NO_DATA_ID_MESSAGE = "No data for this ID";

  public static final String ERROR_WRITER_NOT_FOUND = "Selected Writer not found";
	
}
