import java.awt.EventQueue;
import javax.swing.JPanel;
import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainClass{
		
	public static void main(String[] args) throws FileNotFoundException{ 
		
		
		File userFile = new File("UserAccounts.txt");  //Selects the 'UserAccounts.txt' File
		Scanner userFileScan = new Scanner(userFile);
		
		User usr = null;
		HashMap<String, User> usrHashMap = new HashMap<String, User>();

		while (userFileScan.hasNextLine()) { //reads all the user data from a file, then saves it to a HashMap
			String[] details = userFileScan.nextLine().split(", ") ;
			if (details[6].equals("admin")) { //If the txt file is an admin do this
				usr = new User(Integer.parseInt(details[0].trim()), details[1].trim(), details[2].trim(),
					new Address(Integer.parseInt(details[3].trim()), details[4].trim(), details[5].trim()),
					AccountType.ADMIN);
			}else{ //If it isn't an admin, do the same but set the AccountType to customer instead
				usr = new User(Integer.parseInt(details[0].trim()), details[1].trim(), details[2].trim(),
						new Address(Integer.parseInt(details[3].trim()), details[4].trim(), details[5].trim()),
						AccountType.CUSTOMER);
			}
			usrHashMap.put(usr.getUsername(),usr); //Adds the User object into the hashmap with its Unique Username as the hashkey
		}
		userFileScan.close();  //Closes the input stream after, since we are no longer using it
		
		
		
		
		
			EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePage frame = new HomePage(usrHashMap); //The User Hashmap is parsed to the HomePage
					frame.getContentPane().add(new JPanel());
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
	}
	
	
}
