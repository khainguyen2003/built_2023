package jsoft.ads.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import jsoft.ConnectionPool;
import jsoft.object.UserObject;

/** Cách 3: Tạo file servlet qua extends class HttpServlet -> nên dùng
 * Servlet implementation class UserLogin
 */
@WebServlet("/user/login")
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// Định nghĩa kiểu nội dung xuất về trình khách
	private static final String CONTENT_TYPE = "text/html; charset=utf-8";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/** Phương thức này cung cấp 1 giao diện (GUI) (Cấu trúc HTML)<br />
	 * Được gọi trong 2 trường hợp:<br />
	 * - Thông qua URL/URI
	 * - Thông qua sự kiện của Form (method = "GET") 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * @param request - Lưu trữ các yêu cầu xử lý, các dữ liệu được gửi lên bởi Client
	 * @param response - Lưu trữ các đáp ứng dữ liệu sẽ được trả về Client
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// Xác định kiểu nội dung xuất về trình khách
		response.setContentType(CONTENT_TYPE);
		
		// Tạo đối tượng thực hiện xuất nội dung
		PrintWriter out = response.getWriter();
		
		// Nếu dùng print thì phải dùng ngắt dòng để viết thêm nội dung mới. Và khi không dùng nữa thì phải đóng luồng
		// Kĩ thuật này hiện tại không nên dùng vì thường bị lỗi luồng với close() mà nên dùng append
//		out.print("Nghiên cứu công nghệ servlet.<br />");
//		out.print("Không dùng append");
//		out.close();
		
		// out append khi đưa vào Date.getDay() thì sẽ đưa ra kết quả của phép chia. Muốn in ra thành chuỗi ngày thì phải "" + Date.getDay()
		
		out.append("<!DOCTYPE html>");
		out.append("<html lang=\"en\">");
		out.append("<head>");
		out.append("<meta charset=\"utf-8\" />");
		out.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\" />");
		out.append("<title>Bootstrap demo</title>");
		out.append("<link href=\"/adv/adcss/all.min.css\" rel=\"stylesheet\" />");
		out.append("<link href=\"/adv/adcss/bootstrap.min.css\" rel=\"stylesheet\" />");
		out.append("<link href=\"/adv/adcss/Loginvr3.css\" rel=\"stylesheet\" />");

		out.append("<script src=\"/adv/adjs/loginv3.js\"></script>");
		out.append("</head>");

		out.append("<body>");
		out.append("<div class=\"container-md my-5\">");
		out.append("<div class=\"row\">");
		
		
		// Tìm tham số báo lỗi nếu có
		String error = request.getParameter("err");
		if(error != null) {
			out.append("<div class=\"col-lg-6 offset-lg-3\">");
			out.append("<div class=\"mb-3\">");
			out.append("<div class=\"alert alert-warning alert-dismissible fade show\" role=\"alert\">");
			out.append("<i class=\"fa-solid fa-triangle-exclamation\"></i>");
			switch(error) {
				case "param": 
					out.append("Tham số lấy giá trị khôn chính xác!");
					break;
				case "value": 
					out.append("Không tồn tại giá trị cho tài khoản!");
					break;
				case "notok":
					out.append("Có lỗi trong quá trình đăng nhập!");
					break;
			}
			out.append("<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"alert\" aria-label=\"Close\"></button>");
			out.append("</div>");
			out.append("</div>");
			out.append("</div>");
		}

		out.append("<div class=\"col-lg-6 offset-lg-3 text-bg-light\">");
		out.append("<div class=\"loginview\">");
		out.append("<form method=\"post\" >");
		out.append("<div class=\"row\">");
		out.append("<div class=\"col-12 py-3 text-bg-primary text-uppercase fw-bold text-center\">");
		out.append("<i class=\"fa-solid fa-right-to-bracket\"></i>&nbsp; Login");
		out.append("</div>");
		out.append("</div>");
		out.append("<div class=\"row my-3 align-items-center\">");
		out.append("<label for=\"user\" class=\"col-sm-4 form-label text-end\"");
		out.append(">UserName/Email</label>");
		out.append("<div class=\"col-sm-6\">");
		out.append("<input ");
		out.append("type=\"text\" ");
		out.append("class=\"form-control\" ");
		out.append("id=\"user\" name=\"username\"");
		out.append("aria-describedby=\"emailHelp\" ");
		out.append("onchange=\"checkValidLogin() \" ");
		out.append("/> ");
		out.append("<div id=\"emailHelp\" class=\"form-text\"> ");
		out.append("Bạn có thể dùng tài khoản hộp thư. ");
		out.append("</div> ");
		out.append("<div ");
		out.append("id=\"errName\" ");
		out.append("class=\"alert alert-danger\" ");
		out.append("style=\"display: none\" ");
		out.append("></div> ");
		out.append("</div> ");
		out.append("</div> ");
		out.append("<div class=\"row mb-3 aligt-items-center\"> ");
		out.append("<label for=\"Password\" class=\"col-sm-4 form-label text-end\" ");
		out.append(">Password</label ");
		out.append("> ");
		out.append("<div class=\"col-sm-6\" id=\"password-box\"> ");
		out.append("<input ");
		out.append("type=\"password\" ");
		out.append("class=\"form-control pe-5\" ");
		out.append("id=\"Password\" name=\"userpass\" ");
		out.append("onkeyup=\"checkValidPassword(this.value) \" ");
		out.append("/> ");
		out.append("<span id=\"show-btn\" onclick=\"showPass()\"></span> ");
		out.append("<ul class=\"validation\"> ");
		out.append("<li id=\"chkLower\">At least one lower charactor</li> ");
		out.append("<li id=\"chkUpper\">At least one upper charactor</li> ");
		out.append("<li id=\"chkNumber\">At least one number</li> ");
		out.append("<li id=\"chkSpec\">At least one special charactor</li> ");
		out.append("<li id=\"chklength\">At least 8 charactor</li> ");
		out.append("</ul> ");
		out.append("</div> ");
		out.append("</div> ");
		out.append("<div class=\"row mb-3\"> ");
		out.append("<div class=\"col-sm-8 offset-sm-4\"> ");
		out.append("<input ");
		out.append("type=\"checkbox\" ");
		out.append("class=\"form-check-input\" ");
		out.append("id=\"Chksave\" ");
		out.append("/> ");
		out.append("<label class=\"form-check-label\" for=\"Chksave\" ");
		out.append(">Save this information</label ");
		out.append("> ");
		out.append("</div> ");
		out.append("</div> ");
		out.append("<div class=\"row mb-3\"> ");
		out.append("<div class=\"col-12 text-center\"> ");
		out.append("<a href=\"#\" class=\"text-decoration-none\" ");
		out.append("><i class=\"fa-solid fa-circle-info\"></i>Forget Password ? ");
		out.append("|</a ");
		out.append("> ");
		out.append("&nbsp; &nbsp; &nbsp; ");
		out.append("<a href=\"#\" class=\"text-decoration-none\" ");
		out.append("><i class=\"fa-solid fa-square-phone\"></i>Help!</a ");
		out.append("> ");
		out.append("</div> ");
		out.append("</div> ");

		out.append("<div class=\"row mb-3\"> ");
		out.append("<div class=\"col-12 text-center\"> ");
		out.append("<button type=\"submit\" class=\"btn btn-primary\"> ");
		out.append("<i class=\"fa-solid fa-right-to-bracket\"></i> Login ");
		out.append("</button> ");
		out.append("<button type=\"button\" class=\"btn btn-warning\"> ");
		out.append("<i class=\"fa-solid fa-arrow-right-from-bracket\"></i> Cancel ");
		out.append("</button> ");
		out.append("</div> ");
		out.append("</div> ");

		out.append("<div class=\"row mb-3\"> ");
		out.append("<div class=\"col-12 text-end\"> ");
		out.append("<a href=\"#\" class=\"text-decoration-none\" ");
		out.append("><i class=\"fa-solid fa-language\"></i> Vietnamese</a ");
		out.append("> ");
		out.append("</div> ");
		out.append("</div> ");
		out.append("</form> ");
		out.append("</div> ");
		out.append("</div> ");
		out.append("</div> ");
		out.append("</div> ");

		out.append("<script src=\"/adv/adjs/loginv3.js\"></script> ");
		out.append("<script src=\"/adv/adjs/bootstrap.bundle.min.js\"></script> ");
		out.append("</body> ");
		out.append("</html> ");

	}

	/** Thường được dùng để xử lý dữ liệu do doGet truyền cho<br />
	 * Được gọi trong sự kiện trong sự kiện method="POST" của form<br />
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		
		// Lấy thông tin tài khoản
		String username = request.getParameter("username");
		String userpass = request.getParameter("userpass");
		
		if(username != null && userpass != null) {
			username = username.trim();
			userpass = userpass.trim();
			if(!username.equalsIgnoreCase("") && !userpass.equalsIgnoreCase("")) {
				// Tham chiếu ngữ cảnh ứng dụng. ngữ cảnh ứng dụng là không gian bộ nhớ tồn tại ở server
				ServletContext application = getServletConfig().getServletContext();
				// Tìm bộ quản lý kết nối trong không gian này
				ConnectionPool cp = (ConnectionPool)application.getAttribute("CPool");
				// Tạo đối tương thực thi chức năng
				UserControl uc = new UserControl(cp);
				if(cp == null) {
					// Lấy cp của Basic truyền vào thuộc tính CPool
					application.setAttribute("CPool", uc.getCP());
				}
				
				// Thực hiện đăng nhập
				UserObject user = uc.getUserObject(username, userpass);

				// Trả về kết nối
				uc.releaseConnection();
				
				if(user != null) {
					// Tham chiếu session
					// getSession: nếu giá trị truyền vào true thì sẽ tạo phiên làm việc mới, nếu giá trị truyền vào false thì sẽ hủy session
					// Về xem kỹ thuật đồng bộ phiên
					HttpSession session = request.getSession(true);
					
					// Đưa thông tin đăng nhập vào phiên
					session.setAttribute("userLogined", user);
					
					// Trở về giao diện chính
					response.sendRedirect("/adv/view");
				} else {
					response.sendRedirect("/adv/user/login?err=notok");
				}
			} else {
				response.sendRedirect("/adv/user/login?err=value");
			}
		} else {
			// Đưa về trang thông báo lỗi tham số
			response.sendRedirect("/adv/user/login?err=param");
		}
	}

}
