package servlets;

import java.util.ArrayList;
import java.util.List;

public class Verificator {

  private static String ENTRY_SEPEPATOR = ", \n";
  private List<String> errors;
  
  private String id;
  private String format;
  
  
  public Verificator(String id , String format){
    this.id = id;
    this.format = format;
    errors = new ArrayList<>();
    startValidation();
  }
  
  private void startValidation(){
    if (!VerificationTools.isCorrectIdParameter(id)){
      errors.add("ID PARAMETER ERROR");
    }
    if (!VerificationTools.isCorrectFormatParameter(format)){
      errors.add("FORMAT PARAMETER ERROR");
    }
  }
  
  
  public boolean isValid(){
    return errors.isEmpty();
  }
  
  public List<String> getErrorMessages(){
    return errors;
  }
  
  public String getSingleErrorString(){
    StringBuffer buffer = new StringBuffer();
    
    for (String s: errors){
      buffer.append(s).append(ENTRY_SEPEPATOR);
    }
    int length = buffer.length();
    buffer.delete(length - ENTRY_SEPEPATOR.length() , length);
    return buffer.toString();
  }
  
  
  
  
  
}
