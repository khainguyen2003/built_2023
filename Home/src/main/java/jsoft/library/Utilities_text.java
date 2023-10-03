package jsoft.library;

import java.util.ArrayList;

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
	
	/**
     * Hàm kiểm tra mật khẩu có hợp lệ không
     * @param pass mật khẩu cần kiểm tra
     * @return false nếu không hợp lệ</br> true nếu hợp lệ
     */
    public static boolean isValidPass(String pass) {
    	if(pass == null || pass.equalsIgnoreCase("")) {
    		return false;
    	}
    	// Kiểm tra mật khẩu phải có ít nhất 8 ký tự
        if (pass.length() < 8) {
            return false;
        }

        // Kiểm tra mật khẩu phải chứa ít nhất một chữ cái viết hoa
        if (!pass.matches(".*[A-Z].*")) {
            return false;
        }

        // Kiểm tra mật khẩu phải chứa ít nhất một chữ cái viết thường
        if (!pass.matches(".*[a-z].*")) {
            return false;
        }

        // Kiểm tra mật khẩu phải chứa ít nhất một chữ số
        if (!pass.matches(".*\\d.*")) {
            return false;
        }

        // Kiểm tra mật khẩu phải chứa ít nhất một ký tự đặc biệt (!@#$%^&*()-_=+[]{}|;:'\",.<>?)
        if (!pass.matches(".*[!@#$%^&*()-_=+\\[\\]{}|;:'\",.<>?].*")) {
            return false;
        }

        // Kiểm tra mật khẩu không được chứa các ký tự trắng (space, tab, newline, etc.)
        if (pass.matches(".*\\s.*")) {
            return false;
        }
        
        // Kiểm tra mật khẩu không được chứa đường dẫn (ví dụ: http://, www.)
        if (pass.contains("http://") || pass.contains("www.")) {
            return false;
        }
        if(isCommonPassword(pass)) {
        	return false;
        }

    	return true;
    }
    
    // Hàm kiểm tra mật khẩu có nằm trong danh sách mật khẩu phổ biến không
    public static boolean isCommonPassword(String password) {
        // Danh sách các mật khẩu phổ biến (có thể mở rộng)
        String[] commonPasswords = {"123456", "password", "admin", "qwerty", "letmein"};

        for (String commonPassword : commonPasswords) {
            if (password.equalsIgnoreCase(commonPassword)) {
                return true;
            }
        }

        return false;
    }
    
    /**
     * Phương thức lấy image path từ chuỗi base64 trong database
     * @param encode dạng tên file;base64String
     * @return
     */
    public static ArrayList<String> getImgPath(String encode) {
    	ArrayList<String> img = new ArrayList<>();
    	int semPos = encode.indexOf(";");
    	img.add(encode.substring(0, semPos));
    	img.add(encode.substring(semPos + 1));
    	return img;
    }
}	
