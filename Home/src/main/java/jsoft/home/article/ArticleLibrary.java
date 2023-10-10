package jsoft.home.article;

import jsoft.object.*;
import java.util.*;
import org.javatuples.*;


public class ArticleLibrary {
	public static ArrayList<String> viewPostGrid(Pair<ArrayList<ArticleObject>, ArrayList<ArticleObject>> datas) {
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
			for(int i =1; i<items.size();i++) {
				if(i%2 == 1) {
					tmp.append("<div class=\"col-lg-4 border-start custom-border\">");
				}
				
				item = items.get(i);
				
				tmp.append("<div class=\"post-entry-1\">");
				tmp.append("<a href=\"#\"><img src=\""+item.getArticle_image()+"\" alt=\"\" class=\"img-fluid\"></a>");
				tmp.append("<div class=\"post-meta\"><span class=\"date\">"+item.getCategory_name()+"</span> <span class=\"mx-1\">&bullet;</span> <span>"+item.getArticle_created_date()+"</span></div>");
				tmp.append("<h2><a href=\"#\">"+item.getArticle_title()+"</a></h2>");
				tmp.append("</div>");
				
				if(i%2 == 0 || i == items.size()-1) {
					tmp.append("</div>");
				}
			}
			System.out.println(tmp.toString());
			
//			view.add(tmp.toString());
		}
		if(hot_items.size() > 0) {
			tmp.append("<div class=\"col-lg-4\">");
			tmp.append("<div class=\"trending\">");
			tmp.append("<h3>Xu hướng</h3>");
			tmp.append("<ul class=\"trending-post\">");
			hot_items.forEach(hi -> {
				tmp.append("<li><a href=\"single-post.html\"> <span class=\"number\">"+ (hot_items.indexOf(hi) + 1) +"</span>");
				tmp.append("<h3>"+hi.getArticle_title()+"</h3>");
				tmp.append("<span class=\"author\">"+hi.getArticle_author_name()+"</span>");
				tmp.append("</a></li>");
			});
			tmp.append("</ul>"); // trending-post
			tmp.append("</div>"); // trending
			tmp.append("</div>"); // col-lg-4
		}
		
