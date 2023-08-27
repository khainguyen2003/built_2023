package jsoft.ads.articleExtends;

import jsoft.object.*;

public interface Article_extends {
	public boolean addSection(Article_extendsObject item);
	public boolean editSection(Article_extendsObject item);
	public boolean delSection(Article_extendsObject item);
	
	// Lấy một bản ghi qua id
	public Object getArticle_extends(int id);
	
	//Lấy nhiều bản ghi qua id
	public Object getArticle_extends(Article_extendsObject similar, int at, byte total);
}
