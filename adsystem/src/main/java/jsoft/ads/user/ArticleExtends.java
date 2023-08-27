package jsoft.ads.user;

import jsoft.object.*;

public interface ArticleExtends {
	public boolean addSection(ArticleExtendsObject item);
	public boolean editSection(ArticleExtendsObject item);
	public boolean delSection(ArticleExtendsObject item);
	
	// Lấy 1 bản ghi qua id
	public Object getArticleExtend(int id);
	// Lấy bản nhiều ghi
	public Object getArticleExtends(ArticleExtendsObject similar, int at, byte total);
}
