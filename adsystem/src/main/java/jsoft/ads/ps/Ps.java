package jsoft.ads.ps;

import jsoft.object.PsObject;

public interface Ps {
	public boolean addSection(PsObject item);
	public boolean editSection(PsObject item);
	public boolean delSection(PsObject item);
	
	//Lấy 1 bản ghi qua id
	public Object getPs(int id);
	
	//Lấy bản nhi�?u ghi
	public Object getPs(PsObject similar, int at, byte total);
}
