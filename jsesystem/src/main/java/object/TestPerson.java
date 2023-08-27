package object;

public class TestPerson {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Address addr = new Address("Hà Nội", "Nguyên Xá", "32");
		
		Person s = new Student("Khai", "Nguyen Van", (byte) 20, addr, 2_021_605_672, "CNTT");
		System.out.println(s.toString());

		Person employee1 = new Employee("Khai", "Nguyen Van", (byte) 20, addr, (float) 30, "Web developer");
		System.out.println(employee1.toString());
	}

}
