package jsoft.library;

/** class xác định các phương thức làm việc với văn bản 
 * 
 * @author pc
 *
 */
public class Utilities_text {
	public static boolean checkValidPass(String pass1, String pass2) {
		if(pass1 != null && pass2 != null) {
			if(!pass1.equalsIgnoreCase("") && !pass2.equalsIgnoreCase("")) {
				if(pass1.length() >= 6 && pass1.equalsIgnoreCase(pass2)) {
					return true;
				}
			}
		}
		
		return false;
	}
}	