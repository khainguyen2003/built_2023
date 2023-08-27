package jsoft.ads.product;

import java.sql.*;
import java.util.ArrayList;

import jsoft.object.ProductObject;

public interface Product {
	public boolean addSection(ProductObject item);
	public boolean editSection(ProductObject item);
	public boolean delSection(ProductObject item);
	
	//Lấy 1 bản ghi qua id
	public ResultSet getProduct(int id);
	
	//Lấy bản nhi�?u ghi
	public ArrayList<ResultSet> getProducts(ProductObject similar, int at, byte total);
}
