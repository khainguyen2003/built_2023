package jsoft.library;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

import javax.servlet.http.Part;

public class Utilities_file {
	/**
	 * Hàm encode file
	 * @param path đường dẫn của tệp cần encode
	 * @return một chuỗi encode file dạng base64
	 */
	public static String encodeFile(String path) {
		byte[] bytes = new byte[0];
        try {
            bytes = Files.readAllBytes(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace(); 
        }
        // encode to base 64
        String base64String = Base64.getEncoder().encodeToString(bytes);
		return base64String;
	}
	public static String encodeFile(byte[] file) {
		 String base64String = Base64.getEncoder().encodeToString(file);
			return base64String;
	}
	public static String encodeFile(Part file) {
		if(file != null) {
			byte[] fileData = new byte[0];
			try {
				fileData = file.getInputStream().readAllBytes();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String base64 = Base64.getEncoder().encodeToString(fileData);
			
			return base64;
		}
		
		return null;
	}
	public static byte[] decodeFile(String base64) throws UnsupportedEncodingException{
		byte[] fileDecode = Base64.getDecoder().decode(base64);
			
		return fileDecode;
	}
	
	/**
	 * Hàm kiểm tra định dạng hình ảnh
	 * @param imageData file image cần kiểm tra định dạng
	 * @return định dạng của file
	 */
    public static String getImageFormat(byte[] imageData) {
        if (imageData.length >= 2 && imageData[0] == (byte) 0xFF && imageData[1] == (byte) 0xD8) {
            return "jpeg";
        } else if (imageData.length >= 8 &&
                   imageData[0] == (byte) 0x89 && imageData[1] == (byte) 0x50 &&
                   imageData[2] == (byte) 0x4E && imageData[3] == (byte) 0x47 &&
                   imageData[4] == (byte) 0x0D && imageData[5] == (byte) 0x0A &&
                   imageData[6] == (byte) 0x1A && imageData[7] == (byte) 0x0A) {
            return "png";
        } else if (imageData.length >= 6 &&
                   imageData[0] == (byte) 0x47 && imageData[1] == (byte) 0x49 &&
                   imageData[2] == (byte) 0x46 && imageData[3] == (byte) 0x38 &&
                   (imageData[4] == (byte) 0x37 || imageData[4] == (byte) 0x39) &&
                   imageData[5] == (byte) 0x61) {
            return "gif";
        } else if (imageData.length >= 4 &&
                   imageData[0] == (byte) 0x3C && imageData[1] == (byte) 0x3F &&
                   imageData[2] == (byte) 0x78 && (imageData[3] == (byte) 0x6D || imageData[3] == (byte) 0x6C)) {
            return "svg";
        } else {
            return "Không xác định";
        }
    }
    public static String getImageFormat(String base64Encode) {
    	byte[] imageData;
    	String imageType = "err format";
		try {
			imageData = decodeFile(base64Encode);
			imageType = getImageFormat(imageData);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return imageType;
    }

	public static void main(String[] args) {
		String base64ImgEncode = encodeFile("C:\\Users\\pc\\Dropbox\\PC\\Pictures\\anh-doraemon-hai-huoc_033145846.png");
			String fileFormat = getImageFormat(base64ImgEncode);
			System.out.println(fileFormat);
//		System.out.println(base64ImgEncode);
	}
}
