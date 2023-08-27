package object;

public class Employee extends Person {
	// Có thêm thuộc tính net(lương), position(vị trí)
	public static final float NET = 0;
	public static final String POSITION = "none";
	
	private float net;
	private String position;
	
	
	public Employee() {
		this(Employee.FIRSTNAME, Employee.LASTNAME, Employee.AGE, Employee.ADDRESS, Employee.NET, Employee.POSITION);
	}


	public Employee(String firstName, String lastName, byte age, Address address, float net, String position) {
		super(firstName, lastName, age, address);
		this.net = net;
		this.position = position;
	}


	public float getNet() {
		return net;
	}


	public String getPosition() {
		return position;
	}


	public void setNet(float net) {
		this.net = net;
	}


	public void setPosition(String position) {
		this.position = position;
	}


	@Override
	public String toString() {
		return "Employee [" + super.toString() + ", net=" + net + ", position=" + position + "]";
	}
	
	public static void main(String []args) {
		Address addr = new Address("Hà Nội", "Nguyên Xá", "32");
		Person employee1 = new Employee("Khai", "Nguyen Van", (byte)20, addr, (float)30, "Web developer");
		
		System.out.println(employee1.toString());
		
	}
	
}
