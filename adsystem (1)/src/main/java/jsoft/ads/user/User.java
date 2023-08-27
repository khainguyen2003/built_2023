package jsoft.ads.user;

import jsoft.object.UserObject;

public interface User {
	public boolean addSection(UserObject item);
	public boolean editSection(UserObject item);
	public boolean delSection(UserObject item);
	
	//Lấy 1 bản ghi qua id
	public Object getUser(int id);
	
	//Lấy bản ghi qua ten và pass
	public Object getUser(String userName, String userPass);
	
	//Lấy bản nhiều ghi
	public Object getUser(UserObject similar, int at, byte total);
}
