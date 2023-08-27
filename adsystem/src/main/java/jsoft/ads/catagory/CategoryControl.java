package jsoft.ads.catagory;

import java.util.ArrayList;

import org.javatuples.Pair;
import org.javatuples.Quartet;

import jsoft.ConnectionPool;
import jsoft.object.CategoryObject;

public class CategoryControl {
	private CategoryModel cm;
	
	public CategoryControl(ConnectionPool cp) {
		this.cm = new CategoryModel(cp);
	}
	
	public ConnectionPool getCP() {
		return this.getCP();
	}
	public void releaseConnection() {
		this.cm.releaseConnection();
	}
	
	public boolean addCategory(CategoryObject item) {
		return this.cm.addCategory(item);
	}
	public boolean editCategory(CategoryObject item) {
		return this.cm.editCategory(item);
	}
	public boolean delCategory(CategoryObject item) {
		return this.cm.delCategory(item);
	}
	
	public ArrayList<String> viewCategories(Quartet<CategoryObject, Short, Byte, CATEGORY_SORT_TYPE> infors) {
		Pair<ArrayList<CategoryObject>, Integer> datas = this.cm.getCategories(infors.getValue0(), infors.getValue1(), infors.getValue2(), infors.getValue3());
		
		return CategoryLibrary.viewCategory(datas);
	}
}
