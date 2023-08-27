package jsoft.ads.user;

import jsoft.object.OrderObject;

public interface Order {
	public boolean addSection(OrderObject item);
	public boolean editSection(OrderObject item);
	public boolean delSection(OrderObject item);
	
	//Lấy 1 bản ghi qua id
	public Object getOrder(int id);
	
	//Lấy bản nhiều ghi
	public Object getOrder(OrderObject similar, int at, byte total);
}