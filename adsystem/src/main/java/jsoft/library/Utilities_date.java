package jsoft.library;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

// class định nghĩa các phương thức làm việc với date
public class Utilities_date {
	public static String getDate(String format) {
		DateFormat dateFormat = new SimpleDateFormat();
		Date date = new Date();
		
		return dateFormat.format(date);
	}
	
	public static String getDate() {
		return Utilities_date.getDate("dd/MM/yyyy");
	}
	public static String getDateProfile() {
		return Utilities_date.getDate("ddMMyyHHmmss");
	}
	public static String formatDate(String date, String format) {
		DateFormat dateFormat = new SimpleDateFormat();
		return dateFormat.format(date);
	}
}
