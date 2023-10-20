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
			// bài viết đầu tiên mới nhất
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
	
	public static ArrayList<String> viewNews(Sextet<ArrayList<ArticleObject>,
			ArrayList<ArticleObject>, ArrayList<CategoryObject>, HashMap<String, Integer>, Integer, ArrayList<ArticleObject>> datas, Quartet<ArticleObject, Short, Byte, Boolean> infors) {
		ArrayList<String> view  = new ArrayList<>();
		StringBuilder tmp = new StringBuilder();
		// Danh sách bài viết mới nhất ko phân trang(sidebar)
		ArrayList<ArticleObject> items = datas.getValue0();
		// Danh sách bài viết xem nhiều nhất
		ArrayList<ArticleObject> hot_items = datas.getValue1();
		// Danh sách thể loại
		ArrayList<CategoryObject> cates = datas.getValue2();
		// Danh sách tags
		HashMap<String, Integer> tags = datas.getValue3();
		// Tống số bài viết
		int total = datas.getValue4();
		// Danh sách bài viết mới nhất có phân trang
		ArrayList<ArticleObject> pitems = datas.getValue5();
		
		ArticleObject similar = infors.getValue0();
		short page = infors.getValue1();
		byte totalPerpage = infors.getValue2();
		
		tmp.append("<section>");
		tmp.append("<div class=\"container\">");
		tmp.append("<div class=\"row\">");

		tmp.append("<div class=\"col-md-9\" data-aos=\"fade-up\">");
		tmp.append(ArticleLibrary.viewCateObtions(cates, similar));

		// Hiển thị mới nhất
		pitems.forEach(item -> {
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

		// Phân trang
		tmp.append(ArticleLibrary.getPagination("/home/tin-tuc?cid="+similar.getArticle_category_id()+"", total, page, totalPerpage));
		tmp.append("</div>"); // col-md-9
		
		tmp.append(ArticleLibrary.viewSideBar(items, hot_items, cates, tags));

		

		tmp.append("</div>");
		tmp.append("</div>");
		tmp.append("</section>");
		
		view.add(tmp.toString());
		return view;
	}
	
	public static StringBuilder viewSideBar(
			ArrayList<ArticleObject> newest_items, 
			ArrayList<ArticleObject> hot_items,
			ArrayList<CategoryObject> cates,
			HashMap<String, Integer> tags) {
		StringBuilder tmp = new StringBuilder();
		tmp.append("<div class=\"col-md-3\">");
		tmp.append("<!-- ======= Sidebar ======= -->");
		tmp.append("<div class=\"aside-block\">");

		tmp.append("<ul class=\"nav nav-pills custom-tab-nav mb-4\" id=\"pills-tab\" role=\"tablist\">");
		tmp.append("<li class=\"nav-item\" role=\"presentation\">");
		tmp.append("<button class=\"nav-link active\" id=\"pills-trending-tab\" data-bs-toggle=\"pill\" data-bs-target=\"#pills-trending\" type=\"button\" role=\"tab\" aria-controls=\"pills-trending\" aria-selected=\"true\">Xu hướng</button>");
		tmp.append("</li>");
		tmp.append("<li class=\"nav-item\" role=\"presentation\">");
		tmp.append("<button class=\"nav-link\" id=\"pills-latest-tab\" data-bs-toggle=\"pill\" data-bs-target=\"#pills-latest\" type=\"button\" role=\"tab\" aria-controls=\"pills-latest\" aria-selected=\"false\">Mới nhất</button>");
		tmp.append("</li>");
		tmp.append("</ul>");

		tmp.append("<div class=\"tab-content\" id=\"pills-tabContent\">");
		tmp.append("<!-- Trending -->");
		tmp.append("<div class=\"tab-pane fade show active\" id=\"pills-trending\" role=\"tabpanel\" aria-labelledby=\"pills-trending-tab\">");
		hot_items.forEach(item -> {
				tmp.append("<div class=\"post-entry-1 border-bottom\">");
				tmp.append("<div class=\"post-meta\"><span class=\"date\">"+item.getCategory_name()+"</span> <span class=\"mx-1\">&bullet;</span> <span>"+item.getArticle_created_date()+"</span></div>");
				tmp.append("<h2 class=\"mb-2\"><a href=\"/home/tin-tuc?id=\">"+item.getArticle_title()+"</a></h2>");
				tmp.append("<span class=\"author mb-3 d-block\">"+item.getArticle_author_name()+"</span>");
				tmp.append("</div>");
		});
		tmp.append("</div> <!-- End Trending -->");

		tmp.append("<!-- Latest -->");
		tmp.append("<div class=\"tab-pane fade\" id=\"pills-latest\" role=\"tabpanel\" aria-labelledby=\"pills-latest-tab\">");
		newest_items.forEach(item -> {
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
		tmp.append("<h3 class=\"aside-title\">Thể loại</h3>");
		tmp.append("<ul class=\"aside-links list-unstyled\">");
		cates.forEach(cate -> {
			tmp.append("<li><a href=\"/home/tin-tuc?cid="+cate.getCategory_id()+"\"><i class=\"bi bi-chevron-right\"></i> Business</a></li>");
		});
		tmp.append("</ul>");
		tmp.append("</div><!-- End Categories -->");

		tmp.append("<div class=\"aside-block\">");
		tmp.append("<h3 class=\"aside-title\">Tags</h3>");
		tmp.append("<ul class=\"aside-tags list-unstyled\">");
		
		tags.forEach((tag, number) -> {
			tmp.append("<li><a href=\"/home/tin-tuc/?tag="+tag+"\">"+tag+" ("+number+")</a></li>");
		});
		tmp.append("</ul>");
		tmp.append("</div><!-- End Tags -->");

		tmp.append("</div>");
		
		return tmp;
	}
	
	private static StringBuilder viewCateObtions(ArrayList<CategoryObject> cates, ArticleObject similar) {
		StringBuilder tmp = new StringBuilder();
		
		tmp.append("<div class=\"row align-items-center mb-5\" >");
		tmp.append("<div class=\"col-sm-2\" >");
		tmp.append("<h3 class=\"\">Thể loại:</h3>");
		tmp.append("</div>");
		
		tmp.append("<div class=\"col-sm-4\">");
		tmp.append("<form method=\"\" action=\"\">");
		tmp.append("<select class=\"form-select\" name=\"slcCateID\" id=\"slcCateID\">");
		tmp.append("<option value=\"0\">--chọn--</option>");
		cates.forEach(cate -> {
			if(cate.getCategory_id() == similar.getArticle_category_id()) {
				tmp.append("<option value=\"").append(cate.getCategory_id()).append("\" selected>");
			} else {
				tmp.append("<option value=\"").append(cate.getCategory_id()).append("\">");
			}
			tmp.append(cate.getCategory_name());
			tmp.append("</option>");
		});
		tmp.append("</select>");
		tmp.append("</form>");
		tmp.append("</div>");
		tmp.append("</div>");
		
		tmp.append("<script language=\"javascript\">");
		tmp.append("function refreshCache(e) {");
		tmp.append("let fn = e.target.form; ");
		tmp.append("let cateID = fn.slcCateID.value;");
		tmp.append("fn.method = 'post';");
		tmp.append("fn.action = `/home/tin-tuc?cid=${cateID}`;");
		tmp.append("fn.submit();");
		tmp.append("}");
		tmp.append("let fn = document.getElementById('slcCateID');");
		tmp.append("if(fn != null) {");
		tmp.append("fn.addEventListener('change', refreshCache);");
		tmp.append("}");
		tmp.append("</script>");
		return tmp;
	}
	
	private static String getPagination(String url, int totalRecord, short page, byte recordsPerPage) {
		// Nếu url chưa có tham số thì thêm ?, nếu có thì thêm &
		if(url.indexOf("?") != -1) {
			url += "&";
		} else {
			url += "?";
		}
		// Tính toán tổng số trang
		int countPages = totalRecord / recordsPerPage;
		if(totalRecord % recordsPerPage != 0)
			countPages++;
		if(page > countPages || page <= 0) {
			page = 1;
		}
		StringBuilder tmp = new StringBuilder();

		tmp.append("<div class=\"text-start py-4 row\" >");
		tmp.append("<div class=\"custom-pagination\">");
		tmp.append("<a class=\"prev\" href=\""+url+"page="+((page > 1) ? (page - 1) : page)+"\" ><span aria-hidden=\"true\">&laquo;</span></a>");

		// left current
		String leftCurrent = "";
		int count = 0;
		for(int i = page - 1; i > 0; i--) {
			leftCurrent = "<a  href=\""+url+"page="+i+"\">"+i+"</a>" + leftCurrent;
			if(++count >= 2) {
				break;
			}
		}
		if(page > 4) {
			leftCurrent = "<a  href=\""+url+"\" tabindex=\"-1\" aria-disabled=\"true\" ><span aria-hidden=\"true\">1</span></a></li>" + "<a class=\" disabled\" href=\"#\">...</a>" + leftCurrent;
		}
		tmp.append(leftCurrent);
		tmp.append("<a class=\"active\" href=\""+url+"page="+page+"\">"+page+"</a>");
		
		// right current
		String rightCurrent = "";
		count = 0;
		for(int i = page + 1; i < countPages; i++) {
			System.out.println(i);
			rightCurrent += "<a href=\""+url+"page="+i+"\">"+i+"</a>";
			if(++count >= 2) {
				break;
			}
		}
		if(countPages > page + 2) {
			rightCurrent += "<a class=\"recordsPerPage\" href=\"#\">...</a>";
		}
		tmp.append(rightCurrent);
		if(page != countPages) {
			tmp.append("<a  href=\""+url+"page="+countPages+"\" tabindex=\"-1\" aria-disabled=\"true\" ><span aria-hidden=\"true\">"+countPages+"</span></a>");
		}
		
		tmp.append("<a class=\"next\" href=\""+url+"page="+((page < countPages) ? (page + 1) : page)+"\" ><span aria-hidden=\"true\">&raquo;</span></a>");
		
		tmp.append("</div>");
		tmp.append("</div>");
		
		return tmp.toString();
	}
}
