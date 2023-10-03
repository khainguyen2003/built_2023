package jsoft.ads.section;

import jsoft.ShareControl;
import jsoft.object.SectionObject;
import jsoft.object.UserObject;

import java.util.*;

import org.javatuples.Quartet;

import java.sql.*;

public interface Section extends ShareControl {
	public boolean addSection(SectionObject item);
	public boolean editSection(SectionObject item);
	public boolean delSection(SectionObject item);
	
	//Lấy 1 bản ghi qua id
	public ResultSet getSection(int id);
	
	//Lấy bản nhiều ghi
	public ArrayList<ResultSet> getSections(Quartet<SectionObject, Short, Byte, UserObject> infors);
}
