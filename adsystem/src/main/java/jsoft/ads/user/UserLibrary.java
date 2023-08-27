package jsoft.ads.user;
import jsoft.object.*;
import java.util.*;

public class UserLibrary {
	// Tham số thứ 2 chứa thông tin người dùng đang đăng nhập
	public static ArrayList<String> viewUser(ArrayList<UserObject> items, UserObject user) {
		StringBuilder tmp = new StringBuilder();
		tmp.append("<table class=\"table table-striped\">");
		tmp.append("<thead>");
		tmp.append("<tr>");
		tmp.append("<th scope=\"col\">#</th>");
		tmp.append("<th scope=\"col\">Tên đăng nhập</th>");
		tmp.append("<th scope=\"col\">Tên đầy đủ</th>");
		tmp.append("<th scope=\"col\">Hộp thư</th>");
		tmp.append("<th scope=\"col\">Điện thoại</th>");
		tmp.append("<th scope=\"col\">Địa chỉ</th>");
		tmp.append("<th scope=\"col\" colspan=\"3\">Thực hiện</th>");
		tmp.append("</tr>");
		tmp.append("</thead>");
		tmp.append("<tbody>");
		items.forEach(item-> {
			tmp.append("<tr>");
			tmp.append("<th scope=\"row\">"+item.getUser_id()+"</th>");
			tmp.append("<td>"+ item.getUser_name() +"</td>");
			tmp.append("<td>").append(item.getUser_fullname()).append("</td>");
			tmp.append("<td>").append(item.getUser_email()).append("</td>");
			tmp.append("<td>").append(item.getUser_homephone()).append("</td>");
			tmp.append("<td>").append(item.getUser_address()).append("</td>");
			tmp.append("<td><a href=\"/adv/user/profile?id="+item.getUser_id()+"\" class=\"btn btn-primary btn-sm\"><i class=\"bi bi-eye\"></i></a></td>");
			// Kiểm tra tài khoản trong danh sách có phải là tài khoản đang đăng nhập hay không
			if(item.getUser_id() == user.getUser_id()) {
				tmp.append("<td><a href=\"/adv/user/profile?id="+item.getUser_id()+"\" class=\"btn btn-primary btn-sm\"><i class=\"bi bi-pencil-square\"></i></a></td>");
				tmp.append("<td><a href=\"#\" class=\"btn btn-danger btn-sm disabled\" disabled><i class=\"bi bi-trash\"></i></a></td>");
			} else {
				// Kiểm tra người dùng có phải người quản trị hay không. Nếu có thì người dùng có toàn quyền sửa danh sách người dùng
				if(user.getUser_permission() >=4) {
					tmp.append("<td><a href=\"/adv/user/profile?id="+item.getUser_id()+"\" class=\"btn btn-primary btn-sm\"><i class=\"bi bi-pencil-square\"></i></a></td>");
					tmp.append("<td><a href=\"#\" class=\"btn btn-danger btn-sm\"><i class=\"bi bi-trash\"></i></a></td>");
				} else {
					// Nếu là cha của người dùng trong danh sách thì được phép sửa
					if(item.getUser_parent_id() == user.getUser_id()) {
						tmp.append("<td><a href=\"/adv/user/profile?id="+item.getUser_id()+"\" class=\"btn btn-primary btn-sm\"><i class=\"bi bi-pencil-square\"></i></a></td>");
					} else {
						tmp.append("<td><a href=\"#\" class=\"btn btn-primary btn-sm disabled\"><i class=\"bi bi-pencil-square\"></i></a></td>");
					}
					tmp.append("<td><a href=\"#\" class=\"btn btn-danger btn-sm disabled\" ><i class=\"bi bi-trash\"></i></a></td>");
				}
			}
			tmp.append("</tr>");
		});
		// Khi dùng với servlet thì không dùng kí tự \n
		tmp.append("</tbody>");
		tmp.append("</table>");
		
		ArrayList<String> view = new ArrayList<>();
		view.add(tmp.toString());
		return view;
	}

}