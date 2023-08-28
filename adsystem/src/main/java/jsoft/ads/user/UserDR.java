package jsoft.ads.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsoft.ConnectionPool;
import jsoft.library.*;
import jsoft.object.*;

/**
 * Servlet cho hoạt động xóa và restore
 */
@WebServlet("/user/dr")
public class UserDR extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDR() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Kiểm tra đăng nhập
		UserObject user = (UserObject)request.getSession().getAttribute("userLogined");
		int id = Utilities.getIntParam(request, "id");
		int pid = Utilities.getIntParam(request, "pid");
		
		if(user != null && id>0) {
			if(user.getUser_id() != id) {
				// Nếu user là admin hoặc user là cha của người dùng muốn xóa
				if(user.getUser_permission() >= 4 || user.getUser_id() == pid) {
					ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("CPool");
					UserControl uc = new UserControl(cp);
					UserObject dUser = new UserObject();
					dUser.setUser_id(id);
					dUser.setUser_parent_id(pid);
					boolean result = uc.delUser(dUser);
					uc.releaseConnection();
					if(result) {
						response.sendRedirect("/adv/user/list");
					} else {
						response.sendRedirect("/adv/user/list?err=notok");
					}
				} else {
					response.sendRedirect("/adv/user/list?err=nopermis");
				}
			} else {
				// Người dùng không được xóa tài khoản chính họ
				response.sendRedirect("/adv/user/list?err=acclogin");
			}
		} else {
			response.sendRedirect("/adv/user/list?err=del");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
