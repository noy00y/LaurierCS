package cp213;

import java.util.Scanner;

/**
 * Wraps around an Order object to ask for MenuItems and quantities.
 *
 * @author Harriharan Sivakumar
 * @author Abdul-Rahman Mawlood-Yunis
 * @author David Brown
 * @version 2021-11-01
 */
public class Cashier {

    private Menu menu = null;

    /**
     * Constructor.
     *
     * @param menu A Menu.
     */
    public Cashier(Menu menu) {
	this.menu = menu;
    }

    /**
     * Asks for commands and quantities. Prints a receipt when all orders have been
     * placed.
     *
     * @return the completed Order.
     */
    public Order takeOrder() {
    	Order currentOrder = new Order();// create an order object
    	Scanner sc = new Scanner(System.in);// create a scanner object - for user string inputs
    	String errorMsg = "Not a valid number"; // message to print when there is an error
    	String menuStr = "\nMenu:\n"+this.menu.toString() + "\nPress 0 when done.\n"+"Press any other key to see the menu again.\n"; // the entire menu with all the items and the heading
    	
    	if (menu != null) { // if the menu is not empty
    		System.out.println("Welcome to WLU Foodorama!"); // print the heading
    		System.out.println(menuStr); // print the menu

    		String userInput=""; // to enter the while loop
    		
    		
    		while (!userInput.equals("0")) { // loop while user input is not equal to zero
        		
    			// Get the user input
        		System.out.print("Command: ");
        		userInput = sc.nextLine();
        		// Case: Deals with everything that is not a zero (invalids and valid)
        		
        		
        		if (!userInput.equals("0")){ // Deals with all the non zero cases
        			try { // if its a number (integer)
        				int inputAsInt = Integer.parseInt(userInput); // try converting the input to an integer
        				// Case: If it is an integer
        				if (inputAsInt>this.menu.size()) { // Case: larger than size = print the menu
        					System.out.println(menuStr);
        				} else if (0<inputAsInt & inputAsInt<=this.menu.size()) { // Case: within correct rage ( larger than zero and smaller than or equal to size
        					// Get Quantity
        					System.out.print("How many do you want? ");
        					String quantity = sc.nextLine();
        					try { // Case: Quantity is an integer as a string
        						int quantityInt = Integer.parseInt(quantity);
        						if (quantityInt>0) { // if the quantity is greater than zero
        							currentOrder.add(this.menu.getItem(inputAsInt-1), quantityInt); // add to the order map
        						}else { // if the quantity is invalid 
        							System.out.println(errorMsg); // print error message
        						}
        					} catch (NumberFormatException nfe){ // Case: Quantity is not an integer as a string (invalid input)
        						System.out.println(errorMsg);
        					}
        				}
        			} catch (NumberFormatException nfe) { // if its not a number
        				System.out.println(errorMsg);// print error message
        			}
        		}
    		}
    		// outside the loop: When the input is equal to zero
    		System.out.println("----------------------------------------");
    		System.out.println("Receipt");
    		// Get the Receipt for the order object
    		System.out.println(currentOrder.toString());
    	}
	return currentOrder;
    }
}