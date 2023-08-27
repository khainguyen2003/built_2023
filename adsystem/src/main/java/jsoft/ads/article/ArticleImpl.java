package jsoft.ads.article;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jsoft.ConnectionPool;
import jsoft.ads.basic.BasicImpl;
import jsoft.object.ArticleObject;
import jsoft.object.ArticleObject;

public class ArticleImpl extends BasicImpl implements Article {

	public ArticleImpl(ConnectionPool cp) {
		super(cp, "Article");
	}

	@Override
	public boolean addArticle(ArticleObject item) {
		if (this.isExisting(item)) {
			return false;
		}
		StringBuilder sql = new StringBuilder();
				
		sql.append("INSERT INTO tblsection(");
		sql.append("article_title, article_summary, article_content, ");
		sql.append("article_created_date, article_last_modified, article_image, article_category_id, ");
		sql.append("article_section_id, article_visited, article_author_name, article_enable, article_url_link, ");
		sql.append("article_tag, article_title_en, article_summary_en, article_content_en, article_tag_en, ");
		sql.append("article_fee, article_isfee, article_delete, article_deleted_date, article_restored_date, ");
		sql.append("article_modified_author_name, article_author_permission, article_source, ");
		sql.append("article_language, article_focus, article_type, article_forhome)");
		sql.append("VALUE(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setString(1, item.getArticle_title());
			pre.setString(2, item.getArticle_summary());
			pre.setString(3, item.getArticle_content());
			pre.setString(4, item.getArticle_created_date());
			pre.setString(5, item.getArticle_last_modified());
			pre.setString(6, item.getArticle_image());
			pre.setShort(7, item.getArticle_category_id());
			pre.setShort(8, item.getArticle_section_id());
			pre.setShort(9, item.getArticle_visited());
			pre.setString(10, item.getArticle_author_name());
			pre.setBoolean(11, item.isArticle_enable());
			pre.setString(12, item.getArticle_url_link());
			pre.setString(13, item.getArticle_tag());
			pre.setString(14, item.getArticle_title_en());
			pre.setString(15, item.getArticle_summary_en());
			pre.setString(16, item.getArticle_content_en());
			pre.setString(17, item.getArticle_tag_en());
			pre.setInt(18, item.getArticle_fee());
			pre.setBoolean(19, item.isArticle_isfee());
			pre.setBoolean(20, item.isArticle_delete());
			pre.setString(21, item.getArticle_deleted_date());
			pre.setString(23, item.getArticle_restored_date());
			pre.setString(24, item.getArticle_modified_author_name());
			pre.setByte(25, item.getArticle_author_permission());
			pre.setString(26, item.getArticle_source());
			pre.setByte(27, item.getArticle_language());
			pre.setBoolean(28, item.isArticle_focus());
			pre.setByte(29, item.getArticle_type());
			pre.setBoolean(30, item.isArticle_forhome());
			return this.add(pre);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return false;
	}
	
	@Override
	public boolean editArticle(ArticleObject item) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE tblsection SET ");
		sql.append("article_title=?, ");
		sql.append("article_summary=?, ");
		sql.append("article_content=?, ");
		sql.append("article_created_date=?, ");
		sql.append("article_last_modified=?, ");
		sql.append("article_image=?, ");
		sql.append("article_category_id=?, ");
		sql.append("article_section_id=?, ");
		sql.append("article_visited=? ");
		sql.append("article_author_name=? ");
		sql.append("article_visited=? ");
		sql.append("article_author_name=? ");
		sql.append("article_enable=? ");
		sql.append("article_url_link=? ");
		sql.append("article_tag=? ");
		sql.append("article_title_en=? ");
		sql.append("article_summary_en=? ");
		sql.append("article_content_en=? ");
		sql.append("article_tag_en=? ");
		sql.append("article_fee=? ");
		sql.append("article_isfee=? ");
		sql.append("article_delete=? ");
		sql.append("article_deleted_date=? ");
		sql.append("article_restored_date=? ");
		sql.append("article_modified_author_name=? ");
		sql.append("article_author_permission=? ");
		sql.append("article_source=? ");
		sql.append("article_language=? ");
		sql.append("article_focus=? ");
		sql.append("article_type=? ");
		sql.append("article_forhome=? ");
		sql.append("WHERE article_id=?");
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setString(1, item.getArticle_title());
			pre.setString(2, item.getArticle_summary());
			pre.setString(3, item.getArticle_content());
			pre.setString(4, item.getArticle_created_date());
			pre.setString(5, item.getArticle_last_modified());
			pre.setString(6, item.getArticle_image());
			pre.setShort(7, item.getArticle_category_id());
			pre.setShort(8, item.getArticle_section_id());
			pre.setShort(9, item.getArticle_visited());
			pre.setString(10, item.getArticle_author_name());
			pre.setBoolean(11, item.isArticle_enable());
			pre.setString(12, item.getArticle_url_link());
			pre.setString(13, item.getArticle_tag());
			pre.setString(14, item.getArticle_title_en());
			pre.setString(15, item.getArticle_summary_en());
			pre.setString(16, item.getArticle_content_en());
			pre.setString(17, item.getArticle_tag_en());
			pre.setInt(18, item.getArticle_fee());
			pre.setBoolean(19, item.isArticle_isfee());
			pre.setBoolean(20, item.isArticle_delete());
			pre.setString(21, item.getArticle_deleted_date());
			pre.setString(23, item.getArticle_restored_date());
			pre.setString(24, item.getArticle_modified_author_name());
			pre.setByte(25, item.getArticle_author_permission());
			pre.setString(26, item.getArticle_source());
			pre.setByte(27, item.getArticle_language());
			pre.setBoolean(28, item.isArticle_focus());
			pre.setByte(29, item.getArticle_type());
			pre.setBoolean(30, item.isArticle_forhome());
			
			pre.setInt(31, item.getArticle_id());
			return this.edit(pre);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean delArticle(ArticleObject item) {
		if(!this.isExisting(item)) {
			return false;
		}
		String sql = "DELETE FROM tblsection WHERE section_id=?";
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			pre.setInt(1, item.getArticle_id());
			return this.del(pre);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return false;
	}

	public boolean isExisting(ArticleObject item) {
		boolean flag = false;
		String sql = "SELECT * FROM tblsection WHERE section_name='" + item.getArticle_title() + "'; ";
		ResultSet rs = this.gets(sql);
		if(rs != null) {
			try {
				if(rs.next()) {
					flag = true;
				}
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return flag;
	}
	
	@Override
	public ResultSet getArticle(int id) {
		String sql = "SELECT * FROM tblsection WHERE section_id=?";
		return this.get(sql, id);
	}

	@Override
	public ArrayList<ResultSet> getArticles(ArticleObject similar, int at, byte total) {
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT a.*, c.category_id, c.category_name, s.section_id, s.section_name FROM tblarticle a ");
		sql.append("LEFT JOIN tblcategory c ON a.article_category_id=c.category_id ");
		sql.append("LEFT JOIN tblsection ON s.section_id=c.category_section_id ");
		sql.append("");
		sql.append("ORDER BY a.article_id DESC");
		sql.append("LIMIT ").append(at).append(", ").append(total).append("; ");
		
		sql.append("SELECT COUNT(category_id) AS total FROM tblarticle; ");
		return this.getReList(sql.toString());
	}

}
