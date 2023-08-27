package jsoft.ads.catagory;

import java.util.ArrayList;

import org.javatuples.Pair;

import jsoft.object.CategoryObject;

public class CategoryLibrary {
	public static ArrayList<String> viewCategory(Pair<ArrayList<CategoryObject>, Integer> infors) {
		StringBuilder tmp = new StringBuilder();
		tmp.append("<table class=\"table table-striped\">");
		tmp.append("<thead>");
		tmp.append("<tr>");
		tmp.append("<th scope=\"col\">#</th>");
		tmp.append("<th scope=\"col\">Tên danh mục</th>");
		tmp.append("<th scope=\"col\">Tên người lập</th>");
		tmp.append("<th scope=\"col\">Section</th>");
		tmp.append("<th scope=\"col\">Người quản lý</th>");
		tmp.append("<th scope=\"col\" colspan=\"3\">Thực hiện</th>");
		tmp.append("</tr>");
		tmp.append("</thead>");
		tmp.append("<tbody>");
		ArrayList<CategoryObject> items = infors.getValue0();
		items.forEach(item-> {
			tmp.append("<tr>");
			tmp.append("<th scope=\"row\">"+item.getCategory_id()+"</th>");
			tmp.append("<td>"+ item.getCategory_name() +"</td>");
			tmp.append("<td>").append(item.getCategory_created_author_id()).append("</td>");
			tmp.append("<td>").append(item.getCategory_section_id()).append("</td>");
			tmp.append("<td>").append(item.getCategory_manager_id()).append("</td>");
			tmp.append("<td>Chi tiết</td>");
			tmp.append("<td>Sửa</td>");
			tmp.append("<td>Xóa</td>");
			tmp.append("</tr>");
		});
		// Khi dùng với servlet thì không dùng kí tự \n
		tmp.append("</tbody>");
		tmp.append("</table>\n");
		
		ArrayList<String> view = new ArrayList<>();
		view.add(tmp.toString());
		return view;
	}
}
