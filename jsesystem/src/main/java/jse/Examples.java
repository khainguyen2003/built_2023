package jse;

public class Examples {
	/**
	 * Phương thức kiểm tra n có phải nguyên tố? </br>
	 * <i>Cập nhật ngày 12/02/2023</i>
	 * @author khai
	 * @param n -giá trị cần kiểm tra
	 * @return - Kết quả đúng hay sai
	 */
	// Từ khóa static gắn method isPrime với lớp đối tượng Examples
	public static boolean isPrime(int n) {
		boolean flag = true;
		if(n < 2) {
			flag = false;
		}else {
			for(int i = 2; i <= (int)Math.sqrt(n); i++) {
				if(n % i == 0) {
					flag = false;
					break;
				}
			}
		}
		return flag;
	}
	
	/**
	 * Phương thức tìm UCLN của 2 số nguyên dương</br>
	 * <i>Cập nhật ngày 12/02/2023</i>
	 * @author khai
	 * @param a - số thứ nhất
	 * @param b - số thứ 2
	 * @return - Kết quả là số nguyên hoặc -1 (Không tồn tại)
	 */
	public static int getUCLN(int a, int b) {
		int ucln = -1;
		if(a * b != 0) {
			while(a != b) {
				if(a > b) a -= b;
				else b -= a;
			}
			ucln = a;
		}
		
		return ucln;
	}
	
	/**
	 * Phương thức tìm UCLN của 3 số nguyên dương</br>
	 * <i>Cập nhật ngày 12/02/2023</i>
	 * @author khai
	 * @param a - số thứ nhất
	 * @param b - số thứ 2
	 * @param c - số thứ 3
	 * @return - Kết quả là số nguyên hoặc -1 (Không tồn tại)
	 */
	public static int getUCLM(int a, int b, int c) {
		return Examples.getUCLN(Examples.getUCLN(a, b), c);
	}
	
	public static void main(String[] args) {
		
		// Sinh ngaaux nhieen  gias trij n, m
		int n = (int)(Math.random()*100);
		int m = (int)(Math.random()*100);
		
		// in thông tin
		System.out.print("Gia trị n = " + n + " và m = " + m + " có UCLN là: " + getUCLN(n, m));
		
		// Khởi tạo đối tương thuộc lớp Examples
		// Examples e = new Examples();
		
		// boolean flag = e.isPrime(n);
		
		// Kiểm tra n có phải nguyên tố
//		if(Examples.isPrime(n)) {
//			System.out.print(" là số nguyên tố");
//		}else {
//			System.out.print(" không là số nguyên tố");
//		}	
	}

}
