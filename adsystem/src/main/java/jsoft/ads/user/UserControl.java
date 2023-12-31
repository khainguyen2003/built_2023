package jsoft.ads.user;

import jsoft.*;
import jsoft.object.UserObject;

import java.util.*;
import org.javatuples.*;

public class UserControl {
	private UserModel um;

	public UserControl(ConnectionPool cp) {
		this.um = new UserModel(cp);
	}

	// Phương thức chia sẻ bộ quản lý kết nối
	// Ta chỉ nhìn được các phương thức của interface user. Muốn dùng các phương
	// thức của Basic thì phải extend interface Basic
	public ConnectionPool getCP() {
		return this.um.getCP();
	}

	public void releaseConnection() {
		this.um.releaseConnection();
	}

	public boolean addUser(UserObject item) {
		return this.um.addUser(item);
	}

	public boolean editUser(UserObject item, USER_EDIT_TYPE et) {
		return this.um.editUser(item, et);
	}

	public boolean delUser(UserObject item) {
		return this.um.delUser(item);
	}

	// Phương thức lấy user thông qua id
	public UserObject getUserObject(int id) {
		return this.um.getUserObject(id);
	}

	// Phương thức đăng nhập
	public UserObject getUserObject(String username, String userpass) {
		return this.um.getUserObject(username, userpass);
	}

	public Pair<ArrayList<UserObject>, Integer> getUserObjects(
			Quartet<UserObject, Short, Byte, USER_SORT_TYPE> infors) {
		// Lấy dữ liêu
		UserObject similar = infors.getValue0();
		short page = infors.getValue1();
		byte total = infors.getValue2();
		USER_SORT_TYPE ust = infors.getValue3();

		Pair<ArrayList<UserObject>, Integer> datas = this.um.getUserObjects(similar, page, total, ust);
		
		return datas;
	}

	public ArrayList<String> viewUsers(Quartet<UserObject, Short, Byte, USER_SORT_TYPE> infors) {
		// Lấy dữ liêu
		UserObject similar = infors.getValue0();
		short page = infors.getValue1();
		byte total = infors.getValue2();
		USER_SORT_TYPE ust = infors.getValue3();

		Pair<ArrayList<UserObject>, Integer> datas = this.um.getUserObjects(similar, page, total, ust);

		return UserLibrary.viewUser(datas, infors);
	}

	public static void main(String[] args) {
		UserControl uc = new UserControl(null);
		Quartet<UserObject, Short, Byte, USER_SORT_TYPE> infors = new Quartet<>(null, (short) 1, (byte) 10,
				USER_SORT_TYPE.NAME);

		ArrayList<String> view = uc.viewUsers(infors);

		// Trả về kết nối
		uc.releaseConnection();

		System.out.println(view);
	}
}
