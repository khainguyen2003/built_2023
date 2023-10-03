package jsoft.library;

import net.htmlparser.jericho.*;
import javax.servlet.*; 

public class Utilities {
	
	// 3 phương thức dưới dùng để chuyển chuỗi số sang kiểu tương ứng
	public static byte getByteParam(ServletRequest request, String name) {
		// giá trị -1 biểu thị không tồn tại giá trị tham số
		byte value = -1;
		
		String str_value = request.getParameter(name);
		if(str_value != null && !str_value.equalsIgnoreCase("")) {
			value = Byte.parseByte(str_value);
		}
		
		return value;
	}
	
	public static short getShortParam(ServletRequest request, String name) {
		// giá trị -1 biểu thị không tồn tại giá trị tham số
		short value = -1;
		
		String str_value = request.getParameter(name);
		if(str_value != null && !str_value.equalsIgnoreCase("")) {
			value = Short.parseShort(str_value);
		}
		
		return value;
	}
	
	public static int getIntParam(ServletRequest request, String name) {
		// giá trị -1 biểu thị không tồn tại giá trị tham số
		int value = -1;
		
		String str_value = request.getParameter(name);
		if(str_value != null && !str_value.equalsIgnoreCase("")) {
			value = Integer.parseInt(str_value);
		}
		
		return value;
	}
	
	// Phương thức encode một chuỗi được đưa vào dựa vào thư viện encode của jericho
	public static String encode(String str_unicode) {
		return CharacterReference.encode(str_unicode);
	}
	
	public static String decode(String str_unicode) {
		return CharacterReference.decode(str_unicode);
	}
}