package application;

public class StorageException extends Exception {
  
  private ErrorType errorType = ErrorType.UNSPECIFIED;
  
	public StorageException(String message , ErrorType errorType) {
		super(message);
		this.errorType=errorType;
	}
	
	public StorageException(Throwable cause , ErrorType errorType ){
		super(cause);
		this.errorType=errorType;
	}

	public StorageException(String message , Throwable cause , ErrorType errorType){
		super(message, cause);
		this.errorType=errorType;
	}
	
	
	public ErrorType getErrorType(){
	  return errorType;
	}
	
	
}
