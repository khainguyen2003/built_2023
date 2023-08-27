package jsoft.ads.user;

import jsoft.object.StatisticObject;

public interface Statistic {
	public boolean addSection(StatisticObject item);
	public boolean editSection(StatisticObject item);
	public boolean delSection(StatisticObject item);
	
	//Lấy 1 bản ghi qua id
	public Object getStatistic(int id);
	
	//Lấy bản nhiều ghi
	public Object getStatistic(StatisticObject similar, int at, byte total);
}
