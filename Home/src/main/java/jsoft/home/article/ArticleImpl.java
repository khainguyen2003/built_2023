package jsoft.home.article;

import java.sql.ResultSet;
import java.util.ArrayList;

import org.javatuples.Triplet;

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
	public synchronized ArrayList<ResultSet> getArticles(Triplet<ArticleObject, Short, Byte> infors) {
		ArticleObject similar = infors.getValue0();
		byte totalPerPage = infors.getValue2();
		int at = (infors.getValue1() - 1) * totalPerPage;
		// Danh sách bài viết xem mới nhất
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM tblarticle ");
		sql.append("LEFT JOIN tblcategory ON article_category_id=category_id ");
		sql.append("LEFT JOIN tblsection ON category_section_id=section_id ");
		sql.append("WHERE (article_delete=0) AND (article_enable=1) ");
		sql.append(this.createConditions(similar).toString());
		sql.append("ORDER BY article_id DESC ");
		sql.append("LIMIT ").append(at).append(", ").append(totalPerPage);
		
		// Danh sách bài viết xem nhiều nhất
		sql.append("SELECT * FROM tblarticle ");
		sql.append("LEFT JOIN tblcategory ON article_category_id=category_id ");
		sql.append("LEFT JOIN tblsection ON category_section_id=section_id ");
		sql.append("WHERE (article_delete=0) AND (article_enable=1) ");
		sql.append(this.createConditions(similar).toString());
		sql.append("ORDER BY article_visited DESC ");
		sql.append("LIMIT ").append(at).append(", ").append(totalPerPage);
		
		return this.getReList(sql.toString());
	}
	
	private StringBuilder createConditions(ArticleObject similar) {
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
				tmp.append("(article_section_id=").append(sid).append(")");
			}
			
			short cid = similar.getArticle_category_id();
			if(cid == 0) {
				cid = similar.getCategory_id();
			}
			if(cid > 0) {
				if(!tmp.toString().equals("")) {
					tmp.append(" AND ");
				}
				tmp.append("(article_category_id=").append(cid).append(")");
			}
		}
		if(!tmp.toString().equals("")) {
			tmp.insert(0, " AND ");
		}
		return tmp;
	}
	
}
