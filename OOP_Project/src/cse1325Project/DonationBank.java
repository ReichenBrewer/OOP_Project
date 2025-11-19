package cse1325Project;
import java.util.*;
import java.io.*;
import java.nio.file.*;

public class DonationBank {
	
	//ArrayList to store all the items that the Food Bank Has
	static ArrayList<Item> items = new ArrayList<Item>();
	//File name for both input and output
	final static String FILE_NAME = "data.txt";
	final static String ADMIN_PASSWORD = "1234";
	public static void main(String[] args) throws IOException{
		int choice = 0;
		readData(FILE_NAME);
		Scanner sc = new Scanner(System.in);
		while(choice != 5) {
			System.out.print("Hello, and welcome to the Donation Bank's Database, how can we help you today?"
							+ "\n1. Donate an Item"
							+ "\n2. Search for an Item"
							+ "\n3. Purchase an Item"
							+ "\n4. See Admin Commands"
							+ "\n5. Exit\n"
					);
			choice = sc.nextInt();
			sc.nextLine();
			
			
			switch (choice) {
			// Donating an Item
			case 1:
				System.out.print("Name of the Item you are donating: ");
				String name = sc.nextLine();
				boolean alreadyExists = false;
				Item donation;
				for(int i = 0; i < items.size(); i++) {
					if(items.get(i).getName().equalsIgnoreCase(name)) {
						alreadyExists = true;
						donation = items.get(i);
						System.out.print("Quantity of " + name + " you are donating: ");
						int qty = sc.nextInt();
						sc.nextLine();
						donation.setQuantity(donation.getQuantity() + qty);
					}
				}
				if(!alreadyExists) {
					System.out.print("Quantity of " + name + " you are donating: ");
					int qty = sc.nextInt();
					sc.nextLine();
					System.out.print("Brand of " + name + ": ");
					String brand = sc.nextLine();
					System.out.print("Type of Product " + name + " is (Food, Clothing, etc.): ");
					String section = sc.nextLine();
					System.out.print("Section to place " + name + " in (Breakfast, Snacks, etc): ");
					String type = sc.nextLine();
					System.out.print("Description of " + name + ": ");
					String desc = sc.nextLine();
					donation = new Item(qty, brand, name, section, type, desc);
					items.add(donation);
				}
				break;
			// Searching for an Item
			case 2:
				String lookup = "food";
				System.out.print("Enter Search Term: ");
				lookup = sc.nextLine();
				System.out.println("\nSearch Results for Items Relating to \"" + lookup + "\": ");
				ArrayList<Item> search = getItemsByName(lookup);
				search.addAll(getItemsByType(lookup));
				search.addAll(getItemsByKeyword(lookup));
				if(search.size() == 0) {
					System.out.println("\nNo Results\n");
				}
				printAllItems(search);
				break;
			// Purchasing an item
			case 3:
				System.out.print("Name of the Item you are purchasing: ");
				String input = sc.nextLine();
				boolean exists = false;
				for(int i = 0; i < items.size(); i++) {
					if(items.get(i).getName().equalsIgnoreCase(input)) {
						exists = true;
						if(items.get(i).purchase()) {
							System.out.println("Purchased " + items.get(i).getName());			
						}else {
							System.out.println(items.get(i).getName() + " is out of stock");		
						}
					}
				}
				if(!exists) {
					System.out.println(input + " does not exist in our system, please try searching for an item before purchasing.");	
				}
				break;
			// Ask for a password, then give Admin commands
			case 4:
				System.out.print("Enter the Admin Password: ");
				String passwordAttempt = sc.nextLine();
				if(passwordAttempt.equals(ADMIN_PASSWORD)) {
					adminCommands(sc);
				}else {
					System.out.println("Password inputted does not match the expected password.");
				}
				break;
			// Exit Program
			case 5:
				break;
			// In case of non valid input
			default:
				System.out.println("Unknown input, please input a valid number.");
				break;
			}
			System.out.println("");
			
		}
		sc.close();
		writeData(FILE_NAME);
		System.out.println("Program Exitted Safely");
	}
	
	// Function that allows you to access commands general customers should not have access to.
	public static void adminCommands(Scanner sc) {
		System.out.print("Thank you for Accessing the Admin Terminal, please choose the action you would like to take:"
				+ "\n1. Remove an Item"
				+ "\n2. Edit Product Info"
				+ "\n3. Display Full Inventory"
				+ "\n4. Exit Admin Terminal\n"
		);
		int choice = sc.nextInt();
		sc.nextLine();
		
		
		switch (choice) {
			// Remove an item for the list
			case 1:
				System.out.print("Name of the Item you want to remove: ");
				String input = sc.nextLine();
				boolean exists = false;
				for(int i = 0; i < items.size(); i++) {
					if(items.get(i).getName().equalsIgnoreCase(input)) {
						exists = true;
						System.out.println("Successfully Removed " + items.get(i).getName());		
						items.remove(i);
					}
				}
				if(!exists) {
					System.out.println(input + " does not exist in our system, please try searching for an item before removing it.");	
				}
				break;
			// Edit the product info
			case 2:
				System.out.print("Name of the Item you want to Edit: ");
				String inputItem = sc.nextLine();
				boolean itemExists = false;
				for(int i = 0; i < items.size(); i++) {
					if(items.get(i).getName().equalsIgnoreCase(inputItem)) {
						itemExists = true;	
						System.out.print("What do you want to modify about " + items.get(i).getName() + ":"
								+ "\n1. Change Name"
								+ "\n2. Change Brand"
								+ "\n3. Change Stock"
								+ "\n4. Change Location"
								+ "\n5. Change Type"
								+ "\n6. Change Keywords"
								+ "\n7. Change Description\n"
						);
						choice = sc.nextInt();
						sc.nextLine();
						items.set(i, editItemInfo(items.get(i), choice, sc));
					}
				}
				if(!itemExists) {
					System.out.println(inputItem + " does not exist in our system, please try searching for an item before editing it.");	
				}
				break;
			// Display All Items in the system
			case 3:
				System.out.println("-------------------------------------------------------------\n"
								+ "Full Inventory at the Donation Bank: \n"
								+ "-------------------------------------------------------------");
				
				printAllItems(items);
				break;
			case 4:
				break;
			default:
				System.out.println("Unknown input, please input a valid number.");
				break;
		}
	}
	
