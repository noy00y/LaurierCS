package cp213;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.*;

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
 * @author Harriharan Sivakumar
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
	    Menu menu = new Menu("menu.txt"); // create a menu object wit the text file
	    System.out.println(menu.toString()); // print the menu items as a string
	} catch (FileNotFoundException e) { // if you cannot open the file
	    System.out.println("Cannot open menu file"); // throw an error
	} 
	System.exit(0); // at the end (outside handlers), exit
    }

    private List<MenuItem> items = new ArrayList<MenuItem>(); // create list calles items that contains items of the type MenuItems

    /**
     * Constructor from a file of MenuItem strings. Each line in the file
     * corresponds to a MenuItem. You have to read the file line by line and add
     * each MenuItem to the ArrayList of items.
     *
     * @param filename The name of the file containing the menu items.
     * @throws FileNotFoundException Thrown if file not found or cannot be read.
     */
    public Menu(String filename) throws FileNotFoundException {
    	try {
        	File file = new File(filename);
        	Scanner scanObj = new Scanner(file);
        	while (scanObj.hasNext()){
        		String[] currentItem = scanObj.nextLine().split(" "); // split it into a string object (kind of like a list)
				BigDecimal price = new BigDecimal(currentItem[0]); // current menu items price (first in the String[])
				String name; // current menu items name
				name = currentItem[1]; // get the name/first item
				// since you split by spaces, two word names need to be dealt separately
				if (currentItem.length > 2) {
					for (int i = 2; i<currentItem.length; i++) {
						name += " " + currentItem[i];
					}
				}
				MenuItem currentToMenu = new MenuItem(name,price); // create the menu item object
				items.add(currentToMenu);// add the object to the menu item array
        	}
        	scanObj.close(); // close the scanner object
    	} catch (FileNotFoundException e1) {
		    e1.printStackTrace();
		}	
    }

    /**
     * Returns the List's i-th MenuItem.
     *
     * @param i Index of a MenuItem.
     * @return the MenuItem at index i
     */
    public MenuItem getItem(int i) {
    	// assuming index starts from 0 like standard programming practice
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
    	String menuAsString="";
    	if (items.size() >0) {
    		int count=1; // set count to 2
    		menuAsString = String.format("%2s", count)+") "+ this.items.get(0).toString(); // add the first item name and price (covered in toString())
    		// if there is more than one item in the array list
    		if (items.size()>1) {
        		count ++; // count is two here
        		for (int i=1; i<items.size();i++) { // get the rest of the items
        			menuAsString += "\n" + String.format("%2s", count)+") "+ items.get(i).toString(); // add to the string with newline before them
        			count ++; // increase count 
        		}
    		}
    	}
	return menuAsString;
    }

    /**
     * Returns the number of MenuItems in the items List.
     *
     * @return Size of the items List.
     */
    public int size() {
    	// just return the size of the MenuItems array
	return items.size();
    }
}