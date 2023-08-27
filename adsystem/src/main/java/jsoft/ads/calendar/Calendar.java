package jsoft.ads.calendar;

import jsoft.object.CalendarObject;

public interface Calendar {
	public boolean addSection(CalendarObject item);
	public boolean editSection(CalendarObject item);
	public boolean delSection(CalendarObject item);
	
	//Lấy 1 bản ghi qua id
	public Object getCalendar(int id);
	
	//Lấy bản nhiều ghi
	public Object getCalendar(CalendarObject similar, int at, byte total);
}
