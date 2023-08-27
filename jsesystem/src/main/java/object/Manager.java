package object;

public abstract class Manager {

	// Phương thức trìu tượng
	public abstract String getInfo(Person p);
	
	// Phương thức tường minh
	public String getDetails(Person p) {
		return this.getInfo(p);
	}
	
}
