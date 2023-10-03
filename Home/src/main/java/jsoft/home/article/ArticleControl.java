package jsoft.home.article;

import jsoft.*;
import jsoft.object.*;
import java.util.*;
import java.sql.*;
import org.javatuples.*;

public class ArticleControl {
	private ArticleModel am;
	public ArticleControl(ConnectionPool cp) {
		this.am = new ArticleModel(cp);
	}
	
	public ConnectionPool getCP() {
		return this.am.getCP();
	}
	
	public void releaseConnection() {
		this.am.releaseConnection();
	}
	
	public ArticleObject getArticleObject(int id) {
		return this.am.getArticleObject(id);
	}
}
