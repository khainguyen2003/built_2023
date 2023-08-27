package jsoft.ads.basic;

public interface Basic {
	public boolean add();
	public boolean edit();
	public boolean del();
	
	public Object get(String sql, int id);
	public Object get(String sql, String name, String pass);
	public Object get(String sql);
}
