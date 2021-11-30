import java.text.SimpleDateFormat;
import java.util.Date;

public class Activity {  //This class defines an activity (the things that are stored in activity log). This was made because of the date sort requirement
	
	private int userID;
	private String postcode;
	private int ISBN; 
	private float price; 
	private int stock; 
	private String purchased;
	private String payType;
	private Date date;
		
	public Activity(int userID, String postcode, int ISBN, float price, int stock, String purchased, String payType, Date date){
		this.userID = userID;
		this.postcode = postcode;
		this.ISBN = ISBN;
		this.price = price;
		this.stock = stock;
		this.purchased = purchased;
		this.payType = payType;
		this.date = date;
	}
	
	
	@Override
	public String toString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"); //Puts the date into string form
		return(userID + ", "+ postcode + ", "+ ISBN + ", "+ price +", "+ stock + ", "+ purchased + ", " + payType + ", "+ dateFormat.format(date));
	}
	
	public Date getDate() {
		return(date);
	}
	public int getISBN() {
		return(ISBN);
	}
}
