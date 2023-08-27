package jsoft.ads.order;

import jsoft.object.OrderObject;

public interface Order {
	public boolean addSection(OrderObject item);
	public boolean editSection(OrderObject item);
	public boolean delSection(OrderObject item);
	
	//Lấy 1 bản ghi qua id
	public Object getOrder(int id);
	
	//Lấy bản nhi�?u ghi
	public Object getOrder(OrderObject similar, int at, byte total);
}