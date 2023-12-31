package jsoft.ads.user;

import java.sql.*;
import java.util.ArrayList;

import org.javatuples.Pair;

import jsoft.*;
import jsoft.ads.basic.*;

import jsoft.object.*;

public class UserImpl extends BasicImpl implements User {

	public UserImpl(ConnectionPool cp) {
		super(cp, "User");
	}

	@Override
	public boolean addUser(UserObject item) {
		if (this.isExisting(item)) {
			return false;
		}

		// Hàm md5 là hàm mã hóa
		String sql = "INSERT INTO tbluser(" + "user_name, user_pass, user_fullname, user_birthday, "
				+ "user_mobilephone, user_homephone, user_officephone, user_email, "
				+ "user_address, user_jobarea, user_job, user_position, user_applyyear, "
				+ "user_permission, user_notes, user_roles, user_logined, user_created_date, "
				+ "user_last_modified, user_last_logined, user_parent_id, user_actions" + ")"
				+ "VALUE(?,md5(?),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		// Biên dịch
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			pre.setString(1, item.getUser_name());
			pre.setString(2, item.getUser_pass());
			pre.setString(3, item.getUser_fullname());
			pre.setString(4, item.getUser_birthday());
			pre.setString(5, item.getUser_mobilephone());
			pre.setString(6, item.getUser_homephone());
			pre.setString(7, item.getUser_officephone());
			pre.setString(8, item.getUser_email());
			pre.setString(9, item.getUser_address());
			pre.setString(10, item.getUser_jobarea());
			pre.setString(11, item.getUser_job());
			pre.setString(12, item.getUser_position());
			pre.setShort(13, item.getUser_applyyear());
			pre.setByte(14, item.getUser_permission());
			pre.setString(15, item.getUser_notes());
			pre.setString(16, item.getUser_roles());
			pre.setShort(17, item.getUser_logined());
			pre.setString(18, item.getUser_created_date());
			pre.setString(19, item.getUser_last_modified());
			pre.setString(20, item.getUser_last_logined());
			pre.setInt(21, item.getUser_parent_id());
			pre.setByte(22, item.getUser_actions());

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

	// Phương thức ràng buộc sự duy nhất của user_name
	private boolean isExisting(UserObject item) {
		// Trường hợp giả định tài khoản chưa tồn tại
		boolean flag = false;

		String sql = "SELECT user_id FROM tbluser WHERE user_name='" + item.getUser_name() + "'";
		ResultSet rs = this.gets(sql);
		if (rs != null) {
			try {
				if (rs.next()) {
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
	public boolean editUser(UserObject item, USER_EDIT_TYPE et) {
		String sql = "UPDATE tbluser SET ";
		switch(et) {
			case GENERAL:
				sql += "user_fullname=?, user_birthday=?, "
						+ "user_mobilephone=?, user_homephone=?, user_officephone=?, user_email=?, "
						+ "user_address=?, user_jobarea=?, user_job=?, user_position=?, user_applyyear=?, "
						+ "user_notes=?, "
						+ "user_last_modified=?, user_actions=?, user_image=? ";
				break;
			case SETTINGS:
				sql += "user_permission=?, user_roles=? ";
				break;
			case PASS:
				sql += "user_pass=md5(?) ";
				break;
			case TRASH:
				sql+="user_deleted=1, user_last_modified=? ";
				break;
			}
		
		sql += "WHERE user_id=?";

		// Biên dịch
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			switch(et) {
			case GENERAL:				
				pre.setString(1, item.getUser_fullname());
				pre.setString(2, item.getUser_birthday());
				pre.setString(3, item.getUser_mobilephone());
				pre.setString(4, item.getUser_homephone());
				pre.setString(5, item.getUser_officephone());
				pre.setString(6, item.getUser_email());
				pre.setString(7, item.getUser_address());
				pre.setString(8, item.getUser_jobarea());
				pre.setString(9, item.getUser_job());
				pre.setString(10, item.getUser_position());
				pre.setLong(11, item.getUser_applyyear());			
				pre.setString(12, item.getUser_notes());
				pre.setString(13, item.getUser_last_modified());
				pre.setByte(14, item.getUser_actions());
				pre.setString(15, item.getUser_images());
				pre.setInt(16, item.getUser_id());
				
				break;
			case SETTINGS:
				pre.setLong(1, item.getUser_permission());
				pre.setString(2, item.getUser_roles());
				pre.setInt(3, item.getUser_id());
				break;
			case PASS:
				pre.setString(1, item.getUser_pass());
				pre.setInt(2, item.getUser_id());
				break;
			case TRASH:
				pre.setString(1, item.getUser_last_modified());
				pre.setInt(2, item.getUser_id());
				break;
			}

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
	public boolean delUser(UserObject item) {
		// TODO Auto-generated method stub
		if (!this.isEmpty(item)) {
			return false;
		}
		String sql = "DELETE FROM tbluser WHERE user_id=? ";

		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			pre.setInt(1, item.getUser_id());

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

	private boolean isEmpty(UserObject item) {
		boolean flag = true;

		// Kiểm tra user_name có phải author name trong bảng tblarticle hay không
//		String sql1 = "SELECT article_id FROM tblarticle WHERE article_author_name='" + item.getUser_name() + "'";
//		// Kiểm tra user có phải product_manager hay không
//		String sql2 = "SELECT product_id FROM tblproduct WHERE product_manager_id=?";
//		String sql3 = "SELECT user_id FROM tbluser WHERE user_parent_id=?";
//
//		ResultSet rs1 = this.gets(sql1);
//		ResultSet rs2 = this.get(sql2, item.getUser_id());
//		ResultSet rs3 = this.get(sql3, item.getUser_id());
//
//		if (rs1 != null || rs2 != null || rs3 != null) {
//			try {
//				if (rs1.next() || rs2.next() || rs3.next()) {
//					flag = false;
//				}
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT article_id FROM tblarticle WHERE article_author_name=" + item.getUser_name() + "; ");
		sql.append("SELECT product_id FROM tblproduct WHERE product_manager_id=" + item.getUser_id() + "; ");
		sql.append("SELECT user_id FROM tbluser WHERE user_parent_id=" + item.getUser_id() + ";");
		
		ArrayList<ResultSet> res = this.getReList(sql.toString());
		
		for(ResultSet rs : res) {
			try {
				if(rs != null && rs.next()) {
					flag = false;
					break;
				}
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}

		return flag;
	}

	@Override
	public ResultSet getUser(int id) {
		String sql = "SELECT * FROM tbluser WHERE (user_id=?) AND (user_deleted=0)";

		return this.get(sql, id);
	}

	@Override
	public ResultSet getUser(String userName, String userPass) {  
		String sqlSelect = "SELECT * FROM tbluser WHERE (user_name = ?) AND (user_pass = md5(?)) AND (user_deleted=0) ";
		String sqlUpdate = "UPDATE tbluser SET user_logined = user_logined + 1 WHERE (user_name=?) AND (user_pass = md5(?)) AND (user_deleted=0) ";
		ArrayList<String> sql = new ArrayList<>();
		sql.add(sqlSelect);
		sql.add(sqlUpdate);
		return this.get(sql, userName, userPass);
	}

	// Phương thức này có thể thực hiện nhều câu lệnh cùng lúc
	// similar là đối tượng tương tự
	@Override
	public ArrayList<ResultSet> getUsers(UserObject similar, int at, byte total, USER_SORT_TYPE type) {

		String sql = "SELECT * FROM tbluser ";
		String sql2 = "SELECT COUNT(user_id) AS total FROM tbluser ";
		sql2 += this.createConditions(similar);
		sql += this.createConditions(similar);
		switch (type) {
			case NAME:
				sql += "ORDER BY user_name ASC ";
				break;
			case FULLNAME:
				sql += "ORDER BY user_fullname ASC ";
				break;
			case ADDRESS:
				sql += "ORDER BY user_address ASC ";
				break;
			case CREATED:
				sql += "ORDER BY user_created_date DESC ";
				break;
			case MODIFIED:
				sql += "ORDER BY user_last_modified DESC ";
				break;
			default:
				sql += "ORDER BY user_id ASC ";
		}
		if(total > 0) {
			sql += "LIMIT " + at + ", " + total + "; ";
		} else {
			sql += ";";
		}
		
		StringBuilder multiSelect = new StringBuilder();
		multiSelect.append(sql);
		multiSelect.append(sql2);
		
		return this.getReList(multiSelect.toString());
	}
	
	private String createConditions(UserObject similar) {
		StringBuilder conds = new StringBuilder();
		if(similar !=  null) {
			// Tài khoản đăng nhập truyền cho
			byte permis = similar.getUser_permission();
			conds.append("(user_permission<=").append(permis).append(")");
			// Trường hợp tài khoản không phải admin
			if(permis < 4) {
				// đây sẽ là các user do tài khoản này
				int id = similar.getUser_id();
				if(id > 0) {
					conds.append(" AND ((user_parent_id=").append(id).append(") OR (user_id=").append(id).append("))");
				}
			}
			
			// Từ khóa tìm kiếm
			String key = similar.getUser_name();
			if(key != null && !key.equalsIgnoreCase("")) {
				conds.append(" AND (");
				conds.append("(user_name LIKE '%"+key+"%') OR ");
				conds.append("(user_fullname LIKE '%"+key+"%') OR ");
				conds.append("(user_address LIKE '%"+key+"%') OR ");
				conds.append("(user_email LIKE '%"+key+"%') ");
				conds.append(")");
			}
			
			if(similar.isUser_deleted()) {
				conds.append(" AND (user_deleted=1) ");
			} else {
				conds.append(" AND (user_deleted=0) ");
			}
		}
		
		if(!conds.toString().equalsIgnoreCase("")) {
			conds.insert(0, " WHERE ");
		}
		
		return conds.toString();
	}

	public static void main(String[] args) {
		// Khởi tạo bộ quản lý kết nối
		ConnectionPool cp = new ConnectionPoolImpl();
		System.out.println(cp);

		// Tạo đối tượng thực thi chức năng mức User
		User u = new UserImpl(cp);

		// Đối tượng lưu trữ thông tin
//		UserObject nUser = new UserObject();
//		nUser.setUser_name("ad788899");
//		nUser.setUser_fullname("Nguyễn Văn Khải");
//		nUser.setUser_address("Hà Nội");
//		nUser.setUser_pass("1234");
//		nUser.setUser_email("quyet@gmail.com");
//		nUser.setUser_created_date("07/05/2023");
//		nUser.setUser_parent_id(20);

//		nUser.setUser_id(56);

//		boolean result = u.addUser(nUser);
//
//		if (!result) {
//			System.out.println("\n\n-------------------Không thành công--------------------\n");
//		}
		

		// Lấy tập bản ghi người sử dụng
		ArrayList<ResultSet> res = u.getUsers(null, 0, (byte) 10, USER_SORT_TYPE.NAME);

		ResultSet rs = res.get(0);
		// Duyệt vào hiển thị danh sách này
		String row;
		if (rs != null) {
			try {
				while (rs.next()) {
					row = "ID: " + rs.getInt("user_id");
					row += "\tNAME: " + rs.getString("user_name");
					row += "\tFULLNAME: " + rs.getString("user_fullname");
					row += "\tPASS: " + rs.getString("user_pass");
					row += "\tLOGINED: " + rs.getShort("user_logined");

					System.out.println(row);
				}
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		rs = res.get(1);
		if(rs != null) {
			try {
				if(rs.next()) {
					System.out.println("Tổng số Người sử dụng: total = " + rs.getInt("total"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
