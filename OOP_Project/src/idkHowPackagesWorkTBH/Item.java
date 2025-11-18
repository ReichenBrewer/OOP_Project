package idkHowPackagesWorkTBH;

// Item Class
//
// int quantity - How many items the donation bank has in stock.
// String brand - The brand of the item, such as "General Mills" or "President's Choice" 
// String name - The name of the item, such as "Cheerios" or "Triscuits" 
// String section - Where you can find it in the bank, such as "North" "East" or "Center"
// String type - What type of product it is, such as "Cosmetics" or "Food"
// String[] keywords - Words that someone may want to search for this item by, such as "Vegan" or "Dishwasher-Safe" 
// String description - A description of the item that can be outputed to a customer if they want to know about an item.
public class Item {
	private int quantity;
	private String brand; 
	private String name; 
	private String section;
	private String type; 
	private String[] keywords = new String[15];  
	private String description;
	//Constructor with no keywords, allows items to be added without keywords
	public Item(int quantity, String brand, String name, String section, String type, String description) {
		this.quantity = quantity;
		this.brand = brand; 
		this.name = name; 
		this.section = section;
		this.type = type;   
		this.setKeywords("N/A");
		this.description = description;
	}
	//Constructor to enter all variables of an item at once 
	public Item(int quantity, String brand, String name, String section,String type, String keyword, String description) {
		this.quantity = quantity;
		this.brand = brand; 
		this.name = name; 
		this.section = section;
		this.type = type;   
		this.setKeywords(keyword);
		this.description = description;
	}
	//Constructor to enter all variables from one string, primary use is whenever reading in a file with items line by line.
	public Item(String input) {
		input.replace("\n", "");
		String[] Item = input.split(";");
		this.quantity =  Integer.parseInt(Item[0]);
		this.brand = Item[1]; 
		this.name = Item[2]; 
		this.section = Item[3];  
		this.type = Item[4];   
		this.setKeywords(Item[5]);
		this.description = Item[6];
	}
	@Override
	public String toString() {
		return this.name;
	}
	// Displays an item and all it's attributes to the console. 
	public void displayItem() {
		System.out.print(
			"Name: " + this.name
			+ "\nBrand: " + this.brand
			+ "\nIn Stock: " + this.quantity
			+ "\nSection: " + this.section
			+ "\nType: " + this.type);
		if(!keywords[0].equals("N/A")) {
		System.out.print("\nKeywords: ");
			for(String i:keywords) {
				System.out.print(i + ", ");
			}
		}
		System.out.println("\nItem Description: " + description + "\n");
	}
	// Returns an item with the same formatting used as the input in the single string constructor 
	public String outputItem() {
		String output;
		output = (quantity +";" + brand +";" + name +";" + section + ";" + type +";");
		for(String i:keywords) {
			output += i + "|";
		}
		
		output += ";" + description;
		return output;
	}
	// function to represent a customer buying an item, returns false if no items are left in stock
	public boolean purchase() {
		if(quantity > 0) {
			quantity--;
			return true;
		}
		return false;
	}
	// Overloaded function for purchase, allows for a customer to buy in bulk
	public boolean purchase(int amount) {
		if(quantity >= amount) {
			quantity -= amount;
			return true;
		}
		return false;
	}
	
	// All Getters and Setters for Private Variables 
	public int getQuantity() {
		return quantity;
	}
	public String getBrand() {
		return brand;
	} 
	public String getName() {
		return name;
	} 
	public String getSection() {
		return section;
	}
	public String getType () {
		return type;
	} 
	public String[] getKeywords () {
		return keywords;
	}  
	public String getDescription() {
		return description;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	} 
	public void setName(String name) {
		this.name = name;
	} 
	public void setSection(String section) {
		this.section = section;
	}

	// String keyword - a list of keywords inputed as a String with "|" between each keyword to be added
	public void setKeywords(String keyword) {
		this.keywords = keyword.split("\\|");
	}
	public void setType (String type) {
		this.type = type;
	} 
	public void setDescription(String description) {
		this.description = description;
	}
}
