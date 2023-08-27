package jsoft.ads.article;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.javatuples.Pair;

import jsoft.*;
import jsoft.object.*;

public class ArticleModel {

	private Article a;

	public ArticleModel(ConnectionPool cp) {
		this.a = new ArticleImpl(cp);
	}

	protected void finallize() throws Throwable {
		this.a = null;
	}
	public ConnectionPool getCP() {
		return this.a.getCP();
	}
	public void releaseConnection() {
		this.a.releaseConnection();
	}

	public boolean addArticle(ArticleObject item) {
		return this.a.addArticle(item);
	}

	public boolean editArticle(ArticleObject item) {
		return this.a.editArticle(item);
	}

	public boolean delArticle(ArticleObject item) {
		return this.a.delArticle(item);
	}

	public ArticleObject getArticle(int id) {
		ArticleObject item = new ArticleObject();

		ResultSet rs = this.a.getArticle(id);
		if (rs != null) {
			try {
				if (rs.next()) {
					item.setArticle_id(rs.getShort("article_id"));
					item.setArticle_title(rs.getString("article_title"));
					item.setArticle_summary(rs.getString("article_sumary"));
					item.setArticle_content(rs.getString("article_content"));
					item.setArticle_created_date(rs.getString("article_created_date"));
					item.setArticle_last_modified(rs.getString("article_last_modified"));
					item.setArticle_image(rs.getString("article_image"));
					item.setArticle_category_id(rs.getShort("article_category_id"));
					item.setArticle_section_id(rs.getShort("article_section_id"));
					item.setArticle_visited(rs.getShort("article_visited"));
					item.setArticle_author_name(rs.getString("article_author_name"));
					item.setArticle_enable(rs.getBoolean("article_enable"));
					item.setArticle_url_link(rs.getString("article_url_link"));
					item.setArticle_delete(rs.getBoolean("article_delete"));
					item.setArticle_last_modified(rs.getString("article_last_modified"));
					item.setArticle_language(rs.getByte("article_language"));
					
//					item.setCategory_id(rs.getShort("article_id"));
//					item.setCategory_name(rs.getString("article_name"));
//					
//					item.setSection_id(rs.getShort("article_id"));
//					item.setSection_name(rs.getString("article_name"));
				}
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return item;
	}

	// phương thức này trả về kiểu Pair của tuple. Pair chứa 2 đối tượng luôn đi với nhau nhưng không liên quan đến nhau
	public Pair<ArrayList<ArticleObject>, Integer> getArticles(ArticleObject similar, short page, byte total) {
		ArrayList<ArticleObject> items = new ArrayList<>();
		
		int at = (page - 1) * total;
		ArrayList<ResultSet> res = this.a.getArticles(similar, at, total);
		ArticleObject item;
		ResultSet rs = res.get(0);
		if (rs != null) {
			try {
				while (rs.next()) {
					item = new ArticleObject();
					item.setArticle_id(rs.getShort("article_id"));
					item.setArticle_title(rs.getString("article_title"));
					item.setArticle_summary(rs.getString("article_sumary"));
					item.setArticle_content(rs.getString("article_content"));
					item.setArticle_created_date(rs.getString("article_created_date"));
					item.setArticle_last_modified(rs.getString("article_last_modified"));
					item.setArticle_image(rs.getString("article_image"));
					item.setArticle_category_id(rs.getShort("article_category_id"));
					item.setArticle_section_id(rs.getShort("article_section_id"));
					item.setArticle_visited(rs.getShort("article_visited"));
					item.setArticle_author_name(rs.getString("article_author_name"));
					item.setArticle_enable(rs.getBoolean("article_enable"));
					item.setArticle_url_link(rs.getString("article_url_link"));
					item.setArticle_delete(rs.getBoolean("article_delete"));
					item.setArticle_last_modified(rs.getString("article_last_modified"));
					item.setArticle_language(rs.getByte("article_language"));
					
//					item.setCategory_id(rs.getShort("article_id"));
//					item.setCategory_name(rs.getString("article_name"));
//					
//					item.setSection_id(rs.getShort("article_id"));
//					item.setSection_name(rs.getString("article_name"));
					items.add(item);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		rs = res.get(1);
		if (rs != null) {
			try {
				if (rs.next()) {
					System.out.println("Tổng số section: total = " + rs.getInt("total"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		int all = 0;
		rs = res.get(1);
		if(rs != null) {
			try {
				if(rs.next()) {
					all = rs.getInt("total");
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}

		return new Pair<>(items, all);
	}

	public static void main(String[] args) {
		ConnectionPool cp = new ConnectionPoolImpl();
		ArticleModel section = new ArticleModel(cp);
		Pair<ArrayList<ArticleObject>, Integer> datas = section.getArticles(null, (short) 1, (byte) 100);
		
		ArrayList<ArticleObject> list = datas.getValue0();
		int total = datas.getValue1();
		
		list.forEach(item -> {
			System.out.print(list.indexOf(item) + " - ");
			System.out.println(item.getArticle_title());
		});
		
		System.out.println("Tổng số section: total = " + total);
	}
}
				