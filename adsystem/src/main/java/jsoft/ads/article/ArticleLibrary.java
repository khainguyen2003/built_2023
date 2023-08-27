package jsoft.ads.article;

import java.util.ArrayList;

import org.javatuples.Pair;

import jsoft.object.ArticleObject;

public class ArticleLibrary {
	public static ArrayList<String> viewArticle(Pair<ArrayList<ArticleObject>, Integer> infors) {
		StringBuilder tmp = new StringBuilder();
		tmp.append("<table class=\"table table-striped\">");
		tmp.append("<thead>");
		tmp.append("<tr>");
		tmp.append("<th scope=\"col\">#</th>");
		tmp.append("<th scope=\"col\">Tên tiêu đề</th>");
		tmp.append("<th scope=\"col\">Nội dung</th>");
		tmp.append("<th scope=\"col\">Người tạo</th>");
		tmp.append("<th scope=\"col\">Đường dẫn</th>");
		tmp.append("<th scope=\"col\" colspan=\"3\">Thực hiện</th>");
		tmp.append("</tr>");
		tmp.append("</thead>");
		tmp.append("<tbody>");
		ArrayList<ArticleObject> items = infors.getValue0();
		items.forEach(item-> {
			tmp.append("<tr>");
			tmp.append("<th scope=\"row\">"+item.getArticle_id()+"</th>");
			tmp.append("<td>"+ item.getArticle_title() +"</td>");
			tmp.append("<td>").append(item.getArticle_summary()).append("</td>");
			tmp.append("<td>").append(item.getArticle_author_name()).append("</td>");
			tmp.append("<td>").append(item.getArticle_url_link()).append("</td>");
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
