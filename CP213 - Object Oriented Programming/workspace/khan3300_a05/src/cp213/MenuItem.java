package cp213;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

/**
 * Defines the name and price of a menu item. Price is stored as a BigDecimal to
 * avoid rounding errors.
 *
 * @author your name here
 * @author Abdul-Rahman Mawlood-Yunis
 * @author David Brown
 * @version 2021-11-01
 */
public class MenuItem {

    private String name = null;
    private BigDecimal price = null;

    /**
     * Constructor.
     *
     * @param name  Name of the menu item.
     * @param price Price of the menu item.
     */
    public MenuItem(final String name, final BigDecimal price) {
	this.name = name;
	this.price = price;
	// Set to use 2 decimal points in calculations
	this.price.setScale(2, RoundingMode.HALF_EVEN);
    }

    /**
     * Alternate constructor. Converts a double price to BigDecimal.
     *
     * @param name  Name of the menu item.
     * @param price Price of the menu item.
     */
    public MenuItem(final String name, final double price) {
	// Call super constructor
	this(name, new BigDecimal(price));
    }

    /**
     * name getter
     *
     * @return Name of the menu item.
     */
    public String getName() {
    	return this.name;
    }

    /**
     * price getter
     *
     * @return Price of the menu item.
     */
    public BigDecimal getPrice() {
    	return this.price;
    }

    /**
     * Returns a MenuItem as a String in the format:
     *
     * <pre>
hot dog      $ 1.25
pizza        $10.00
</pre>
     */
    @Override
    public String toString() {
    	String price = String.format("$%5.2f", this.price);
    	
    	// Create padding:
    	int padding = 19 - this.name.length() - price.length();
    	String pad = "";
    	for (int i = 0; i < padding; i++) {
    		pad = pad + " ";
    	}
    	
    	return this.name + pad + price;
    }
}