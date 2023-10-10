package jsoft.library;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.poi.common.usermodel.fonts.FontCharset;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import jsoft.object.UserObject;

public class Utilities_file {
	/**
	 * HÃ m encode file
	 * @param path Ä‘Æ°á»�ng dáº«n cá»§a tá»‡p cáº§n encode
	 * @return má»™t chuá»—i encode file dáº¡ng base64
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
	 * HÃ m kiá»ƒm tra Ä‘á»‹nh dáº¡ng hÃ¬nh áº£nh
	 * @param imageData file image cáº§n kiá»ƒm tra Ä‘á»‹nh dáº¡ng
	 * @return Ä‘á»‹nh dáº¡ng cá»§a file
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
            return "KhÃ´ng xÃ¡c Ä‘á»‹nh";
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
    
    /* ================= export excel function start =================*/
	/* Phương thức exportExcel v1
	 * public static XSSFWorkbook exportExcel(Map<String, Object[]> data) { // Blank
	 * workbook XSSFWorkbook workbook = new XSSFWorkbook();
	 * 
	 * // Creating a blank Excel sheet XSSFSheet sheet =
	 * workbook.createSheet("student Details");
	 * 
	 * Set<String> keyset = data.keySet(); int rownum = 0;
	 * 
	 * for (String key : keyset) {
	 * 
	 * // Creating a new row in the sheet Row row = sheet.createRow(rownum++);
	 * 
	 * Object[] objArr = data.get(key);
	 * 
	 * int cellnum = 0;
	 * 
	 * for (Object obj : objArr) {
	 * 
	 * // This line creates a cell in the next // column of that row Cell cell =
	 * row.createCell(cellnum++);
	 * 
	 * if (obj instanceof String) cell.setCellValue((String)obj);
	 * 
	 * else if (obj instanceof Integer) cell.setCellValue((Integer)obj); } }
	 * 
	 * return workbook; }
	 */

    /** Phương thức export excel v2
     * 
     * @param row
     * @param columnCount
     * @param value
     * @param style
     */
    public static void createCell(XSSFSheet sheet, Row row, int columnCount, Object value, CellStyle style) {
    	sheet.autoSizeColumn(columnCount);
		Cell cell = row.createCell(columnCount);
		if (value instanceof Integer) {
			cell.setCellValue((Integer) value);
		} 
        else if (value instanceof Long) {
			cell.setCellValue((Long) value);
		} else if (value instanceof Boolean) {
			cell.setCellValue((Boolean) value);
		} else if(value instanceof Short) {
			cell.setCellValue((short) value);
		} else {
			cell.setCellValue((String) value);
		}
		cell.setCellStyle(style);
	}

	public static XSSFWorkbook write(ArrayList<UserObject> data) {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Exam Records");
		// Tao tiêu đề
		Row row = sheet.createRow(0);

		// set style cho tiêu đề
		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(16);
		style.setFont(font);
//		style.setFillBackgroundColor("#FFE4D6");
		// Thêm giá trị vào các cột tiêu đề
		createCell(sheet, row, 0, "STT", style);
		createCell(sheet, row, 1, "Tên", style);
		createCell(sheet, row, 2, "Họ và tên", style);
		createCell(sheet, row, 3, "Score", style);
		int rowCount = 1;
		
		font.setFontHeight(14);
		style.setFont(font);

		for (UserObject record : data) {
			row = sheet.createRow(rowCount++);
			int columnCount = 0;

			createCell(sheet,row, columnCount++, record.getUser_id(), style);
			createCell(sheet,row, columnCount++, jsoft.library.Utilities.decode(record.getUser_fullname()), style);
			createCell(sheet,row, columnCount++, record.getUser_last_logined(), style);
			createCell(sheet,row, columnCount++, record.getUser_logined(), style);

		}
		
		return workbook;
	}

	public static void generate(HttpServletResponse response, ArrayList<UserObject> datas) throws IOException {
		
		XSSFWorkbook workbook = write(datas);
		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();

		outputStream.close();

	}
	
	/* ================= export excel function end =================*/
}
