package object;

import java.util.*;

public interface BTTU extends CTX, CTH, GDS {
	public List<Person> getAll();
	public List<Person> getPersons(Address addr);
	public List<Person> getNames();
	public List<Person> getNames(Address addr);
	
	public HashMap<Integer, Person> getPosition();
	public HashMap<Integer, Person> getPosition(Address addr);
}
