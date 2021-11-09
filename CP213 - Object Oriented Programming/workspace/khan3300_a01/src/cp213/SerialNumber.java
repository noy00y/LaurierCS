package cp213;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Your name and id here
 * @version 2021-09-11
 */
public class SerialNumber {

    /**
     * Determines if a string contains all digits.
     *
     * @param str The string to test.
     * @return true if str is all digits, false otherwise.
     */
    public static boolean allDigits(final String str) {

	// your code here
    boolean digit = true;
    int n = str.length();
    
    for(int i = 0; i < n; i++) {
    	char c= str.charAt(i);
    	
    	if (Character.isDigit(c) == false) {
    		digit = false;
    	}
    }
    
    	
	return digit;
    }

    /**
     * Determines if a string is a good serial number. Good serial numbers are of
     * the form 'SN/nnnn-nnn', where 'n' is a digit.
     *
     * @param sn The serial number to test.
     * @return true if the serial number is valid in form, false otherwise.
     */
    public static boolean validSn(final String sn) {

	// Declarations:
    boolean valid = true;
    
    // If SN isn't proper length
    if (sn.length() != 11) {
    	valid = false;
    }
    
    // "SN/-" condition:
    else if (sn.charAt(0) != 'S' || sn.charAt(1) != 'N' || sn.charAt(2) != '/' || sn.charAt(7) != '-') {
    	valid = false;
    }
    
    // Continue Evaluating:
    else {
    	
    	// First 4 numbers:
    	for(int i = 3; i < 7; i++) {
    		if (Character.isDigit(sn.charAt(i)) == false) {
    			valid = false;
    		}
    	}
    	
    	// Last 3 numbers:
    	for(int i = 8; i < 11; i++) {
    		if (Character.isDigit(sn.charAt(i)) == false) {
    			valid = false;
    		}
    	}
    	
    }

	return valid;
    }

    /**
     * Evaluates serial numbers from a file. Writes valid serial numbers to
     * good_sns, and invalid serial numbers to bad_sns.
     *
     * @param fileIn  a file already open for reading
     * @param goodSns a file already open for writing
     * @param badSns  a file already open for writing
     */
    public static void validSnFile(final Scanner fileIn, final PrintStream goodSns, final PrintStream badSns) {

    	// Declartions:

	return;
    }

}
