package jsoft.ads.user;

import jsoft.object.ProductObject;

public interface Product {
	public boolean addSection(ProductObject item);
	public boolean editSection(ProductObject item);
	public boolean delSection(ProductObject item);
	
	//Lấy 1 bản ghi qua id
	public Object getProduct(int id);
	
	//Lấy bản nhiều ghi
	public Object getProduct(ProductObject similar, int at, byte total);
}
