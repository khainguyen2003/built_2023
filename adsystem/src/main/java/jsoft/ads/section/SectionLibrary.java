package jsoft.ads.section;
import jsoft.object.*;
import java.util.*;
import org.javatuples.*;

public class SectionLibrary {
	public static ArrayList<String> viewSection(Pair<ArrayList<SectionObject>, Integer> datas) {
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
		items.forEach(item-> {
			tmp.append("<tr>");
			tmp.append("<th scope=\"row\">"+item.getSection_id()+"</th>");
			tmp.append("<td>"+ item.getSection_created_date() +"</td>");
			tmp.append("<td>").append(item.getSection_name()).append("</td>");
			tmp.append("<td>").append(item.getSection_manager_id()).append("</td>");
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
		
		tmp.setLength(0);
		tmp.append("<nav aria-label=\"...\">");
		tmp.append("<ul class=\"pagination\">");
		tmp.append("<li class=\"page-item disabled\">");
		tmp.append("<a class=\"page-link\" href=\"#\" tabindex=\"-1\" aria-disabled=\"true\">Previous</a>");
		tmp.append("</li>");
		tmp.append("<li class=\"page-item\"><a class=\"page-link\" href=\"#\">1</a></li>");
		tmp.append("<li class=\"page-item active\" aria-current=\"page\">");
		tmp.append("<a class=\"page-link\" href=\"#\">2</a>");
		tmp.append("</li>");
		tmp.append("<li class=\"page-item\"><a class=\"page-link\" href=\"#\">3</a></li>");
		tmp.append("<li class=\"page-item\">");
		tmp.append("<a class=\"page-link\" href=\"#\">Next</a>");
		tmp.append("</li>");
		tmp.append("</ul>");
		tmp.append("</nav>");
		
		// Paging
		view.add(tmp.toString());
		return view;
	}
}
