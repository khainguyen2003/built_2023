package util;

import java.text.Collator;
import java.util.*;

import object.*;

public class MyArray {
	public static final String ALPHABET = "aàáảãạăằắẳẵặbcdđeèéẻẽẹêềếểễệghiìíỉĩịklmnoòóỏõọôồốổỗộơờớởỡợpqrstuùúủũụưừứửữựvxyỳýỷỹỵ";

	// Sinh mảng 1 chiều
	public static int[] generateArray(int n) {
		int[] arrInt = new int[n];
		for (int i = 0; i < arrInt.length; i++) {
			arrInt[i] = (int) (Math.random() * 100);
		}

		return arrInt;
	}

	// Sinh mảng 2 chiều
	public static int[][] generateArray(int rows, int cols) {
		int[][] arrInt = new int[rows][cols];

		for (int i = 0; i < rows; i++) {
			arrInt[i] = MyArray.generateArray(cols);
		}

		return arrInt;
	}

	// Sinh mảng Object
	public static Person[] generatePerson(int n) {
		Person[] list = new Person[n];

		// Danh sách tên
		String[] firstNames = { "Anh", "Bảo Anh", "Anh Bảo", "Hùng", "Huyền", "Trang", "Minh", "Hân", "Minh Anh",
				"Tuấn Anh", "Anh Tuấn", "Châu", "Châu Anh", "Minh Châu", "Thủy", "Đạt", "Hoàng", "Yến", "Yến Anh", "Vũ",
				"Khải", "Linh", "Huy", "Thọ", "Chiến", "Trường", "Huyền Anh", "Tùng", "Huyên", "Mai", "Hưởng", "Mạnh",
				"Tiến Anh" };

		String[] lastNames = { "Nguyễn", "Hoàng", "Lê", "Bùi", "Ngô", "Đoàn", "Đào", "Dương", "Hồ", "Trần", "Lương",
				"Ma", "Vương", "Phạm", "Phan", "Quách" };

		// Sinh mảng
		int index;
		for (int i = 0; i < n; i++) {
			// Cấp phát địa chỉ trong bộ nhớ cho phần tử mảng
			list[i] = new Person();

			// Ngẫu nhiên chỉ số để lấy tên trong firstName
			index = (int) (Math.random() * firstNames.length);
			list[i].setFirstName(firstNames[index]);

			// Ngẫu nhiên chỉ số để lấy họ trong lastName
			index = (int) (Math.random() * lastNames.length);
			list[i].setLastName(lastNames[index]);

			// Ngẫu nhiên tuổi
			index = 18 + (int) (Math.random() * 5);
			list[i].setAge((byte) index);

		}

		return list;
	}

	public static int[] bubbleSortArray(int[] arrInt, boolean isINC) {
		byte ori = (byte) (isINC ? 1 : -1);

		// Thực hiện sx
		int tmp;
		for (int i = 0; i < arrInt.length - 1; i++) {
			for (int j = arrInt.length - 1; j > i; j--) {
				if (arrInt[j] * ori < arrInt[j - 1] * ori) {
					tmp = arrInt[j];
					arrInt[j] = arrInt[j - 1];
					arrInt[j - 1] = tmp;
				}
			}
		}

		return arrInt;
	}

	public static Integer[] sortArrayV2(int[] arrInt, boolean isINC) {

		// Thực hiện sx
		Integer[] data = new Integer[arrInt.length];
		Arrays.setAll(data, i -> arrInt[i]);

		if (isINC) {
			Arrays.sort(data);
		} else {
			Arrays.sort(data, Collections.reverseOrder());
		}

		return data;
	}

	public static Integer[] sortArrayV2(Integer[] arrInt, boolean isINC) {

		if (isINC) {
			Arrays.sort(arrInt);
		} else {
			Arrays.sort(arrInt, Collections.reverseOrder());
		}

		return arrInt;
	}

	public static int[][] sortArray(int[][] arrInt, boolean isINC) {
		int rows = arrInt.length;
		int cols = arrInt[0].length;

		int[] tmp = new int[rows * cols];

		// Sao chép dữ liệu
		int r = 0, c = 0;
		for (int i = 0; i < tmp.length; i++) {
			tmp[i] = arrInt[r][c++];

			if (c == cols) {
				r++;
				c = 0;
			}
		}

		// Chuyển về mảng 2 chiều
		r = 0;
		c = 0;
		for (int i = 0; i < tmp.length; i++) {
			// kĩ thuật unboxing: 1 tp nguyên thủy gán giá trị của tp đtg
			arrInt[r][c++] = tmp[i];

			if (c == cols) {
				r++;
				c = 0;
			}
		}

		return arrInt;
	}

