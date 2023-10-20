<!-- 
file jsp chuyên dùng để lấy dữ liệu
 -->

<%@page import="jsoft.library.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jsoft.*, jsoft.object.*" %>
<%@ page import="java.util.*, org.javatuples.*" %>
<%@ page import="jsoft.home.article.*" %>

<%
	// Xác định tập kí tự cần lấy
	request.setCharacterEncoding("utf-8");

	// Lấy đường dẫn xác định vị trí
	String uri = request.getRequestURI();
	// cắt bỏ /home/ khỏi uri
	uri = uri.substring(6);
	// Nếu at = -1 thì là trang chủ, nếu không thì là trang con
	int at = uri.indexOf("/");
	// Tìm bộ quản lý kết nối
	ConnectionPool cp = (ConnectionPool)application.getAttribute("CPool");
	ArticleControl ac = new ArticleControl(cp);
	if(cp == null) {
		application.setAttribute("CPool", ac.getCP());
	}
	
	ArticleObject similar = new ArticleObject();
	similar.setArticle_section_id((short)2);
	short curPage = Utilities.getShortPage(request, "page");
	if(at != -1) { // Lấy cấu trúc trang con
		short cid = Utilities.getShortParam(request, "cid");
	
		// System.out.println(cid);
		similar.setArticle_category_id(cid);
		
		Quartet<ArticleObject, Short, Byte, Boolean> infors = new Quartet<>(similar, curPage, (byte)3, false);
		ArrayList<String> news = ac.viewNews(infors);
		if(news.size() > 0) {
			session.setAttribute("news", news.get(0));
		}
	} else {
		// giá trị truyền vào: đối tượng article, trang khởi đầu, số bài viết trên 1 trang
		Quartet<ArticleObject, Short, Byte, Boolean> infors = new Quartet<>(similar, curPage, (byte)5, true);
		ArrayList<String> postGrid = ac.viewPostGrid(infors);
		
		session.setAttribute("postGrid", postGrid);
	}
	
	
	ac.releaseConnection();
	
	// Gửi cấu trúc hiển thị vào phiên

	
%>