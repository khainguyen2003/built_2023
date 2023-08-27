package object;

public class Address {
	public static final String CITYNAME = "No city name";
	public static final String DISTRICTNAME = "No district name";
	public static final String STREETNAME = "No street name";
	
	// Object's properties
	private String cityName;
	private String districtName;
	private String streetName;
	
	// constructor đăc biệt loại 1
	public Address() {
		this(Address.CITYNAME, Address.DISTRICTNAME, Address.STREETNAME);
	}
	// constructor đăc biệt loại 2
	public Address(String cityName, String districtName, String streetName) {
		this.cityName = cityName;
		this.districtName = districtName;
		this.streetName = streetName;
	}
	
	// Constuctor đặc biệt loại 3
	public Address(Address addr) {
		this(addr.getCityName(), addr.getDistrictName(), addr.getStreetName());
	}
	
	
	//Getter methods
	public String getCityName() {
		return cityName;
	}
	public String getDistrictName() {
		return districtName;
	}
	public String getStreetName() {
		return streetName;
	}
	//Setter methods
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	
	@Override
	public String toString() {
		return "Address: " + streetName + ", " + districtName + " - " + cityName; 
	}
	
}
