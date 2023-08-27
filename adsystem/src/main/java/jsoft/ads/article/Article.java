package jsoft.ads.article;

import java.sql.ResultSet;
import java.util.ArrayList;

import jsoft.ShareControl;
import jsoft.object.ArticleObject;

public interface Article extends ShareControl{
	public boolean addArticle(ArticleObject item);
	public boolean editArticle(ArticleObject item);
	public boolean delArticle(ArticleObject item);
	
	//Lấy 1 bản ghi qua id
	public ResultSet getArticle(int id);
	
	//Lấy bản nhiều ghi
	public ArrayList<ResultSet> getArticles(ArticleObject similar, int at, byte total);
}
