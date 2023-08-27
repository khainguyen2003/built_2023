package object;

public class Person implements Comparable<Person>{

	// Constants
	public static final String FIRSTNAME = "No Firstname";
	public static final String LASTNAME = "No Lastname";
	public static final byte AGE = 0;
	public static final Address ADDRESS = new Address();

	// Object's properties
	private String firstName;
	private String lastName;
	private byte age;
	private Address address;

	// contructor
	public Person() {
//		this.firstName = "No Firstname";
//		this.lastName = "No Lastname";
//		this.age = 0;

		this(Person.FIRSTNAME, Person.LASTNAME, Person.AGE, Person.ADDRESS);
	}

//	public Person(byte age) {
//		this(Person.FIRSTNAME, Person.LASTNAME, age);
//	}
//	public Person(String firstName, byte age) {
//		this(firstName, Person.LASTNAME, age);
//	}
	public Person(String firstName, String lastName, byte age, Address address) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		// Cách 1 - gán địa chỉ trong bộ nhớ
//		this.address = address;
		// Cách 2 - khởi tạo vùng bộ nhớ mới và nhận giá trị
		this.address = new Address(address);
	}
//	public Person(byte a, String f, String l) {
//		firstName = f;
//		lastName = l;
//		age = a;
//	}

	// Getter methods
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public byte getAge() {
		return age;
	}

	public Address getAddress() {
		return address;
	}

	// setter methods
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setAge(byte age) {
		this.age = age;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Person setAddress(String cityName, String districtName, String streetName) {
		this.address = new Address(cityName, districtName, streetName);
		return this;
	}

	// Other methods
	public String toString() {
		return lastName + " " + firstName + ", " + age + " tuổi - " + this.address.toString();
	}

	/**
	 * Phương thức xóa đối tượng <i>Cập nhật ngày 12/02/2023</i>
	 * 
	 * @author khai
	 */
	protected void finalize() throws Throwable {
		System.gc();
	}

	public static void main(String[] args) {
		// Khai báo và khởi tạo đối tượng
		Address addr = new Address("Hà Nội", "Nguyên Xá", "32");
		
		// Person p;
//		Person p1 = new Person();
//		Person p2 = new Person((byte) 20);
//		Person p3 = new Person("Khai", (byte) 20);
		Person p4 = new Person("Khai", "Nguyen", (byte) 20, addr);

//		System.out.println(p1);
//		System.out.println(p2);
//		System.out.println(p3);
		System.out.println(p4.toString());
	}

	@Override
	public int compareTo(Person o) {
		// TODO Auto-generated method stub
		return this.age - o.age;
	}
}
