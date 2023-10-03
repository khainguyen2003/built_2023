package jsoft.ads.section;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.javatuples.Quartet;

import jsoft.*;
import jsoft.object.*;

/**
 * Servlet implementation class SectionList
 */
@WebServlet("/section/list")
public class SectionList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Định nghĩa kiểu nội dung xuất về trình khách
	private static final String CONTENT_TYPE = "text/html; charset=utf-8";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SectionList() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Tìm thông tin đăng nhập
		UserObject user = (UserObject) request.getSession().getAttribute("userLogined");
		if (user != null) {
			view(request, response, user);
		} else {
			response.sendRedirect("/adv/user/login");
		}
	}

	protected void view(HttpServletRequest request, HttpServletResponse response, UserObject user) throws ServletException, IOException {
		// Xác định kiểu nội dung xuất về trình khách
		response.setContentType(CONTENT_TYPE);

		// Tạo đối tượng thực hiện xuất nội dung
		PrintWriter out = response.getWriter();

		// Thiết lập tập ký tự cần lấy. Việc thiết lập này cần xác định từ đầu
		request.setCharacterEncoding("utf-8");

		// Tìm bộ quản lý kết nối
		ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("CPool");
		// Tạo đối tượng thực thi chức năng
		SectionControl sc = new SectionControl(cp);
		if (cp == null) {
			getServletContext().setAttribute("CPool", sc.getCP());
		}

		// Lấy từ khóa tìm kiếm
		String key = request.getParameter("key");
		String saveKey = (key != null && !key.equalsIgnoreCase("")) ? key.trim() : "";

		// Trả về kết nối
		sc.releaseConnection();
		// request.getRequestDispatcher đươc gọi là các luồng rẽ nhánh
		RequestDispatcher header = request.getRequestDispatcher("/header?pos=selist");
		
		SectionObject similar = new SectionObject();
		Quartet<SectionObject, Short, Byte, UserObject> infors = new Quartet<>(similar, (short)1, (byte)10, user);
		ArrayList<String> view = sc.viewSection(infors);
		if (header != null) {
			header.include(request, response);
		}

		out.append("<main id=\"main\" class=\"main\">");

		RequestDispatcher err = request.getRequestDispatcher("/error");
		if (err != null) {
			err.include(request, response);
		}

		out.append("<div class=\"pagetitle d-flex\">");
		out.append("<h1>Danh sách chuyên mục</h1>");
		out.append("<nav class=\"ms-auto\">");
		out.append("<ol class=\"breadcrumb\">");
		out.append("<li class=\"breadcrumb-item d-flex\"><a href=\"/adv/view\"><i class=\"bi bi-house\"></i></a></li>");
		out.append("<li class=\"breadcrumb-item\">Chuyên mục</li>");
		out.append("<li class=\"breadcrumb-item active\">Danh sách</li>");
		out.append("</ol>");
		out.append("</nav>");
		out.append("</div><!-- End Page Title -->");

		out.append("<section class=\"section\">");
		out.append("<div class=\"row\">");
		out.append("<div class=\"col-lg-12\">");
		out.append("<div class=\"card\">");
		out.append("<div class=\"card-body\">");
		// Thêm setion
		out.append(
				"<button type=\"button\" class=\"btn btn-primary btn-sm my-2\" data-bs-toggle=\"modal\" data-bs-target=\"#addSection\">");
		out.append("<i class=\"bi bi-person-plus\"></i> Thêm mới chuyên mục");
		out.append("</button>");

		out.append(
				"<div class=\"modal fade\" id=\"addSection\" data-bs-backdrop=\"static\" data-bs-keyboard=\"false\" tabindex=\"-1\" aria-labelledby=\"staticBackdropLabel\" aria-hidden=\"true\">");
		out.append("<div class=\"modal-dialog modal-lg\">");
		out.append("<form method=\"post\" action=\"/adv/user/list?act=addUser\" class=\"needs-validation\" novalidate>");
		out.append("<div class=\"modal-content\">");
		out.append("<div class=\"modal-header\">");
		out.append(
				"<h1 class=\"modal-title fs-5\" id=\"addUserLabel\"><i class=\"bi bi-person-plus\"></i> Thêm mới Chuyên mục</h1>");
		out.append(
				"<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>");
		out.append("</div>");
		out.append("<div class=\"modal-body\">");

		out.append("<div class=\"row mb-3\">");
		out.append("<div class=\"col-lg-6\">");
		out.append("<label for=\"username\" class=\"form-label\">Tên chuyên mục</label>");
		out.append("<input type=\"text\" class=\"form-control\" id=\"username\" name=\"txtUsername\" required>");
		out.append("<div class=\"invalid-feedback\">Hãy nhập vào tên của tài khoản</div>");
		out.append("</div>");

		out.append("<div class=\"col-lg-6\">");
		out.append("<label for=\"manager\" class=\"form-label\">Quản lý</label>");
		out.append("<select class=\"form-select bg-white\" id=\"manager\" name=\"slcManager\" required>");
//		out.append("<option class=\"bg-white\" value=\"\">-------chọn-------</option>");
		out.append(view.get(1));
		out.append("</select>");
		
		out.append("<div class=\"invalid-feedback\">Hãy chọn người quản lý danh mục</div>");
		out.append("</div>");
		out.append("</div>");// row
		
		out.append("<div class=\"row mb-3\">");
		out.append("<div class=\"col-lg-12\">");
		out.append("<label for=\"sectionnotes\" class=\"form-label\">Ghi chú</label>");
		out.append("<textarea rows=\"8\" class=\"form-control\" id=\"sectionnotes\" name=\"txtSectionnotes\"></textarea>");
		out.append("</div>"); // col-lg-12
		out.append("</div>"); //row

		out.append("</div>");
		out.append("<div class=\"modal-footer\">");
		out.append(
				"<button type=\"submit\" class=\"btn btn-primary\"><i class=\"bi bi-person-add\"></i> Thêm mới</button>");
		out.append(
				"<button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\"><i class=\"bi bi-x-lg\"></i> Thoát</button>");
		out.append("</div>");// modal-footer
		out.append("</div>");// modal-content
		out.append("</form>");
		out.append("</div>");// modal-dialog
		out.append("</div>");// modal
		
		out.append(view.get(0));
		out.append("</div>"); // card-body
		out.append("</div>"); // card
		out.append("</div>"); // col-lg-12
		out.append("</div>"); // row
		out.append("</section>");

		out.append("</main><!-- End #main -->");

		// request.getRequestDispatcher đươc gọi là các luồng rẽ nhánh
		RequestDispatcher footer = request.getRequestDispatcher("/footer");
		if (footer != null) {
			footer.include(request, response);
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
