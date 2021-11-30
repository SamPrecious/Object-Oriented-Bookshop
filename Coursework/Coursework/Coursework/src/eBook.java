import java.util.Date;

public class eBook extends Book{
	private int noPages;
	private String format;
	
	public eBook(int ISBN, String bookType, String title, String language, String genre, Date contractDate, float price, int stock, int noPages, String format) {
		super(ISBN, bookType, title, language, genre, contractDate, price, stock);
		this.noPages = noPages;
		this.format = format;
	}
	
	
	public eBook(eBook newEbook) { //This method uses polymorphism to create a new eBook based off an existing one
		super(newEbook.getISBN(), newEbook.getBookType(), newEbook.getTitle(), newEbook.getLanguage(), newEbook.getGenre(),
				newEbook.getContractDate(), newEbook.getPrice(), newEbook.getStock());
		this.noPages = newEbook.getNoPages();
		this.format = newEbook.getFormat();
	}
	


	@Override
	public String toString() {
		return(getData() + ", " + Integer.toString(noPages) + ", " + format);
	}
	
	public int getNoPages() {
		return noPages;
	}



	public String getFormat() {
		return format;
	}

}
