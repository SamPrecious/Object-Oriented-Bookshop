import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Scanner;
import java.util.Date;

public class ActivityWrite {
	private String filename;
	private ArrayList<Book> basketList;
	private User user;
	private String purchased;
	private String payType;
	
	public ActivityWrite(String filename, ArrayList<Book> basketList, User user, String purchased, String payType) {
		this.filename = filename;
		this.basketList = basketList;
		this.user = user;
		this.purchased = purchased;
		this.payType = payType;
	}
	public void writeFile() throws IOException, ParseException{
		
		Calendar cal = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        
        
        
        //String currentDate = dateFormat.format(cal.getTime()); //Gets todays currentDate
        Date currentDate = dateFormat.parse(dateFormat.format(cal.getTime()));
        BufferedWriter bw = null;
        ArrayList<Activity> currentActivities = new ArrayList<Activity>();
        Activity currentActivity = null;
        for(Book book: basketList) {
        	currentActivity = new Activity(user.getUserID(), user.getAddr(), book.getISBN(), book.getPrice(), book.getStock(), purchased, payType, currentDate);
        	currentActivities.add(currentActivity);  //adds the current activity to the arraylist
        }
        //Activity currentActivity = new Activity(user.getUserID() + user.getAddr() + book.getISBN() + book.getPrice() + book.getStock() + purchased);
        
        //Here it reads all the current activities already around and adds them to the list       
   
        File bookFile = new File("ActivityLog.txt"); //Selects the 'Stock.txt' File
		Scanner bookFileScan = new Scanner(bookFile);
    	
		

		while (bookFileScan.hasNextLine()) {
			String[] details = bookFileScan.nextLine().split(", ");
			currentActivity = new Activity(Integer.parseInt(details[0].trim()), details[1].trim(), Integer.parseInt(details[2].trim()),
					Float.parseFloat(details[3].trim()), Integer.parseInt(details[4].trim()), details[5].trim(), details[6].trim(), dateFormat.parse(details[7].trim()));
			currentActivities.add(currentActivity);							
		}
		bookFileScan.close();
		
		DateCompare activityDateCompare = new DateCompare(); //Allows us to compare by price
		Collections.sort(currentActivities, activityDateCompare);  //Sorts the arrayList in date order
		
    	bw = new BufferedWriter(new FileWriter(filename,false)); // False here as we dont want to append the data, but want to overwrite it
    	
		for(Activity activity: currentActivities) {
			bw.write(activity.toString() + "\n");  //Writes all activities back into the text file
		}
        bw.close(); //Closes the file writer       	        	     
	}
}
