package jsoft.home.article;

import jsoft.*;
import jsoft.object.*;
import java.util.*;
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
	public ArrayList<String> viewPostGrid(Quartet<ArticleObject, Short, Byte, Boolean> infors) {
		Pair<ArrayList<ArticleObject>, ArrayList<ArticleObject>> datas = this.am.getArticleObjects(infors);
		
		return ArticleLibrary.viewPostGrid(datas);
	}
	
	public ArrayList<String> viewNews(Quartet<ArticleObject, Short, Byte, Boolean> infors) {
		Sextet<ArrayList<ArticleObject>, ArrayList<ArticleObject>, ArrayList<CategoryObject>, HashMap<String, Integer>, Integer, ArrayList<ArticleObject>> datas = this.am.getNewsArticleObjects(infors);
		
		// cho infors để vừa phân trang, vửa định vị ví combobook
		return ArticleLibrary.viewNews(datas, infors);
	}
	
}
