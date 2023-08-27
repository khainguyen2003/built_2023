package jsoft.ads.user;

import jsoft.object.SectionObject;

public interface Section {
	public boolean addSection(SectionObject item);
	public boolean editSection(SectionObject item);
	public boolean delSection(SectionObject item);
	
	//Lấy 1 bản ghi qua id
	public Object getSection(int id);
	
	//Lấy bản nhiều ghi
	public Object getSection(SectionObject similar, int at, byte total);
}
