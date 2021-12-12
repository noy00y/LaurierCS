package cp213;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.print.PrinterJob;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * The GUI for the Order class.
 *
 * @author your name here
 * @author Abdul-Rahman Mawlood-Yunis
 * @author David Brown
 * @version 2021-11-01
 */
@SuppressWarnings("serial")
public class OrderPanel extends JPanel {

    /**
     * Implements an ActionListener for the 'Print' button. Prints the current
     * contents of the Order to a system printer or PDF.
     */
    private class PrintListener implements ActionListener {

	@Override
	public void actionPerformed(final ActionEvent e) {
	    final PrinterJob printJob = PrinterJob.getPrinterJob();
	    printJob.setPrintable(order);

	    if (printJob.printDialog()) {
		try {
		    printJob.print();
		} catch (final Exception printException) {
		    System.err.println(printException);
		}
	    }
	}
    }

    /**
     * Implements a FocusListener on a JTextField. Accepts only positive integers,
     * all other values are reset to 0. Adds a new MenuItem to the Order with the
     * new quantity, updates an existing MenuItem in the Order with the new
     * quantity, or removes the MenuItem from the Order if the resulting quantity is
     * 0.
     */
    private class QuantityListener implements FocusListener {

		// Declarations
	    JTextField field = null;
	    MenuItem item = null;
	    JLabel subtotalLabel = null;
	    JLabel taxLabel = null;
	    JLabel totalLabel = null;
	
	    // Construct
	    public QuantityListener (JTextField temp, MenuItem item, JLabel subtotalLabel, JLabel taxLabel, JLabel totalLabel) {
	    	this.field = temp;
	    	this.item = item;
	    	this.subtotalLabel = subtotalLabel;
	    	this.taxLabel = taxLabel;
	    	this.totalLabel = totalLabel;
	    }
    
		@Override
		public void focusGained(final FocusEvent evt) {
	
		    // your code here
			field.selectAll();
		}

		@Override
		public void focusLost(final FocusEvent evt) {
	
		    // Declarations:
			boolean isInt = true;
			int temp = 0;
			
			// Validation:
			try {
				temp = Integer.parseInt(field.getText());
			} catch(NumberFormatException nfe) {
				isInt = false;
			}
			
			// Updating Totals
			if (isInt && temp >= 0) {
				order.update(item, temp);
				subtotalLabel.setText(String.format("$%.2f", order.getSubTotal().doubleValue()));
				taxLabel.setText(String.format("$%.2f", order.getTaxes().doubleValue()));
				totalLabel.setText(String.format("$%.2f", order.getTotal().doubleValue()));
			}
			
			else {
				order.update(item, 0);
				subtotalLabel.setText(String.format("$%.2f", order.getSubTotal().doubleValue()));
				taxLabel.setText(String.format("$%.2f", order.getTaxes().doubleValue()));
				totalLabel.setText(String.format("$%.2f", order.getTotal().doubleValue()));
				field.setText("0");
			}
		}
    }

    // Attributes
    private Menu menu = null;
    private final Order order = new Order();
    private final JButton printButton = new JButton("Print");
    private final JLabel subtotalLabel = new JLabel("0");
    private final JLabel taxLabel = new JLabel("0");
    private final JLabel totalLabel = new JLabel("0");

    /**
     * Displays the menu in a GUI.
     *
     * @param menu The menu to display.
     */
    public OrderPanel(final Menu menu) {
	this.menu = menu;
	this.layoutView();
    }

    /**
     * Uses the GridLayout to place the labels and buttons.
     */
    private void layoutView() {
	this.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
	// Number of rows of GridLayout must be updated to accommodate all MenuItems,
	// totals, and Print button
	this.setLayout(new GridLayout(3, 4));
	this.add(new JLabel("Item"));
	this.add(new JLabel("Price"));
	this.add(new JLabel("Quantity"));

	// your code here
	for (int i = 0; i < menu.size(); i++) {
		JTextField temp = new JTextField("0", 3);
		temp.setHorizontalAlignment(JTextField.RIGHT);
		
		this.add(new JLabel(menu.getItem(i).getName(), SwingConstants.LEFT));
		this.add(new JLabel(String.format("$%.2f", menu.getItem(i).getPrice().doubleValue()), SwingConstants.RIGHT));
	}
	
	// Add all other JLabels, JTextFields, and JButtons here
	

	// Register the PrinterListener with the print button.
	this.printButton.addActionListener(new PrintListener());
	this.add(this.printButton);
	return;
    }
}