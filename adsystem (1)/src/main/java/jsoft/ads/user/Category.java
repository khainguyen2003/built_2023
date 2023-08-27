package jsoft.ads.user;

import jsoft.object.CategoryObject;

public interface Category {
	public boolean addSection(CategoryObject item);
	public boolean editSection(CategoryObject item);
	public boolean delSection(CategoryObject item);
	
	//Lấy 1 bản ghi qua id
	public Object getCategory(int id);
	
	//Lấy bản nhiều ghi
	public Object getCategory(CategoryObject similar, int at, byte total);
}
