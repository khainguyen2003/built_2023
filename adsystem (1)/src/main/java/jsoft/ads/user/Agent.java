package jsoft.ads.user;

import jsoft.object.AgentObject;

public interface Agent {
	public boolean addSection(AgentObject item);
	public boolean editSection(AgentObject item);
	public boolean delSection(AgentObject item);
	
	//Lấy 1 bản ghi qua id
	public Object getAgent(int id);
	
	//Lấy bản nhiều ghi
	public Object getAgent(AgentObject similar, int at, byte total);
}
