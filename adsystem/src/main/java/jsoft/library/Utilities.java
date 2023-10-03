package jsoft.library;

import net.htmlparser.jericho.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.*;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import jsoft.object.UserObject;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Utilities {

	// 3 phương thức dưới dùng để chuyển chuỗi số sang kiểu tương ứng
	public static byte getByteParam(ServletRequest request, String name) {
		// giá trị -1 biểu thị không tồn tại giá trị tham số
		byte value = -1;

		String str_value = request.getParameter(name);
		if (str_value != null && !str_value.equalsIgnoreCase("")) {
			value = Byte.parseByte(str_value);
		}

		return value;
	}

	public static short getShortParam(ServletRequest request, String name) {
		// giá trị -1 biểu thị không tồn tại giá trị tham số
		short value = -1;

		String str_value = request.getParameter(name);
		if (str_value != null && !str_value.equalsIgnoreCase("")) {
			value = Short.parseShort(str_value);
		}

		return value;
	}

	public static int getIntParam(ServletRequest request, String name) {
		// giá trị -1 biểu thị không tồn tại giá trị tham số
		int value = -1;

		String str_value = request.getParameter(name);
		if (str_value != null && !str_value.equalsIgnoreCase("")) {
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
//	public static void runSendMail(String to, String subject, String body) throws IOException {
//		ExecutorService emailExecutor = Executors.newSingleThreadExecutor();
//        emailExecutor.execute(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                   Utilities.sendMail(to, subject, body);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        emailExecutor.shutdown(); // it is very important to shutdown your non-singleton ExecutorService.
//	}

	public static boolean sendMail(String to, String subject, String body) throws IOException {
		
			boolean sendSucc = false;
			String username = jsoft.library.Utilities_const.USERMAIL_NAME.label;
			String userpass = jsoft.library.Utilities_const.USERMAIL_PASS.label;
			String host = jsoft.library.Utilities_const.MAIL_HOST.label;
			String port = jsoft.library.Utilities_const.MAIL_PORT.label;
			System.out.println(username + ":" + userpass);
			Properties props = new Properties();
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.port", port);
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.starttls.required", "true");
			props.put("mail.smtp.ssl.protocols", "TLSv1.2");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			Session session = Session.getInstance(props, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, userpass);
				}
			});
			try {
				Message message = new MimeMessage(session);
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
				message.setSubject(subject);
				message.setText(body);
				// send message
				Transport.send(message);
//				System.out.println("message sent successfully");

				sendSucc = true;
			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
			
			return sendSucc;
	}
	
	public static boolean sendOTPEmail(UserObject user, String otp) {
		String to = user.getUser_email();
		String subject = "Here's your One Time Password (OTP) - Expire in 5 minutes!";
	     
	    String content = "<p>Hello " + user.getUser_name() + "</p>"
	            + "<p>For security reason, you're required to use the following "
	            + "One Time Password to login:</p>"
	            + "<p><b>" + otp + "</b></p>"
	            + "<br>"
	            + "<p>Note: this OTP is set to expire in 5 minutes.</p>";
	    try {
			return sendMail(to, subject, content);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return false;
	}
	
//	public void clearOTP(UserObject user) {
//	    user.setOneTimePassword(null);
//	    user.setOtpRequestedTime(null);
//	    customerRepo.save(customer);   
//	}
	public static boolean sendRandomPassToEmail(UserObject user, String nPass) {
		System.out.println("euser_email:" + user.getUser_email());
		String to = user.getUser_email();
		String subject = "Here's your One Time Password (OTP) - Expire in 5 minutes!";
	     
	    String content = "<p>Hello " + user.getUser_name() + "</p>"
	            + "<p>For security reason, you're required to use the following "
	            + "One Time Password to login:</p>"
	            + "<p><b>" + nPass + "</b></p>"
	            + "<br>"
	            + "<p>Note: this OTP is set to expire in 5 minutes.</p>";
	    try {
			return sendMail(to, subject, content);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return false;
	}
	
	public static void main(String[] args) {
	  
	        // Blank workbook
	        XSSFWorkbook workbook = new XSSFWorkbook();
	  
	        // Creating a blank Excel sheet
	        XSSFSheet sheet
	            = workbook.createSheet("student Details");
	  
	        // Creating an empty TreeMap of string and Object][]
	        // type
	        Map<String, Object[]> data
	            = new TreeMap<String, Object[]>();
	  
	        // Writing data to Object[]
	        // using put() method
	        data.put("1",
	                 new Object[] { "ID", "NAME", "LASTNAME" });
	        data.put("2",
	                 new Object[] { 1, "Pankaj", "Kumar" });
	        data.put("3",
	                 new Object[] { 2, "Prakashni", "Yadav" });
	        data.put("4", new Object[] { 3, "Ayan", "Mondal" });
	        data.put("5", new Object[] { 4, "Virat", "kohli" });
	  
	        // Iterating over data and writing it to sheet
	        Set<String> keyset = data.keySet();
	  
	        int rownum = 0;
	  
	        for (String key : keyset) {
	  
	            // Creating a new row in the sheet
	            Row row = sheet.createRow(rownum++);
	  
	            Object[] objArr = data.get(key);
	  
	            int cellnum = 0;
	  
	            for (Object obj : objArr) {
	  
	                // This line creates a cell in the next
	                //  column of that row
	                Cell cell = row.createCell(cellnum++);
	  
	                if (obj instanceof String)
	                    cell.setCellValue((String)obj);
	  
	                else if (obj instanceof Integer)
	                    cell.setCellValue((Integer)obj);
	            }
	        }
	  
	        // Try block to check for exceptions
	        try {
	  
	            // Writing the workbook
	            FileOutputStream out = new FileOutputStream(
	                new File("D:/example.xlsx"));
	            workbook.write(out);
	  
	            // Closing file output connections
	            out.close();
	  
	            // Console message for successful execution of
	            // program
	            System.out.println(
	                "example.xlsx written successfully on disk.");
	        }
	  
	        // Catch block to handle exceptions
	        catch (Exception e) {
	  
	            // Display exceptions along with line number
	            // using printStackTrace() method
	            e.printStackTrace();
	        }
	        
	     // Try block to check for exceptions
//	        try {
//	  
//	            // Reading file from local directory
//	            FileInputStream file = new FileInputStream(
//	                new File("gfgcontribute.xlsx"));
//	  
//	            // Create Workbook instance holding reference to
//	            // .xlsx file
//	            XSSFWorkbook workbook = new XSSFWorkbook(file);
//	  
//	            // Get first/desired sheet from the workbook
//	            XSSFSheet sheet = workbook.getSheetAt(0);
//	  
//	            // Iterate through each rows one by one
//	            Iterator<Row> rowIterator = sheet.iterator();
//	  
//	            // Till there is an element condition holds true
//	            while (rowIterator.hasNext()) {
//	  
//	                Row row = rowIterator.next();
//	  
//	                // For each row, iterate through all the
//	                // columns
//	                Iterator<Cell> cellIterator
//	                    = row.cellIterator();
//	  
//	                while (cellIterator.hasNext()) {
//	  
//	                    Cell cell = cellIterator.next();
//	  
//	                    // Checking the cell type and format
//	                    // accordingly
//	                    switch (cell.getCellType()) {
//	  
//	                    // Case 1
//	                    case NUMERIC:
//	                        System.out.print(
//	                            cell.getNumericCellValue()
//	                            + "t");
//	                        break;
//	  
//	                    // Case 2
//	                    case STRING:
//	                        System.out.print(
//	                            cell.getStringCellValue()
//	                            + "t");
//	                        break;
//	                    }
//	                }
//	  
//	                System.out.println("");
//	            }
//	  
//	            // Closing file output streams
//	            file.close();
//	        }
//	  
//	        // Catch block to handle exceptions
//	        catch (Exception e) {
//	  
//	            // Display the exception along with line number
//	            // using printStackTrace() method
//	            e.printStackTrace();
//	        }
	}
}