	// Takes in an Item and allows an admin to make modifications to it
	//
	// Item toEdit - the Item object to be operated on
	public static Item editItemInfo(Item toEdit, int operation, Scanner sc) {
		String input = "";
		switch (operation) {
		// Change the Name of the Item
			case 1:
				System.out.print("What would you like to change the name of "+ toEdit.getName() + " to? (Currently " + toEdit.getName() + ") : ");
				input = sc.nextLine();
				toEdit.setName(input);
				break;
		// Change the Brand of the Item
			case 2:
				System.out.print("What would you like to change the brand of "+ toEdit.getName() + " to? (Currently " + toEdit.getBrand() + ") : ");
				input = sc.nextLine();
				toEdit.setBrand(input);
				break;
		// Change the Current Stock of the Item
			case 3:
				System.out.print("What would you like to change the quantity of "+ toEdit.getName() + " to? (Currently " + toEdit.getQuantity() + ") : ");
				int num = sc.nextInt();
				toEdit.setQuantity(num);
				sc.nextLine();
				break;
		// Change the Location of the Item
			case 4:
				System.out.print("What would you like to change the location of "+ toEdit.getName() + " to? (Currently " + toEdit.getSection() + ") : ");
				input = sc.nextLine();
				toEdit.setSection(input);
				break;
		// Change the Type of the Item
			case 5:
				System.out.print("What would you like to change the type of "+ toEdit.getName() + " to? (Currently " + toEdit.getType() + ") : ");
				input = sc.nextLine();
				toEdit.setType(input);
				break;
		// Change the Keywords of the Item
			case 6:
				System.out.print("What would you like to change the keywords of "+ toEdit.getName() + " to? (Break up multiple keywords with the \"|\" character): ");
				input = sc.nextLine();
				toEdit.setKeywords(input);
				break;
		// Change the Description of the Item
			case 7:
				System.out.print("What would you like to change the description of "+ toEdit.getName() + " to? (Currently " + toEdit.getDescription() + ") : ");
				input = sc.nextLine();
				toEdit.setDescription(input);
				break;
			default:
				System.out.print("Unknown input, please input a valid number.");
		}
		return toEdit;
	}
	
	
	// Reads the data from the file and adds it to the items ArrayList
	//
	// String file - string containing the file name to open
	//
	// Return - returns true if file opens successfully and adds items, if file fails to open, return false.
	public static boolean readData(String file) throws IOException {
		File database = new File(file);
		if(database.exists()) {
			Scanner sc = new Scanner(database);
			while(sc.hasNextLine()) {
				Item tempCard = new Item(sc.nextLine());
				items.add(tempCard);
			}
			sc.close();
			Files.newBufferedWriter(Paths.get("data.txt") , StandardOpenOption.TRUNCATE_EXISTING);
			return true;
		} else {
			return false;
		}
	}

	// Writes the data from the ArrayList to the file
	//
	// String file - string containing the file name to open
	//
	// Return - returns true if file opens successfully and adds items, if file fails to open, return false.
	// Implementation Unfinished.
	public static boolean writeData(String file) throws IOException {
		PrintWriter writeFile = new PrintWriter (file);
		for(int i = 0; i < items.size(); i++) {
			writeFile.println(items.get(i).outputItem());
		}
		writeFile.close();
		return true;
		
	}

	// Returns an ArrayList of Items, with all items
	// having their name be a substring match of parameter "name".
	public static ArrayList<Item> getItemsByName(String name) {
		ArrayList<Item> list = new ArrayList<Item>(items.size());
		for (Item item : items) {
			if(item.getName().toLowerCase().contains(name.toLowerCase())) {
				list.add(item);
			}
		}
		return list;
	}

	// Returns an ArrayList of Items, with all items
	// having their type be a substring match of type.
	public static ArrayList<Item> getItemsByType(String type) {
		ArrayList<Item> list = new ArrayList<Item>(items.size());
		for (Item item : items) {
			if(item.getType().toLowerCase().contains(type.toLowerCase())) {
				list.add(item);
			}
		}
		return list;
	}

	// search using a singular keyword. keywords must
	// be exact, barring capitalization.
	// this has O(n^2) efficiency. too bad!
	public static ArrayList<Item> getItemsByKeyword(String keyword) {
		ArrayList<Item> list = new ArrayList<Item>(items.size());
		for (Item item : items) {
			String[] keywords = item.getKeywords();
			for (String kwd : keywords) {
				if (kwd.toLowerCase().contains(keyword.toLowerCase())) {
					list.add(item);
					break; // in case we have duplicate keyword, which WILL happen (humans are stupid)
				}
			}
		}
		return list;
	}
	
	
	// Function to output all data from an array of Items
	public static void printAllItems(ArrayList<Item> items) {
		for (Item inventory : items) {
			inventory.displayItem();
		}
	}

}