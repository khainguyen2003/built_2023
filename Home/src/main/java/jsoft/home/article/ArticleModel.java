package jsoft.home.article;

import jsoft.*;
import jsoft.object.*;
import java.util.*;
import java.sql.*;
import org.javatuples.*;
public class ArticleModel {
	private Article a;
	public ArticleModel(ConnectionPool cp) {
		this.a = new ArticleImpl(cp);
	}
	
	public ConnectionPool getCP() {
		return this.a.getCP();
	}
	
	public void releaseConnection() {
		this.a.releaseConnection();
	}
	
	public ArticleObject getArticleObject(int id) {
		ArticleObject item = null;
		ResultSet rs = this.a.getArticle(id);
		if(rs != null) {
			try {
				if(rs.next()) {
					item = new ArticleObject();
					item.setArticle_id(rs.getShort("article_id"));
					item.setArticle_title(rs.getString("article_title"));
					item.setArticle_summary(rs.getString("article_summary"));
					item.setArticle_content(rs.getString("article_content"));
					item.setArticle_image(rs.getString("article_image"));
					item.setArticle_created_date(rs.getString("article_created_date"));
					item.setArticle_last_modified(rs.getString("article_last_modified"));
					item.setArticle_author_name(rs.getString("article_author_name"));
					item.setArticle_tag(rs.getString("article_tag"));
					item.setCategory_id(rs.getShort("category_id"));
					item.setCategory_name(rs.getString("category_name"));
					
					item.setSection_id(rs.getShort("section_id"));
					item.setSection_name(rs.getString("section_name"));
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		
		return item;
	}
	// lấy về 1 cặp các article mới nhất và xem nhiều nhất
	public Pair<ArrayList<ArticleObject>, ArrayList<ArticleObject>> getArticleObjects(Triplet<ArticleObject, Short, Byte> infors) {
		ArrayList<ResultSet> res = this.a.getArticles(infors);
		
		return new Pair<>(this.getArticleObjects(res.get(0)), this.getArticleObjects(res.get(1)));
	}
	
	private ArrayList<ArticleObject> getArticleObjects(ResultSet rs) {
		ArrayList<ArticleObject> items = new ArrayList<>();
		ArticleObject item = null;
		if(rs != null) {
			try {
				if(rs.next()) {
					item = new ArticleObject();
					item.setArticle_id(rs.getShort("article_id"));
					item.setArticle_title(rs.getString("article_title"));
					item.setArticle_summary(rs.getString("article_summary"));
					item.setArticle_content(rs.getString("article_content"));
					item.setArticle_image(rs.getString("article_image"));
					item.setArticle_created_date(rs.getString("article_created_date"));
					item.setArticle_last_modified(rs.getString("article_last_modified"));
					item.setArticle_author_name(rs.getString("article_author_name"));
					item.setArticle_tag(rs.getString("article_tag"));
					
					item.setCategory_id(rs.getShort("category_id"));
					item.setCategory_name(rs.getString("category_name"));
					
					item.setSection_id(rs.getShort("section_id"));
					item.setSection_name(rs.getString("section_name"));
					
					items.add(item);
				}
			} catch (SQLException e) {
				System.out.println(e);
			}
		}
		
		
		
		return items;
	}
}
