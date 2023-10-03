package jsoft.ads.section;

import jsoft.*;
import jsoft.object.*;
import java.util.*;
import org.javatuples.*;

public class SectionControl {
	public SectionModel sm;
	
	public SectionControl(ConnectionPool cp) {
		this.sm = new SectionModel(cp);
	}
	
	// Phương thức chia sẻ bộ quản lý kết nối
	// Ta chỉ nhìn được các phương thức của interface user. Muốn dùng các phương thức của Basic thì phải extend interface Basic
	public ConnectionPool getCP() {
		return this.sm.getCP();
	}
	
	public void releaseConnection() {
		this.sm.releaseConnection();
	}
	
	public boolean addSection(SectionObject item) {
		return this.sm.addSection(item);
	}
	public boolean editSection(SectionObject item) {
		return this.sm.editSection(item);
	}
	public boolean delSection(SectionObject item) {
		return this.sm.delSection(item);
	}
	
	public ArrayList<String> viewSection(Quartet<SectionObject, Short, Byte, UserObject> infors) {
		Quartet<ArrayList<SectionObject>, Integer, HashMap<Integer, String>, ArrayList<UserObject>> datas = this.sm.getSections(infors);
		
		return SectionLibrary.viewSection(datas);
	}
	
	public static void main(String[] args) {
		SectionControl sc = new SectionControl(null);
		Quartet<SectionObject, Short, Byte, UserObject> infors = new Quartet<>(null, (short)1, (byte)10, null);
		
		ArrayList<String> view = sc.viewSection(infors);
		sc.releaseConnection();
		System.out.println(view);
	}
}
