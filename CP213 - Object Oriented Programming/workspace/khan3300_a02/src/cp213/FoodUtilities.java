package cp213;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Utilities for working with Food objects.
 *
 * @author David Brown
 * @version 2021-09-24
 */
public class FoodUtilities {

    /**
     * Determines the average calories in a list of foods. No rounding necessary.
     * Foods list parameter may be empty.
     *
     * @param foods a list of Food
     * @return average calories in all Food objects
     */
    public static int averageCalories(final ArrayList<Food> foods) {

	// your code here
    	int total_calories = 0;
    	int total_foods = 0;
    	int avg_calories = 0;
    	
    	for (Food food : foods) {
    		
    		total_calories = total_calories + food.getCalories(); //food.getCalories();
    		total_foods++;
    	}
    	
    	avg_calories = total_calories / total_foods;

	return avg_calories;
    }

    /**
     * Determines the average calories in a list of foods for a particular origin.
     * No rounding necessary. Foods list parameter may be empty.
     *
     * @param foods  a list of Food
     * @param origin the origin of the Food
     * @return average calories for all Foods of the specified origin
     */
    public static int averageCaloriesByOrigin(final ArrayList<Food> foods, final int origin) {

	// your code here
    	int total_calories = 0;
    	int total_foods = 0;
    	int avg_calories = 0;
    	
    	for (Food food : foods) {
    		
    		if (food.getOrigin() == origin) {
    			total_calories = total_calories + food.getCalories(); 
        		total_foods++;
    		}
    	}
    	
    	avg_calories = total_calories / total_foods;
    	
	return avg_calories;
    }

    /**
     * Creates a list of foods by origin.
     *
     * @param foods  a list of Food
     * @param origin a food origin
     * @return a list of Food from origin
     */
    public static ArrayList<Food> getByOrigin(final ArrayList<Food> foods, final int origin) {

	// your code here
    	ArrayList<Food> food_by_origin = new ArrayList<Food>();
    	
    	for (Food food : foods) {
    		if (food.getOrigin() == origin) {
    			food_by_origin.add(food);
    		}
    	}
    	
	return food_by_origin;
    }

    /**
     * Creates a Food object by requesting data from a user. Uses the format:
     *
     * <pre>
    Name: name
    Origins
     0 Canadian
     1 Chinese
    ...
    11 English
    Origin: origin number
    Vegetarian (Y/N): Y/N
    Calories: calories
     * </pre>
     *
     * @param keyboard a keyboard Scanner
     * @return a Food object
     */
    public static Food getFood(final Scanner keyboard) {

	// your code here

    	
	return null;
    }

    /**
     * Creates a list of vegetarian foods.
     *
     * @param foods a list of Food
     * @return a list of vegetarian Food
     */
    public static ArrayList<Food> getVegetarian(final ArrayList<Food> foods) {

	// your code here
    	ArrayList<Food> vegan = new ArrayList<Food>();
    	
    	for (Food food : foods) {
    		if (food.isVegetarian() == true) {
    			System.out.println(food);
    			vegan.add(food);
    		}
    	}

	return vegan;
    }

    /**
     * Creates and returns a Food object from a line of string data.
     *
     * @param line a vertical bar-delimited line of food data in the format
     *             name|origin|isVegetarian|calories
     * @return the data from line as a Food object
     */
    public static Food readFood(final String line) {

	// your code here
    	
    	char caught = '|';
    	boolean shift = false;
    	int counts = 0;
    	
    	// Getting Name:
    	String name = "";
    	while(!shift) {
    		char temp = line.charAt(counts);
    		
    		if (temp == caught) {
    			shift = true;
    		}
    		
    		else {
    			name = name + temp;
    		}
    		
    		counts++;
    	}
    	
    	// Getting Origin:
    	shift = false;
    	String origin = "";
    	int origin_int = 0;
    	
    	while(!shift) {
    		char temp = line.charAt(counts);
    		
    		if (temp == caught) {
    			shift = true;
    		}
    		
    		else {
    			origin = origin + temp;
    		}
    		
			counts++;
    	}
    	
    	origin_int = Integer.parseInt(origin);
    	
    	// Getting Vegan:
    	shift = false;
    	String vegan_s = "";
    	boolean vegan = false;
    	
    	while(!shift) {
    		char temp = line.charAt(counts);
    		
    		if (temp == caught) {
    			shift = true;
    		}
    		
    		else {
    			vegan_s = vegan_s + temp;
    		}
    		
    		counts++;
    	}
    	
    	if (vegan_s.charAt(0) == 'f') {
    		vegan = false;
    	}
    	
    	else if (vegan_s.charAt(0) == 't') {
    		vegan = true;
    	}
    	
    	// Getting Calories:
    	shift = false;
    	String calories = "";
    	int calories_int = 0;
    	
    	while(!shift && counts < line.length()) {
    		char temp = line.charAt(counts);
//    		System.out.println("temp: " + temp);
    		
    		if (temp == caught) {
    			shift = true;
    		}
    		
    		else {
    			calories = calories + temp;
        		
    		}
    		counts++;
    	}
    	
    	calories_int = Integer.parseInt(calories);
    	
    	Food new_food = new Food(name, origin_int, vegan, calories_int);
    	
	return new_food;
    }

    /**
     * Reads a file of food strings into a list of Food objects.
     *
     * @param fileIn a Scanner of a Food data file in the format
     *               name|origin|isVegetarian|calories
     * @return a list of Food
     */
    public static ArrayList<Food> readFoods(final Scanner fileIn) {

	// your code here
    ArrayList<Food> foods = new ArrayList<Food>();
    
    	while (fileIn.hasNextLine()) {
    		String line = fileIn.nextLine();
    		foods.add(readFood(line));
    	}

	return foods;
    }

    /**
     * Searches for foods that fit certain conditions.
     *
     * @param foods        a list of Food
     * @param origin       the origin of the food; if -1, accept any origin
     * @param maxCalories  the maximum calories for the food; if 0, accept any
     * @param isVegetarian whether the food is vegetarian or not; if false accept
     *                     any
     * @return a list of foods that fit the conditions specified
     */
    public static ArrayList<Food> foodSearch(final ArrayList<Food> foods, final int origin, final int maxCalories,
	    final boolean isVegetarian) {

	// your code here
        ArrayList<Food> search = new ArrayList<Food>();
    	for (Food food : foods) {
    		if (food.getOrigin() == origin && food.getCalories() < maxCalories && food.isVegetarian() == isVegetarian) {
//    			System.out.println(food);
    			search.add(food);
    		}
    	}
    	
	return search;
    }

    /**
     * Writes the contents of a list of Food to a PrintStream.
     *
     * @param foods a list of Food
     * @param ps    the PrintStream to write to
     */
    public static void writeFoods(final ArrayList<Food> foods, PrintStream ps) {

	// your code here

    	
    }
}
