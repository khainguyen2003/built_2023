package jsoft.library;

import net.htmlparser.jericho.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Properties;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;

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
	
	public static String getCurrenPar(HttpServletRequest request) {
		String currentPar = request.getRequestURI().toString();
        String queryString = request.getQueryString();
        if(queryString != null) {
        	currentPar += "?" + queryString;
        }
        return currentPar;
	}
	
	public static String addParameter(HttpServletRequest request, String paramName, String paramValue) {
		String currentURI = request.getRequestURI().toString();
        String queryString = request.getQueryString();

        // Nếu đường dẫn URL hiện tại đã chứa các tham số, thì thêm tham số mới vào sau dấu "&"
        if (queryString == null) {
            queryString = paramName + "=" + paramValue;
        } else {
            queryString += "&" + paramName + "=" + paramValue;
        }

        try {
            // Encode lại queryString để đảm bảo nó chứa các ký tự hợp lệ trong URL
            queryString = URLEncoder.encode(queryString, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            // Xử lý lỗi encoding ở đây nếu cần
        }

        // Tạo đường dẫn URL mới bằng cách kết hợp đường dẫn hiện tại và queryString
        String newURL = currentURI + "?" + queryString;

        return newURL;
    }
}
