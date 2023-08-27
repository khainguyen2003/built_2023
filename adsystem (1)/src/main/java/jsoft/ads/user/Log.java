package jsoft.ads.user;

import jsoft.object.LogObject;

public interface Log {
	public boolean addSection(LogObject item);
	public boolean editSection(LogObject item);
	public boolean delSection(LogObject item);
	
	//Lấy 1 bản ghi qua id
	public Object getLog(int id);
	
	//Lấy bản nhiều ghi
	public Object getLog(LogObject similar, int at, byte total);
}
