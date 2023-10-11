package jsoft.home.article;

import jsoft.*;
import jsoft.library.Utilities;
import jsoft.object.*;
import java.util.*;
import java.util.stream.Collectors;
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
	/**
	 * Phương thức lấy 
	 * giá trị 4 là cặp tag và số lượng tag
	 */
	public Quartet<ArrayList<ArticleObject>, ArrayList<ArticleObject>, ArrayList<CategoryObject>, HashMap<String, Integer>> getNewsArticleObjects(Triplet<ArticleObject, Short, Byte> infors) {
		ArrayList<ResultSet> res = this.a.getArticles(infors);
		
		ArrayList<CategoryObject> cates = new ArrayList<>();
		CategoryObject cate = new CategoryObject();
		ResultSet rs = res.get(2);
		if(rs != null) {
			try {
				while(rs.next()) {
					cate = new CategoryObject();
					cate.setCategory_id(rs.getShort("category_id"));
					cate.setCategory_name(rs.getString("category_name"));
					cates.add(cate);
				}
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		HashMap<String, Integer> tags = new HashMap<>();
		rs = res.get(3);
		// Một bài viết sẽ có nhiều tag và giữa các tag sẽ phân biệt bằng dấu ,
		String tag;
		String[] tagList;
		if(rs != null) {
			try {
				while(rs.next()) {
					tag = Utilities.decode(rs.getString("article_tag")).toLowerCase();
					tagList = tag.split(",");
					for(String word : tagList) {
						// Viết "".equalsIgnoreCase(word) thay vì word.equalsIgnoreCase("") để tránh lỗi
						if(!"".equalsIgnoreCase(word)) {
							if(tags.containsKey(word)) {
								tags.replace(word, tags.get(word) + 1);
							} else {
								tags.put(word, 1);
							}
						}
					}
				}
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// Loại bỏ những phần tử có giá trị nhỏ hơn 3
		tags.keySet().removeAll(tags.entrySet().stream().filter(a -> a.getValue().compareTo(3) < 0)
				.map(e -> e.getKey()).collect(Collectors.toList()));
		
		// Thêm vào vị trí cuối
		return new Quartet<>(this.getArticleObjects(res.get(0)), this.getArticleObjects(res.get(1)), cates, tags);
	}
	
	private ArrayList<ArticleObject> getArticleObjects(ResultSet rs) {
		ArrayList<ArticleObject> items = new ArrayList<>();
		ArticleObject item = null;
		if(rs != null) {
			try {
				while(rs.next()) {
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
