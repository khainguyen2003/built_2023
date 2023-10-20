package jsoft.library;

import net.htmlparser.jericho.*;
import javax.servlet.*; 

public class Utilities {
	
	// 3 phÆ°Æ¡ng thá»©c dÆ°á»›i dÃ¹ng Ä‘á»ƒ chuyá»ƒn chuá»—i sá»‘ sang kiá»ƒu tÆ°Æ¡ng á»©ng
	public static byte getByteParam(ServletRequest request, String name) {
		// giÃ¡ trá»‹ -1 biá»ƒu thá»‹ khÃ´ng tá»“n táº¡i giÃ¡ trá»‹ tham sá»‘
		byte value = -1;
		
		String str_value = request.getParameter(name);
		if(str_value != null && !str_value.equalsIgnoreCase("")) {
			value = Byte.parseByte(str_value);
		}
		
		return value;
	}
	
	public static short getShortParam(ServletRequest request, String name) {
		// giÃ¡ trá»‹ -1 biá»ƒu thá»‹ khÃ´ng tá»“n táº¡i giÃ¡ trá»‹ tham sá»‘
		short value = 0;
		
		String str_value = request.getParameter(name);
		if(str_value != null && !str_value.equalsIgnoreCase("")) {
			value = Short.parseShort(str_value);
		}
		
		return value;
	}
	
	/** Phương thức lấy giá trị trang hiện tại từ request
	 * 
	 * @param request
	 * @param name 
	 * @return
	 */
	public static short getShortPage(ServletRequest request, String name) {
		// giÃ¡ trá»‹ -1 biá»ƒu thá»‹ khÃ´ng tá»“n táº¡i giÃ¡ trá»‹ tham sá»‘
		short value = 1;
		
		String str_value = request.getParameter(name);
		if(str_value != null && !str_value.equalsIgnoreCase("")) {
			value = Short.parseShort(str_value);
		}
		
		return value;
	}
	
	public static int getIntParam(ServletRequest request, String name) {
		// giÃ¡ trá»‹ -1 biá»ƒu thá»‹ khÃ´ng tá»“n táº¡i giÃ¡ trá»‹ tham sá»‘
		int value = 0;
		
		String str_value = request.getParameter(name);
		if(str_value != null && !str_value.equalsIgnoreCase("")) {
			value = Integer.parseInt(str_value);
		}
		
		return value;
	}
	
	// PhÆ°Æ¡ng thá»©c encode má»™t chuá»—i Ä‘Æ°á»£c Ä‘Æ°a vÃ o dá»±a vÃ o thÆ° viá»‡n encode cá»§a jericho
	public static String encode(String str_unicode) {
		return CharacterReference.encode(str_unicode);
	}
	
	public static String decode(String str_unicode) {
		return CharacterReference.decode(str_unicode);
	}
}
