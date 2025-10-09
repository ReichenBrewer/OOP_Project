package idkHowPackagesWorkTBH;

// Item Class
//
// int cost - How much the item costs, this is an int to make it easier to search by prices.
// String brand - The brand of the item, such as "General Mills" or "President's Choice" 
// String name - The name of the item, such as "Cheerios" or "Triscuits" 
// String section - Where you can find it in the bank, such as "North" "East" or "Center"
// String type - What type of product it is, such as "Cosmetics" or "Food"
// String[] keywords - Words that someone may want to search for this item by, such as "Vegan" or "Dishwasher-Safe" 
// String desc - A description of the item that can be outputted to a customer if they want to know about an item.
public class Item {
	int cost;
	String brand; 
	String name; 
	String section;
	String type; 
	String[] keywords = new String[15];  
	String desc;
	//Constructor with no keywords, allows items to be added without keywords
	Item(int cost, String brand, String name, String section, String type, String desc) {
		this.cost = cost;
		this.brand = brand; 
		this.name = name; 
		this.section = section;
		this.type = type;   
		this.desc = desc;
	}
	//Constructor to enter all variables of an item at once 
	Item(int cost, String brand, String name, String section,String type, String keyword, String desc) {
		this.cost = cost;
		this.brand = brand; 
		this.name = name; 
		this.section = section;
		this.type = type;   
		this.addKeywords(keyword);
		this.desc = desc;
	}
	//Constructor to enter all variables from one string, primary use is whenever reading in a file with items line by line.
	Item(String input) {
		input.replace("\n", "");
		String[] Item = input.split(";");
		this.cost =  Integer.parseInt(Item[0]);
		this.brand = Item[1]; 
		this.name = Item[2]; 
		this.section = Item[3];  
		this.type = Item[4];   
		this.addKeywords(Item[5]);
		this.desc = Item[6];
	}
	// Adds keywords to an item
	//
	// String keyword - a list of keywords inputted as a String with "|" between each keyword to be added
	void addKeywords(String keyword) {
		this.keywords = keyword.split("\\|");
	}
	// Displays an item and all it's attributes to the console. 
	void displayItem() {
		System.out.print("Costs: " + cost + "\nBrand: " + brand + "\nName: " + name + "\nSection: " + section + "\nType: " + type + "\nKeywords: ");
		for(String i:keywords) {
			System.out.print(i + ", ");
		}
		
		System.out.println("\nItem Description: " + desc);
	}
	// Returns an item with the same formatting used as the input in the single string constructor 
	String outputItem() {
		String output;
		output = (cost +";" + brand +";" + name +";" + section + ";" + type +";");
		for(String i:keywords) {
			output += i + "|";
		}
		
		output += ";" + desc;
		return output;
	}
		
}
