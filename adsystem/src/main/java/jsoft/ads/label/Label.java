package jsoft.ads.label;

import jsoft.object.LabelObject;

public interface Label {
	public boolean addSection(LabelObject item);
	public boolean editSection(LabelObject item);
	public boolean delSection(LabelObject item);
	
	//Lấy 1 bản ghi qua id
	public Object getLabel(int id);
	
	//Lấy bản nhi�?u ghi
	public Object getLabel(LabelObject similar, int at, byte total);
}
