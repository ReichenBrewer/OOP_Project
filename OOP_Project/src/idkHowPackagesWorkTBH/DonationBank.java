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
	static boolean readData(String file) throws IOException {
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
		}else {
			return false;
		}
	}

	// Writes the data from the ArrayList to the file
	//
	// String file - string containing the file name to open
	//
	// Return - returns true if file opens successfully and adds items, if file fails to open, return false.
	// Implementation Unfinished.
	static boolean writeData(String file) throws IOException {
		PrintWriter writeFile = new PrintWriter (file);
		for(int i = 0; i < items.size(); i++) {
			writeFile.println(items.get(i).outputItem());
		}
		writeFile.close();
		return true;
		
	}

}

