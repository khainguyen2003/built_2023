package jsoft.ads.feedback;

import jsoft.object.FeedbackObject;

public interface Feedback {
	public boolean addSection(FeedbackObject item);
	public boolean editSection(FeedbackObject item);
	public boolean delSection(FeedbackObject item);
	
	//Lấy 1 bản ghi qua id
	public Object getFeedback(int id);
	
	//Lấy bản nhi�?u ghi
	public Object getFeedback(FeedbackObject similar, int at, byte total);
}
