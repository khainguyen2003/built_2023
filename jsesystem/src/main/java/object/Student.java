package object;

public class Student extends Person {
	private static final int STUDENT_ID = 0;
	private static final String STUDENT_SPECIALY = "none";
	
	
	//	Student's properties
	private int student_id;
	private String student_specialy;
	
	public Student() {
		this(Student.FIRSTNAME, Student.LASTNAME, Student.AGE, Student.ADDRESS, 
				Student.STUDENT_ID, Student.STUDENT_SPECIALY);
	}
	
	public Student(String firstName, String lastName, byte age, Address address, 
			int student_id, String student_specialy) {
		super(firstName, lastName, age, address);
		this.student_id = student_id;
		this.student_specialy = student_specialy;
	}

	public int getStudent_id() {
		return student_id;
	}

	public String getStudent_specialy() {
		return student_specialy;
	}

	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}

	public void setStudent_specialy(String student_specialy) {
		this.student_specialy = student_specialy;
	}
	
	@Override
	public String toString() {
		return "Student [" + super.toString() + ": SI = " + student_id + ", SS = " + student_specialy + "]";
	}
	
	public static void main(String[] args) {
		Address addr = new Address("Hà Nội", "Nguyên Xá", "32");
		// Person là cha của lớp Student nên có thể khai váo theo kiểu up cating A a = new B() (A là cha của B) 
		// không được khai báo ngược lại(B a = new A());
		// Kĩ thuật này sẽ ưu tiên trả về kết quả của các phương thức lớp con nếu phương thức đó trùng với
		// phương thức của cha, nếu lớp con không có thì trả về kq lớp cha
		// Kĩ thuật trên là một kĩ thuật đa hình trong java
		Person s = new Student("Khai", "Nguyen Van", (byte)20, addr, 2_021_605_672, "CNTT");
		
		System.out.println(s.toString());
	}
}
