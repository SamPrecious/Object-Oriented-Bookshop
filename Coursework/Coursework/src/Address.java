public class Address {  //This handles the address of the Users
	
	private int number;
	private String postcode;
	private String city;
	
	public Address (int number, String postcode, String city) {
		this.number = number;
		this.postcode = postcode;
		this.city = city;
	}
	
	@Override
	public String toString() {
		return (this.number + ", " + this.postcode + ", " + this.city);
	}
	
	public String getPostCode() {
		return(this.postcode);
	}
	
}
