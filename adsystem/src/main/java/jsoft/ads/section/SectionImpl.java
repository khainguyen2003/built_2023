package jsoft.ads.section;

import jsoft.*;
import jsoft.ads.basic.BasicImpl;
import jsoft.object.SectionObject;
import jsoft.object.UserObject;

import java.util.*;

import org.javatuples.Quartet;

import java.sql.*;

public class SectionImpl extends BasicImpl implements Section {

	public SectionImpl(ConnectionPool cp, String objectName) {
		super(cp, objectName);
	}

	@Override
	public boolean addSection(SectionObject item) {
		if (this.isExisting(item)) {
			return false;
		}
		String sql = "INSERT INTO tblsection(";
		sql += "section_name, section_notes, section_created_date, ";
		sql += "section_manager_id, section_enable, section_delete, ";
		sql += "section_last_modified, section_created_author_id, ";
		sql += "section_name_en, section_language) ";
		sql += "VALUE(?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			pre.setString(1, item.getSection_name());
			pre.setString(2, item.getSection_notes());
			pre.setString(3, item.getSection_created_date());
			pre.setInt(4, item.getSection_manager_id());
			pre.setBoolean(5, item.isSection_enable());
			pre.setBoolean(6, item.isSection_delete());
			pre.setString(7, item.getSection_last_modified());
			pre.setInt(8, item.getSection_created_author_id());
			pre.setString(9, item.getSection_name_en());
			pre.setByte(10, item.getSection_language());
			return this.add(pre);
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
	
	public boolean isExisting(SectionObject item) {
		boolean flag = false;
		String sql = "SELECT * FROM tblsection WHERE section_name='" + item.getSection_name() + "'; ";
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
	public boolean editSection(SectionObject item) {
		String sql = "UPDATE tblsection SET ";
		sql += "section_name=?, ";
		sql += "section_notes=?, ";
		sql += "section_manager_id=?, ";
		sql += "section_enable=?, ";
		sql += "section_delete=?, ";
		sql += "section_last_modified=?, ";
		sql += "section_created_author_id=?, ";
		sql += "section_name_en=?, ";
		sql += "section_language=? ";
		sql += "WHERE section_id=?";
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			pre.setString(1, item.getSection_name());
			pre.setString(2, item.getSection_notes());
			pre.setInt(3, item.getSection_manager_id());
			pre.setBoolean(4, item.isSection_enable());
			pre.setBoolean(5, item.isSection_delete());
			pre.setString(6, item.getSection_last_modified());
			pre.setInt(7, item.getSection_created_author_id());
			pre.setString(8, item.getSection_name_en());
			pre.setByte(9, item.getSection_language());
			pre.setInt(10, item.getSection_id());
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
	public boolean delSection(SectionObject item) {
		if(!this.isExisting(item)) {
			return false;
		}
		String sql = "DELETE FROM tblsection WHERE section_id=?";
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			pre.setInt(1, item.getSection_id());
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

	@Override
	public ResultSet getSection(int id) {
		String sql = "SELECT * FROM tblsection WHERE section_id=?";
		return this.get(sql, id);
	}

	@Override
	public ArrayList<ResultSet> getSections(Quartet<SectionObject, Short, Byte, UserObject> infors) {
		SectionObject similar = infors.getValue0();
		byte total = infors.getValue2();
		int at = (infors.getValue1() - 1) * total;
		UserObject user = infors.getValue3();
		
		StringBuilder sql = new StringBuilder();
		// Danh sách chuyên mục
		sql.append("SELECT * FROM tblsection ");
		sql.append("LEFT JOIN tbluser ON section_manager_id=user_id ");
		sql.append("ORDER BY section_id ASC ");
		sql.append("LIMIT " + at + ", " + total + "; ");
		// tổng số chuyên mục
		sql.append("SELECT COUNT(section_id) AS total FROM tblsection; ");
		// Danh sách Người sử dụng sẽ được cấp quyền quản lý, phụ thuộc theo tài khoản đăng nhập
		sql.append("SELECT * FROM tbluser WHERE ");
		sql.append("(user_permission<=").append(user.getUser_permission()).append(") AND (");
		// user đăng nhập là cha của người dùng lấy ra từ database hoặc là chính người dùng đó
		sql.append("(user_parent_id=").append(user.getUser_id()).append(") OR (user_id=").append(user.getUser_id()).append(")");
		sql.append("); ");
		return this.getReList(sql.toString());
	}
	
}