package storageHandler;
import commons.Format;

public class StorageManagerFactory {

	/**
	 * Creates a StorageManager for each HTTPRequest. 
	 * 
	 */
  
  private static final String LOG_TAG = StorageManagerFactory.class.getName(); 
  
  
	public static IStorageManager getStorageManager() {
		return new StorageManager();
	}

}
