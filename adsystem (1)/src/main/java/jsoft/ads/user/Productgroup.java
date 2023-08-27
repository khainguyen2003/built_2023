package jsoft.ads.user;

import jsoft.object.ProductgroupObject;

public interface Productgroup {
	public boolean addSection(ProductgroupObject item);
	public boolean editSection(ProductgroupObject item);
	public boolean delSection(ProductgroupObject item);
	
	//Lấy 1 bản ghi qua id
	public Object getProductgroup(int id);
	
	//Lấy bản nhiều ghi
	public Object getProductgroup(ProductgroupObject similar, int at, byte total);
}
