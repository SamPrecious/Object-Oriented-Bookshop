import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JLayeredPane;
import java.awt.CardLayout;
import javax.swing.JTextField;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.event.AncestorEvent;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JTable;

public class HomePage extends JFrame {

	private JPanel contentPane;
	private HashMap<String, User> usrHashMap;
	private JTextField isbnField;
	private JTextField titleField;
	private JTextField releaseDateField;
	private JTextField priceField;
	private JTextField stockField;
	private JTextField addInfo1Field;
	private JTextField isbnField2;
	private JTextField amountField;
	private JTextField cardNumberField;
	private JTextField securityNumberField;
	private JTextField paypalEmailField;
	private boolean audio5Filter = false; //Default value is false
	private JTable stockTable;
	/**
	 * Create the frame.
	 * @throws ParseException 
	 * @throws FileNotFoundException 
	 * @throws NumberFormatException 
	 */
	public HomePage(HashMap<String, User> usrHashMap) throws NumberFormatException, FileNotFoundException, ParseException {
		this.usrHashMap = usrHashMap;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1260, 851);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 8, true));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		PurchasePrice cost = new PurchasePrice();
		
		
		JLabel slctUser = new JLabel("Select User");
		slctUser.setForeground(Color.WHITE);
		slctUser.setFont(new Font("Tahoma", Font.PLAIN, 20));
		slctUser.setHorizontalAlignment(SwingConstants.CENTER);
		slctUser.setBounds(0, 25, 1244, 68);
		contentPane.add(slctUser);
		
		String s1[] = new String[usrHashMap.size()];
		int nextIndex = 0;
		for(HashMap.Entry<String, User> entry : usrHashMap.entrySet()){
			s1[nextIndex] = entry.getValue().getUsername();
			nextIndex++;
		}
		
		JComboBox userBox = new JComboBox(s1);    //The userBox contains individual usernames 
		userBox.setBounds(527, 92, 188, 57);
		contentPane.add(userBox);
		
		JButton loginButton = new JButton("Login");
		loginButton.setBackground(Color.WHITE);
		ArrayList<Book> CurrentStock = new ArrayList<Book>(bookList.bookLister());  //Calls a method to retrieve all the Books in Stock.txt
		ArrayList<Book> tempArray = new ArrayList<Book>();  //Creates a temporary array that copies CurrentStock for subtracting values from stock
		ArrayList<Book> basket = new ArrayList<Book>();;
	    JScrollPane bookScrollPane = new JScrollPane();
		bookScrollPane.setBounds(260, 247, 915, 213);
		contentPane.add(bookScrollPane);
		
		String[] columnNames = {"ISBN", "Book Type", "Title", "Language", "Genre", "Release Date", "Price", "Stock",
				"Info 1", "Info 2"};
                
		
		TableModel tableModel = new DefaultTableModel(columnNames, 0);
		stockTable = new JTable(tableModel);
		stockTable.setForeground(Color.WHITE);
		stockTable.setBackground(Color.DARK_GRAY);
		stockTable.setEnabled(false);  //This stops us from editing the table
		bookScrollPane.setViewportView(stockTable);
		bookScrollPane.setVisible(false);
		
		setTextArea(CurrentStock, stockTable, userBox);
		
		
		JLayeredPane layeredPane = new JLayeredPane();  //The layered pane holds the adminPanel and custPanel
		layeredPane.setBounds(68, 485, 1107, 316);
		contentPane.add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		//The seperate JPanels (adminPanel & custPanel) are used to show certain (different) things based on if the user is an Admin or a Customer
		JPanel adminPanel = new JPanel();  
		adminPanel.setBackground(Color.DARK_GRAY);
		adminPanel.setBorder(new LineBorder(new Color(0, 0, 0), 7));
		layeredPane.add(adminPanel, "name_318342812075800");
		adminPanel.setLayout(null);

		isbnField = new JTextField();  //These are all the input fields for the book
		isbnField.setBounds(10, 35, 127, 20);
		adminPanel.add(isbnField);
		isbnField.setColumns(10);
		
		titleField = new JTextField();
		titleField.setBounds(257, 35, 110, 20);
		adminPanel.add(titleField);
		titleField.setColumns(10);

		releaseDateField = new JTextField();
		releaseDateField.setBounds(607, 35, 90, 20);
		adminPanel.add(releaseDateField);
		releaseDateField.setColumns(10);

		priceField = new JTextField();
		priceField.setBounds(707, 35, 80, 20);
		adminPanel.add(priceField);
		priceField.setColumns(10);

		stockField = new JTextField();
		stockField.setBounds(797, 35, 80, 20);
		adminPanel.add(stockField);
		stockField.setColumns(10);

		addInfo1Field = new JTextField();
		addInfo1Field.setBounds(887, 35, 100, 20);
		adminPanel.add(addInfo1Field);
		addInfo1Field.setColumns(10);

		String paperbackInfo2[] = {"new", "used"};
		String audiobookInfo2[] = {"MP3", "WMA", "AAC"};
		String ebookInfo2[] = {"EPUB", "MOBI", "AZW3", "PDF"};
		
		String bookTypes[] = {"paperback", "audiobook", "ebook"};
		JComboBox bookTypeBox = new JComboBox(bookTypes); 
		
		JComboBox info2Box = new JComboBox(paperbackInfo2);
		info2Box.setBounds(997, 34, 100, 22);
		adminPanel.add(info2Box);
		
		
		bookTypeBox.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {  //This changes the items in the info 2 box based on what item the user is currently selecting
				
				info2Box.removeAllItems();  //Removes the current items
				if(bookTypeBox.getSelectedItem() == "paperback") {
					for (int i = 0; i < paperbackInfo2.length; i++) {
			            String curItem = paperbackInfo2[i];
			            info2Box.addItem(curItem);
			        }
				}else if(bookTypeBox.getSelectedItem() == "audiobook"){
					for (int i = 0; i < audiobookInfo2.length; i++) {
			            String curItem = audiobookInfo2[i];
			            info2Box.addItem(curItem);
			        }
				}else {  //ebook
					for (int i = 0; i < ebookInfo2.length; i++) {
			            String curItem = ebookInfo2[i];
			            info2Box.addItem(curItem);
			        }
				}
				info2Box.revalidate();
				info2Box.repaint();

			}
		});
		bookTypeBox.setBounds(147, 34, 100, 22);
		adminPanel.add(bookTypeBox);

		String[] allGenres = {"All","Politics", "Medicine", "Business", "Computer Science", "Biography"};
		JComboBox genreFilterBox = new JComboBox(allGenres);
		genreFilterBox.setVisible(false); //Hides it initially
		
		String languageValues[] = {"English", "French"};
		JComboBox languageBox = new JComboBox(languageValues);
		languageBox.setBounds(377, 34, 100, 22);
		adminPanel.add(languageBox);
		
		String genres[] = {"Politics", "Medicine", "Business", "Computer Science", "Biography"};
		JComboBox genreBox = new JComboBox(genres);
		genreBox.setBounds(487, 34, 110, 22);
		adminPanel.add(genreBox);
		
		JLabel verifLabel = new JLabel("");  //The label that notifies of success or error 
		verifLabel.setForeground(Color.WHITE);
		verifLabel.setHorizontalAlignment(SwingConstants.CENTER);
		verifLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		verifLabel.setBounds(0, 184, 1107, 88);
		adminPanel.add(verifLabel);
		
		JButton addBook = new JButton("Add Book");
		addBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			//The next if statement makes sure all inputs are at least long enough
				if(isbnField.getText().length() == 8 && titleField.getText().length() > 0 && releaseDateField.getText().length() > 0 
						&& priceField.getText().length() > 0 && stockField.getText().length() > 0 
						&& addInfo1Field.getText().length() > 0) {
					try {  //This try catch throws if the user does not enter a value conforming to the required input
				        int isbnInt = Integer.parseInt(isbnField.getText());
				        float priceFloat = Float.parseFloat(priceField.getText());
				        int stockInt = Integer.parseInt(stockField.getText());
				        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				        dateFormat.setLenient(false);  //This line stops the date from accepting things like 01-00-2004
				        
				        Date releaseDateVal = dateFormat.parse(releaseDateField.getText());
				        				        				        

				        Book newBook = null;
				      
				        if (bookTypeBox.getSelectedItem().equals("paperback")) { 
					        int info1Num = Integer.parseInt(addInfo1Field.getText());
				        	newBook = new Paperback(isbnInt, "paperback", titleField.getText(), languageBox.getSelectedItem().toString(),
				        			genreBox.getSelectedItem().toString(), releaseDateVal, priceFloat,stockInt, info1Num,
				        			info2Box.getSelectedItem().toString());
				        }else if(bookTypeBox.getSelectedItem().equals("audiobook")) {
					        float info1Num = Float.parseFloat(addInfo1Field.getText());
					        newBook = new Audiobook(isbnInt, "audiobook", titleField.getText(), languageBox.getSelectedItem().toString(),
				        			genreBox.getSelectedItem().toString(), releaseDateVal, priceFloat,stockInt, info1Num,
				        			info2Box.getSelectedItem().toString());
				        }else {  //ebook
					        int info1Num = Integer.parseInt(addInfo1Field.getText());

				        	newBook = new eBook(isbnInt, "ebook", titleField.getText(), languageBox.getSelectedItem().toString(),
				        			genreBox.getSelectedItem().toString(), releaseDateVal, priceFloat,stockInt, info1Num,
				        			info2Box.getSelectedItem().toString());
				        }
				        boolean bookFound = false;
				        for(Book currentBook : CurrentStock){  //Loops through all the books in the arraylist and prints them onto the textArea
							if (currentBook.getISBN() == newBook.getISBN()) {  //If it is a book that is already in the system
								bookFound = true;
					        	newBook.setStock(currentBook.getStock());  //We have saved the stock in another int, this is temporary for ease of checking objects
								if(currentBook.toString().equals(newBook.toString())) {  //Checks if the book objects are the same
									currentBook.setStock(currentBook.getStock() + stockInt);  //If both books are the same, it just adds the stock on
									
									setTextArea(CurrentStock, stockTable, genreFilterBox);  //Sets the text based on the criteria 
									
									BookWrite writeBook = new BookWrite("Stock.txt", CurrentStock);  //Runs the script to update the TXT file with the new arraylist
									writeBook.writeFile();
									verifLabel.setText("Book added successfully");
									break;  //Breaks here as we have modified the file and it would be inefficient to do so again
								}else {  //If it is a different book with the same ISBN
									verifLabel.setText("Sorry, but this ISBN is already being used for a book with different properties");
								}																																
							}						
						}	
				        if(!bookFound){  //This only runs if a book with the same ISBN was not found
							CurrentStock.add(newBook);
							
							setTextArea(CurrentStock, stockTable, genreFilterBox);  //Sets the text based on the criteria


							BookWrite writeBook = new BookWrite("Stock.txt", CurrentStock); //Runs the script to update the TXT file with the new arraylist
							writeBook.writeFile();
							verifLabel.setText("Book Successfully Added");
						}
				        
				        
				    } catch (NumberFormatException nfe) {
				        verifLabel.setText("Error, make sure all your numerical inputs are in the correct format");
				    } catch (ParseException e1) {
				    	verifLabel.setText("Incorrect Date Format");
					}catch (Exception e2) {
				    	verifLabel.setText("Incorrect Date Format");

				        }
				}else {
					verifLabel.setText("Make sure all inputs are the right length");  //At least 1 long for all, but 8 long for ISBN 
				}
			}
		});
		addBook.setBounds(399, 93, 300, 57);
		adminPanel.add(addBook);
		
		
		
		
		JLabel isbnLabel = new JLabel("ISBN");
		isbnLabel.setForeground(Color.WHITE);
		isbnLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		isbnLabel.setHorizontalAlignment(SwingConstants.CENTER);
		isbnLabel.setBounds(10, 11, 127, 13);
		adminPanel.add(isbnLabel);
		
		JLabel bookTypeLabel = new JLabel("Book Type");
		bookTypeLabel.setForeground(Color.WHITE);
		bookTypeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		bookTypeLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		bookTypeLabel.setBounds(147, 10, 100, 13);
		adminPanel.add(bookTypeLabel);
		
		JLabel bookTitleLabel = new JLabel("Title");
		bookTitleLabel.setForeground(Color.WHITE);
		bookTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		bookTitleLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		bookTitleLabel.setBounds(257, 11, 110, 13);
		adminPanel.add(bookTitleLabel);
		
		JLabel languageLabel = new JLabel("Language");
		languageLabel.setForeground(Color.WHITE);
		languageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		languageLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		languageLabel.setBounds(377, 11, 100, 13);
		adminPanel.add(languageLabel);
		
		JLabel genreLabel = new JLabel("Genre");
		genreLabel.setForeground(Color.WHITE);
		genreLabel.setHorizontalAlignment(SwingConstants.CENTER);
		genreLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		genreLabel.setBounds(487, 10, 110, 13);
		adminPanel.add(genreLabel);
		
		JLabel releaseDateLabel = new JLabel("Release Date");
		releaseDateLabel.setForeground(Color.WHITE);
		releaseDateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		releaseDateLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		releaseDateLabel.setBounds(607, 11, 90, 13);
		adminPanel.add(releaseDateLabel);
		
		JLabel priceLabel = new JLabel("Price");
		priceLabel.setForeground(Color.WHITE);
		priceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		priceLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		priceLabel.setBounds(707, 11, 80, 13);
		adminPanel.add(priceLabel);
		
		JLabel amountLabel = new JLabel("Amount");
		amountLabel.setForeground(Color.WHITE);
		amountLabel.setHorizontalAlignment(SwingConstants.CENTER);
		amountLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		amountLabel.setBounds(797, 11, 80, 13);
		adminPanel.add(amountLabel);
		
		JLabel info1Label = new JLabel("Additional Info 1");
		info1Label.setForeground(Color.WHITE);
		info1Label.setHorizontalAlignment(SwingConstants.CENTER);
		info1Label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		info1Label.setBounds(887, 11, 100, 13);
		adminPanel.add(info1Label);
		
		JLabel info2Label = new JLabel("Additional Info 2");
		info2Label.setForeground(Color.WHITE);
		info2Label.setHorizontalAlignment(SwingConstants.CENTER);
		info2Label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		info2Label.setBounds(997, 11, 100, 13);
		adminPanel.add(info2Label);
		
		
		
		
				
		JPanel custPanel = new JPanel();
		custPanel.setBackground(Color.DARK_GRAY);
		custPanel.setBorder(new LineBorder(new Color(0, 0, 0), 6, true));
		layeredPane.add(custPanel, "name_318388907083400");
		custPanel.setLayout(null);
		
		JLabel basketLabel = new JLabel("Shopping Basket");
		basketLabel.setForeground(Color.WHITE);
		basketLabel.setFont(new Font("Arial", Font.BOLD, 30));
		basketLabel.setHorizontalAlignment(SwingConstants.CENTER);
		basketLabel.setBounds(0, 0, 1107, 57);
		custPanel.add(basketLabel);
		
		JTextArea basketTextArea = new JTextArea();
		basketTextArea.setEditable(false); //Doesnt allow users to write in the text area
		
		JLabel isbn2Lbl = new JLabel("ISBN");
		isbn2Lbl.setForeground(Color.WHITE);
		isbn2Lbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		isbn2Lbl.setHorizontalAlignment(SwingConstants.CENTER);
		isbn2Lbl.setBounds(388, 55, 131, 24);
		custPanel.add(isbn2Lbl);
		
		JLabel amountLbl = new JLabel("Amount");
		amountLbl.setForeground(Color.WHITE);
		amountLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		amountLbl.setHorizontalAlignment(SwingConstants.CENTER);
		amountLbl.setBounds(529, 57, 54, 21);
		custPanel.add(amountLbl);
		
		isbnField2 = new JTextField();
		
		
		isbnField2.setBounds(388, 80, 131, 30);
		custPanel.add(isbnField2);
		isbnField2.setColumns(10);
		
		amountField = new JTextField();
		amountField.setBounds(529, 80, 54, 30);
		custPanel.add(amountField);
		amountField.setColumns(10);
		
		JLabel basketErrorField = new JLabel("");
		basketErrorField.setForeground(Color.WHITE);
		basketErrorField.setHorizontalAlignment(SwingConstants.RIGHT);
		basketErrorField.setBounds(55, 80, 323, 30);
		custPanel.add(basketErrorField);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(192, 128, 700, 100);
		custPanel.add(scrollPane);
		
		
		scrollPane.setViewportView(basketTextArea);
		
		JButton addToBasketBtn = new JButton("Add To Basket");
		addToBasketBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					int isbnValue = Integer.parseInt(isbnField2.getText());
					int amountValue = Integer.parseInt(amountField.getText());
					boolean isbnFound = false;  //Creates a variable to know when the book has been found in the array (or the update was successful)
					boolean matchingISBN = false;  //Tells us if the ISBN matched
					for(Book tempBook : tempArray){ //Loops through all the values in the temporary arraylist
						if(isbnValue == tempBook.getISBN() && tempBook.getStock() >= amountValue  && amountValue != 0) { //If there is enough stock to satisfy & the ISBNs are equal:
							
							
							basketTextArea.setText(null); //Empties the basket text area
							
							for(Book basketBook : basket){
								if(basketBook.getISBN() == tempBook.getISBN()) {  //If a matching book is already in the basket
									isbnFound = true;
									basketBook.setStock(basketBook.getStock() + amountValue); //Increases the amount of said book in the basket by the amount of stock requested
								}
								basketTextArea.append(basketBook.toString() + "\n");  //Loops through the basket adding every item
							}
							
							if(!isbnFound) { //If no matching book was found in the basket, add a new book object to it
								isbnFound = true;  //There is now a matching ISBN in the basket
								Book newBook = cloneBook(tempBook);  //Copies the current book in the temporary array
								newBook.setStock(amountValue); //Sets the stock of the newly copied book to the one requested
								basket.add(newBook); //Adds the newly cloned book into the basket
								basketTextArea.append(newBook.toString() + "\n"); //Gives the user some written feedback in a text area
							}
							
							tempBook.setStock(tempBook.getStock() - amountValue); //Reduces the temporary book array stock by the amount requested

							basketErrorField.setText("Item Added Successfully"); //Tells the user its working
							break;  //This exits the loop early so it doesn't needlessly loop after finding what it came for	
						}else if(isbnValue == tempBook.getISBN()) {  //If it finds the ISBN but the other values don't work:
							isbnFound = true; //The ISBN was found in the temporary array, but there was a stock error
							if(amountValue == 0){
								basketErrorField.setText("You cannot add 0 books");							
							}else {
								basketErrorField.setText("There is not enough stock to handle your request");
							}
						}
					}
					if(!isbnFound) {  //If the book is never found or added, tell the user what went wrong
						basketErrorField.setText("No ISBN matches your input");
					}
					

					
				}catch (NumberFormatException nfe) {
					basketErrorField.setText("Invalid Number Format");
					
				}													
			}
		});
		addToBasketBtn.setBounds(593, 80, 131, 30);
		custPanel.add(addToBasketBtn);
		
		JButton emptyBasketButton = new JButton("Empty Basket");
		emptyBasketButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				basketTextArea.setText(null);
				tempArray.clear(); //Empties the temporary array
				tempArray.addAll(tempCreate(CurrentStock));  //Resets the temporary array to its original values (everything in the current stock)
				basket.clear();  //Empties the basket
			}
		});
		emptyBasketButton.setBounds(360, 253, 119, 30);
		custPanel.add(emptyBasketButton);
		
		JButton audioFilterButton = new JButton("Audiobooks > 5 Hours");
		
		JPanel cardCheckoutPanel = new JPanel();  //Creates a JPanel for the card checkout
		cardCheckoutPanel.setBackground(Color.DARK_GRAY);
		cardCheckoutPanel.setBorder(new LineBorder(new Color(0, 0, 0), 6, true));
		layeredPane.add(cardCheckoutPanel, "name_498552124252000");
		cardCheckoutPanel.setLayout(null);
		
		JLabel cardNumLabel = new JLabel("Card Number");
		cardNumLabel.setForeground(Color.WHITE);
		cardNumLabel.setHorizontalAlignment(SwingConstants.CENTER);
		cardNumLabel.setFont(new Font("Arial", Font.BOLD, 18));
		cardNumLabel.setBounds(353, 72, 203, 37);
		cardCheckoutPanel.add(cardNumLabel);
		
		JLabel securityCodeLabel = new JLabel("Security Code");
		securityCodeLabel.setForeground(Color.WHITE);
		securityCodeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		securityCodeLabel.setFont(new Font("Arial", Font.BOLD, 18));
		securityCodeLabel.setBounds(590, 72, 126, 37);
		cardCheckoutPanel.add(securityCodeLabel);
		
		cardNumberField = new JTextField();
		cardNumberField.setFont(new Font("Arial", Font.PLAIN, 15));
		cardNumberField.setBounds(353, 109, 203, 37);
		cardCheckoutPanel.add(cardNumberField);
		cardNumberField.setColumns(10);
		
		securityNumberField = new JTextField();
		securityNumberField.setFont(new Font("Arial", Font.BOLD, 15));
		securityNumberField.setBounds(590, 107, 126, 40);
		cardCheckoutPanel.add(securityNumberField);
		securityNumberField.setColumns(10);
		
		
		JLabel cardPurchaseErrorLabel = new JLabel("");
		cardPurchaseErrorLabel.setForeground(Color.WHITE);
		cardPurchaseErrorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		cardPurchaseErrorLabel.setFont(new Font("Arial", Font.BOLD, 18));
		cardPurchaseErrorLabel.setBounds(353, 268, 363, 37);
		cardCheckoutPanel.add(cardPurchaseErrorLabel);
		
		
		JButton purchaseCardButton = new JButton("Purchase");
		purchaseCardButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cardNumberField.getText().length() == 6 && securityNumberField.getText().length() == 3) {
					try {  //This try catch makes sure that the user has entered an input  
				        int cardNum = Integer.parseInt(cardNumberField.getText());  //If it cannot parse these ints, the try catch will throw
				        int secNum = Integer.parseInt(securityNumberField.getText());
				        //If it has gotten here, then the format is correct
				        ActivityWrite newPurchase = new ActivityWrite("ActivityLog.txt", basket, usrHashMap.get(userBox.getSelectedItem()),
				        		"purchased", "Credit Card");  //Writes new details to the ActivityLog.txt file to show books that were purchased
				        newPurchase.writeFile();
				        
				        basket.clear(); //Empties the contents of the shopping basket
						CurrentStock.clear();  //This clears the current stock and updates it with the temporary array.
						CurrentStock.addAll(tempCreate(tempArray));
						
						
						
						BookWrite writeBook = new BookWrite("Stock.txt", CurrentStock);  //Runs the script to update the TXT file with the new stock
						writeBook.writeFile();
				        
						cardNumberField.setText(null);  //Clears the text fields before exiting out so card details arent saved
						securityNumberField.setText(null);
						layeredPane.remove(cardCheckoutPanel);  //Hides the customer panel so the area can be covered with the new cardCheckoutPanel
						layeredPane.add(custPanel);  //Shows us the cardCheckoutPanel panel
						layeredPane.repaint();   //Repaints the layeredPane to update its visibility to the user
						layeredPane.revalidate();
						
						setTextArea(CurrentStock, stockTable, genreFilterBox);  //Sets the text based on the criteria
						
						basketTextArea.setText(null); //Since the basket is empty, this removes all the text in it
						
						
						NumberFormat formatter = NumberFormat.getCurrencyInstance();  //Creates a money format for the numbers
						String costInMoney = formatter.format(cost.getCost());  //This makes the cost go to money format (i.e. 50.5 -> £50.50)
						
						purchasedFrame secondFrame = new purchasedFrame(costInMoney, "Credit Card");
						secondFrame.setVisible(true);
						
					}catch (NumberFormatException nfe) {
				        cardPurchaseErrorLabel.setText("Please only input integers");
				        
					} catch (IOException e1) {
				        cardPurchaseErrorLabel.setText("File Read Error");

					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else {
					if(securityNumberField.getText().length() == 3) {
						cardPurchaseErrorLabel.setText("The card number must be 6 digits long");
					}else {
						cardPurchaseErrorLabel.setText("The security number must be 3 digits long");
					}
				}
				
			}
		});
		purchaseCardButton.setBounds(536, 183, 180, 60);
		cardCheckoutPanel.add(purchaseCardButton);
		
		
		
		JLabel cardTotalPrice = new JLabel("");
		cardTotalPrice.setForeground(Color.WHITE);
		cardTotalPrice.setHorizontalAlignment(SwingConstants.CENTER);
		cardTotalPrice.setFont(new Font("Arial", Font.BOLD, 18));
		cardTotalPrice.setBounds(353, 11, 363, 37);
		cardCheckoutPanel.add(cardTotalPrice);
		
		JButton cancelCardButton = new JButton("cancel");
		cancelCardButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ActivityWrite newPurchase = new ActivityWrite("ActivityLog.txt", basket, usrHashMap.get(userBox.getSelectedItem()),
		        		"cancelled", "");  //Writes new details to the ActivityLog.txt file to show books that were purchased
		        //The final variable is blank as there is nothing it was bought with
				
				try {
					newPurchase.writeFile();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				basket.clear(); //Empties the contents of the shopping basket
				tempArray.clear();
				tempArray.addAll(tempCreate(CurrentStock));  //Resets the temporary array as the basket is now empty
				cardNumberField.setText(null);  //Clears the text fields before exiting out so card details arent saved
				securityNumberField.setText(null);
				layeredPane.remove(cardCheckoutPanel);  //Hides the customer panel so the area can be covered with the new cardCheckoutPanel
				layeredPane.add(custPanel);  //Shows us the cardCheckoutPanel panel
				layeredPane.repaint();   //Repaints the layeredPane to update its visibility to the user
				layeredPane.revalidate();
				
				basketTextArea.setText(null); //Since the basket is empty, this removes all the text in it

			}
		});
		cancelCardButton.setBounds(353, 183, 180, 60);
		cardCheckoutPanel.add(cancelCardButton);
		
		JLabel checkoutErrorLabel = new JLabel("You cannot checkout with an empty basket");
		checkoutErrorLabel.setForeground(Color.WHITE);
		checkoutErrorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		checkoutErrorLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		checkoutErrorLabel.setBounds(360, 281, 377, 24);
		custPanel.add(checkoutErrorLabel);
		checkoutErrorLabel.setVisible(false);  //Initially start off hidden
		
		JButton cardCheckoutButton = new JButton("Card Checkout");
		cardCheckoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!basket.isEmpty()) {  //If the basket is empty there is no reason to checkout
					cost.setCost(0);
					for(Book currentBook: basket) { //Loops through all variables in the basket
						cost.setCost(cost.getCost() + (currentBook.getPrice()*currentBook.getStock()));  //Increments the price of every book
					}
					NumberFormat formatter = NumberFormat.getCurrencyInstance();  //Creates a money format for the numbers
					String costInMoney = formatter.format(cost.getCost());  //This makes the cost go to money format (i.e. 50.5 -> £50.50)
					cardTotalPrice.setText("The total cost is: " + costInMoney);
					
					
					layeredPane.remove(custPanel);  //Hides the customer panel so the area can be covered with the new cardCheckoutPanel
					layeredPane.add(cardCheckoutPanel);  //Shows us the cardCheckoutPanel panel
					layeredPane.repaint();   //Repaints the layeredPane to update its visibility to the user
					layeredPane.revalidate();  //Tells the layout manager to recalculate the layout that is necessary
					checkoutErrorLabel.setVisible(false);  //Hides the error again
				}else {
					checkoutErrorLabel.setVisible(true);  //Makes the error visible
				}

			}
		});
		cardCheckoutButton.setBounds(489, 253, 119, 30);
		custPanel.add(cardCheckoutButton);
		
		JLabel paypalTotalPrice = new JLabel("");
		paypalTotalPrice.setForeground(Color.WHITE);
		paypalTotalPrice.setHorizontalAlignment(SwingConstants.CENTER);
		paypalTotalPrice.setFont(new Font("Arial", Font.BOLD, 18));
		paypalTotalPrice.setBounds(385, 11, 363, 37);
		
		JPanel paypalCheckoutPanel = new JPanel();
		paypalCheckoutPanel.setBackground(Color.DARK_GRAY);
		paypalCheckoutPanel.setBorder(new LineBorder(new Color(0, 0, 0), 6));
		layeredPane.add(paypalCheckoutPanel, "name_531075419301099");
		paypalCheckoutPanel.setLayout(null);
		
		JButton paypalCheckoutButton = new JButton("PayPal");
		paypalCheckoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!basket.isEmpty()) {  //If the basket is empty there is no reason to checkout
					cost.setCost(0);
					for(Book currentBook: basket) { //Loops through all variables in the basket
						cost.setCost(cost.getCost() + (currentBook.getPrice()*currentBook.getStock()));  //Increments the price of every book
					}
					NumberFormat formatter = NumberFormat.getCurrencyInstance();  //Creates a money format for the numbers
					String costInMoney = formatter.format(cost.getCost());  //This makes the cost go to money format (i.e. 50.5 -> £50.50)
					paypalTotalPrice.setText("The total cost is: " + costInMoney);
					
					
					layeredPane.remove(custPanel);  //Hides the customer panel so the area can be covered with the new cardCheckoutPanel
					layeredPane.add(paypalCheckoutPanel);  //Shows us the cardCheckoutPanel panel
					layeredPane.repaint();   //Repaints the layeredPane to update its visibility to the user
					layeredPane.revalidate();  //Tells the layout manager to recalculate the layout that is necessary
					checkoutErrorLabel.setVisible(false);  //Hides the error again
				}else {
					checkoutErrorLabel.setVisible(true);  //Makes the error visible
				}
				
			}
		});
		paypalCheckoutButton.setFont(new Font("Arial", Font.BOLD, 17));
		paypalCheckoutButton.setForeground(Color.BLUE);
		paypalCheckoutButton.setBackground(Color.YELLOW);
		paypalCheckoutButton.setBounds(618, 253, 119, 30);
		custPanel.add(paypalCheckoutButton);
		
		
		
		JLabel emailAddress = new JLabel("Email Address");
		emailAddress.setForeground(Color.WHITE);
		emailAddress.setHorizontalAlignment(SwingConstants.CENTER);
		emailAddress.setFont(new Font("Arial", Font.BOLD, 18));
		emailAddress.setBounds(460, 59, 203, 37);
		paypalCheckoutPanel.add(emailAddress);
		
		paypalEmailField = new JTextField();
		paypalEmailField.setFont(new Font("Arial", Font.PLAIN, 15));
		paypalEmailField.setColumns(10);
		paypalEmailField.setBounds(460, 96, 203, 37);
		paypalCheckoutPanel.add(paypalEmailField);
		
		JLabel paypalErrorLabel = new JLabel("");
		paypalErrorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		paypalErrorLabel.setFont(new Font("Arial", Font.BOLD, 18));
		paypalErrorLabel.setBounds(385, 229, 363, 37);
		
		JButton purchasePaypalButton = new JButton("Purchase");
		purchasePaypalButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//The program first does a loose email check. This isn't 100% accurate but is fairly accurate
				if(paypalEmailField.getText().contains("@")) {
					String[] tempStringArray = paypalEmailField.getText().split("@"); //Splits the array when it finds an @
					paypalErrorLabel.setText(Integer.toString(tempStringArray.length));
					if(tempStringArray.length == 2) {
						if(tempStringArray[1].split("\\.").length >= 2 && !tempStringArray[0].contains("@") && !tempStringArray[1].contains("@")) { // The . doesnt split without the //
							paypalErrorLabel.setText("Done");
							
							ActivityWrite newPurchase = new ActivityWrite("ActivityLog.txt", basket, usrHashMap.get(userBox.getSelectedItem()),
					        		"purchased", "PayPal");  //Writes new details to the ActivityLog.txt file to show books that were purchased
					        try {
								newPurchase.writeFile();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (ParseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
					        
					        
					        basket.clear(); //Empties the contents of the shopping basket
							CurrentStock.clear();  //This clears the current stock and updates it with the temporary array.
							CurrentStock.addAll(tempCreate(tempArray));
							
							BookWrite writeBook = new BookWrite("Stock.txt", CurrentStock);  //Runs the script to update the TXT file with the new stock
							writeBook.writeFile();
					        
							paypalEmailField.setText(null);  //Clears the text fields before exiting out so card details arent saved
							layeredPane.remove(paypalCheckoutPanel);  //Hides the customer panel so the area can be covered with the new cardCheckoutPanel
							layeredPane.add(custPanel);  //Shows us the cardCheckoutPanel panel
							layeredPane.repaint();   //Repaints the layeredPane to update its visibility to the user
							layeredPane.revalidate();
							
							
							setTextArea(CurrentStock, stockTable, genreFilterBox); 
							
							basketTextArea.setText(null); //Since the basket is empty, this removes all the text in it
							NumberFormat formatter = NumberFormat.getCurrencyInstance();  //Creates a money format for the numbers
							String costInMoney = formatter.format(cost.getCost());  //This makes the cost go to money format (i.e. 50.5 -> £50.50)
							
							purchasedFrame secondFrame = new purchasedFrame(costInMoney, "PayPal");
							secondFrame.setVisible(true);
	
						}else { paypalErrorLabel.setText("Invalid Email Format"); }
					}else { paypalErrorLabel.setText("Invalid Email Format"); }				
				}else { paypalErrorLabel.setText("Invalid Email Format");
				}
			}
		});
		purchasePaypalButton.setBounds(568, 144, 180, 60);
		paypalCheckoutPanel.add(purchasePaypalButton);
		
		JButton cancelPaypalButton = new JButton("cancel");
		cancelPaypalButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ActivityWrite newPurchase = new ActivityWrite("ActivityLog.txt", basket, usrHashMap.get(userBox.getSelectedItem()),
		        		"cancelled", " ");  //Writes new details to the ActivityLog.txt file to show books that were purchased
		        //The final variable is blank as there is nothing it was bought with
				
				try {
					newPurchase.writeFile();
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				basket.clear(); //Empties the contents of the shopping basket
				tempArray.clear();
				tempArray.addAll(tempCreate(CurrentStock));  //Resets the temporary array as the basket is now empty
				paypalEmailField.setText(null);  //Clears the text fields before exiting out so card details arent saved
				layeredPane.remove(paypalCheckoutPanel);  //Hides the customer panel so the area can be covered with the new cardCheckoutPanel
				layeredPane.add(custPanel);  //Shows us the cardCheckoutPanel panel
				layeredPane.repaint();   //Repaints the layeredPane to update its visibility to the user
				layeredPane.revalidate();
				
				basketTextArea.setText(null); //Since the basket is empty, this removes all the text in it

			}
		});
		cancelPaypalButton.setBounds(385, 144, 180, 60);
		paypalCheckoutPanel.add(cancelPaypalButton);
		
		
		paypalCheckoutPanel.add(paypalErrorLabel);
		
		
		paypalCheckoutPanel.add(paypalTotalPrice);
								
		
		layeredPane.removeAll(); //Hides all the current Layered Panes
		
		
		
		
		int x = 0;
		loginButton.addActionListener(new ActionListener() {  //This button also handles log outs
			public void actionPerformed(ActionEvent e) {
				
				layeredPane.removeAll();  //Hides whatever layered panes are open
				layeredPane.repaint();    //Repaints the layeredPane to update its visibility to the user
				if(loginButton.getText().equals("Login")) {//If it is in login mode when the user presses it:
					
					setTextArea(CurrentStock, stockTable, genreFilterBox);  //Sets the text area based on a set of criteria	

					
					bookScrollPane.setVisible(true);  //Shows all users the Scroll Box containing all books when they are logged in
					//textArea.setText(null); // clears the text in the box. This stops the text printing multiple times
					
					userBox.setVisible(false); 
					loginButton.setText("Logout");  //Changes text to Logout
					genreFilterBox.setVisible(true);
					audioFilterButton.setVisible(true);
					
					if(usrHashMap.get(userBox.getSelectedItem()).getAccountType().equals(AccountType.ADMIN)) {  //Checks to see if the user is an admin or customer
						slctUser.setText("You are currently logged in as an admin");
						layeredPane.add(adminPanel);  //Shows us the admin panel
						layeredPane.repaint();   //Repaints the layeredPane to update its visibility to the user
						layeredPane.revalidate();  //Tells the layout manager to recalculate the layout that is necessary
					}else {
						slctUser.setText("You are currently logged in as an customer");	
						layeredPane.add(custPanel);  //Shows us the customer panel
						layeredPane.repaint();
						layeredPane.revalidate();
						tempArray.clear(); //Empties the temporary array so it can refresh it below (so the basket goes when logged out)
						
						tempArray.addAll(tempCreate(CurrentStock));  //Calls a function to create a copy of all objects in the Current Stock array
					}
				
				}else { //If the button says Logout (its the only other option)
					//Resets the basket & temporary array
					basketTextArea.setText(null);  //Empties the basketTextArea 
					tempArray.clear(); //Empties the temporary array
					tempArray.addAll(tempCreate(CurrentStock));  //Resets the temporary array to its original values (everything in the current stock)
					basket.clear();  //Empties the basket
					
					//Brings us back to what it was before logging in visually
					slctUser.setText("Select User");      
					userBox.setVisible(true);   //Allows the user to select the user again after logging out by showing the drop down box
					bookScrollPane.setVisible(false);  //Hides the book browser for users not logged in
					genreFilterBox.setVisible(false);
					audioFilterButton.setVisible(false);
					loginButton.setText("Login");  //Changes text back to Login
					layeredPane.removeAll();
					
				}				
																						
			}
		});
		loginButton.setBounds(527, 171, 188, 50);
		contentPane.add(loginButton);
		
		
		genreFilterBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTextArea(CurrentStock, stockTable, genreFilterBox);  //Sets the text area based on a set of criteria	
			}
		});
		genreFilterBox.setBounds(66, 365, 165, 68);
		contentPane.add(genreFilterBox);
		
		
		audioFilterButton.setBackground(Color.RED);
		audioFilterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setFilter(!getFilter());  //Flips the value of the boolean value
				if(getFilter()) {
					audioFilterButton.setBackground(Color.GREEN);
				}else {
					audioFilterButton.setBackground(Color.RED);
				}
				setTextArea(CurrentStock, stockTable, genreFilterBox);
				
			}
		});
		audioFilterButton.setBounds(68, 257, 163, 87);
		contentPane.add(audioFilterButton);
		audioFilterButton.setVisible(false);
		
		
	}
	
	public void setTextArea(ArrayList<Book> CurrentStock, JTable textArea, JComboBox genreFilterBox) {  //A method to set the text area based on the Filter criteria
		priceCompare bookPriceCompare = new priceCompare(); //Allows us to compare by price
		Collections.sort(CurrentStock, bookPriceCompare);  //Sorts the arrayList based on the bookPriceCompare comparator
				
		DefaultTableModel model = (DefaultTableModel)textArea.getModel(); 
		model.setRowCount(0); //Resets the table
		
		
		
		for(Book currentBook : CurrentStock){
			if(getFilter()) {
				if(currentBook instanceof Audiobook) {
					if(((Audiobook) currentBook).getLength() > 5) {  //It only runs this if it's an instance of Audiobook or it will throw errors
						if(genreFilterBox.getSelectedItem() == "All") {
							Object[] data = currentBook.toString().split(",");  //Splits the toString() method into an array
							model.addRow(data); //adds said array into table	
						}else if(currentBook.getGenre().equals(genreFilterBox.getSelectedItem())) { //This only writes all matching genres to the textArea
							Object[] data = currentBook.toString().split(",");  //Splits the toString() method into an array
							model.addRow(data); //adds said array into table	
						}
					}
				}
				
			}else {
				if(genreFilterBox.getSelectedItem() == "All") {
					Object[] data = currentBook.toString().split(",");  //Splits the toString() method into an array
					model.addRow(data); //adds said array into table
				}else {
					if(currentBook.getGenre().equals(genreFilterBox.getSelectedItem())) {  //This only writes all matching genres to the textArea
						Object[] data = currentBook.toString().split(",");  //Splits the toString() method into an array
						model.addRow(data); //adds said array into table	
					}
				}
			}
		}
	}
	
	public ArrayList<Book> tempCreate(ArrayList<Book> currentStock){  //We use this rather than having to write the same function multiple times in our code
		//This function creates a temporary array that clones the current stock array WITHOUT using the same book objects (makes new identical ones) so the stock is separated
		
		ArrayList<Book> tempArray = new ArrayList<Book>();
		
		for(Book currentBook : currentStock){  //Clones all the books
			tempArray.add(cloneBook(currentBook));  //Calls cloning function and adds said clones into the temporary array		
		}
		
		return tempArray;
	}
	
	public Book cloneBook(Book currentBook) {  //This function creates a clone of an input book
		Book newBook;
		
		if(currentBook instanceof Audiobook) {  //These create a clone of the book, rather than using the old pointer/reference to the other object
			newBook = new Audiobook((Audiobook)currentBook);
		}else if(currentBook instanceof Paperback) {
			newBook = new Paperback((Paperback)currentBook);
		}else { //In this case it is an eBook
			newBook = new eBook((eBook)currentBook);
		}
		return(newBook);
	}
	
	public boolean getFilter() { // setters & getters for a boolean value in the code
		return(audio5Filter);
	}
	public void setFilter(boolean audio5Filter) {
		this.audio5Filter = audio5Filter;
	}
}
