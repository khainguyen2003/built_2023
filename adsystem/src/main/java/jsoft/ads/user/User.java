package jsoft.ads.user;

import java.sql.*;

import jsoft.ShareControl;
import jsoft.object.*;
import java.util.*;

public interface User extends ShareControl {
	public boolean addUser(UserObject item);

	public boolean editUser(UserObject item, USER_EDIT_TYPE et);

	public boolean delUser(UserObject item);

	// Lấy 1 bản ghi qua id
	public ResultSet getUser(int id);

	// Lấy bản ghi qua ten và pass
	public ResultSet getUser(String userName, String userPass);

	// Lấy bản nhiều ghi với các đối tượng có giá trị tương đương hoặc gần giống
	// thuộc tính truyền vào
	public ArrayList<ResultSet> getUsers(UserObject similar, int at, byte total, USER_SORT_TYPE type);
	
//	Nếu một dối tượng có nhiều hơn một số thuộc tính so với số trường của bảng đối tượng đó biểu diễn thì đó được gọi là hybrid hóa đối tượng
}
