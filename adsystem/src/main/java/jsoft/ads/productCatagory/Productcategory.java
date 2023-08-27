package jsoft.ads.productCatagory;

import jsoft.object.ProductcategoryObject;

public interface Productcategory {
	public boolean addSection(ProductcategoryObject item);
	public boolean editSection(ProductcategoryObject item);
	public boolean delSection(ProductcategoryObject item);
	
	//Lấy 1 bản ghi qua id
	public Object getProductcategoryt(int id);
	
	//Lấy bản nhi�?u ghi
	public Object getProductcategory(ProductcategoryObject similar, int at, byte total);
}