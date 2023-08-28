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

/**
 * Servlet implementation class View
 */
@WebServlet("/user/profile")
public class UserProfiles extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Định nghĩa kiểu nội dung xuất về trình khách
	private static final String CONTENT_TYPE = "text/html; charset=utf-8";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserProfiles() {
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

		// Tìm id của người sử dụng để cập nhập
		int id = jsoft.library.Utilities.getIntParam(request, "id");

		// biến xét trường hợp user có tồn tại không
		boolean isEdit = false;
		// khai báo đối tượng được chỉnh sửa (edituser)
		UserObject euser = null;
		if (id > 0) {
			// Tìm bộ quản lý kết nối
			ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("CPool");
			// Tạo đối tượng thực thi chức năng
			UserControl uc = new UserControl(cp);
			if (cp == null) {
				getServletContext().setAttribute("CPool", uc.getCP());
			}

			// Lấy câu trúc
			UserObject similar = new UserObject();
			similar.setUser_id(user.getUser_id());
			similar.setUser_permission(user.getUser_permission());

			// Lấy cấu trúc
			Quartet<UserObject, Short, Byte, USER_SORT_TYPE> infors = new Quartet<>(similar, (short) 1, (byte) 10,
					USER_SORT_TYPE.NAME);

			ArrayList<String> viewList = uc.viewUsers(infors);
			euser = uc.getUserObject(id);

			// Trả về kết nối
			uc.releaseConnection();
		}
		// request.getRequestDispatcher đươc gọi là các luồng rẽ nhánh
		RequestDispatcher header = request.getRequestDispatcher("/header?pos=urlist");
		if (header != null) {
			header.include(request, response);
		}

		out.append("<main id=\"main\" class=\"main\">");

		RequestDispatcher err = request.getRequestDispatcher("/error");
		if (err != null) {
			err.include(request, response);
		}

		out.append("<div class=\"pagetitle d-flex\">");
		out.append("<h1>Danh sách người sử dụng</h1>");
		out.append("<nav class=\"ms-auto\">");
		out.append("<ol class=\"breadcrumb\">");
		out.append("<li class=\"breadcrumb-item d-flex\"><a href=\"/adv/view\"><i class=\"bi bi-house\"></i></a></li>");
		out.append("<li class=\"breadcrumb-item\">Người sử dụng</li>");
		out.append("<li class=\"breadcrumb-item active\">Cập nhật chi tiêt</li>");
		out.append("</ol>");
		out.append("</nav>");
		out.append("</div><!-- End Page Title -->");

		out.append("<section class=\"section profile\">");
		out.append("<div class=\"row\">");
		out.append("<div class=\"col-xl-4\">");

		out.append("<div class=\"card\">");
		out.append("<div class=\"card-body profile-card pt-4 d-flex flex-column align-items-center\">");

		if (euser != null) {
			out.append("<img src=\"assets/img/profile-img.jpg\" alt=\"Profile\" class=\"rounded-circle\">");
			out.append("<h2>" + euser.getUser_fullname() + "</h2>");
			out.append("<h3>" + euser.getUser_name() + "</h3>");
			out.append("<div class=\"social-links mt-2\">");
			out.append("<a href=\"#\" class=\"twitter\"><i class=\"bi bi-twitter\"></i></a>");
			out.append("<a href=\"#\" class=\"facebook\"><i class=\"bi bi-facebook\"></i></a>");
			out.append("<a href=\"#\" class=\"instagram\"><i class=\"bi bi-instagram\"></i></a>");
			out.append("<a href=\"#\" class=\"linkedin\"><i class=\"bi bi-linkedin\"></i></a>");
			out.append("</div>"); // social-links
		}
		out.append("</div>"); // card-body
		out.append("</div>"); // card

		out.append("</div>"); // col-xl-4

		out.append("<div class=\"col-xl-8\">");

		out.append("<div class=\"card\">");
		out.append("<div class=\"card-body pt-3\">");
		out.append("<!-- Bordered Tabs -->");
		out.append("<ul class=\"nav nav-tabs nav-tabs-bordered\">");

		out.append("<li class=\"nav-item\">");
		out.append(
				"<button class=\"nav-link active\" data-bs-toggle=\"tab\" data-bs-target=\"#overview\"><i class=\"bi bi-info-square\"></i> Tổng quát</button>");
		out.append("</li>");

		out.append("<li class=\"nav-item\">");
		out.append(
				"<button class=\"nav-link\" data-bs-toggle=\"tab\" data-bs-target=\"#edit\"><i class=\"bi bi-pencil-square\"></i> Chỉnh sửa</button>");
		out.append("</li>");

		out.append("<li class=\"nav-item\">");
		out.append(
				"<button class=\"nav-link\" data-bs-toggle=\"tab\" data-bs-target=\"#settings\"><i class=\"bi bi-gear\"></i> Cài đặt</button>");
		out.append("</li>");

		out.append("<li class=\"nav-item\">");
		out.append(
				"<button class=\"nav-link\" data-bs-toggle=\"tab\" data-bs-target=\"#change-password\"><i class=\"bi bi-pass\"></i> Đổi mật khẩu</button>");
		out.append("</li>");

		out.append("</ul>");
		out.append("<div class=\"tab-content pt-2\">");

		String summary = "Không có thông tin", name = "Không có thông tin", fullname = "Không có thông tin",
				address = "", email = "", hphone = "", job = "", jobarea = "", ophone = "", mphone="";
		String birthday = "";
		short logined = euser.getUser_logined();
		if (euser != null) {
			summary = euser.getUser_notes();
			name = euser.getUser_name();
			fullname = euser.getUser_fullname();
			address = euser.getUser_address();
			email = euser.getUser_email();
			hphone = euser.getUser_homephone();
			ophone = euser.getUser_officephone();
			mphone = euser.getUser_mobilephone();
			job = euser.getUser_job();
			jobarea = euser.getUser_jobarea();
			birthday = euser.getUser_birthday();
			isEdit = true;
		}
		out.append("<div class=\"tab-pane fade show active profile-overview\" id=\"overview\">");
		out.append("<h5 class=\"card-title\">Tóm tắt</h5>");
		out.append("<p class=\"small fst-italic\">" + summary + "</p>");

		out.append("<h5 class=\"card-title\">Profile Details</h5>");

		out.append("<div class=\"row\">");
		out.append("<div class=\"col-lg-3 col-md-4 label \">Họ và tên</div>");
		out.append("<div class=\"col-lg-6 col-md-5\">" + fullname + "</div>");
		out.append("<div class=\"col-lg-3 col-md-3\">(" + name + ")</div>");
		out.append("</div>");

		out.append("<div class=\"row\">");
		out.append("<div class=\"col-lg-3 col-md-4 label\">Birthday</div>");
		out.append("<div class=\"col-lg-9 col-md-8\">" + birthday + "</div>");
		out.append("</div>");

		out.append("<div class=\"row\">");
		out.append("<div class=\"col-lg-3 col-md-4 label\">Nghề nghiệp</div>");
		out.append("<div class=\"col-lg-9 col-md-8\">" + job + "</div>");
		out.append("</div>");

		out.append("<div class=\"row\">");
		out.append("<div class=\"col-lg-3 col-md-4 label\">Lĩnh vực</div>");
		out.append("<div class=\"col-lg-9 col-md-8\">" + jobarea + "</div>");
		out.append("</div>");

		out.append("<div class=\"row\">");
		out.append("<div class=\"col-lg-3 col-md-4 label\">Địa chỉ</div>");
		out.append("<div class=\"col-lg-9 col-md-8\">" + address + "</div>");
		out.append("</div>");

		out.append("<div class=\"row\">");
		out.append("<div class=\"col-lg-3 col-md-4 label\">Điện thoại</div>");
		out.append("<div class=\"col-lg-9 col-md-8\">" + hphone + "</div>");
		out.append("</div>");

		out.append("<div class=\"row\">");
		out.append("<div class=\"col-lg-3 col-md-4 label\">Email</div>");
		out.append("<div class=\"col-lg-9 col-md-8\">" + email + "</div>");
		out.append("</div>");

		out.append("<div class=\"row\">");
		out.append("<div class=\"col-lg-3 col-md-4 label\">Lần đăng nhập</div>");
		out.append("<div class=\"col-lg-9 col-md-8\">" + logined + "</div>");
		out.append("</div>");

		out.append("</div>");

		out.append("<div class=\"tab-pane fade edit pt-3\" id=\"edit\">");

		out.append("<!-- Profile Edit Form -->");
		out.append("<form method=\"post\" action=\"/adv/user/profile\">");
		out.append("<div class=\"row mb-3\">");
		out.append("<label for=\"profileImage\" class=\"col-md-3 col-lg-2 col-form-label\">Profile Image</label>");
		out.append("<div class=\"col-md-9 col-lg-10\">");
		out.append("<img src=\"assets/img/profile-img.jpg\" alt=\"Profile\">");
		out.append("<div class=\"pt-2\">");
		out.append(
				"<a href=\"#\" class=\"btn btn-primary btn-sm\" title=\"Upload new profile image\"><i class=\"bi bi-upload\"></i></a>");
		out.append(
				"<a href=\"#\" class=\"btn btn-danger btn-sm\" title=\"Remove my profile image\"><i class=\"bi bi-trash\"></i></a>");
		out.append("</div>");
		out.append("</div>");
		out.append("</div>");

		out.append("<div class=\"row mb-3 align-items-center\">");
		out.append("<label for=\"fullName\" class=\"col-md-3 col-lg-2 text-end\">Họ và tên</label>");
		out.append("<div class=\"col-md-6 col-lg-7\">");
		out.append("<div class=\"input-group\">");
		out.append("<input name=\"txtFullName\" type=\"text\" class=\"form-control\" id=\"fullName\" value=\""
				+ fullname + "\">");
		out.append("<input name=\"txtAlias\" type=\"text\" class=\"form-control\" id=\"alias\" readonly value=\"\">");
		out.append("</div>");
		out.append("</div>");
		out.append("<div class=\"col-md-3 col-lg-3\">");
		out.append("<input name=\"txtName\" type=\"text\" class=\"form-control\" id=\"name\" disabled value=\"" + name
				+ "\">");
		out.append("</div>");
		out.append("</div>");

		out.append("<div class=\"row mb-3\">");
		out.append("<label for=\"notes\" class=\"col-md-3 col-lg-2 col-form-label text-end\">Tóm tắt</label>");
		out.append("<div class=\"col-md-9 col-lg-10\">");
		out.append("<textarea name=\"txtNotes\" class=\"form-control\" id=\"notes\" style=\"height: 100px\">" + summary
				+ "</textarea>");
		out.append("</div>");
		out.append("</div>");

		out.append("<div class=\"row mb-3 align-items-center\">");
		out.append("<label for=\"birthday\" class=\"col-md-3 col-lg-2 text-end\">Ngày sinh</label>");
		out.append("<div class=\"col-md-3 col-lg-4\">");
		out.append("<input name=\"txtBirthday\" type=\"date\" class=\"form-control\" id=\"birthday\" value=\""+birthday+"\">");
		out.append("</div>");
		
		out.append("<label for=\"slcGender\" class=\"col-md-3 col-lg-2 text-end\">Giới tính</label>");
		out.append("<div class=\"col-md-3 col-lg-4\">");
		out.append("<select class=\"form-control\" id=\"slcGender\" name=\"slcGender\">");
		out.append("<option value=\"\">---</option>");
		out.append("<option value=\"0\">Nam</option>");
		out.append("<option value=\"1\">Nữ</option>");
		out.append("</select>");
		out.append("</div>");
		out.append("</div>");

		out.append("<div class=\"row mb-3 align-items-center\">");
		out.append("<label for=\"Job\" class=\"col-md-3 col-lg-2 text-end\">Nghề nghiệp</label>");
		out.append("<div class=\"col-md-3 col-lg-4\">");
		out.append("<input name=\"txtJob\" type=\"text\" class=\"form-control\" id=\"Job\" value=\"" + job + "\">");
		out.append("</div>");

		out.append("<label for=\"jobarea\" class=\"col-md-3 col-lg-2 text-end\">Lĩnh vực</label>");
		out.append("<div class=\"col-md-3 col-lg-4\">");
		out.append("<input name=\"txtJobarea\" type=\"text\" class=\"form-control\" id=\"jobarea\" value=\"" + jobarea
				+ "\">");
		out.append("</div>");
		out.append("</div>");

		out.append("<div class=\"row mb-3 align-items-center\">");
		out.append("<label for=\"Address\" class=\"col-md-3 col-lg-2 text-end\">Địa chỉ</label>");
		out.append("<div class=\"col-md-9 col-lg-10\">");
		out.append("<input name=\"txtAddress\" type=\"text\" class=\"form-control\" id=\"Address\" value=\"" + address
				+ "\">");
		out.append("</div>");
		out.append("</div>");

		out.append("<div class=\"row mb-3  align-items-center\">");
		out.append("<label for=\"HPhone\" class=\"col-md-3 col-lg-2 text-end\">Điện thoại</label>");
		out.append("<div class=\"col-md-9 col-lg-10\">");
		out.append("<div class=\"input-group\">");
		out.append(
				"<input name=\"txtHPhone\" type=\"text\" class=\"form-control\" id=\"HPhone\" value=\"" + hphone + "\" placeholder=\"Home phone\" title=\"Home phone\">");
		out.append(
				"<input name=\"txtOPhone\" type=\"text\" class=\"form-control\" id=\"OPhone\" value=\"\" placeholder=\"Office phone\" title=\"Office phone\">");
		out.append(
				"<input name=\"txtMPhone\" type=\"text\" class=\"form-control\" id=\"MPhone\" value=\"\" placeholder=\"Mobile phone\" title=\"Mobile phone\">");
		out.append("</div>");
		out.append("</div>");
		out.append("</div>");

		out.append("<div class=\"row mb-3 align-items-center\">");
		out.append("<label for=\"Email\" class=\"col-md-3 col-lg-2 text-end\">Hộp thư</label>");
		out.append("<div class=\"col-md-3 col-lg-4\">");
		out.append(
				"<input name=\"txtEmail\" type=\"email\" class=\"form-control\" id=\"Email\" value=\"" + email + "\">");
		out.append("</div>");

		out.append("<label for=\"Facebook\" class=\"col-md-3 col-lg-2 col-form-label\">Facebook</label>");
		out.append("<div class=\"col-md-3 col-lg-4\">");
		out.append(
				"<input name=\"facebook\" type=\"text\" class=\"form-control\" id=\"Facebook\" value=\"https://facebook.com/#\">");
		out.append("</div>");
		out.append("</div>");

		out.append("<div class=\"text-center\">");
		out.append("<button type=\"submit\" class=\"btn btn-primary\">Lưu thay đổi</button>");
		out.append("</div>");
		if (isEdit) {
			out.append("<input type=\"hidden\" name=\"idForPost\" value=\"" + id + "\" />");
			out.append("<input type=\"hidden\" name=\"act\" value=\"edit\" />");
		}
		out.append("</form><!-- End Profile Edit Form -->");

		out.append("</div>");

		out.append("<div class=\"tab-pane fade pt-3\" id=\"settings\">");

		out.append("<!-- Settings Form -->");

		// Truyền id theo cơ chế biến form ẩn để thực hiện edit

		out.append("<form>");

		out.append("<div class=\"row mb-3\">");
		out.append("<label for=\"fullName\" class=\"col-md-4 col-lg-3 col-form-label\">Email Notifications</label>");
		out.append("<div class=\"col-md-8 col-lg-9\">");
		out.append("<div class=\"form-check\">");
		out.append("<input class=\"form-check-input\" type=\"checkbox\" id=\"changesMade\" checked>");
		out.append("<label class=\"form-check-label\" for=\"changesMade\">");
		out.append("Changes made to your account");
		out.append("</label>");
		out.append("</div>");
		out.append("<div class=\"form-check\">");
		out.append("<input class=\"form-check-input\" type=\"checkbox\" id=\"newProducts\" checked>");
		out.append("<label class=\"form-check-label\" for=\"newProducts\">");
		out.append("Information on new products and services");
		out.append("</label>");
		out.append("</div>");
		out.append("<div class=\"form-check\">");
		out.append("<input class=\"form-check-input\" type=\"checkbox\" id=\"proOffers\">");
		out.append("<label class=\"form-check-label\" for=\"proOffers\">");
		out.append("Marketing and promo offers");
		out.append("</label>");
		out.append("</div>");
		out.append("<div class=\"form-check\">");
		out.append("<input class=\"form-check-input\" type=\"checkbox\" id=\"securityNotify\" checked disabled>");
		out.append("<label class=\"form-check-label\" for=\"securityNotify\">");
		out.append("Security alerts");
		out.append("</label>");
		out.append("</div>");
		out.append("</div>");
		out.append("</div>");

		out.append("<div class=\"text-center\">");
		out.append("<button type=\"submit\" class=\"btn btn-primary\">Save Changes</button>");
		out.append("</div>");
		out.append("</form><!-- End settings Form -->");

		out.append("</div>");

		out.append("<div class=\"tab-pane fade pt-3\" id=\"change-password\">");
		out.append("<!-- Change Password Form -->");
		out.append("<form method=\"post\" action=\"\">");

		out.append("<div class=\"row mb-3\">");
		out.append(
				"<label for=\"currentPassword\" class=\"col-md-4 col-lg-3 col-form-label\">Current Password</label>");
		out.append("<div class=\"col-md-8 col-lg-9\">");
		out.append("<input name=\"password\" type=\"password\" class=\"form-control\" id=\"currentPassword\">");
		out.append("</div>");
		out.append("</div>");

		out.append("<div class=\"row mb-3\">");
		out.append("<label for=\"newPassword\" class=\"col-md-4 col-lg-3 col-form-label\">New Password</label>");
		out.append("<div class=\"col-md-8 col-lg-9\">");
		out.append("<input name=\"newpassword\" type=\"password\" class=\"form-control\" id=\"newPassword\">");
		out.append("</div>");
		out.append("</div>");

		out.append("<div class=\"row mb-3\">");
		out.append(
				"<label for=\"renewPassword\" class=\"col-md-4 col-lg-3 col-form-label\">Re-enter New Password</label>");
		out.append("<div class=\"col-md-8 col-lg-9\">");
		out.append("<input name=\"renewpassword\" type=\"password\" class=\"form-control\" id=\"renewPassword\">");
		out.append("</div>");
		out.append("</div>");

		out.append("<div class=\"text-center\">");
		out.append("<button type=\"submit\" class=\"btn btn-primary\">Change Password</button>");
		out.append("</div>");
		out.append("</form><!-- End Change Password Form -->");

		out.append("</div>");

		out.append("</div><!-- End Bordered Tabs -->");

		out.append("</div>");
		out.append("</div>");

		out.append("</div>"); // col-xl-8
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
		UserObject user = (UserObject) request.getSession().getAttribute("userLogined");

		// Thiết lập tập ký tự cần lấy. Việc thiết lập này cần xác định từ đầu
		request.setCharacterEncoding("utf-8");

		// Lấy id của người sử dụng để chỉnh sửa
		int id = jsoft.library.Utilities.getIntParam(request, "idForPost");
		String action = request.getParameter("act");

		if (id > 0) {
			if (action != null && action.equalsIgnoreCase("edit")) {
				// Lấy các thông tin bắt buộc
				String fullname = request.getParameter("txtFullName");
				String address = request.getParameter("txtAddress");
				String job = request.getParameter("txtJob");
				String jobarea = request.getParameter("txtJobarea");
				String Hphone = request.getParameter("txtHPhone");
				String Mphone = request.getParameter("txtMPhone");
				String Ophone = request.getParameter("txtOPhone");
				String email = request.getParameter("txtEmail");
				String notes = request.getParameter("txtNotes");
				String birthday = request.getParameter("txtBirthday");

				byte permis = jsoft.library.Utilities.getByteParam(request, "slcPermis");

				if (fullname != null && !fullname.equalsIgnoreCase("")
						&& email != null
						&& !email.equalsIgnoreCase("") && Hphone != null && !Hphone.equalsIgnoreCase("")) {
					// Tạo đối tương lưu trữ thông tin
					UserObject nuser = new UserObject();
					nuser.setUser_id(id);
					nuser.setUser_fullname(jsoft.library.Utilities.encode(fullname));
					nuser.setUser_address(jsoft.library.Utilities.encode(address));
					nuser.setUser_parent_id(user.getUser_id());
					nuser.setUser_email(email);
					nuser.setUser_homephone(Hphone);
					nuser.setUser_mobilephone(Mphone);
					nuser.setUser_officephone(Ophone);
					nuser.setUser_last_modified(jsoft.library.Utilities_date.getDate());
					nuser.setUser_job(jsoft.library.Utilities.encode(job));
					nuser.setUser_jobarea(jsoft.library.Utilities.encode(jobarea));
					nuser.setUser_notes(jsoft.library.Utilities.encode(notes));
					nuser.setUser_birthday(birthday);

					ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("CPool");
					UserControl uc = new UserControl(cp);
					if (cp == null) {
						getServletContext().setAttribute("CPool", uc.getCP());
					}

					// Thực hiện cập nhật thông tin user
					boolean result = uc.editUser(nuser, USER_EDIT_TYPE.GENERAL);

					// Trả về kết nối
					uc.releaseConnection();

					if (result) {
						response.sendRedirect("/adv/user/list");
					} else {
						// 
						response.sendRedirect("/adv/user/list?err=edit");
					}
				} else { // Tạo luồng mới khi sự kiện mới với tài nguyên mới
					response.sendRedirect("/adv/user/list?err=upd");
				}
			}
		} else { // trường hơp không tồn tại id
			response.sendRedirect("/adv/user/list?err=profiles");
		}
	}
}
