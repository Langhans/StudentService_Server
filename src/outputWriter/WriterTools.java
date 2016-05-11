package outputWriter;

import application.Settings;

public class WriterTools {

  
  public static String getNoSuchStudentIdMessage(int id) {
    return String.format(Settings.ERROR_NO_SUCH_STUDENT_ID_STUB, id);
  }
}
