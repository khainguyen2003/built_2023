package jsoft.ads.user;

import jsoft.object.PatternObject;

public interface Pattern {
	public boolean addSection(PatternObject item);
	public boolean editSection(PatternObject item);
	public boolean delSection(PatternObject item);
	
	//Lấy 1 bản ghi qua id
	public Object getPattern(int id);
	
	//Lấy bản nhiều ghi
	public Object getPattern(PatternObject similar, int at, byte total);
}
