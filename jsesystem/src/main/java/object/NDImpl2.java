package object;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NDImpl2 implements ND, BTTU {

	@Override
	public boolean addPerson(Person p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean editPerson(Person p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delPerson(Person p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getInfo(Person p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Person> searchPerson(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Person> searchPerson(byte min_age, byte max_age) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Person> searchPerson(Address addr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Person> searchPerson(Person similar) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Person> statisticPerson(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Person> statisticPerson(byte age) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Person> statisticPerson(Address addr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Person> statisticPerson(Address addr, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Person> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Person> getPersons(Address addr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Person> getNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Person> getNames(Address addr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<Integer, Person> getPosition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<Integer, Person> getPosition(Address addr) {
		// TODO Auto-generated method stub
		return null;
	}

}
