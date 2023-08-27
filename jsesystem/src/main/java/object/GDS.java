package object;

import java.util.List;

public interface GDS {
	// statistic: thống kê
	public List<Person> statisticPerson(String name);
	public List<Person> statisticPerson(byte age);
	public List<Person> statisticPerson(Address addr);
	public List<Person> statisticPerson(Address addr, String name);
}
