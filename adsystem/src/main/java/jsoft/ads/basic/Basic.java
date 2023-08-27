package jsoft.ads.basic;

import java.sql.*;

import jsoft.*;
import java.util.*;

public interface Basic extends ShareControl {

	//PreparedStatement pre đã được biên dịch và đã truyền giá trị
	public boolean add(PreparedStatement pre);
	public boolean edit(PreparedStatement pre);
	public boolean del(PreparedStatement pre);
	
	// Phương thức lấy 1 kết quả
	public ResultSet get(String sql, int id);
	public ResultSet get(String sql, String name, String pass);
	// Phương thức lấy nhiều kết quả
	public ResultSet gets(String sql);
	
	public ArrayList<ResultSet> getReList(String multiSelect);
	
}