package jsoft.ads.catagory;

import java.sql.*;
import java.util.*;

import jsoft.ConnectionPool;
import jsoft.ads.basic.BasicImpl;
import jsoft.object.CategoryObject;

public class CategoryImpl extends BasicImpl implements Category {

	public CategoryImpl(ConnectionPool cp, String objectName) {
		super(cp, objectName);
	}

	@Override
	public boolean addCategory(CategoryObject item) {
		if(isExisting(item)) {
			return false;
		}
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO tblcategory(");
		sql.append("category_name, category_section_id, category_notes, category_created_date, category_created_author_id, ");
		sql.append("category_last_modified, category_manager_id, category_enable, category_delete, category_image, ");
		sql.append("category_name_en, category_language)");
		sql.append("VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setString(1, item.getCategory_name());
			pre.setShort(2, item.getCategory_section_id());
			pre.setString(3, item.getCategory_notes());
			pre.setString(4, item.getCategory_created_date());
			pre.setInt(5, item.getCategory_created_author_id());
			pre.setString(6, item.getCategory_last_modified());
			pre.setInt(7, item.getCategory_manager_id());
			pre.setBoolean(8, item.isCategory_enable());
			pre.setBoolean(9, item.isCategory_delete());
			pre.setString(10, item.getCategory_image());
			pre.setString(11, item.getCategory_name_en());
			pre.setShort(12, item.getCategory_language());
			
			return this.add(pre);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean editCategory(CategoryObject item) {
		if(!isExisting(item)) {
			return false;
		}
		
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE tblcategory SET(");
		sql.append("category_name=?, category_section_id=?, category_notes=?, category_created_date=?, category_created_author_id=?, ");
		sql.append("category_last_modified=?, category_manager_id=?, category_enable=?, category_delete=?, category_image=?, ");
		sql.append("category_name_en=?, category_language=?) WHERE category_id=?");
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setString(1, item.getCategory_name());
			pre.setShort(2, item.getCategory_section_id());
			pre.setString(3, item.getCategory_notes());
			pre.setString(4, item.getCategory_created_date());
			pre.setInt(5, item.getCategory_created_author_id());
			pre.setString(6, item.getCategory_last_modified());
			pre.setInt(7, item.getCategory_manager_id());
			pre.setBoolean(8, item.isCategory_enable());
			pre.setBoolean(9, item.isCategory_delete());
			pre.setString(10, item.getCategory_image());
			pre.setString(11, item.getCategory_name_en());
			pre.setShort(12, item.getCategory_language());
			pre.setShort(13, item.getCategory_id());
			
			return this.edit(pre);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean delCategory(CategoryObject item) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM tblcategory WHERE category_id=? ");
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setShort(1, item.getCategory_id());
			
			return this.del(pre);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		return false;
	}
	
	public boolean isExisting(CategoryObject item) {
		boolean flag = false;
		String sql = "SELECT * FROM tblcategory WHERE category_name='" + item.getCategory_name() + "'; ";
		ResultSet rs = this.gets(sql);
		if(rs != null) {
			try {
				if(rs.next()) {
					flag = true;
				}
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return flag;
	}

	@Override
	public ResultSet getCategory(int id) {
		String sql = "SELECT * FROM tblcategory WHERE category_id=?";
		return this.get(sql, id);
	}

	@Override
	public ArrayList<ResultSet> getCategorys(CategoryObject similar, int at, byte total, CATEGORY_SORT_TYPE type) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM tblcategory ");
		switch(type) {
			case CATEGORY_NAME:
				sql.append("ORDER BY category_name ASC ");
				break;
			case CATEGORY_LAST_MODIFIED:
				sql.append("ORDER BY category_last_modified ASC ");
				break;
			default:
				sql.append("ORDER BY category_id ASC ");
		}
		sql.append("LIMIT " + at + ", " + total + "; ");
		sql.append("SELECT COUNT(category_id) AS total FROM tblcategory");
		return this.getReList(sql.toString());
	}

}
