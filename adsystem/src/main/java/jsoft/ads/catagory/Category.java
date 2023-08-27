package jsoft.ads.catagory;

import java.sql.ResultSet;
import java.util.ArrayList;

import jsoft.ShareControl;
import jsoft.object.CategoryObject;

public interface Category extends ShareControl{
	public boolean addCategory(CategoryObject item);
	public boolean editCategory(CategoryObject item);
	public boolean delCategory(CategoryObject item);
	
	//Lấy 1 bản ghi qua id
	public ResultSet getCategory(int id);
	
	//Lấy bản nhiều ghi
	public ArrayList<ResultSet> getCategorys(CategoryObject similar, int at, byte total, CATEGORY_SORT_TYPE type);
}
