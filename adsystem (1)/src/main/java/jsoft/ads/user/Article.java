package jsoft.ads.user;

import jsoft.object.ArticleObject;

public interface Article {
	public boolean addSection(ArticleObject item);
	public boolean editSection(ArticleObject item);
	public boolean delSection(ArticleObject item);
	
	//Lấy 1 bản ghi qua id
	public Object getArticle(int id);
	
	//Lấy bản nhiều ghi
	public Object getArticle(ArticleObject similar, int at, byte total);
}
