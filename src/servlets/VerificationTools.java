package servlets;

import application.Settings;
import commons.Format;

public class VerificationTools {

	/**
	 * parses the Format parameter 
	 * @param param
	 * @return parsed Format or Format.NOT_SUPPORTED if input not parsable
	 */
	public static Format parseFormatParameter(String param){
		Format format;
		
		try{
			format = Format.valueOf(param.toUpperCase());
			return format;
		} catch (IllegalArgumentException e){
			return Format.NOT_SUPPORTED;
		}
	}
	
	/**
	 * Parses int from String
	 * @param param
	 * @return -99 if input not parsable, parsed int if successful 
	 */
	public static int parseIdParameter(String param){
		try{
			int id = Integer.parseInt(param);
			return id;
		} catch (NumberFormatException e){
			return -99;
		}
	}
	
	// is dependent on parseFormatParameter - method!
	public static boolean isCorrectFormatParameter(String format){
	  if (format == null) return false;
	  
	  return (parseFormatParameter(format) != Format.NOT_SUPPORTED);
	}
	
	
	public static boolean isCorrectIdParameter(String id_param){
		if (id_param == null) return false;
		
		return id_param.equalsIgnoreCase("all")
				|| parseIdParameter(id_param) > 0;
		} 
		
}
