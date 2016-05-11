package storageHandler;

import java.util.List;

import application.StorageException;
import commons.Student;
import commons.StudentsRegistrations;

public interface IStorageManager {
/*
	public String getAllStudents() throws StorageException;
	
	public String getRegistrationsForAStudent(int id) throws StorageException;
	*/
	
	  
	public List<Student> getAllStudents() throws StorageException;
  
  public StudentsRegistrations getRegistrationsForAStudent(int id) throws StorageException;
	
	
	
	
}
