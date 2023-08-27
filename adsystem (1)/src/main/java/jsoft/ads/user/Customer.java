package jsoft.ads.user;

import jsoft.object.CustomerObject;

public interface Customer {
	public boolean addSection(CustomerObject item);
	public boolean editSection(CustomerObject item);
	public boolean delSection(CustomerObject item);
	
	//Lấy 1 bản ghi qua id
	public Object getCustomer(int id);
	
	//Lấy bản nhiều ghi
	public Object getCustomer(CustomerObject similar, int at, byte total);
}
