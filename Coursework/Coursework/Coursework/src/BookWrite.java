import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class BookWrite { //This class handles writing the arraylist to the file
	private String filename;
	private ArrayList<Book> bookList;
	
	public BookWrite(String filename, ArrayList<Book> bookList) {
		this.filename = filename;
		this.bookList = bookList;
	}

	public void writeFile() {
		isbnCompare bookISBNCompare = new isbnCompare(); //Sorts the array by its ISBN before writing it to the file
		Collections.sort(bookList, bookISBNCompare);
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(filename,false)); // False here as we dont want to append the data, but want to overwrite it
			
			for(Book book: bookList) {
				bw.write(book.toString() + "\n");
			}

		} catch(IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		finally {
			try {
				if(bw != null) {
					bw.close();
				}
			} catch(IOException e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
			}
		}
	}

}
