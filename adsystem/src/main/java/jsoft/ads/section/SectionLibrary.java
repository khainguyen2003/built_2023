package jsoft.ads.section;
import jsoft.object.*;
import java.util.*;
import org.javatuples.*;

public class SectionLibrary {
	public static ArrayList<String> viewSection(Quartet<ArrayList<SectionObject>, Integer, HashMap<Integer, String>, ArrayList<UserObject>> datas) {
		StringBuilder tmp = new StringBuilder();
		tmp.append("<table class=\"table table-striped\">");
		tmp.append("<thead>");
		tmp.append("<tr>");
		tmp.append("<th scope=\"col\">#</th>");
		tmp.append("<th scope=\"col\">Ngày tạo</th>");
		tmp.append("<th scope=\"col\">Tên chuyên mục</th>");
		tmp.append("<th scope=\"col\">Người quản lý</th>");
		tmp.append("<th scope=\"col\">Mô tả chi tiết</th>");
		tmp.append("<th scope=\"col\" colspan=\"2\">Thực hiện</th>");
		tmp.append("</tr>");
		tmp.append("</thead>");
		tmp.append("<tbody>");
		ArrayList<SectionObject> items = datas.getValue0();
		HashMap<Integer, String> manager_name = datas.getValue2();
		items.forEach(item-> {
			tmp.append("<tr>");
			tmp.append("<th scope=\"row\">"+item.getSection_id()+"</th>");
			tmp.append("<td>"+ item.getSection_created_date() +"</td>");
			tmp.append("<td>").append(item.getSection_name()).append("</td>");
			tmp.append("<td>").append(manager_name.get(item.getSection_manager_id())).append("</td>");
			tmp.append("<td>").append(item.getSection_notes()).append("</td>");
			tmp.append("<td>Sửa</td>");
			tmp.append("<td>Xóa</td>");
			tmp.append("</tr>");
		});
		// Khi dùng với servlet thì không dùng kí tự \n
		tmp.append("</tbody>");
		tmp.append("</table>\n");
		
		ArrayList<String> view = new ArrayList<>();
		view.add(tmp.toString());
		
		// Danh sách cấp quyền quản lý
		ArrayList<UserObject> users = datas.getValue3();
		view.add(SectionLibrary.viewManagerOptions(users, 0));
		return view;
	}
	
	public static String viewManagerOptions(ArrayList<UserObject> users, int selected_id) {
//		StringBuilder data = new StringBuilder();
//		users.forEach(user -> {
//			data.append("{id:" + user.getUser_id() + ", text:'"+ user.getUser_name() +"'},");
//		});
//		System.out.println("data: [" + data.toString() + "]");
		StringBuilder tmp = new StringBuilder();
//		tmp.append("<select class=\"form-select bg-white\" id=\"manager\" name=\"slcManager\" required>");
//		tmp.append("</select>");
//		tmp.append("<script>");
//
//		tmp.append("$('#manager').select2({ ");
//		tmp.append("data: [" + data.toString() + "],");
//		tmp.append("width: '100%',");
//		tmp.append("});");
//		tmp.append("</script>"); // script
		
		users.forEach(item -> {
			if(item.getUser_id() == selected_id) {
				tmp.append("<option value=\"").append(item.getUser_id()).append("\" selected> ");
			} else {
				tmp.append("<option value=\"").append(item.getUser_id()).append("\"> ");
			}
			tmp.append(item.getUser_fullname()).append(" (").append(item.getUser_name()).append(")");
			tmp.append("</option>");
		});
		
		return tmp.toString();
	}
	
}
