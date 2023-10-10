package jsoft.ads.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.javatuples.Pair;
import org.javatuples.Quartet;

import jsoft.ConnectionPool;
import jsoft.ads.user.USER_SORT_TYPE;
import jsoft.ads.user.UserControl;
import jsoft.object.UserObject;

/**
 * Servlet implementation class ExportExcel
 */
@WebServlet("/export/excel")
public class ExportExcel extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExportExcel() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserObject user = (UserObject) request.getSession().getAttribute("userLogined");
		if (user != null) {
//			String prevUri = (String)request.getAttribute("currentUri");
			String pos = request.getParameter("pos");
//			String saveKey = request.getParameter("key");
//			System.out.println(prevUri);
			UserObject similar = new UserObject();
			similar.setUser_id(user.getUser_id());
			similar.setUser_permission(user.getUser_permission());
//			similar.setUser_name(saveKey);

			/*
			 * // Blank workbook XSSFWorkbook workbook = new XSSFWorkbook();
			 * 
			 * // Creating an empty TreeMap of string and Object][] // type Map<String,
			 * Object[]> data = new TreeMap<String, Object[]>();
			 */

			if (pos.equalsIgnoreCase("urlist")) {
				// Creating a blank Excel sheet
//				XSSFSheet sheet = workbook.createSheet("user Details");
				
				// Tìm bộ quản lý kết nối
				ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("CPool");
				// Tạo đối tượng thực thi chức năng
				UserControl uc = new UserControl(cp);
				if (cp == null) {
					getServletContext().setAttribute("CPool", uc.getCP());
				}
				// Lấy cấu trúc
				Quartet<UserObject, Short, Byte, USER_SORT_TYPE> infors = new Quartet<>(similar, (short)0, (byte) -1,
						USER_SORT_TYPE.NAME);
				// Lấy dữ liệu
				Pair<ArrayList<UserObject>, Integer> datas = uc.getUserObjects(infors);
				uc.releaseConnection();
				ArrayList<UserObject> userList = datas.getValue0();
				response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//				String currentDateTime = jsoft.library.Utilities_date.getDateProfile();

				String headerKey = "Content-Disposition";
				String headerValue = "attachment; filename=records.xlsx";
				response.setHeader(headerKey, headerValue);
				
				jsoft.library.Utilities_file.generate(response, userList);
			}
		} else {
			response.sendRedirect("/adv/user/login");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
