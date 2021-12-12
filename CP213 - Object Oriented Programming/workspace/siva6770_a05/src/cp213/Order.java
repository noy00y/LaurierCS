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
 * @author Harriharan Sivakumar
 * @author Abdul-Rahman Mawlood-Yunis
 * @author David Brown
 * @version 2021-11-01
 */
public class Order implements Printable {

    /**
     * The current tax rate on menu items.
     */
    public static final BigDecimal TAX_RATE = new BigDecimal(0.13); // as a percent
    private Map<MenuItem, Integer> items = new HashMap<MenuItem, Integer>();

    /**
     * Update the quantity of a particular MenuItem in an order.
     *
     * @param item     The MenuItem to purchase - the HashMap key.
     * @param quantity The number of the MenuItem to purchase - the HashMap value.
     */
    public void add(MenuItem item, int quantity) {
    	if (items.containsKey(item)) { // if the item already exists in the order items
    		// replace the current order items quantity to the sum of the previous + current quantity 
    		items.replace(item, items.get(item)+quantity); 
    	}
    	else {
    		items.putIfAbsent(item, quantity); // add it if it exists
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
    		// add the current items price * quantity to subtotal
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
    	BigDecimal daTaxoz = new BigDecimal(String.format("%.4s", (this.getSubTotal().multiply(TAX_RATE)).toString()));
	return daTaxoz; // multiply the tax rate with the sub total
    }

    /**
     * Calculates and returns the total price of all MenuItems order, including tax.
     *
     * @return total price
     */
    public BigDecimal getTotal() {
    	BigDecimal subtotal = this.getSubTotal();
    	BigDecimal daTaxoz = this.getTaxes();
    	BigDecimal daTotal = new BigDecimal(String.format("%.4s", (subtotal.add(daTaxoz)).toString()));
	return daTotal;
    }

    /**
     * Returns a String version of a receipt for all the MenuItems in the order.
     */
    @Override
    public String toString() {
    	String orderBill="";
    	// get the name, quantity @ price = subtotal for each item in the hash map
    	for (Map.Entry<MenuItem,Integer> set: items.entrySet()) {
    		orderBill += String.format("%-15s", set.getKey().getName()) + String.format("%-2s", set.getValue()) + "@ $" + String.format("%5.2f", set.getKey().getPrice().doubleValue()) + " = $" + String.format("%6s", set.getKey().getPrice().multiply(new BigDecimal(set.getValue().toString())).toString()) + "\n";
    	}
    	// add the sub total, taxes and then the total
    	orderBill += String.format("%-28s", "Subtotal:") + "$" + String.format("%6.2f", this.getSubTotal().doubleValue())+"\n";
    	orderBill += String.format("%-28s", "Taxes:") + "$" + String.format("%6.2f", this.getTaxes().doubleValue())+"\n";
    	orderBill += String.format("%-28s", "Total:") + "$" + String.format("%6.2f", this.getTotal().doubleValue())+"\n";
    return orderBill;
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
    	if (!items.containsKey(item)) { // if it does not contain the key
    		items.put(item, quantity); // add the key
    	} else if (items.containsKey(item) & (quantity == 0 | quantity == -1)) { // if it contains the key and the quantity is -1 or 0
    		items.remove(item); // remove the key
    	} else { // if it contains the key and the quantity is greater than 0
    		items.replace(item, quantity); // replace the key with the new value
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
	    Graphics2D g2d = (Graphics2D) graphics; // create a 2d graphics object for the graphics item
	    g2d.setFont(new Font("MONOSPACED", Font.PLAIN, 13)); // sets the font obuject for the Graphics2D object
	    g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY()); // translated to avoid clipping
	    // your code here - write the contents of Order using drawStringss
	    // drawString just writes the string onto the window
	    g2d.drawString(this.toString(), 100, 100);
	    
	} else {
	    result = NO_SUCH_PAGE;
	}
	return result;
    }
}