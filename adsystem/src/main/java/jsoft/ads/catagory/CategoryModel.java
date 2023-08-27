package jsoft.ads.catagory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.javatuples.Pair;

import jsoft.*;
import jsoft.object.*;

public class CategoryModel {

	private Category c;

	public CategoryModel(ConnectionPool cp) {
		this.c = new CategoryImpl(cp, "category");
	}

	protected void finallize() throws Throwable {
		this.c = null;
	}
	public ConnectionPool getCP() {
		return this.c.getCP();
	}
	public void releaseConnection() {
		this.c.releaseConnection();
	}

	public boolean addCategory(CategoryObject item) {
		return this.c.addCategory(item);
	}

	public boolean editCategory(CategoryObject item) {
		return this.c.editCategory(item);
	}

	public boolean delCategory(CategoryObject item) {
		return this.c.delCategory(item);
	}

	public CategoryObject getCategory(int id) {
		CategoryObject item = new CategoryObject();

		ResultSet rs = this.c.getCategory(id);
		if (rs != null) {
			try {
				if (rs.next()) {
					item.setCategory_id(rs.getShort("category_id"));
					item.setCategory_name(rs.getString("category_name"));
					item.setCategory_section_id(rs.getShort("category_section_id"));
					item.setCategory_notes(rs.getString("category_notes"));
					item.setCategory_created_date(rs.getString("category_created_date"));
					item.setCategory_created_author_id(rs.getInt("category_created_author_id"));
					item.setCategory_last_modified(rs.getString("category_last_modified"));
					item.setCategory_manager_id(rs.getInt("category_manager_id"));
					item.setCategory_enable(rs.getBoolean("category_enable"));
					item.setCategory_delete(rs.getBoolean("category_delete"));
					item.setCategory_image(rs.getString("category_image"));
					item.setCategory_name_en(rs.getString("category_name_en"));
					item.setCategory_language(rs.getShort("category_language"));
				}
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return item;
	}

	// phương thức này trả về kiểu Pair của tuple. Pair chứa 2 đối tượng luôn đi với nhau nhưng không liên quan đến nhau
	public Pair<ArrayList<CategoryObject>, Integer> getCategories(CategoryObject similar, short page, byte total, CATEGORY_SORT_TYPE type) {
		ArrayList<CategoryObject> items = new ArrayList<>();
		
		int at = (page - 1) * total;
		ArrayList<ResultSet> res = this.c.getCategorys(similar, at, total, type);
		CategoryObject item;
		ResultSet rs = res.get(0);
		if (rs != null) {
			try {
				while (rs.next()) {
					item = new CategoryObject();
					item.setCategory_id(rs.getShort("category_id"));
					item.setCategory_name(rs.getString("category_name"));
					item.setCategory_section_id(rs.getShort("category_section_id"));
					item.setCategory_notes(rs.getString("category_notes"));
					item.setCategory_created_date(rs.getString("category_created_date"));
					item.setCategory_created_author_id(rs.getInt("category_created_author_id"));
					item.setCategory_last_modified(rs.getString("category_last_modified"));
					item.setCategory_manager_id(rs.getInt("category_manager_id"));
					item.setCategory_enable(rs.getBoolean("category_enable"));
					item.setCategory_delete(rs.getBoolean("category_delete"));
					item.setCategory_image(rs.getString("category_image"));
					item.setCategory_name_en(rs.getString("category_name_en"));
					item.setCategory_language(rs.getShort("category_language"));
					items.add(item);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		rs = res.get(1);
		if (rs != null) {
			try {
				if (rs.next()) {
					System.out.println("Tổng số category: total = " + rs.getInt("total"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		int all = 0;
		rs = res.get(1);
		if(rs != null) {
			try {
				if(rs.next()) {
					all = rs.getInt("total");
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}

		return new Pair<>(items, all);
	}

	public static void main(String[] args) {
		ConnectionPool cp = new ConnectionPoolImpl();
		CategoryModel category = new CategoryModel(cp);
		Pair<ArrayList<CategoryObject>, Integer> datas = category.getCategories(null, (short) 1, (byte) 100, CATEGORY_SORT_TYPE.CATEGORY_NAME);
		
		ArrayList<CategoryObject> list = datas.getValue0();
//		int total = datas.getValue1();
		
		list.forEach(item -> {
			System.out.print(list.indexOf(item) + " - ");
			System.out.println(item.getCategory_name());
		});
		
		System.out.println("Tổng số category: total = " + datas.getValue1());
	}
}
				