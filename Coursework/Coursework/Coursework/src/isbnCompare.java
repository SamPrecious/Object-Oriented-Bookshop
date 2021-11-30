import java.util.Comparator;

public class isbnCompare implements Comparator<Book>{ //This compares ISBN's to see what is lower & higher
	public int compare(Book bk1, Book bk2) {
		if (bk1.getISBN() < bk2.getISBN()) return -1;
		if (bk1.getISBN() > bk2.getISBN()) return 1;
		else return 0;
	} 
	
}
