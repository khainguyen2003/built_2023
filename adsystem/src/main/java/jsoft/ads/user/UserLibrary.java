package jsoft.ads.user;
import jsoft.library.Utilities;
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
		// Trong thùng rác chỉ có thao tác phục hồi hoặc xóa tuyệt đối
		if(user.isUser_deleted()) {
			tmp.append("<th scope=\"col\">Ngày xóa</th>");
			tmp.append("<th scope=\"col\" colspan=\"2\">Thực hiện</th>");
		} else {
			tmp.append("<th scope=\"col\">Hộp thư</th>");
			tmp.append("<th scope=\"col\">Điện thoại</th>");
			tmp.append("<th scope=\"col\">Địa chỉ</th>");
			tmp.append("<th scope=\"col\" colspan=\"3\">Thực hiện</th>");
		}
		tmp.append("</tr>");
		tmp.append("</thead>");
		tmp.append("<tbody>");
		items.forEach(item-> {
			tmp.append("<tr>");
			tmp.append("<th scope=\"row\">"+item.getUser_id()+"</th>");
			tmp.append("<td>"+ item.getUser_name() +"</td>");
			tmp.append("<td>").append(item.getUser_fullname()).append("</td>");
			if(user.isUser_deleted()) { // trong danh sách người dùng bị xóa
				tmp.append("<td>").append(item.getUser_last_modified()).append("</td>");
				tmp.append("<td><button class=\"btn btn-danger btn-sm\" data-bs-toggle=\"modal\" data-bs-target=\"#delUser"+item.getUser_id()+"\"><i class=\"bi bi-trash\"></i></button></td>");
				tmp.append(UserLibrary.viewDelUser(item));
			} else { // Trạng thái bình thường
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
						tmp.append("<td><button class=\"btn btn-danger btn-sm\" data-bs-toggle=\"modal\" data-bs-target=\"#delUser"+item.getUser_id()+"\"><i class=\"bi bi-trash\"></i></button></td>");
						tmp.append(UserLibrary.viewDelUser(item));
					} else {
						// Nếu là cha của người dùng trong danh sách thì được phép sửa
						if(item.getUser_parent_id() == user.getUser_id()) {
							tmp.append("<td><a href=\"/adv/user/profile?id="+item.getUser_id()+"\" class=\"btn btn-primary btn-sm\"><i class=\"bi bi-pencil-square\"></i></a></td>");
						} else {
							tmp.append("<td><a href=\"#\" class=\"btn btn-primary btn-sm disabled\"><i class=\"bi bi-pencil-square\"></i></a></td>");
						}
						tmp.append("<td><button class=\"btn btn-danger btn-sm disabled\" data-bs-toggle=\"modal\" data-bs-target=\"#delUser"+item.getUser_id()+"\" ><i class=\"bi bi-trash\"></i></button></td>");
					}
				}	
			}
			tmp.append("</tr>");
		});
		// Khi dùng với servlet thì không dùng kí tự \n
		tmp.append("</tbody>");
		tmp.append("</table>");
		
		ArrayList<String> view = new ArrayList<>();
		view.add(tmp.toString());
		
		// Cấu trúc biểu đồ
		String chart = UserLibrary.createChart(items).toString();
		view.add(chart);
		return view;
	}
	
	private static StringBuilder viewDelUser(UserObject item) {
		StringBuilder tmp = new  StringBuilder();
		String title;
		if(item.isUser_deleted()) {
			title = "Xóa vĩnh viễn";
		} else {
			title = "Xóa tài khoản";
		}
		// Modal
		tmp.append("<div class=\"modal fade\" id=\"delUser"+item.getUser_id()+"\" tabindex=\"-1\" aria-labelledby=\"UserLabel\""+item.getUser_id()+" aria-hidden=\"true\">");
		tmp.append("<div class=\"modal-dialog\">");
		tmp.append("<div class=\"modal-content\">");
		tmp.append("<div class=\"modal-header\">");
		tmp.append("<h1 class=\"modal-title fs-5\" id=\"UserLabel\""+item.getUser_id()+">"+title+"</h1>");
		tmp.append("<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>");
		tmp.append("</div>");
		tmp.append("<div class=\"modal-body\">");
		if(item.isUser_deleted()) {
			tmp.append("<p>Bạn có chắc chắn muốn xóa vĩnh viễn tài khoản <b>").append(item.getUser_fullname()).append(" ("+item.getUser_name()+")</b></p>");
			tmp.append("<p class=\"txt-alert\">Tài khoản không thể phục hồi được nữa</p>");
		} else {
			tmp.append("Bạn có chắc chắn muốn xóa tài khoản <b>").append(item.getUser_fullname()).append(" ("+item.getUser_name()+")</b></br>");
			tmp.append("<p class=\"txt-alert\">Hệ thống tạm thời lưu vào thùng rác, tài khoản có thể phục hồi trong vòng 30 ngày</p>");	
		}
		tmp.append("</div>");
		tmp.append("<div class=\"modal-footer\">");
		if(item.isUser_deleted()) {
			tmp.append("<a href=\"/adv/user/dr?id="+item.getUser_id()+"&pid="+item.getUser_parent_id()+"\" class=\"btn btn-danger\">Xóa vĩnh viễn</a>");
		} else {
			// dr: delete + restore
			tmp.append("<a href=\"/adv/user/dr?id="+item.getUser_id()+"&t&pid="+item.getUser_parent_id()+"\" class=\"btn btn-danger\">Xóa</a>");		
		}
		tmp.append("<button type=\"button\" class=\"btn btn-primary\" data-bs-dismiss=\"modal\">Hủy</button>");
		tmp.append("</div>");
		tmp.append("</div>");
		tmp.append("</div>");
		tmp.append("</div>");
		return tmp;
	}
	
	public static StringBuilder createChart(ArrayList<UserObject> items) {
		StringBuilder data = new StringBuilder();
		StringBuilder accounts = new StringBuilder();
		items.forEach(item -> {
			data.append(item.getUser_logined());
			accounts.append("'"+Utilities.decode(item.getUser_fullname())).append(" (").append(item.getUser_name()).append(")'");
			if(items.indexOf(item) < items.size()) {
				data.append(", ");
				accounts.append(", ");
			}
		});
//		System.out.println(data.toString());
		StringBuilder tmp = new StringBuilder();
		tmp.append("<div class=\"card\">");
		tmp.append("<div class=\"card-body\">");
		tmp.append("<h5 class=\"card-title\">Bar Chart</h5>");

		tmp.append("<!-- Bar Chart -->");
		tmp.append("<div id=\"barChart\"></div>");

		tmp.append("<script>");
		tmp.append("document.addEventListener(\"DOMContentLoaded\", () => {");
		tmp.append("new ApexCharts(document.querySelector(\"#barChart\"), {");
		tmp.append("series: [{");
		tmp.append("data: ["+data.toString()+"],");
		tmp.append("name: [\"logined\"],");
		tmp.append("}],");
		
		tmp.append("chart: {");
		tmp.append("type: 'bar',");
		tmp.append("height: 350");
		tmp.append("},");
		tmp.append("plotOptions: {");
		tmp.append("bar: {");
		tmp.append("borderRadius: 2,");
		tmp.append("horizontal: true,");
		tmp.append("}");
		tmp.append("},");
		tmp.append("dataLabels: {");
		tmp.append("enabled: false");
		tmp.append("},");
		tmp.append("xaxis: {");
		tmp.append("categories: ["+accounts.toString()+"],");
		tmp.append("}");
		tmp.append("}).render();");
		tmp.append("});");
		tmp.append("</script>");
		tmp.append("<!-- End Bar Chart -->");

		tmp.append("</div>");
		tmp.append("</div>");
		
		return tmp;
	}

}
