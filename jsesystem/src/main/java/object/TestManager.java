package object;

public class TestManager {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Address addr = new Address("Hà Nội", "Nguyên Xá", "32");

		Person s = new Student("Khai", "Nguyen Van", (byte) 20, addr, 2_021_605_672, "CNTT");
		Person e = new Employee("Khai", "Nguyen Van", (byte) 20, addr, (float) 30, "Web developer");
		
		// -----------------------------
		Manager sm = new StudentManager();
		Manager em = new EmployeeManager();
		
		System.out.println(sm.getDetails(s));
		System.out.println(em.getDetails(e));
	}

}
