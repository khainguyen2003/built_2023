package jsoft.home.basic;

import java.sql.*;
import java.util.ArrayList;

import jsoft.*;

public class BasicImpl implements Basic {

	// Bộ quản lý kết nối của riêng basic
	private ConnectionPool cp;

	// Kết nối để basic sử dụng
	protected Connection con;

	// Đối tượng làm việc với basic
	private String objectName;

	public BasicImpl(ConnectionPool cp, String objectName) {
		// Xác định đối tượng làm việc
		this.objectName = objectName;
		System.out.println(cp);

		// Xác định bộ quản lý kết nối
		if (cp == null) {
			this.cp = new ConnectionPoolImpl();
		} else {
			this.cp = cp;
		}

		// Xin kết nối để làm việc
		try {
			this.con = this.cp.getConnection(this.objectName);

			/**
			 * Kiểm tra kết nối có ở chế độ tự động kết nối hay không, nếu ở chế độ này thì
			 * sẽ set về false Việc thiết lập chế độ này là để đảm bảo tính toàn vẹn của
			 * giao dịch trong trường hợp xảy ra lỗi trong quá trình thực hiện các thao tác
			 * trên cơ sở dữ liệu. Nếu chế độ tự động commit được thiết lập và xảy ra lỗi,
			 * các thao tác trước đó sẽ được tự động commit vào cơ sở dữ liệu, gây mất mát
			 * dữ liệu hoặc phá vỡ tính toàn vẹn của dữ liệu.
			 */
			if (this.con.getAutoCommit()) {
				this.con.setAutoCommit(false);
			}

		} catch (SQLException e) {
			// in ra thông tin lỗi
			e.printStackTrace();
		}
	}

	@Override
	public ResultSet get(String sql, int id) {
		// Biên dịch câu lệnh
		try {
			// Tạo một đối tượng PreparedStatement để thực thi câu lệnh SQL được truyền vào.
			PreparedStatement pre = this.con.prepareStatement(sql);

			if (id > 0) {
				// Nếu id lớn hơn 0 thì sẽ thiết lập giá trị id cho tham số đầu tiên trong câu
				// lệnh sql
				pre.setInt(1, id);
			}
			// Nếu id <= 0 thì câu lệnh sql sẽ nhận tham số mặc định hoặc không có tham số
			// Câu lệnh sql sẽ trả về đối tượng 'ResultSEt nếu thành công hoặc null nếu lỗi
			return pre.executeQuery();

		} catch (SQLException e) {
			// in ra thông tin lỗi
			e.printStackTrace();

			try {
				// Hoàn tác lại các thay đổi trước đó
				this.con.rollback();
			} catch (SQLException e1) {
				// In ra thông tin lỗi
				e1.printStackTrace();
			}
		} finally {
			// giải phóng bộ nhớ
			sql = null;
		}

		return null;
	}

	/*
	 * ResultSet là một đối tượng trong Java JDBC được sử dụng để lưu trữ tập kết
	 * quả của một truy vấn SQL. ResultSet chứa các hàng (rows) và các cột (columns)
	 * của kết quả truy vấn.
	 */
	/** 
	 * Trong phương thức này, đầu tiên, câu lệnh SQL được biên dịch và tạo một đối
	 * tượng PreparedStatement để thực thi câu lệnh SQL. Sau đó, nếu có tham số name
	 * và pass được truyền vào, giá trị này được đặt vào vị trí thích hợp trong câu
	 * lệnh SQL.
	 * 
	 * Nếu thực thi truy vấn SQL không thành công, phương thức sẽ in ra thông tin
	 * lỗi và hoàn tác lại các thay đổi trước đó bằng cách gọi phương thức
	 * rollback(). Cuối cùng, phương thức giải phóng bộ nhớ và trả về một đối tượng
	 * ResultSet, chứa kết quả truy vấn, nếu truy vấn thành công.
	 * 
	 * Thay vì return ngay thì sẽ lấy tham chiếu của 1 query
	 */
	@Override
	public synchronized ResultSet get(ArrayList<String> sql, String name, String pass) {
		try {
			String sql_select = sql.get(0);
			PreparedStatement pre = this.con.prepareStatement(sql_select);

			pre.setString(1, name);
			pre.setString(2, pass);
			
			ResultSet rs = pre.executeQuery();
			if(rs != null) {
				String str_update = sql.get(1);
				PreparedStatement preUpdate = this.con.prepareStatement(str_update);
				preUpdate.setString(1, name);
				preUpdate.setString(2, pass);
				
				int result = preUpdate.executeUpdate();
				if(result == 0) {
					this.con.rollback();
					return null;
				} else {
					// khi có câu lệnh update, insert thì phải kiểm tra autocomit. nếu autocommit là false thì mới  commit
					if(!this.con.getAutoCommit()) {
						this.con.commit();
						
					}
					return rs;
				}
			}

		} catch (SQLException e) {
			// in thông tin lỗi
			e.printStackTrace();
			try {
				// Hoàn tác lại các cập nhật trước đó
				this.con.rollback();
			} catch (SQLException e1) {
				// in ra thông tin lỗi
				e1.printStackTrace();
			}
		} finally {
			// Giải phóng bộ nhớ
			name = null;
			pass = null;
		}

		return null;
	}

	/*
	 * ResultSet là một đối tượng trong Java JDBC được sử dụng để lưu trữ tập kết
	 * quả của một truy vấn SQL. ResultSet chứa các hàng (rows) và các cột (columns)
	 * của kết quả truy vấn.
	 */
	/**
	 * Phương thức `gets(String sql)` là một phương thức đơn giản của lớp
	 * `BasicImpl` trong Java. Phương thức này trả về kết quả truy vấn dữ liệu từ cơ
	 * sở dữ liệu thông qua câu lệnh SQL được truyền vào qua tham số `sql`. Phương
	 * thức này sử dụng lại phương thức `get(String sql, int id)` với tham số `id`
	 * được truyền vào là `0` để chỉ định rằng không có điều kiện lọc được sử dụng.
	 * Trong trường hợp phương thức `get(String sql, int id)` trả về giá trị `null`
	 * hoặc xảy ra lỗi, phương thức `gets(String sql)` cũng sẽ trả về giá trị
	 * `null`.
	 */
	@Override
	public ResultSet gets(String sql) {
		// TODO Auto-generated method stub
		return this.get(sql, 0);
	}

	@Override
	public ConnectionPool getCP() {
		// TODO Auto-generated method stub

		// Chia sẻ bộ quản lý kết nối
		return this.cp;
	}

	@Override
	public void releaseConnection() {
		// TODO Auto-generated method stub

		// ra lệnh trả về kết nối
		try {
			this.cp.releaseConnection(this.con, this.objectName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public ArrayList<ResultSet> getReList(String multiSelect) {
		// TODO Auto-generated method stub
		
		ArrayList<ResultSet> res = new ArrayList<>();
		try {
			PreparedStatement pre = this.con.prepareStatement(multiSelect);
			
			boolean result = pre.execute();
			do {
				// lấy ra tập kết quả
				res.add(pre.getResultSet());
				
				// Chuyển sang resultSet tiếp theo. 
				// getMoreResults() sẽ đóng 
				result = pre.getMoreResults(Statement.KEEP_CURRENT_RESULT);
			} while (result);
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
		return res;
	}

}