package idkHowPackagesWorkTBH;
import java.util.*;
import java.io.*;
import java.nio.file.*;

public class DonationBank {
	
	//ArrayList to store all the items that the Food Bank Has
	static ArrayList<Item> items = new ArrayList<Item>();
	//File name for both input and output
	final static String fileName = "data.txt";
	
	public static void main(String[] args) throws IOException{
		readData(fileName);
		for(int i = 0; i < items.size(); i++) {
			items.get(i).displayItem();
		}
		writeData(fileName);
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
			if(item.getName().equalsIgnoreCase(name)) {
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
			if(item.getType().equalsIgnoreCase(type)) {
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
				if (kwd.equalsIgnoreCase(keyword)) {
					list.add(item);
					break; // in case we have duplicate keyword, which WILL happen (humans are stupid)
				}
			}
		}
		return list;
	}

}

