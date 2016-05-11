package storageHandler;

import java.util.ArrayList;
import java.util.List;

import application.ErrorType;
import application.Settings;
import application.StorageException;
import commons.Format;
import commons.Service;
import commons.Student;
import commons.StudentsRegistrations;
import outputWriter.WriterSelector;

public class StorageManager implements IStorageManager {

  private static final String LOG_TAG = StorageManager.class.getName();

  /**
   * Manages the process of fetching the requested data from the storage and
   * translating it to the requested format. This class guarantees to return a
   * response-content as a java.lang.String.
   * 
   * @param format
   *          - requested format type String
   */

  public StorageManager() {
  }

  @Override
  public List<Student> getAllStudents() throws StorageException {
    try {
      DbHandler db = DbHandler.getInstance();
      List<Student> students = db.getAllStudents();
      
      if(students == null){
        throw new StorageException(
            Settings.ERROR_EMPTY_DATASET_MESSAGE, ErrorType.DATABASE_ERROR);
      } 
      return students;
    } catch (StorageException e) {
      throw e;
    } catch (Exception e) {
      throw new StorageException(e.getMessage(), ErrorType.UNSPECIFIED);
    }
  }

  @Override
  public StudentsRegistrations getRegistrationsForAStudent(int id) throws StorageException {
    try {
      DbHandler db = DbHandler.getInstance();
      // throws a StorageException on db-layer if id does not exist!
      StudentsRegistrations stReg = db.getAStudentsRegistrations(id);
      return stReg;
    } catch (StorageException e) {
      throw e;
    } catch (Exception e) {
      throw new StorageException(e.getMessage(), ErrorType.UNSPECIFIED);
    }
  }
}
