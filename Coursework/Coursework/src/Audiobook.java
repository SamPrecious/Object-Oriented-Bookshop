import java.util.Date;

public class Audiobook extends Book{
	private float length;
	private String format;								

	


	public Audiobook(int ISBN, String bookType, String title, String language, String genre, Date contractDate, float price, int stock, float length, String format) {
		super(ISBN, bookType, title, language, genre, contractDate, price, stock);
		this.length = length;
		this.format = format;
	}
	
	public Audiobook(Audiobook newAudiobook) { //This method uses polymorphism to create a new audiobook based off an existing one
		super(newAudiobook.getISBN(), newAudiobook.getBookType(), newAudiobook.getTitle(), newAudiobook.getLanguage(), newAudiobook.getGenre(),
				newAudiobook.getContractDate(), newAudiobook.getPrice(), newAudiobook.getStock());
		this.length = newAudiobook.getLength();
		this.format = newAudiobook.getFormat();
	}
	
	
	@Override
	public String toString() {
		return(getData() + ", " + Float.toString(length) + ", " + format);
	}
	
	public float getLength() {
		return length;
	}


	public String getFormat() {
		return format;
	}
}
