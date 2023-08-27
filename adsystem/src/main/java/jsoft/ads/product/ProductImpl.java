package jsoft.ads.product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jsoft.ConnectionPool;
import jsoft.ads.basic.BasicImpl;
import jsoft.object.ProductObject;

public class ProductImpl extends BasicImpl implements Product {

	public ProductImpl(ConnectionPool cp, String objectName) {
		super(cp, objectName);
	}

	@Override
	public boolean addSection(ProductObject item) {
		if(isExisting(item)) {
			return false;
		}
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO tblproduct(product_name, product_image, product_price, product_discount_price, product_enable, ");
		sql.append("product_delete, product_visited, product_total, product_manager_id, product_intro, product_notes, ");
		sql.append("product_code, product_created_date, product_modified_date, product_pc_id, product_pg_id, product_ps_id, ");
		sql.append("product_is_detail, product_deleted_date, product_promotion_price, product_sold, product_best_seller, ");
		sql.append("product_promotion, product_price_calc_description, product_size, product_name_en, product_customer_id, product_perspective_id ");
		sql.append("VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setString(1, item.getProduct_name());
			pre.setString(2, item.getProduct_image());
			pre.setInt(3, item.getProduct_price());
			pre.setInt(4, item.getProduct_discount_price());
			pre.setBoolean(5, item.isProduct_enable());
			pre.setBoolean(6, item.isProduct_delete());
			pre.setShort(7, item.getProduct_visited());
			pre.setShort(8, item.getProduct_total());
			pre.setShort(9, item.getProduct_total());
			pre.setInt(10, item.getProduct_manager_id());
			pre.setString(11, item.getProduct_intro());
			pre.setString(12, item.getProduct_notes());
			pre.setString(13, item.getProduct_code());
			pre.setString(14, item.getProduct_created_date());
			pre.setString(15, item.getProduct_modified_date());
			pre.setShort(16, item.getProduct_pc_id());
			pre.setShort(17, item.getProduct_pg_id());
			pre.setShort(18, item.getProduct_ps_id());
			pre.setBoolean(19, item.isProduct_is_detail());
			pre.setString(20, item.getProduct_deleted_date());
			pre.setInt(21, item.getProduct_promotion_price());
			pre.setInt(22, item.getProduct_sold());
			pre.setByte(23, item.getProduct_best_seller());
			pre.setByte(24, item.getProduct_promotion());
			pre.setShort(25, item.getProduct_price_calc_description());
			pre.setString(26, item.getProduct_size());
			pre.setString(27, item.getProduct_name_en());
			pre.setInt(28, item.getProduct_customer_id());
			pre.setInt(29, item.getProduct_perspective_id());
			
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

	@Override
	public boolean editSection(ProductObject item) {
		
		return false;
	}

	@Override
	public boolean delSection(ProductObject item) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean isExisting(ProductObject item) {
		boolean flag = false;
		String sql = "SELECT * FROM tblproduct WHERE product_name='" + item.getProduct_name() + "';";
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
	public ResultSet getProduct(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<ResultSet> getProducts(ProductObject similar, int at, byte total) {
		// TODO Auto-generated method stub
		return null;
	}

}