		view.add(tmp.toString());
		return view;
	}
	
	public static ArrayList<String> viewNews(Pair<ArrayList<ArticleObject>, ArrayList<ArticleObject>> datas) {
		ArrayList<String> view  = new ArrayList<>();
		StringBuilder tmp = new StringBuilder();
		// Danh sách bài viết mới nhất
		ArrayList<ArticleObject> items = datas.getValue0();
		// Danh sách bài viết xem nhiều nhất
		ArrayList<ArticleObject> hot_items = datas.getValue1();
		
		tmp.append("<section>");
		tmp.append("<div class=\"container\">");
		tmp.append("<div class=\"row\">");

		tmp.append("<div class=\"col-md-9\" data-aos=\"fade-up\">");
		tmp.append("<h3 class=\"category-title\">Category: Business</h3>");

		// Hiển thị mới nhất
		items.forEach(item -> {
			tmp.append("<div class=\"d-md-flex post-entry-2 half\">");
			tmp.append("<a href=\"/home/tin-tuc/?id=\" class=\"me-4 thumbnail\">");
			tmp.append("<img src=\""+item.getArticle_image()+"\" alt=\""+item.getArticle_title()+"\" class=\"img-fluid\">");
			tmp.append("</a>");
			tmp.append("<div>");
			tmp.append("<div class=\"post-meta\"><span class=\"date\">"+item.getCategory_name()+"</span> <span class=\"mx-1\">&bullet;</span> <span>"+item.getArticle_created_date()+"</span></div>");
			tmp.append("<h3><a href=\"/home/tin-tuc?id=\">"+item.getArticle_title()+"</a></h3>");
			tmp.append("<p>"+item.getArticle_summary()+"</p>");
			tmp.append("<div class=\"d-flex align-items-center author\">");
			tmp.append("<div class=\"photo\"><img src=\"assets/img/person-2.jpg\" alt=\"\" class=\"img-fluid\"></div>");
			tmp.append("<div class=\"name\">");
			tmp.append("<h3 class=\"m-0 p-0\">"+item.getArticle_author_name()+"</h3>");
			tmp.append("</div>");
			tmp.append("</div>");
			tmp.append("</div>");
			tmp.append("</div>");	
		});

		tmp.append("<div class=\"text-start py-4\">");
		tmp.append("<div class=\"custom-pagination\">");
		tmp.append("<a href=\"#\" class=\"prev\">Prevous</a>");
		tmp.append("<a href=\"#\" class=\"active\">1</a>");
		tmp.append("<a href=\"#\">2</a>");
		tmp.append("<a href=\"#\">3</a>");
		tmp.append("<a href=\"#\">4</a>");
		tmp.append("<a href=\"#\">5</a>");
		tmp.append("<a href=\"#\" class=\"next\">Next</a>");
		tmp.append("</div>");
		tmp.append("</div>");
		tmp.append("</div>");

		tmp.append("<div class=\"col-md-3\">");
		tmp.append("<!-- ======= Sidebar ======= -->");
		tmp.append("<div class=\"aside-block\">");

		tmp.append("<ul class=\"nav nav-pills custom-tab-nav mb-4\" id=\"pills-tab\" role=\"tablist\">");
		tmp.append("<li class=\"nav-item\" role=\"presentation\">");
		tmp.append("<button class=\"nav-link\" id=\"pills-trending-tab\" data-bs-toggle=\"pill\" data-bs-target=\"#pills-trending\" type=\"button\" role=\"tab\" aria-controls=\"pills-trending\" aria-selected=\"true\">Xu hướng</button>");
		tmp.append("</li>");
		tmp.append("<li class=\"nav-item\" role=\"presentation\">");
		tmp.append("<button class=\"nav-link\" id=\"pills-latest-tab\" data-bs-toggle=\"pill\" data-bs-target=\"#pills-latest\" type=\"button\" role=\"tab\" aria-controls=\"pills-latest\" aria-selected=\"false\">Mới nhất</button>");
		tmp.append("</li>");
		tmp.append("</ul>");

		tmp.append("<div class=\"tab-content\" id=\"pills-tabContent\">");
		tmp.append("<!-- Trending -->");
		hot_items.forEach(item -> {
			if(items.indexOf(item) < 5) {
				tmp.append("<div class=\"tab-pane fade\" id=\"pills-trending\" role=\"tabpanel\" aria-labelledby=\"pills-trending-tab\">");
				tmp.append("<div class=\"post-entry-1 border-bottom\">");
				tmp.append("<div class=\"post-meta\"><span class=\"date\">"+item.getCategory_name()+"</span> <span class=\"mx-1\">&bullet;</span> <span>"+item.getArticle_created_date()+"</span></div>");
				tmp.append("<h2 class=\"mb-2\"><a href=\"/home/tin-tuc?id=\">"+item.getArticle_title()+"</a></h2>");
				tmp.append("<span class=\"author mb-3 d-block\">"+item.getArticle_author_name()+"</span>");
				tmp.append("</div>");
				tmp.append("</div> <!-- End Trending -->");
			}
		});

		tmp.append("<!-- Latest -->");
		tmp.append("<div class=\"tab-pane fade\" id=\"pills-latest\" role=\"tabpanel\" aria-labelledby=\"pills-latest-tab\">");
		items.forEach(item -> {
			tmp.append("<div class=\"post-entry-1 border-bottom\">");
			tmp.append("<div class=\"post-meta\"><span class=\"date\">"+item.getCategory_name()+"</span> <span class=\"mx-1\">&bullet;</span> <span>"+item.getArticle_created_date()+"</span></div>");
			tmp.append("<h2 class=\"mb-2\"><a href=\"#\">"+item.getArticle_summary()+"</a></h2>");
			tmp.append("<span class=\"author mb-3 d-block\">"+item.getArticle_author_name()+"</span>");
			tmp.append("</div>");
		});
		tmp.append("</div> <!-- End Latest -->");
		

		tmp.append("</div>");
		tmp.append("</div>");

		tmp.append("<div class=\"aside-block\">");
		tmp.append("<h3 class=\"aside-title\">Video</h3>");
		tmp.append("<div class=\"video-post\">");
		tmp.append("<a href=\"https://www.youtube.com/watch?v=AiFfDjmd0jU\" class=\"glightbox link-video\">");
		tmp.append("<span class=\"bi-play-fill\"></span>");
		tmp.append("<img src=\"assets/img/post-landscape-5.jpg\" alt=\"\" class=\"img-fluid\">");
		tmp.append("</a>");
		tmp.append("</div>");
		tmp.append("</div><!-- End Video -->");

		tmp.append("<div class=\"aside-block\">");
		tmp.append("<h3 class=\"aside-title\">Categories</h3>");
		tmp.append("<ul class=\"aside-links list-unstyled\">");
		tmp.append("<li><a href=\"category.html\"><i class=\"bi bi-chevron-right\"></i> Business</a></li>");
		tmp.append("</ul>");
		tmp.append("</div><!-- End Categories -->");

		tmp.append("<div class=\"aside-block\">");
		tmp.append("<h3 class=\"aside-title\">Tags</h3>");
		tmp.append("<ul class=\"aside-tags list-unstyled\">");
		tmp.append("<li><a href=\"category.html\">Business</a></li>");
		tmp.append("</ul>");
		tmp.append("</div><!-- End Tags -->");

		tmp.append("</div>");

		tmp.append("</div>");
		tmp.append("</div>");
		tmp.append("</section>");
		
		view.add(tmp.toString());
		return view;
	}
}
