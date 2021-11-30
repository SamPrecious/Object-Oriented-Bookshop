import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Book {  //This class holds the books
	
	
	private int ISBN;
	private String bookType;
	private String title;
	private String language;
	private String genre;
	private Date contractDate;
	private float price;
	private int stock;
	
	public Book(int ISBN, String bookType, String title, String language, String genre, Date contractDate, float price, int stock) {
		this.ISBN = ISBN;
		this.bookType = bookType;
		this.title = title;
		this.language = language;
		this.genre = genre;
		this.contractDate = contractDate;
		this.price = price;
		this.stock = stock;
	}
	
	public Book(Book newBook) {  //This method uses polymorphism to create a new book based off an existing one
		this.ISBN = newBook.getISBN();
		this.bookType = newBook.getBookType();
		this.title = newBook.getTitle();
		this.language = newBook.getLanguage();
		this.genre = newBook.getGenre();
		this.contractDate = newBook.getContractDate();
		this.price = newBook.getPrice();
		this.stock = newBook.getStock();
		
	}
	
	

	public abstract String toString(); //This forces the other books to have a return method
	
	//These are all the getters for the book class
	public String getData() { //Unlike normal getters, this returns ALL the data in one go to reduce reuse of code in the subclasses
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"); //Puts the date into string form
		
		return(Integer.toString(ISBN) + ", "+ bookType + ", "+ title + ", "+ language +", "+ genre + ", "+
		dateFormat.format(contractDate) + ", " + String.valueOf(price) + ", " + Integer.toString(stock));
	}

	
	
	//The lone setter
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	
	//Getters
	public String getBookType() {
		return bookType;
	}

	public String getTitle() {
		return title;
	}

	public String getLanguage() {
		return language;
	}

	public String getGenre() {
		return genre;
	}

	public Date getContractDate() {
		return contractDate;
	}
	
	public int getISBN() {
		return ISBN;
	}
	
	public float getPrice() {
		return price;
	}
	
	public int getStock() {
		return stock;
	}

}
