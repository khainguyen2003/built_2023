package object;

public class StudentManager extends Manager {

	@Override
	public String getInfo(Person p) {
		// TODO Auto-generated method stub
		return "SM - " + p.toString();
	}

}
