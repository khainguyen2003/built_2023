package jsoft.home.article;

import jsoft.*;
import jsoft.object.*;
import java.util.*;
import java.sql.*;
import org.javatuples.*;

public class ArticleControl {
	private ArticleModel am;
	public ArticleControl(ConnectionPool cp) {
		this.am = new ArticleModel(cp);
	}
	
	public ConnectionPool getCP() {
		return this.am.getCP();
	}
	
	public void releaseConnection() {
		this.am.releaseConnection();
	}
	
	public ArticleObject getArticleObject(int id) {
		return this.am.getArticleObject(id);
	}
	// giá trị truyền vào: đối tượng article, trang khởi đầu, số bài viết trên 1 trang
	public ArrayList<String> viewPostGrid(Triplet<ArticleObject, Short, Byte> infors) {
		Pair<ArrayList<ArticleObject>, ArrayList<ArticleObject>> datas = this.am.getArticleObjects(infors);
		
		return ArticleLibrary.viewPostGrid(datas);
	}
	
	public ArrayList<String> viewNews(Triplet<ArticleObject, Short, Byte> infors) {
		Pair<ArrayList<ArticleObject>, ArrayList<ArticleObject>> datas = this.am.getArticleObjects(infors);
		
		return ArticleLibrary.viewNews(datas);
	}
}
