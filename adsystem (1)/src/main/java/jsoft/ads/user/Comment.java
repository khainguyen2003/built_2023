package jsoft.ads.user;

import jsoft.object.CommentObject;

public interface Comment {
	public boolean addSection(CommentObject item);
	public boolean editSection(CommentObject item);
	public boolean delSection(CommentObject item);
	
	//Lấy 1 bản ghi qua id
	public Object getComment(int id);
	
	//Lấy bản nhiều ghi
	public Object getComment(CommentObject similar, int at, byte total);

}
