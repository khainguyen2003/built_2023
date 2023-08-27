package jsoft.ads.productSystem;

import jsoft.object.ProductsystemObject;

public interface Productsystem {
	public boolean addSection(ProductsystemObject item);
	public boolean editSection(ProductsystemObject item);
	public boolean delSection(ProductsystemObject item);
	
	//Lấy 1 bản ghi qua id
	public Object getProductsystem(int id);
	
	//Lấy bản nhi�?u ghi
	public Object getProductsystem(ProductsystemObject similar, int at, byte total);
}
