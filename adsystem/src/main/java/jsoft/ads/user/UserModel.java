package jsoft.ads.user;

import jsoft.*;
import jsoft.object.*;

import java.util.*;

import org.javatuples.Pair;

import java.sql.*;

public class UserModel {
	private User u;

	public UserModel(ConnectionPool cp) {
		this.u = new UserImpl(cp);
	}

	protected void finalize() throws Throwable {
		this.u = null;
	}

	// Phương thức chia sẻ bộ quản lý kết nối
	// Ta chỉ nhìn được các phương thức của interface user. Muốn dùng các phương
	// thức của Basic thì phải extend interface Basic
	public ConnectionPool getCP() {
		return this.u.getCP();
	}

	public void releaseConnection() {
		this.u.releaseConnection();
	}

	// ***********************Chuyen huong dieu khien tu User
	// Impl*****************************************
	public boolean addUser(UserObject item) {
		return this.u.addUser(item);
	}

	public boolean editUser(UserObject item, USER_EDIT_TYPE et) {
		return this.u.editUser(item, et);
	}

	public boolean delUser(UserObject item) {
		return this.u.delUser(item);
	}

	// ****************************************************************

	public UserObject getUserObject(int id) {
		// Gan gia tri khoi tao cho doi tuong UserObject
		UserObject item = null;

		// Lay ban ghi
		ResultSet rs = this.u.getUser(id);

		// Chuyen doi ban ghi thanh doi tuong
		if (rs != null) {
			try {
				if (rs.next()) {
					item = new UserObject();
					item.setUser_id(rs.getInt("user_id"));
					item.setUser_name(rs.getString("user_name"));
					item.setUser_fullname(rs.getString("user_fullname"));
					item.setUser_email(rs.getString("user_email"));
					item.setUser_address(rs.getString("user_address"));
					item.setUser_birthday(rs.getString("user_birthday"));
					item.setUser_homephone(rs.getString("user_homephone"));
					item.setUser_officephone(rs.getString("user_officephone"));
					item.setUser_mobilephone(rs.getString("user_mobilephone"));
					item.setUser_permission(rs.getByte("user_permission"));
					item.setUser_notes(rs.getString("user_notes"));
					item.setUser_logined(rs.getShort("user_logined"));
					item.setUser_job(rs.getString("user_job"));
					item.setUser_jobarea(rs.getString("user_jobarea"));
					item.setUser_parent_id(rs.getInt("user_parent_id"));
					item.setUser_deleted(rs.getBoolean("user_deleted"));
					item.setUser_images(rs.getString("user_image"));

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return item;
	}

	// Xử lý đăng nhập
	public UserObject getUserObject(String username, String userpass) {
		// Gan gia tri khoi tao cho doi tuong UserObject
		UserObject item = null;

		// Lay ban ghi
		ResultSet rs = this.u.getUser(username, userpass);

		// Chuyen doi ban ghi thanh doi tuong
		if (rs != null) {
			try {
				if (rs.next()) {
					item = new UserObject();
					item.setUser_id(rs.getInt("user_id"));
					item.setUser_name(rs.getString("user_name"));
					item.setUser_pass(rs.getString("user_pass"));
					item.setUser_fullname(rs.getString("user_fullname"));
					item.setUser_email(rs.getString("user_email"));
					item.setUser_address(rs.getString("user_address"));
					item.setUser_homephone(rs.getString("user_homephone"));
					item.setUser_officephone(rs.getString("user_officephone"));
					item.setUser_mobilephone(rs.getString("user_mobilephone"));
					item.setUser_permission(rs.getByte("user_permission"));
					item.setUser_deleted(rs.getBoolean("user_deleted"));
					item.setUser_parent_id(rs.getInt("user_parent_id"));
					item.setUser_logined(rs.getShort("user_logined"));
					item.setUser_images(rs.getString("user_image"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return item;
	}

	/** Phương thức lấy về danh sách user và tổng số bản ghi
	 * 
	 * @param similar: lưu trữ các giá trị để dùng làm điều kiện where khi truy nhập dữ liệu
	 * @param page trang hiện tại
	 * @param total tổng số bản ghi trên trang hiện tại
	 * @param type kiểu sắp xếp
	 * @return cặp danh sách users và tổng số bản ghi lấy được
	 */
	public Pair<ArrayList<UserObject>, Integer> getUserObjects(UserObject similar, short page, byte total,
			USER_SORT_TYPE type) {

		// Gan gia tri khoi tao cho doi tuong UserObject
		ArrayList<UserObject> items = new ArrayList<>();
		UserObject item = null;

		// Lay ban ghi
		int at = (page - 1) * total;
		ArrayList<ResultSet> res = this.u.getUsers(similar, at, total, type);
		ResultSet rs = res.get(0);

		// Chuyen doi ban ghi thanh doi tuong
		if (rs != null) {
			try {
				while (rs.next()) {
					item = new UserObject();
					item.setUser_id(rs.getInt("user_id"));
					item.setUser_name(rs.getString("user_name"));
					item.setUser_pass(rs.getString("user_pass"));
					item.setUser_fullname(rs.getString("user_fullname"));
					item.setUser_email(rs.getString("user_email"));
					item.setUser_address(rs.getString("user_address"));
					item.setUser_homephone(rs.getString("user_homephone"));
					item.setUser_officephone(rs.getString("user_officephone"));
					item.setUser_mobilephone(rs.getString("user_mobilephone"));
					item.setUser_permission(rs.getByte("user_permission"));
					item.setUser_last_modified(rs.getString("user_last_modified"));
					item.setUser_deleted(rs.getBoolean("user_deleted"));
					item.setUser_logined(rs.getShort("user_logined"));
					item.setUser_parent_id(rs.getInt("user_parent_id"));

					// Dua doi tuong vao tap hop
					items.add(item);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// Lây tổng số bản ghi
		int totalGlobal = 0;
		rs = res.get(1);
		if (rs != null) {
			try {
				if (rs.next()) {
					totalGlobal = rs.getInt("total");
				}
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return new Pair<>(items, totalGlobal);
	}

//	public static void main(String[] args) {
//		ConnectionPool cp = new ConnectionPoolImpl();
//		
//		UserModel um = new UserModel(cp);
//		
//		ArrayList<UserObject> items = um.getUserObjects(null, (short) 1, (byte) 20, USER_SORT_TYPE.FULLNAME);
//		
//		items.forEach(item -> System.out.println(item));
//	};

}
