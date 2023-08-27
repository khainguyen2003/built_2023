package jsoft.ads.user;

import jsoft.object.ComputerObject;

public interface Computer {
	public boolean addSection(ComputerObject item);
	public boolean editSection(ComputerObject item);
	public boolean delSection(ComputerObject item);
	
	//Lấy 1 bản ghi qua id
	public Object getComputert(int id);
	
	//Lấy bản nhiều ghi
	public Object getComputert(ComputerObject similar, int at, byte total);
}
