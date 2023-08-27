package object;

import java.util.ArrayList;

public interface CTH {
	public ArrayList<Person> searchPerson(String name);
	public ArrayList<Person> searchPerson(byte min_age, byte max_age);
	public ArrayList<Person> searchPerson(Address addr);
	// similar: tương tự, giống nhau
	public ArrayList<Person> searchPerson(Person similar);
}
