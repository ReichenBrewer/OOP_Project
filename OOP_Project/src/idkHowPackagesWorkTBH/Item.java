package idkHowPackagesWorkTBH;

// Item Class
//
// int qty - How many items the donation bank has in stock.
// String brand - The brand of the item, such as "General Mills" or "President's Choice" 
// String name - The name of the item, such as "Cheerios" or "Triscuits" 
// String section - Where you can find it in the bank, such as "North" "East" or "Center"
// String type - What type of product it is, such as "Cosmetics" or "Food"
// String[] keywords - Words that someone may want to search for this item by, such as "Vegan" or "Dishwasher-Safe" 
// String desc - A description of the item that can be outputted to a customer if they want to know about an item.
public class Item {
	private int qty;
	private String brand; 
	private String name; 
	private String section;
	private String type; 
	private String[] keywords = new String[15];  
	private String desc;
	//Constructor with no keywords, allows items to be added without keywords
	public Item(int qty, String brand, String name, String section, String type, String desc) {
		this.qty = qty;
		this.brand = brand; 
		this.name = name; 
		this.section = section;
		this.type = type;   
		this.desc = desc;
	}
	//Constructor to enter all variables of an item at once 
	public Item(int qty, String brand, String name, String section,String type, String keyword, String desc) {
		this.qty = qty;
		this.brand = brand; 
		this.name = name; 
		this.section = section;
		this.type = type;   
		this.setKeywords(keyword);
		this.desc = desc;
	}
	//Constructor to enter all variables from one string, primary use is whenever reading in a file with items line by line.
	public Item(String input) {
		input.replace("\n", "");
		String[] Item = input.split(";");
		this.qty =  Integer.parseInt(Item[0]);
		this.brand = Item[1]; 
		this.name = Item[2]; 
		this.section = Item[3];  
		this.type = Item[4];   
		this.setKeywords(Item[5]);
		this.desc = Item[6];
	}
	@Override
	public String toString() {
		return this.name;
	}
	// Displays an item and all it's attributes to the console. 
	public void displayItem() {
		System.out.print(
			"Quantity: " + this.qty
			+ "\nBrand: " + this.brand
			+ "\nName: " + this.name
			+ "\nSection: " + this.section
			+ "\nType: " + this.type
			+ "\nKeywords: "
		);
		for(String i:keywords) {
			System.out.print(i + ", ");
		}
		
		System.out.println("\nItem Description: " + desc);
	}
	// Returns an item with the same formatting used as the input in the single string constructor 
	public String outputItem() {
		String output;
		output = (qty +";" + brand +";" + name +";" + section + ";" + type +";");
		for(String i:keywords) {
			output += i + "|";
		}
		
		output += ";" + desc;
		return output;
	}
	
	// All Getters and Setters for Private Variables 
	public int getQuantity() {
		return qty;
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
		return desc;
	}
	
	public void setQuantity(int qty) {
		this.qty = qty;
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

	// String keyword - a list of keywords inputted as a String with "|" between each keyword to be added
	public void setKeywords(String keyword) {
		this.keywords = keyword.split("\\|");
	}
	public void setType (String type) {
		this.type = type;
	} 
	public void setDescription(String desc) {
		this.desc = desc;
	}
}
