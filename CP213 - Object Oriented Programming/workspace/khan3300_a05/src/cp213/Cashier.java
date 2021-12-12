package cp213;

import java.util.Scanner;

/**
 * Wraps around an Order object to ask for MenuItems and quantities.
 *
 * @author your name here
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

    	// Declarations:
    	Order newOrder = new Order();
    	System.out.println("Welcome to WLU Foodorama!\n");
    	this.printAux();
    	
    	// Printing Iterations:
    	Scanner scanner = new Scanner(System.in);
    	String userInput = "";
    	
    	while(!userInput.equals("0")) {
    		
    		// User Prompt
    		System.out.println("Command: ");
    		userInput = scanner.nextLine();
    		
    		// Input Validation
    		if (!userInput.equals("0")) {
    			try {
    				int intInput = Integer.parseInt(userInput);
    				
    				// Out of Range:
    				if (intInput > this.menu.size()) {
    					this.printAux();
    				}
    				
    				// Within Range:
    				else if (0 < intInput && intInput <= this.menu.size()) {
    					
    					System.out.println("How many do you want?");
    					String qTemp = scanner.nextLine();
    					
    					// Valid:
    					try {
    						int quantity = Integer.parseInt(qTemp);
    						
    						if (quantity > 0) {
    							newOrder.add(this.menu.getItem(intInput - 1), quantity);
    						}

    						// Invalid Quantity
    						else {
    							System.out.println("Not a valid number");
    						}
    						
    					// Error Catch
    					} catch (NumberFormatException nfe){ // Case: Quantity is not an integer as a string (invalid input)
    						System.out.println("Not a valid number");
    					}
    				}
    				
    			// Error Catch:
    			} catch (NumberFormatException nfe) { 
    				System.out.println("Not a valid number");
    			}
    		}
    	}
    	
    	// Reciept:
    	System.out.println("----------------------------------------");
		System.out.println("Receipt");
    	
		System.out.println(newOrder.toString());
    	
    	// Returning Order:	
    	return newOrder;
    }
    
    // Printing Menu
    public void printAux() {
    	System.out.println("Menu:\n" + menu.toString() + "Press 0 when done.\n" + "Press any other key to see the menu again.\n");
    }
}