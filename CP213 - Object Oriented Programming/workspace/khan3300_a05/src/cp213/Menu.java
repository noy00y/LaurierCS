package cp213;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Stores a List of MenuItems and provides a method return these items in a
 * formatted String. May be constructed from an existing List or from a file
 * with lines in the format:
 *
 * <pre>
1.25 hot dog
10.00 pizza
...
 * </pre>
 *
 * @author your name here
 * @author Abdul-Rahman Mawlood-Yunis
 * @author David Brown
 * @version 2021-11-01
 */
public class Menu {

    /**
     * For testing. Reads contents of "menu.txt" from root of project.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {
	try {
	    Menu menu = new Menu("menu.txt");
	    System.out.println(menu.toString());
	} catch (FileNotFoundException e) {
	    System.out.println("Cannot open menu file");
	}
	System.exit(0);
    }

    private List<MenuItem> items = new ArrayList<MenuItem>();

    /**
     * Constructor from a file of MenuItem strings. Each line in the file
     * corresponds to a MenuItem. You have to read the file line by line and add
     * each MenuItem to the ArrayList of items.
     *
     * @param filename The name of the file containing the menu items.
     * @throws FileNotFoundException Thrown if file not found or cannot be read.
     */
    public Menu(String filename) throws FileNotFoundException {

    	// Declarations:
    	File file = new File("menu.txt");
    	Scanner fileReader = new Scanner(file);
    	
    	// Iteration:
    	while (fileReader.hasNextLine()) {
    		String temp = fileReader.nextLine();
    		
    		// Iterate through String Data:
    		String price = "";
    		String name = "";
    		
    		for (int i = 0; i < temp.length(); i++) {
    			
    			// Price
    			if (Character.isDigit(temp.charAt(i)) || temp.charAt(i) == '.') {
    				price = price + temp.charAt(i);
    			}
    			
    			// Name:
    			else if (Character.isLetter(temp.charAt(i))) {
    				name = name + temp.charAt(i);
    			}
    		}
    		
//    		MenuItem data = new MenuItem(name, Double.parseDouble(price));
    		items.add(new MenuItem(name, Double.parseDouble(price)));
    	}
    	
    	// Close File:
    	fileReader.close();
    }

    /**
     * Returns the List's i-th MenuItem.
     *
     * @param i Index of a MenuItem.
     * @return the MenuItem at index i
     */
    public MenuItem getItem(int i) {
    	return items.get(i);
    }

    /**
     * Returns the Menu items as a String in the format:
     *
     * <pre>
    5) poutine      $ 3.75
    6) pizza        $10.00
     * </pre>
     *
     * where n) is the index + 1 of the MenuItems in the List.
     */
    @Override
    public String toString() {

    	// your code here
    	String output = "";
    	int counts = 1;
    	for (MenuItem item : items) {
    		output = output + String.valueOf(counts) + ") " + item.toString() + "\n";
    		counts++;
    	}
    	
	return output;
    }

    /**
     * Returns the number of MenuItems in the items List.
     *
     * @return Size of the items List.
     */
    public int size() {

    	// your code here
    	int size = 0;
    	for (MenuItem item : items) {
    		size++;
    	}
    	
	return size;
    }
}