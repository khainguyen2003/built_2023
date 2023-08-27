package util;

import java.util.*;

public class MyString {
	
	/** Đếm số lần xh của 1 ký tự trong chuỗi
	 * 
	 * @param str
	 * @param ch
	 * @return
	 */
	public static int countChar(String str, char ch, boolean isIgnoreCase) {
		int count = 0;
		
		if(isIgnoreCase) {
			str = str.toLowerCase();
			ch = Character.toLowerCase(ch);
		}
		
		for(int i = 0; i < str.length(); i++) {
			if(str.charAt(i) == ch) {
				count++;
			}
		}
		return count;
	}
	
	/** chuẩn hóa 1 chuỗi ký tự
	 * 
	 * @param str
	 * @return chuỗi đã chuẩn hóa
	 */
	public static String formatString(String str, formatAllow fa) {
		// Danh sách các kí tự đặc biệt
		char[] chs = {'`', '~', '!', '@', '#', '$', '%', '^', '&', '*', '(',
					')', '-', '_', '+', '=', '{', '[', ']', '}', ':', ';',
					'<', '>', '?', '/', '|', '\\', '\t' };
		
		// Loại bỏ ký tự đặc biệt trong chuỗi
		for(char ch : chs) {
			str = str.replace(ch, ' ');
		}
		
		// Loại bỏ dấu cách thừa
		str = str.trim();
		while(str.indexOf("  ") != -1) {
			str = str.replace("  ", " ");
		}
		str.replace(" . ", ". ");
		str.replace(" , ", ", ");
		
		// Xử lý theo tên riêng / câu văn bản / ...
		switch(fa) {
			case NAME:
			case FULLNAME:
				String[] words = str.split(" ");
				// chuỗi chuyển trung gian
				String tmp = "";
				for(String word : words) {
					word = word.toLowerCase();
					tmp += Character.toUpperCase(word.charAt(0)) + word.substring(1) + " ";
				}
				str = tmp.trim();
				break;
			case SENTENCE:
				str = Character.toUpperCase(str.charAt(0)) + str.substring(1);
				break;
			default:
		}
		
		return str;
	}
	
	/** Đếm tần suất xuất hiện của các từ trong câu
	 * 
	 * @param str
	 * @param ch_split
	 * @return
	 */
	public static HashMap<String, Integer> statisticWords(String str, String ch_split) {
		HashMap<String, Integer> tmp = new HashMap<String, Integer>();
		
		String[] tmp_words = str.split(ch_split);
		for(String word : tmp_words) {
			if(!word.equalsIgnoreCase("")) {
				word = word.trim();
				if(tmp.containsKey(word)) {
					tmp.replace(word, tmp.get(word) + 1);
				}else {
					tmp.put(word, 1);
				}
			}
		}
		
		return tmp;
	}
	
	public static void printStatistic(HashMap<String, Integer> sta) {
		// danh sách map có sx
		TreeMap<String, Integer> tmp = new TreeMap<String, Integer>(sta);
		for(Map.Entry<String, Integer> e: tmp.entrySet()) {
			System.out.println(e.getKey() + " - " + e.getValue());
		}
	}
	
	public static int countWords(String str) {
		// Chuẩn hóa
		str = MyString.formatString(str, formatAllow.DEFAULT);
		
		return MyString.countChar(str, ' ', false) + 1;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "@@abc@mnbd@abc@@oie@@htm@@@";
		
		MyString.printStatistic(MyString.statisticWords(str, "@"));
		
	}

}

enum formatAllow {
	NAME,
	FULLNAME,
	SENTENCE,
	DEFAULT
}
