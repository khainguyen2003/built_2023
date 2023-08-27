package object;

public class EmployeeManager extends Manager {

	@Override
	public String getInfo(Person p) {
		// TODO Auto-generated method stub
		return "EM - " + p.toString();
	}

}
