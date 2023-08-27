package jsoft.ads.user;

import jsoft.object.InteriorObject;

public interface Interior {
	public boolean addSection(InteriorObject item);
	public boolean editSection(InteriorObject item);
	public boolean delSection(InteriorObject item);
	
	//Lấy 1 bản ghi qua id
	public Object getInterior(int id);
	
	//Lấy bản nhiều ghi
	public Object getInterior(InteriorObject similar, int at, byte total);
}
