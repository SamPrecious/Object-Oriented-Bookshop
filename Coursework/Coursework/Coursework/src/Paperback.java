import java.util.Date;

public class Paperback extends Book{ 
	private int noPages;
	private String condition;
	
	public Paperback(int ISBN, String bookType, String title, String language, String genre, Date contractDate, float price, int stock, int noPages, String condition) {
		super(ISBN, bookType, title, language, genre, contractDate, price, stock);
		this.noPages = noPages;
		this.condition = condition;
	}
	
	

	public Paperback(Paperback newPaperback) { //This method uses polymorphism to create a new paperback book based off an existing one
		super(newPaperback.getISBN(), newPaperback.getBookType(), newPaperback.getTitle(), newPaperback.getLanguage(), newPaperback.getGenre(),
				newPaperback.getContractDate(), newPaperback.getPrice(), newPaperback.getStock());
		this.noPages = newPaperback.getNoPages();
		this.condition = newPaperback.getCondition();
	}
	
	

	@Override
	public String toString() {
		return(getData() + ", " + Integer.toString(noPages) + ", " + condition);
	}
	
	//getters
	public int getNoPages() {
		return noPages;
	}

	public String getCondition() {
		return condition;
	}
	
}
