package jsoft.home.article;

import java.sql.ResultSet;
import java.util.ArrayList;

import org.javatuples.Quartet;

import jsoft.ConnectionPool;
import jsoft.home.basic.BasicImpl;
import jsoft.object.ArticleObject;

public class ArticleImpl extends BasicImpl implements Article {

	public ArticleImpl(ConnectionPool cp) {
		super(cp, "Article-Home");
	}

	@Override
	public ConnectionPool getCP() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void releaseConnection() {
		// TODO Auto-generated method stub

	}

	@Override
	public synchronized ResultSet getArticle(int id) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM tblarticle ");
		sql.append("LEFT JOIN tblcategory ON article_category_id=category_id ");
		sql.append("LEFT JOIN tblsection ON category_section_id=section_id ");
		sql.append("WHERE (article_id=?) AND (article_delete=0) AND (article_enable=1)");
		return this.get(sql.toString(), id);
	}

	@Override
	public synchronized ArrayList<ResultSet> getArticles(Quartet<ArticleObject, Short, Byte, Boolean> infors) {
		ArticleObject similar = infors.getValue0();
		byte totalPerPage = infors.getValue2();
		int at = (infors.getValue1() - 1) * totalPerPage;
		Boolean isDetail = infors.getValue3();
		
		// ds mới nhất và xem nhiều nhât truyền vào true vì 2 danh sách này không phụ thuộc vào select category
		// Danh sách bài viết xem mới nhất
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM tblarticle ");
		sql.append("LEFT JOIN tblcategory ON article_category_id=category_id ");
		sql.append("LEFT JOIN tblsection ON category_section_id=section_id ");
		sql.append("WHERE (article_delete=0) AND (article_enable=1)");
		sql.append(this.createConditions(similar, true).toString());
		sql.append("ORDER BY article_id DESC ");
		sql.append("LIMIT 5; ");
		
		// Danh sách bài viết xem nhiều nhất
		sql.append("SELECT * FROM tblarticle ");
		sql.append("LEFT JOIN tblcategory ON article_category_id=category_id ");
		sql.append("LEFT JOIN tblsection ON category_section_id=section_id ");
		sql.append("WHERE (article_delete=0) AND (article_enable=1)");
		sql.append(this.createConditions(similar, true).toString());
		sql.append("ORDER BY article_visited DESC ");
		sql.append("LIMIT 5; ");
		// Danh sách thể loại
		sql.append("SELECT * FROM tblcategory ");
		sql.append("WHERE (category_section_id=").append(similar.getArticle_section_id()).append(") ");
		sql.append("ORDER BY category_name ASC; ");
		
		// Danh sách các tag bài viết
		sql.append("SELECT article_tag FROM tblarticle ");
		sql.append("WHERE (article_section_id=").append(similar.getArticle_section_id()).append("); ");
		
		if (isDetail) {
			sql.append("SELECT * FROM tblarticle a ");
			sql.append("LEFT JOIN tblcategory c on a.article_category_id=c.category_id ");
			sql.append("LEFT JOIN tblsection s on c.category_section_id=s.section_id ");
			sql.append("");
			sql.append("WHERE (a.article_id="+similar.getArticle_id()+") AND (a.article_delete=0) AND (a.article_enable=1) ");
		} else {
			
			//Danh sách bài viết mới nhất theo phân trang
			sql.append("SELECT * FROM tblarticle a ");
			sql.append("LEFT JOIN tblcategory c on a.article_category_id=c.category_id ");
			sql.append("LEFT JOIN tblsection s on c.category_section_id=s.section_id ");
			sql.append("WHERE (a.article_delete=0) AND (a.article_enable=1)");
			sql.append(this.createConditions(similar,false));
			sql.append("");
			sql.append("ORDER BY a.article_id DESC ");
			sql.append("LIMIT ").append(at).append(", ").append(totalPerPage).append("; ");
			System.out.println(sql.toString());
			// Tổng số bài viết
			sql.append("SELECT count(article_id) AS total FROM tblarticle ");
			sql.append("WHERE (article_delete=0) AND (article_enable=1) ");
			sql.append(this.createConditions(similar, false)).append("; ");
		}
//		System.out.println("sql: " + sql.toString());
		return this.getReList(sql.toString());
	}
	
	private StringBuilder createConditions(ArticleObject similar, boolean isDesableCate) {
		StringBuilder tmp = new StringBuilder();
		
		if(similar != null) {
			short sid = similar.getArticle_section_id();
			if(sid == 0) {
				sid = similar.getCategory_section_id();
			}
			if(sid == 0) {
				sid = similar.getSection_id();
			}
			
			if(sid > 0) {
				tmp.append("(article_section_id=").append(sid).append(") ");
			}
			
			if(!isDesableCate) {
				short cid = similar.getArticle_category_id();
				if(cid == 0) {
					cid = similar.getCategory_id();
				}
				System.out.println("cid: " + cid);
				if(cid > 0) {
					if(!tmp.toString().equals("")) {
						tmp.append(" AND ");
					}
					tmp.append("(article_category_id=").append(cid).append(") ");
				}
			}
		}
		if(!tmp.toString().equals("")) {
			tmp.insert(0, " AND ");
		}
		return tmp;
	}
	
}
