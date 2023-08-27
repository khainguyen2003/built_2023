package jsoft.ads.section;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.javatuples.Pair;

import jsoft.*;
import jsoft.object.*;

public class SectionModel {

	private Section s;

	public SectionModel(ConnectionPool cp) {
		this.s = new SectionImpl(cp, "section");
	}

	protected void finallize() throws Throwable {
		this.s = null;
	}
	
	// Phương thức chia sẻ bộ quản lý kết nối
	// Ta chỉ nhìn được các phương thức của interface user. Muốn dùng các phương thức của Basic thì phải extend interface Basic
	public ConnectionPool getCP() {
		return this.s.getCP();
	}
	
	public void releaseConnection() {
		this.s.releaseConnection();
	}

	public boolean addSection(SectionObject item) {
		return this.s.addSection(item);
	}

	public boolean editSection(SectionObject item) {
		return this.s.editSection(item);
	}

	public boolean delSection(SectionObject item) {
		return this.s.delSection(item);
	}

	public SectionObject getSection(int id) {
		SectionObject item = new SectionObject();

		ResultSet rs = this.s.getSection(id);
		if (rs != null) {
			try {
				if (rs.next()) {
					item.setSection_id(rs.getShort("section_id"));
					item.setSection_name(rs.getString("section_name"));
					item.setSection_notes(rs.getString("section_notes"));
					item.setSection_created_date(rs.getString("section_created_date"));
					item.setSection_manager_id(rs.getInt("section_manager_id"));
					item.setSection_enable(rs.getBoolean("section_enable"));
					item.setSection_delete(rs.getBoolean("section_delete"));
					item.setSection_last_modified(rs.getString("section_last_modified"));
					item.setSection_created_author_id(rs.getInt("section_created_author_id"));
					item.setSection_name_en(rs.getString("section_name_en"));
					item.setSection_language(rs.getByte("section_language"));
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
	// Section sẽ lấy về tập bản ghi và tổng số bản ghi được dùng để phân trang
	public Pair<ArrayList<SectionObject>, Integer> getSections(SectionObject similar, short page, byte total) {
		ArrayList<SectionObject> items = new ArrayList<>();
		
		int at = (page - 1) * total;
		ArrayList<ResultSet> res = this.s.getSections(similar, at, total);
		SectionObject item;
		ResultSet rs = res.get(0);
		if (rs != null) {
			try {
				while (rs.next()) {
					item = new SectionObject();
					item.setSection_id(rs.getShort("section_id"));
					item.setSection_name(rs.getString("section_name"));
					item.setSection_notes(rs.getString("section_notes"));
					item.setSection_created_date(rs.getString("section_created_date"));
					item.setSection_manager_id(rs.getInt("section_manager_id"));
					item.setSection_enable(rs.getBoolean("section_enable"));
					item.setSection_delete(rs.getBoolean("section_delete"));
					item.setSection_last_modified(rs.getString("section_last_modified"));
					item.setSection_created_author_id(rs.getInt("section_created_author_id"));
					item.setSection_name_en(rs.getString("section_name_en"));
					item.setSection_language(rs.getByte("section_language"));
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
					System.out.println("Tổng số section: total = " + rs.getInt("total"));
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
		SectionModel section = new SectionModel(cp);
		Pair<ArrayList<SectionObject>, Integer> datas = section.getSections(null, (short) 1, (byte) 100);
		
		ArrayList<SectionObject> list = datas.getValue0();
		int total = datas.getValue1();
		
		list.forEach(item -> {
			System.out.print(list.indexOf(item) + " - ");
			System.out.println(item.getSection_name());
		});
		
		System.out.println("Tổng số section: total = " + datas.getValue1());
	}
}
				