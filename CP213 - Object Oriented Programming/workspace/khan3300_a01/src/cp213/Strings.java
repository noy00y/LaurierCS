package cp213;

/**
 * @author Your name and id here
 * @version 2021-09-11
 */
public class Strings {
    // Constants
    public static final String VOWELS = "aeiouAEIOU";

    /**
     * Determines if string is a "palindrome": a word, verse, or sentence (such as "Able
     * was I ere I saw Elba") that reads the same backward or forward. Ignores case,
     * spaces, digits, and punctuation in the string parameter s.
     *
     * @param string a string
     * @return true if string is a palindrome, false otherwise
     */
    public static boolean isPalindrome(final String string) {
    	
    	// Declarations:
    	boolean palindrome = false;
    	String reverse = "";
    	String process = "";
    	
    	// cutting down the string:
    	for(int i = 0; i < string.length(); i++) {
    		char c = string.charAt(i);
    		
    		if (Character.isLetter(c) == true) {
        		c = Character.toLowerCase(c);
    			process = process + c;
        	}
    	}
    	
    	// Processing:
    	for(int j = process.length() - 1; j >= 0; j--) {
    		reverse = reverse + process.charAt(j);
    	}
    	
    	/*
    	System.out.println(process);
    	System.out.println(reverse);
    	*/
    	
    	if (process.equals(reverse) == true) {
    		palindrome = true;
    	}
    	
    	return palindrome;
    }

    /**
     * Determines if name is a valid Java variable name. Variables names must start
     * with a letter or an underscore, but cannot be an underscore alone. The rest
     * of the variable name may consist of letters, numbers and underscores.
     *
     * @param name a string to test as a Java variable name
     * @return true if name is a valid Java variable name, false otherwise
     */
    public static boolean isValid(final String name) {

		// your code here
	    boolean valid = false;
	    char start = name.charAt(0);
	    
	    if (Character.isLetter(start) == true || start == '_') {
	    	valid = true;
	    }
	    
		return valid;
    }

    /**
     * Converts a word to Pig Latin. The conversion is:
     * <ul>
     * <li>if a word begins with a vowel, add "way" to the end of the word.</li>
     * <li>if the word begins with consonants, move the leading consonants to the
     * end of the word and add "ay" to the end of that. "y" is treated as a
     * consonant if it is the first character in the word, and as a vowel for
     * anywhere else in the word.</li>
     * </ul>
     * Preserve the case of the word - i.e. if the first character of word is
     * upper-case, then the new first character should also be upper case.
     *
     * @param word The string to convert to Pig Latin
     * @return the Pig Latin version of word
     */
    public static String pigLatin(String word) {

	// Declarations:
    char start = word.charAt(0);
    int len = word.length();
    
    // Begins with Vowel:
    if(start == 'a' || start == 'e' || start == 'i' || start == 'o' || start == 'u' ) {
    	word = word + "way";
    }
    
    else {
    	// Declarations:
    	boolean upper = false;
    	String temp = "";
    	
    	// Check if upper case:
    	if (Character.isUpperCase(start) == true) {
    		upper = true;
    	}
    	
    	// Adding shift + 1 letters to temp:
    	for (int i = 1; i < len; i++) {
    		
    		// If first word was upper case
    		if(i == 1 && upper) {
    			temp = temp + Character.toUpperCase(word.charAt(i));
    		}
    		
    		else {
        		temp = temp + word.charAt(i);
    		}
    	}
    	
    	temp = temp + Character.toLowerCase(start) + "ay";
    	word = temp;
    	
    }

	return word;
    }

}
