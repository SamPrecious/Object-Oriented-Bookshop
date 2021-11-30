import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class bookList {  //This creates an ArrayList with all the book objects in it
	public static ArrayList<Book> bookLister() throws FileNotFoundException, NumberFormatException, ParseException { //This class returns a HashMap of all the books in stock
		File bookFile = new File("Stock.txt"); //Selects the 'Stock.txt' File
		Scanner bookFileScan = new Scanner(bookFile);
	
		Paperback paperback = null;
		Audiobook audiobook = null;
		eBook ebook = null;

		ArrayList<Book> currBookStock = new ArrayList<Book>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		
		while (bookFileScan.hasNextLine()) { //reads all the user data from a file, then saves it to an HashMap
			String[] details = bookFileScan.nextLine().split(", ") ;  //Creates an array with all book details split up
			if (details[1].equals("paperback")) { 
				paperback = new Paperback(Integer.parseInt(details[0].trim()), details[1].trim(), details[2].trim(),
					details[3].trim(), details[4].trim(), dateFormat.parse(details[5].trim()), Float.parseFloat(details[6].trim()),
					Integer.parseInt(details[7].trim()), Integer.parseInt(details[8].trim()), details[9].trim());
				currBookStock.add(paperback);
			}else if (details[1].equals("audiobook")){ //If it isn't an admin, do the same but set the AccountType to customer instead
				audiobook = new Audiobook(Integer.parseInt(details[0].trim()), details[1].trim(), details[2].trim(),
						details[3].trim(), details[4].trim(), dateFormat.parse(details[5].trim()), Float.parseFloat(details[6].trim()),
						Integer.parseInt(details[7].trim()), Float.parseFloat(details[8].trim()), details[9].trim());
				currBookStock.add(audiobook);
			}else {
				ebook = new eBook(Integer.parseInt(details[0].trim()), details[1].trim(), details[2].trim(),
						details[3].trim(), details[4].trim(), dateFormat.parse(details[5].trim()), Float.parseFloat(details[6].trim()),
						Integer.parseInt(details[7].trim()), Integer.parseInt(details[8].trim()), details[9].trim());
				currBookStock.add(ebook);
			}
		}
		bookFileScan.close();

		return(currBookStock);
	}
}
