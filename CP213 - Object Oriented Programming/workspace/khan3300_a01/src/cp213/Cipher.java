package cp213;

/**
 * @author Your name and id here
 * @version 2021-09-11
 */
public class Cipher {
    // Constants
    public static final String ALPHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final int ALPHA_LENGTH = ALPHA.length();
    

    /**
     * Encipher a string using a shift cipher. Each letter is replaced by a letter
     * 'n' letters to the right of the original. Thus for example, all shift values
     * evenly divisible by 26 (the length of the English alphabet) replace a letter
     * with itself. Non-letters are left unchanged.
     *
     * @param s string to encipher
     * @param n the number of letters to shift
     * @return the enciphered string
     */
    public static String shift(final String s, final int n) {

		// Declarations:
	    int offset = n % 26; // shifting amount
	    String shift = "";

	    // Iteration:
	    for (int i = 0; i < s.length(); i++) {
	    	
	    	char current = s.charAt(i);
	    	
	    	// If letter:
	    	if (Character.isLetter(current) == true) {
	    		 
	    		// Finding position and shift:
	    		boolean found = false;
	    		int position = 0;
	    		
	    		while (!found) {
	    			if (current == ALPHA.charAt(position)) {
	    				found = true;
	    			}
	    			
	    			else {
		    			position++;
	    			}
	    		}
	    		
//		    	System.out.print("pos: " + position + " | ");
//		    	System.out.print("off: " + offset + " | ");
	    		
	    		// Shifting:
		    	
	    		if (position + offset < 26) {
//			    	System.out.print("pos + off = " + (position + offset) + " | ");
	    			shift = shift + ALPHA.charAt(position + offset);
	    		}
	    		
	    		else {
	    			int temp = (position + offset) - 26;
//			    	System.out.print("temp = " + (temp) + " | ");
	    			shift = shift + ALPHA.charAt(temp);
	    		}
	    		
	    	}
	    	
	    	// Ignore others
	    	else {
	    		shift = shift + current;
	    	}
	    	
	    }
	    
		return shift;
    }

    /**
     * Encipher a string using the letter positions in ciphertext. Each letter is
     * replaced by the letter in the same ordinal position in the ciphertext.
     * Non-letters are left unchanged. Ex:
     *
     * <pre>
    Alphabet:   ABCDEFGHIJKLMNOPQRSTUVWXYZ
    Ciphertext: AVIBROWNZCEFGHJKLMPQSTUXYD
     * </pre>
     *
     * A is replaced by A, B by V, C by I, D by B, E by R, and so on. Non-letters
     * are ignored.
     *
     * @param s          string to encipher
     * @param ciphertext ciphertext alphabet
     * @return the enciphered string
     */
    public static String substitute(final String s, final String ciphertext) {

	// your code here 
    String sub = "";
    
    // Iteration:
    
    for (int i = 0; i < s.length(); i++) {
    	
    	char current = s.charAt(i);
    	
    	// If letter:
    	if (Character.isLetter(current) == true) {
    		
    		// Finding position for cipher:
    		boolean found = false;
    		int position = 0;
    		
    		
    		while (!found) {
    			if (current == ALPHA.charAt(position)) {
    				found = true;
    			}
    			
    			else {
	    			position++;
    			}
    		}
    		
//    		System.out.print("position: " + position + " | ");
//    		System.out.print("cipher: " + ciphertext.charAt((position)) + " | ");
    		
    		sub = sub + ciphertext.charAt((position));
//    		System.out.println();
    	}
    	
    	else {
    		sub = sub + current;
    	}
    	
    }
    
	return sub;
    }

}
