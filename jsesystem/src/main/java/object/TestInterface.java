package object;

public class TestInterface {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// có thể khai báo là CTX thay cho ND, ds phương thức ở các kiểu khác nhau là khác nhau
		ND nd1 = new NDImpl1(); 
		// có thể khai báo là CTX, CTH, GDS, BTTU thay cho ND, ds phương thức nd là ds phương thức của kiểu khai báo (CTX, CTH, GDS, BTTU)
		BTTU nd2 = new NDImpl2();
		

	}

}