	/**
	 * Merge sort
	 * 
	 * @param list
	 * @param name
	 * @return
	 */
	/*
	 * public static void Merge(int[] arrInt, int left, int right, int mid, boolean
	 * isINC) { int ori = (isINC) ? 1 : -1; int size1 = mid - left + 1; int size2 =
	 * right - mid;
	 * 
	 * // Tạo mảng tạm int[] leftArr = new int[size1]; int[] rightArr = new
	 * int[size2];
	 * 
	 * // Sao chép dữ liệu sang mảng tạm for(int i = 0; i < size1; i++) { leftArr[i]
	 * = arrInt[i + left]; } for(int j = 0; j < size2; j++) { rightArr[j] = arrInt[j
	 * + mid + 1]; }
	 * 
	 * // Trộn 2 mảng tạm int i = 0, j = 0, k = left; while(i < size1 && j < size2)
	 * { if(leftArr[i] * ori <= rightArr[j] * ori) { arrInt[k] = leftArr[i]; i++;
	 * }else { arrInt[k] = rightArr[j]; j++; } k++; } while(i < size1) { arrInt[k] =
	 * leftArr[i]; i++; k++; } while(j < size2) { arrInt[k] = rightArr[j]; j++; k++;
	 * } } public static void MergeSort(int[] arrInt, int left, int right) { if(left
	 * < right) { int mid = left + (right - left) / 2; MergeSort(arrInt, left, mid);
	 * MergeSort(arrInt, mid + 1, right);
	 * 
	 * Merge(arrInt, left, right, mid, true); } }
	 */
	// End merge sort

	/**
	 * Quick sort
	 * 
	 * @param list
	 * @param name
	 * @return
	 */
	/*
	 * public static void swap(int[] arrInt, int i, int j) { int tmp = arrInt[i];
	 * arrInt[i] = arrInt[j]; arrInt[j] = tmp; }
	 * 
	 * public static int partition(int[] arrInt, int left, int right) { int pivot =
	 * arrInt[right], i = left - 1; for(int j = left; j < right; j++) { if(arrInt[j]
	 * < pivot) { i++; swap(arrInt, i, j); } } swap(arrInt, i + 1, right); return i
	 * + 1;
	 * 
	 * } public static void QuickSort(int[] arrInt, int left, int right) { if(left <
	 * right) { int p = partition(arrInt, left, right); QuickSort(arrInt, left, p -
	 * 1); QuickSort(arrInt, p + 1, right); } }
	 */
	// End Quick sort

	// Sắp xếp theo thứ tự theo tên của person
	/*
	 * public static void sortPersonByName(Person[] list, char[] alphabet){
	 * Arrays.sort(list, (p1, p2) -> { String name1 = p1.getFirstName(); String
	 * name2 = p2.getFirstName(); int index1 = name1.lastIndexOf(' '); int index2 =
	 * name2.lastIndexOf(' '); if(index1 != -1) { name1 = name1.substring(index1 +
	 * 1); } if(index2 != -1) { name2 = name2.substring(index2 + 1); } int
	 * compareName = name1.compareTo(name2); if(compareName == 0) { String lastName1
	 * = p1.getLastName(); String lastName2 = p2.getLastName(); return
	 * lastName1.compareTo(lastName2); }else { return compareName; } }); }
	 */

	/*
	 * // Tìm với mảng tĩnh public static Person[] searchPerson(Person[] list,
	 * String name) { // Khai báo mảng kq Person[] results = null;
	 * 
	 * // Đếm số kq int count = 0; for(Person p : list) {
	 * if(p.getFirstName().contains(name)) { count++; } }
	 * 
	 * // Khởi tạo mảng kết quả results = new Person[count];
	 * 
	 * // Ghi nhận kết quả count = 0; for(Person p : list) {
	 * if(p.getFirstName().contains(name)) { results[count++] = p; } }
	 * 
	 * return results; }
	 */

