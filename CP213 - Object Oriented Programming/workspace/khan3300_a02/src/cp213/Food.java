package cp213;

import java.io.PrintStream;

/**
 * Food class definition.
 *
 * @author David Brown
 * @version 2021-09-24
 */
public class Food implements Comparable<Food> {

    // Constants
    public static final String ORIGINS[] = { "Canadian", "Chinese", "Indian", "Ethiopian", "Mexican", "Greek",
	    "Japanese", "Italian", "Moroccan", "Scottish", "Columbian", "English" };

    /**
     * Creates a string of food origins in the format:
     *
     * <pre>
Origins
 0 Canadian
 1 Chinese
...
11 English
     * </pre>
     *
     * @return A formatted numbered string of valid food origins.
     */
    public static String originsMenu() {

	String menu = "Origins\n" +
				"0 Canadian\n" +
				"1 Chinese\n" +
				"2 Indian\n" +
				"3 Ethiopian\n" +
				"4 Mexican\n" +
				"5 Greek\n" +
				"6 Japanese\n" +
				"7 Italian\n" +
				"8 Moroccan\n" +
				"9 Scottish\n" +
				"10 Columbian\n" +
				"11 English\n";

	return menu;
    }

    // Attributes
    private String name = null;
    private int origin = 0;
    private boolean isVegetarian = false;
    private int calories = 0;

    /**
     * Food constructor.
     *
     * @param name         food name
     * @param origin       food origin code
     * @param isVegetarian whether food is vegetarian
     * @param calories     caloric content of food
     */
    public Food(final String name, final int origin, final boolean isVegetarian, final int calories) {

	// your code here
    	/*
    	assert origin < 12: "Invalid origin ID";
    	assert isVegetarian == true || isVegetarian == false: "Must be True or False";
    	assert calories >= 0: "Calories must be >= 0";
    	*/
	    this.name = name;
	    this.origin = origin;
	    this.isVegetarian = isVegetarian;
	    this.calories = calories;
    	
	return;
    }

    /*
     * (non-Javadoc) Compares this food against another food.
     *
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    /**
     * Foods are compared by name, then by origin if the names match.
     * Must ignore case.
     */
    @Override
    public int compareTo(final Food target) {

	// your code here
    int comparison;
    	
    int v1 = Character.toLowerCase(this.name.charAt(0));
    int v2 = Character.toLowerCase(target.name.charAt(0));
    
    if (v1 == v2) {
    	comparison = this.origin - target.origin;
    }
    
    else {
    	comparison = v1 - v2;
    }
    
	return comparison;
    }

    /**
     * Getter for calories attribute.
     *
     * @return calories
     */
    public int getCalories() {

	// your code here
    int calories = this.calories;
    	
	return calories;
    }

    /**
     * Getter for name attribute.
     *
     * @return name
     */
    public String getName() {

	// your code here
    String name = this.name;

	return name;
    }

    /**
     * Getter for origin attribute.
     *
     * @return origin
     */
    public int getOrigin() {

	// your code here
    int origin = this.origin;

	return origin;
    }

    /**
     * Getter for string version of origin attribute.
     *
     * @return string version of origin
     */
    public String getOriginString() {

	// your code here
    String origin = ORIGINS[this.getOrigin()];

	return origin;
    }

    /**
     * Getter for isVegetarian attribute.
     *
     * @return isVegetarian
     */
    public boolean isVegetarian() {

	// your code here
    Boolean vegetarian = this.isVegetarian;
    	
	return vegetarian;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object//toString() Creates a formatted string of food data.
     */
    /**
     * Returns a string version of a Food object in the form:
<pre>
Name:       name
Origin:     origin string
Vegetarian: true/false
Calories:   calories
</pre>
     */
    @Override
    public String toString() {

	// your code here
    String origin = ORIGINS[this.getOrigin()];
    	
    String output = "Name: " + this.name + "\n" +
    				"Origin: " + origin + "\n" +
    				"Vegetarian: " + this.isVegetarian + "\n" +
    				"Calories: " + this.calories + "\n";
    	
	return output;
    }

    /**
     * Writes a single line of food data to an open PrintStream. The contents of
     * food are written as a string in the format name|origin|isVegetarian|calories to ps.
     *
     * @param ps The PrintStream to write to.
     */
    public void write(final PrintStream ps) {

	// your code here

    	
	return;
    }

}
