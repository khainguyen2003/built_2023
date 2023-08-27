package jsoft.ads.user;

import jsoft.object.AgentObject;
import jsoft.object.Article_extendsObject;

public interface Article_extends {
	public boolean addSection(Article_extendsObject item);
	public boolean editSection(Article_extendsObject item);
	public boolean delSection(Article_extendsObject item);
	
	//Lấy 1 bản ghi qua id
	public Object getArticle_extends(int id);
	
	//Lấy bản nhiều ghi
	public Object getArticle_extends(Article_extendsObject similar, int at, byte total);
}
