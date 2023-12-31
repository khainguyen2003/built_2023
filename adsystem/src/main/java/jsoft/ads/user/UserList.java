package jsoft.ads.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.javatuples.Quartet;

import jsoft.object.UserObject;

import jsoft.*;
import jsoft.library.Utilities;

/**
 * Servlet implementation class View
 */
@WebServlet("/user/list")
public class UserList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Định nghĩa kiểu nội dung xuất về trình khách
	private static final String CONTENT_TYPE = "text/html; charset=utf-8";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserList() {
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

	protected void view(HttpServletRequest request, HttpServletResponse response, UserObject user)
			throws ServletException, IOException {
		// Xác định kiểu nội dung xuất về trình khách
		response.setContentType(CONTENT_TYPE);

		// Tạo đối tượng thực hiện xuất nội dung
		PrintWriter out = response.getWriter();
		
		// Thiết lập tập ký tự cần lấy. Việc thiết lập này cần xác định từ đầu
		request.setCharacterEncoding("utf-8");

		// Tìm bộ quản lý kết nối
		ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("CPool");
		// Tạo đối tượng thực thi chức năng
		UserControl uc = new UserControl(cp);
		if (cp == null) {
			getServletContext().setAttribute("CPool", uc.getCP());
		}
		
		// Lấy từ khóa tìm kiếm
		String key = request.getParameter("key");
		String saveKey = (key != null && !key.equalsIgnoreCase("")) ? key.trim() : "";
		
		// Lấy câu trúc
		UserObject similar = new UserObject();
		similar.setUser_id(user.getUser_id());
		similar.setUser_permission(user.getUser_permission());
		similar.setUser_name(saveKey);
		
		// Tìm tham số xác định loại danh sách
		String trash = request.getParameter("trash");
		String title, pos;
		if(trash == null) {
			similar.setUser_deleted(false);
			pos = "urlist";
			title = "Danh sách người sử dụng";
		} else {
			similar.setUser_deleted(true);
			pos = "urtrash";
			title = "Danh sách người bị xóa";
		}

		short page = Utilities.getShortParam(request, "page");
		if(page < 1) {
			page = 1;
		}
		// Lấy cấu trúc
		Quartet<UserObject, Short, Byte, USER_SORT_TYPE> infors = new Quartet<>(similar, page, (byte) 30,
				USER_SORT_TYPE.NAME);

		ArrayList<String> viewList = uc.viewUsers(infors);

		// Trả về kết nối
		uc.releaseConnection();
		// request.getRequestDispatcher đươc gọi là các luồng rẽ nhánh
		RequestDispatcher header = request.getRequestDispatcher("/header?pos="+pos);
		
		if (header != null) {
			header.include(request, response);
		}

		out.append("<main id=\"main\" class=\"main\">");
		
		RequestDispatcher err = request.getRequestDispatcher("/error");
		if(err != null) {
			err.include(request, response);
		}

		out.append("<div class=\"pagetitle d-flex\">");
		out.append("<h1>"+title+"</h1>");
		out.append("<nav class=\"ms-auto\">");
		out.append("<ol class=\"breadcrumb\">");
		out.append("<li class=\"breadcrumb-item d-flex\"><a href=\"/adv/view\"><i class=\"bi bi-house\"></i></a></li>");
		out.append("<li class=\"breadcrumb-item\">Người sử dụng</li>");
		out.append("<li class=\"breadcrumb-item active\">Danh sách</li>");
		out.append("</ol>");
		out.append("</nav>");
		out.append("</div><!-- End Page Title -->");

		out.append("<section class=\"section\">");
		out.append("<div class=\"row\">");
		out.append("<div class=\"col-lg-12\">");
		out.append("<div class=\"card\">");
		out.append("<div class=\"card-body\">");
		if(trash==null) {
			out.append(
					"<button type=\"button\" class=\"btn btn-primary btn-sm my-2\" data-bs-toggle=\"modal\" data-bs-target=\"#addUser\">");
			out.append("<i class=\"bi bi-person-plus\"></i> Thêm mới");
			out.append("</button>");
	
			out.append(
					"<div class=\"modal fade\" id=\"addUser\" data-bs-backdrop=\"static\" data-bs-keyboard=\"false\" tabindex=\"-1\" aria-labelledby=\"staticBackdropLabel\" aria-hidden=\"true\">");
			out.append("<div class=\"modal-dialog modal-lg\">");
			out.append("<form method=\"post\" action=\"/adv/user/list\" class=\"needs-validation\" novalidate>");
			out.append("<div class=\"modal-content\">");
			out.append("<div class=\"modal-header\">");
			out.append(
					"<h1 class=\"modal-title fs-5\" id=\"addUserLabel\"><i class=\"bi bi-person-plus\"></i> Thêm mới Người sử dụng</h1>");
			out.append(
					"<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>");
			out.append("</div>");
			out.append("<div class=\"modal-body\">");
	
			out.append("<div class=\"row mb-3\">");
			out.append("<div class=\"col-lg-6\">");
			out.append("<label for=\"username\" class=\"form-label\">Tên tài khoản</label>");
			out.append("<input type=\"text\" class=\"form-control\" id=\"username\" name=\"txtUsername\" required>");
			out.append("<div class=\"invalid-feedback\">Hãy nhập vào tên của tài khoản</div>");
			out.append("</div>");
	
			out.append("<div class=\"col-lg-6\">");
			out.append("<label for=\"useralias\" class=\"form-label\">Họ và tên</label>");
			out.append("<input type=\"text\" class=\"form-control\" id=\"useralias\" name=\"txtUserfullname\" required>");
			out.append("</div>");
			out.append("</div>");
	
			out.append("<div class=\"row mb-3\">");
			out.append("<div class=\"col-lg-6\">");
			out.append("<label for=\"userpass\" class=\"form-label\">Mật khẩu</label>");
			out.append("<input type=\"password\" class=\"form-control\" id=\"userpass\" name=\"txtUserpass\" required>");
			out.append("<div class=\"invalid-feedback\">Hãy nhập vào mật khẩu cho tài khoản</div>");
			out.append("</div>");
	
			out.append("<div class=\"col-lg-6\">");
			out.append("<label for=\"userpass2\" class=\"form-label\">Mật khẩu</label>");
			out.append("<input type=\"password\" class=\"form-control\" id=\"userpass2\" name=\"txtUserpass2\" required>");
			out.append("<div class=\"invalid-feedback\">Hãy nhập mật khẩu xác nhận cho tài khoản</div>");
			out.append("</div>");
			out.append("</div>");
	
			out.append("<div class=\"row mb-3\">");
			out.append("<div class=\"col-lg-6\">");
			out.append("<label for=\"useremail\" class=\"form-label\">Hộp thư</label>");
			out.append("<input type=\"text\" class=\"form-control\" id=\"useremail\" name=\"txtUseremail\" required>");
			out.append("<div class=\"invalid-feedback\">Hãy nhập hộp thư cho tài khoản</div>");
			out.append("</div>");
	
			out.append("<div class=\"col-lg-6\">");
			out.append("<label for=\"userphone\" class=\"form-label\">Điện thoại</label>");
			out.append("<input type=\"text\" class=\"form-control\" id=\"userphone\" name=\"txtUserphone\" required>");
			out.append("<div class=\"invalid-feedback\">Hãy nhập mật khẩu xác nhận cho tài khoản</div>");
			out.append("</div>");
			out.append("</div>");
	
			out.append("<div class=\"row mb-3\">");
			out.append("<div class=\"col-lg-6\">");
			out.append("<label for=\"userpermis\" class=\"form-label\">Quyền thực thi</label>");
			out.append("<select class=\"form-select bg-white\" id=\"userpermis\" name=\"slcPermis\" required>");
			out.append("<option class=\"bg-white\" value=\"\">-------chọn-------</option>");
			out.append("<option class=\"bg-white\" value=\"1\">Thành viên</option>");
			out.append("<option class=\"bg-white\" value=\"2\">Tác giả</option>");
			out.append("<option class=\"bg-white\" value=\"3\">Quản lý</option>");
			out.append("<option class=\"bg-white\" value=\"4\">Quản trị</option>");
			out.append("<option class=\"bg-white\" value=\"5\">Quản trị cấp cao</option>");
			out.append("</select>");
			out.append("<div class=\"invalid-feedback\">Hãy nhập mật khẩu xác nhận cho tài khoản</div>");
			out.append("</div>");
			out.append("</div>");
	
			out.append("</div>");
			out.append("<div class=\"modal-footer\">");
			out.append(
					"<button type=\"submit\" class=\"btn btn-primary\"><i class=\"bi bi-person-add\"></i> Thêm mới</button>");
			out.append(
					"<button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\"><i class=\"bi bi-x-lg\"></i> Thoát</button>");
			out.append("</div>");// modal-footer
			out.append("</div>");// modal-content
			
			out.append("<input type=\"hidden\" name=\"act\" value=\"addUser\" />");
			out.append("</form>");
			out.append("</div>");// modal-dialog
			out.append("</div>");// modal
		}

		out.append("<a href=\"/adv/export/excel?pos="+pos+"\" class=\"btn btn-primary\">Xuất file</a>");

		out.append(viewList.get(0)); // Phần trình bày user list
		out.append(viewList.get(2)); // Phần phân trang
		out.append("</div>"); // card-body
		out.append("</div>"); // card
		out.append("</div>"); // col-lg-12
		
		out.append("<div class=\"row\">");
		out.append("<div class=\"col-lg-12\">");
		out.append(viewList.get(1)); // Phần trình bày biểu đồ
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
		UserObject user = (UserObject)request.getSession().getAttribute("userLogined");
		
		// Thiết lập tập ký tự cần lấy. Việc thiết lập này cần xác định từ đầu
		request.setCharacterEncoding("utf-8");
		String act = request.getParameter("act");
		if(act != null) {
			if(act.equalsIgnoreCase("addUser")) {
				// Lấy các thông tin bắt buộc
				String name = request.getParameter("txtUsername");
				String pass1 = request.getParameter("txtUserpass");
				String pass2 = request.getParameter("txtUserpass2");
				String email = request.getParameter("txtUseremail");
				String phone = request.getParameter("txtUserphone");

				byte permis = jsoft.library.Utilities.getByteParam(request, "slcPermis");

				if (name != null && !name.equalsIgnoreCase("") && jsoft.library.Utilities_text.checkValidPass(pass1, pass2) && email != null
						&& !email.equalsIgnoreCase("") && phone != null && !phone.equalsIgnoreCase("") && permis > 0) {
					// Lấy tiếp thông tin không bắt buộc
					String fullname = request.getParameter("txtUserfullname");
					// Tạo đối tương lưu trữ thông tin
					UserObject nuser = new UserObject();
					nuser.setUser_name(name);
					nuser.setUser_fullname(jsoft.library.Utilities.encode(fullname));
					nuser.setUser_pass(pass1);
					nuser.setUser_parent_id(user.getUser_id());
					nuser.setUser_email(email);
					nuser.setUser_homephone(phone);
					nuser.setUser_created_date(jsoft.library.Utilities_date.getDate());
					nuser.setUser_permission(permis);
					
					ConnectionPool cp = (ConnectionPool)getServletContext().getAttribute("CPool");
					UserControl uc = new UserControl(cp);
					if(cp == null) {
						getServletContext().setAttribute("CPool", uc.getCP());
					}
					
					// Thực hiện thêm mới
					boolean result = uc.addUser(nuser);
					
					// Trả về kết nối
					uc.releaseConnection();
					
					if(result) {
						response.sendRedirect("/adv/user/list");
					} else {
						response.sendRedirect("/adv/user/list?err=add");
					}
				} else { 
					response.sendRedirect("/adv/user/list?err=valueadd");
				}
			}else if(act.equalsIgnoreCase("search")) {
				String key= request.getParameter("keyword");
				if(key != null && !key.equalsIgnoreCase("")) {
					response.sendRedirect("?key="+key);				
				} else {
					response.sendRedirect("/adv/user/list");
				}
			}
		}
		
	}
}
