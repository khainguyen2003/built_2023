package jsoft.home.article;

import java.sql.ResultSet;
import java.util.ArrayList;

import org.javatuples.Quartet;

import jsoft.ShareControl;
import jsoft.object.ArticleObject;

public interface Article extends ShareControl{
	
	//Lấy 1 bản ghi qua id
	public ResultSet getArticle(int id);
	
	//Lấy bản nhiều ghi
	public ArrayList<ResultSet> getArticles(Quartet<ArticleObject, Short, Byte, Boolean> infors);
}
