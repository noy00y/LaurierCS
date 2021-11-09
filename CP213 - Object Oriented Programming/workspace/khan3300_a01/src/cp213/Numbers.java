package cp213;

/**
 * @author Your name and id here
 * @version 2021-09-11
 */
public class Numbers {

    /**
     * Determines closest value of two values to a target value.
     *
     * @param target the target value
     * @param v1     first comparison value
     * @param v2     second comparison value
     * @return one of v1 or v2 that is closest to target, v1 is the value chosen if
     *         v1 and v2 are an equal distance from target
     */
    public static double closest(final double target, final double v1, final double v2) {

		// Vars
	    double chosen_v = 0;
	    double distance_v1;
	    double distance_v2;
	    
	    // Conditions:
	    if (v1 < target) {
	    	distance_v1 = target - v1;
	    }
	    
	    else {
	    	distance_v1 = v1 - target;
	    }
	    
	    if (v2 < target) {
	    	distance_v2 = target - v2;
	    }
	    
	    else {
	    	distance_v2 = v2 - target;
	    }
	    
	    // Eval:
	    if (distance_v1 == distance_v2) {
	    	chosen_v = v1;
	    }
	    
	    else if (distance_v1 < distance_v2) {
	    	chosen_v = v1;
	    }
	    	
	    else if (distance_v1 > distance_v2) {
	    	chosen_v = v2;
	    }
	    
	    return chosen_v;
    }


    /**
     * Determines if n is a prime number. Prime numbers are whole numbers greater
     * than 1, that have only two factors – 1 and the number itself. Prime numbers
     * are divisible only by the number 1 or itself.
     *
     * @param n an integer
     * @return true if n is prime, false otherwise
     */
    public static boolean isPrime(final int n) {

		// your code here
	    boolean p = true;
	    
	    if (n <= 1) {
	    	p = false;
	    }
	    
	    else {
	    	for (int i = 2; i < n; i++) {
	    		if (n % i == 0) {
	    			p = false;
	    		}
	    	}
	    }
	    
	    
		return p;
    }

    /**
     * Sums and returns the total of a partial harmonic series. This series is the
     * sum of all terms 1/i, where i ranges from 1 to n (inclusive). Ex:
     *
     * n = 3: sum = 1/1 + 1/2 + 1/3 = 1.8333333333333333
     *
     * @param n an integer
     * @return sum of partial harmonic series from 1 to n
     */
    public static double sumPartialHarmonic(final int n) {

	// your code here
	    double sum = 0;
	    
	    for (double i = 1; i <= n; i++) {
	    	sum = sum + 1/i;
	    }
	
		return sum;
    }

}
