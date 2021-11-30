import java.util.Comparator;

public class priceCompare implements Comparator<Book>{  //This compares prices to see what is lower & higher
	public int compare(Book bk1, Book bk2) {
		if (bk1.getPrice() < bk2.getPrice()) return -1;
		if (bk1.getPrice() > bk2.getPrice()) return 1;
		else return 0;
		} 
}
