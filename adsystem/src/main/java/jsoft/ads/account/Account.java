package jsoft.ads.account;

import jsoft.object.AccountObject;

public interface Account {
	public boolean addSection(AccountObject item);
	public boolean editSection(AccountObject item);
	public boolean delSection(AccountObject item);
	
	//Lấy 1 bản ghi qua id
	public Object getAccount(int id);
	
	//Lấy bản nhiều ghi
	public Object getAccounts(AccountObject similar, int at, byte total);
}
