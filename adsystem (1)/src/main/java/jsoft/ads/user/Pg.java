package jsoft.ads.user;

import jsoft.object.PgObject;

public interface Pg {
	public boolean addSection(PgObject item);
	public boolean editSection(PgObject item);
	public boolean delSection(PgObject item);
	
	//Lấy 1 bản ghi qua id
	public Object getPg(int id);
	
	//Lấy bản nhiều ghi
	public Object getPg(PgObject similar, int at, byte total);
}
