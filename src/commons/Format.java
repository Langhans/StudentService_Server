package commons;

public enum Format {

  /**
   * Data transport formats which are supported by the server. Even containing 
   * default MIME-types as java.lang.String constants  
   */
  
	JSON("application/json"),
	
	XML("application/xml"),
	
	NOT_SUPPORTED("text/html");
	
  
	private String contentType;
	
	
	private Format (String contentType){
		this.contentType=contentType;
	}
	
	public String getContentType(){
		return contentType;
	}
	
	
}
