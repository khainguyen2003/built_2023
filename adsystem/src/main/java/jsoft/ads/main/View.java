package jsoft.ads.main;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import jsoft.object.UserObject;

/**
 * Servlet implementation class View
 */
@WebServlet("/view")
public class View extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Định nghĩa kiểu nội dung xuất về trình khách
	private static final String CONTENT_TYPE = "text/html; charset=utf-8";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public View() {
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

		RequestDispatcher header = request.getRequestDispatcher("/header");
		if(header != null) {
			header.include(request, response);
		}

		out.append("<main id=\"main\" class=\"main\">");

		out.append("<div class=\"pagetitle d-flex\">");
		out.append("<h1>Blank Page</h1>");
		out.append("<nav class=\"ms-auto\">");
		out.append("<ol class=\"breadcrumb\">");
		out.append("<li class=\"breadcrumb-item d-flex\"><a href=\"/adv/view\"><i class=\"bi bi-house\"></i></a></li>");
		out.append("<li class=\"breadcrumb-item\">Pages</li>");
		out.append("<li class=\"breadcrumb-item active\">Blank</li>");
		out.append("</ol>");
		out.append("</nav>");
		out.append("</div><!-- End Page Title -->");

		out.append("<section class=\"section\">");
		out.append("<div class=\"row\">");
		out.append("<div class=\"col-lg-12\">");
		out.append("<div class=\"card\">");
		out.append("<div class=\"card-body\">");
		out.append("<h5 class=\"card-title\">Example Card</h5>");
		out.append("<p>This is an examle page with no contrnt. You can use it as a starter for your custom pages.</p>");
		out.append("</div>"); // card-body
		out.append("</div>"); // card
		out.append("</div>"); // col-lg-12

		out.append("</div>"); // row
		out.append("</section>");

		out.append("</main><!-- End #main -->");

		RequestDispatcher footer = request.getRequestDispatcher("/footer");
		if(footer != null) {
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
