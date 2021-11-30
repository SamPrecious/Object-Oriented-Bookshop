public class User{   //This object holds all the users imported from a text file.
	private final int userID;
	private String username;
	private String surname;
	private Address addr;
	private AccountType userAccountType;  //Enum to tell us if they are a customer OR admin
	
	public User(int userID, String username, String surname, Address addr, AccountType userAccountType){
		this.userID = userID;
		this.username = username;
		this.surname = surname;
		this.addr = addr;
		this.userAccountType = userAccountType;
	}
	
	public int getUserID() {
		return(this.userID);
	}
	
	public String getUsername() {
		return(this.username);
	}
	
	public String getAddr() { //Since this is the only address attribute we print, this is all we return
		return(this.addr.getPostCode());
	}
	
	public String getSurname() {
		return(this.surname);
	}
	
	public AccountType getAccountType() {
		return(this.userAccountType);
	}
	
	
}