	// Tìm với Arraylist
	public static ArrayList<Person> searchPerson(Person[] list, String name) {
		// Mảng kết quả
		ArrayList<Person> results = new ArrayList<>();

		// Ghi nhận kết quả
		for (Person p : list) {
			if (p.getFirstName().contains(name)) {
				results.add(p);
			}
		}

		return results;
	}

	/**
	 * sắp xếp theo tuổi
	 * 
	 */
	public static ArrayList<Person> sortedByAgePerson(Person[] list, boolean isINC) {
		// chuyển mảng đôkis tượng sang tập hợp
		ArrayList<Person> tmp = new ArrayList<Person>();

		// sao chép phần tư
		Collections.addAll(tmp, list);

		// sắp xếp
		if (isINC) {
			Collections.sort(tmp);
		} else {
			Collections.sort(tmp, Collections.reverseOrder());
		}

		return tmp;
	}

	public static ArrayList<Person> sortedByNamePersonV3(Person[] list, boolean isINC) {
		// chuyển mảng đôkis tượng sang tập hợp
		ArrayList<Person> tmp = new ArrayList<Person>();

		// sao chép phần tư
		Collections.addAll(tmp, list);

		// Xác định điều kiện sắp xếp
		sortedByNamePersonV2 s = new sortedByNamePersonV2();

		if (isINC) {
			Collections.sort(tmp, s);
		} else {
			Collections.sort(tmp, s.reversed());
		}

		return tmp;
	}

	// In mảng 1 chiều
	public static void printArray(int[] arrInt) {
		// Cách 1 - C/C++
		/*
		 * for(int i = 0; i < n; i++) { System.out.print(arrInt[i] + " "); }
		 */

		// Cách 2 - jdk5
		/*
		 * for (int value : arrInt) { System.out.print(value + " "); }
		 */

		// Cách 3 - sử dụng Arrays (chuyển đổi mảng nguyên thủy sang mảng đối tượng)
		Integer[] data = new Integer[arrInt.length];
		Arrays.setAll(data, i -> arrInt[i]);

		System.out.println(Arrays.toString(data));
	}

	// In mảng 2 chiều
	public static void printArray(int[][] arrInt) {
		// Cách 1 - gọi phương thức 1 chiều ở trên
		for (int[] row : arrInt) {
			MyArray.printArray(row);
		}

		// Cách 2 - Sử dụng Arrays
//		System.out.println(Arrays.deepToString(arrInt));
	}

	public static void printPerson(Person[] list) {
		for (Person p : list) {
			System.out.println(p);
		}
	}

	public static void printPerson(ArrayList<Person> list) {
		// Cách 4: sử dụng biểu thức Lamda trong JAVA 8
		list.forEach(item -> System.out.println(item));
	}

	public static void main(String[] args) {

		// ------------- Khai báo mảng
		// c2: b không là mảng
//		int[] b = new int[5];

		// c1: arrInt là mảng
//		int[] arrInt = MyArray.generateArray(10);
//		MyArray.printArray(arrInt);
//		System.out.println();
//		
//		MyArray.QuickSort(arrInt, 0, arrInt.length - 1);
//		MyArray.printArray(arrInt);
//		System.out.println();

		Person[] list = MyArray.generatePerson(20);

		MyArray.printPerson(list);

//		System.out.println("\n\n-----------------------------------------------------------------\n");
//
//		// Tìm kiếm
//		ArrayList<Person> results = MyArray.searchPerson(list, "Anh");
//		MyArray.printPerson(results);

		// Sắp xếp theo thứ tự theo tên của person
		System.out.println("\n\n-----------------------------------------------------------------\n");

		ArrayList<Person> sortedTmp = sortedByNamePersonV3(list, true);

		MyArray.printPerson(sortedTmp);
	}
}

class sortedByNamePersonV2 implements Comparator<Person> {

	// Tạo một Collator cho tiếng Việt
	Collator collator = Collator.getInstance(new Locale("vi", "VN"));

	@Override
	public int compare(Person o1, Person o2) {

		String name1 = o1.getFirstName();
		String name2 = o2.getFirstName();

		// Chỉ lấy tến sau cùng (1 từ)
		int at = name1.trim().lastIndexOf(' ');
		if (at != -1) {
			name1 = name1.substring(at + 1);
		}
		at = name2.trim().lastIndexOf(' ');
		if (at != -1) {
			name2 = name2.substring(at + 1);
		}

		return collator.compare(name1, name2);
	}
}