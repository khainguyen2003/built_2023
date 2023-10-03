package jsoft.home.article;

import jsoft.object.*;
import java.util.*;
import org.javatuples.*;


public class ArticleLibrary {
	public static ArrayList<String> viewNews(Pair<ArrayList<ArticleObject>, ArrayList<ArticleObject>> datas) {
		ArrayList<String> view  = new ArrayList<>();
		StringBuilder tmp = new StringBuilder();
		// Danh sách bài viết mới nhất
		ArrayList<ArticleObject> items = datas.getValue0();
		// Danh sách bài viết xem nhiều nhất
		ArrayList<ArticleObject> hot_items = datas.getValue1();
		
		if(items.size() > 0) {
			// Vị trí đầu tiên
			ArticleObject item = items.get(0);
			tmp.append("<div class=\"post-entry-1 lg\">");
			tmp.append("	<a href=\"single-post.html\"><img");
			tmp.append("		src=\""+item.getArticle_image()+"\" alt=\"\" class=\"img-fluid\" /></a>");
			tmp.append("	<div class=\"post-meta\">");
			tmp.append("		<span class=\"date\">"+item.getCategory_name()+"</span> <span class=\"mx-1\">&bullet;</span>");
			tmp.append("		<span>"+item.getArticle_created_date()+"</span>");
			tmp.append("	</div>");
			tmp.append("	<h2>");
			tmp.append("		<a href=\"single-post.html\">"+item.getArticle_title()+"</a>");
			tmp.append("	</h2>");
			tmp.append("	<p class=\"mb-4 d-block\">"+item.getArticle_summary()+"</p>");
			tmp.append("	<div class=\"name\">");
			tmp.append("		<h3 class=\"m-0 p-0\">"+item.getArticle_author_name()+"</h3>");
			tmp.append("	</div>");
			tmp.append("</div>");
			
			view.add(tmp.toString());
			
			// 2 cột
			tmp.setLength(0);
			for(int i = 1; i < items.size(); i++) {
				// do lây out 2 cột này có 3 dòng
				if(i % 3 == 1) {
					tmp.append("<div class=\"col-lg-4 border-start custom-border\">");
				}
				item = items.get(i);
				tmp.append("<div class=\"post-entry-1\">");
				tmp.append("<a href=\"#\"><img");
				tmp.append("src=\""+item.getArticle_image()+"\" alt=\"\" class=\"img-fluid\" /></a>");
				tmp.append("<div class=\"post-meta\">");
				tmp.append("<span class=\"date\">"+item.getCategory_name()+"</span> <span class=\"mx-1\">&bullet;</span>");
				tmp.append("<span>"+item.getArticle_last_modified()+"</span>");
				tmp.append("</div>");
				tmp.append("<h2>");
				tmp.append("<a href=\"single-post.html\">"+item.getArticle_title()+"</a>");
				tmp.append("</h2>");
				tmp.append("</div>");
				
				if(i % 3 == 0 || i == items.size() - 1) {
					tmp.append("</div>");
				}
			}
			
//			view.add(tmp.toString());
		}
		if(hot_items.size() > 0) {
			tmp.append("<div class=\"col-lg-4\">");
			tmp.append("<div class=\"trending\">");
			tmp.append("<h3>Trending</h3>");
			tmp.append("<ul class=\"trending-post\">");
			hot_items.forEach(hi -> {
				tmp.append("<li><a href=\"single-post.html\"> <span class=\"number\">1</span>");
				tmp.append("<h3>The Best Homemade Masks for Face (keep the Pimples");
				tmp.append("Away)</h3> <span class=\"author\">Jane Cooper</span>");
				tmp.append("</a></li>");
			});
			tmp.append("</ul>"); // trending-post
			tmp.append("</div>"); // trending
			tmp.append("</div>"); // col-lg-4
		}
		
		return view;
	}
}
