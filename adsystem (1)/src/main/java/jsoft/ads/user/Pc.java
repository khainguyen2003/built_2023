package jsoft.ads.user;

import jsoft.object.PcObject;

public interface Pc {
	public boolean addSection(PcObject item);
	public boolean editSection(PcObject item);
	public boolean delSection(PcObject item);
	
	//Lấy 1 bản ghi qua id
	public Object getPc(int id);
	
	//Lấy bản nhiều ghi
	public Object getPc(PcObject similar, int at, byte total);
}
