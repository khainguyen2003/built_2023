package jsoft.ads.article;

import java.util.ArrayList;

import org.javatuples.Pair;
import org.javatuples.Triplet;

import jsoft.ConnectionPool;
import jsoft.object.ArticleObject;

public class ArticleControl {
	private ArticleModel am;
	
	public ArticleControl(ConnectionPool cp) {
		this.am = new ArticleModel(cp);
	}
	
	public ConnectionPool getCP() {
		return this.getCP();
	}
	public void releaseConnection() {
		this.am.releaseConnection();
	}
	
	public boolean addArticle(ArticleObject item) {
		return this.am.addArticle(item);
	}
	public boolean editArticle(ArticleObject item) {
		return this.am.editArticle(item);
	}
	public boolean delArticle(ArticleObject item) {
		return this.am.delArticle(item);
	}
	
	public ArrayList<String> viewCategories(Triplet<ArticleObject, Short, Byte> infors) {
		Pair<ArrayList<ArticleObject>, Integer> datas = this.am.getArticles(infors.getValue0(), infors.getValue1(), infors.getValue2());
		
		return ArticleLibrary.viewArticle(datas);
	}
}
