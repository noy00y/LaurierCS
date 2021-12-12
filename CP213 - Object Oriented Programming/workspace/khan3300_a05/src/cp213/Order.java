package cp213;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Stores a HashMap of MenuItem objects and the quantity of each MenuItem
 * ordered. Each MenuItem may appear only once in the HashMap.
 *
 * @author your name here
 * @author Abdul-Rahman Mawlood-Yunis
 * @author David Brown
 * @version 2021-11-01
 */
public class Order implements Printable {

    /**
     * The current tax rate on menu items.
     */
    public static final BigDecimal TAX_RATE = new BigDecimal(0.13);
    private Map<MenuItem, Integer> items = new HashMap<MenuItem, Integer>();

    /**
     * Update the quantity of a particular MenuItem in an order.
     *
     * @param item     The MenuItem to purchase - the HashMap key.
     * @param quantity The number of the MenuItem to purchase - the HashMap value.
     */
    public void add(MenuItem item, int quantity) {

    	// Item already in hash map?:
    	if (items.containsKey(item)) {
    		items.put(item, items.get(item) +quantity);
    	}

    	// Else:
    	else {
    		items.put(item, quantity);
    	}
    }

    /**
     * Calculates the total value of all MenuItems and their quantities in the
     * HashMap.
     *
     * @return the total price for the MenuItems ordered.
     */
    public BigDecimal getSubTotal() {
    	
    	BigDecimal subTotal = new BigDecimal("0");
    	for(Map.Entry<MenuItem, Integer> set: items.entrySet()) {
    		subTotal = subTotal.add(set.getKey().getPrice().multiply(new BigDecimal(set.getValue().toString()))); 
    	}

    	return subTotal;
    }

    /**
     * Calculates and returns the total taxes to apply to the subtotal of all
     * MenuItems in the order. Tax rate is TAX_RATE.
     *
     * @return total taxes on all MenuItems
     */
    public BigDecimal getTaxes() {
    	return this.getSubTotal().multiply(TAX_RATE);
    }

    /**
     * Calculates and returns the total price of all MenuItems order, including tax.
     *
     * @return total price
     */
    public BigDecimal getTotal() {
		// Declaratoins:
    	BigDecimal totalRate = new BigDecimal(1.13);
    	return this.getSubTotal().multiply(totalRate);
    }

    /**
     * Returns a String version of a receipt for all the MenuItems in the order.
     */
    @Override
    public String toString() {

    	// Declarations
    	String bill = "";
    	
    	//  Order Items
    	for (Map.Entry<MenuItem,Integer> temp: items.entrySet()) {
    		bill += String.format("%-15s", temp.getKey().getName()) + String.format("%-2s", temp.getValue()) + "@ $" + String.format("%5.2f", temp.getKey().getPrice().doubleValue()) + " = $" + String.format("%6s", temp.getKey().getPrice().multiply(new BigDecimal(temp.getValue().toString())).toString()) + "\n\n";
    	}
    	
    	// Order Final:
    	bill += String.format("%-28s", "Subtotal:") + "$" + String.format("%6.2f", this.getSubTotal().doubleValue())+"\n";
    	bill += String.format("%-28s", "Taxes:") + "$" + String.format("%6.2f", this.getTaxes().doubleValue())+"\n";
    	bill += String.format("%-28s", "Total:") + "$" + String.format("%6.2f", this.getTotal().doubleValue())+"\n";
    	
    	return bill;
    }

    /**
     * Replaces the quantity of a particular MenuItem in an Order with a new
     * quantity. If the MenuItem is not in the order, it is added. If quantity is 0
     * or negative, the MenuItem is removed from the Order.
     *
     * @param item The MenuItem to update
     * @param quantity The quantity to apply to item
     */
    public void update(MenuItem item, int quantity) {
    	
    	// Case 1: No Key --> Add as New
    	if (!items.containsKey(item)) {
    		items.put(item, quantity); 
    	} 
    	
    	// Case 2: Existing Key but no Quantity --> Remove Key
    	else if (items.containsKey(item) & (quantity == 0 | quantity == -1)) { 
    		items.remove(item); 
    	} 
    	
    	// Existing Key and Quantity --> Update:
    	else { 
    		items.replace(item, quantity); 
    	}
    }

    /*
     * Implements the Printable interface print method. Prints lines to a Graphics2D
     * object using the drawString method. Prints the current contents of the Order.
     */
    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
	int result = PAGE_EXISTS;

	if (pageIndex == 0) {
	    Graphics2D g2d = (Graphics2D) graphics;
	    g2d.setFont(new Font("MONOSPACED", Font.PLAIN, 12));
	    g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

	    // your code here - write the contents of Order using drawString
	    g2d.drawString(this.toString(), 100, 100);
	} else {
	    result = NO_SUCH_PAGE;
	}
	return result;
    }
}