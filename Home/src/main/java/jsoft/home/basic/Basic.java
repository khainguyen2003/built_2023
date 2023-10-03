package jsoft.home.basic;

import java.sql.*;

import jsoft.*;
import java.util.*;

public interface Basic extends ShareControl {

	// Phương thức lấy 1 kết quả
	public ResultSet get(String sql, int id);
	public ResultSet get(ArrayList<String> sql, String name, String pass);
	// Phương thức lấy nhiều kết quả
	public ResultSet gets(String sql);
	
	public ArrayList<ResultSet> getReList(String multiSelect);
	
}