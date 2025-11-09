
/**
 * @author Catherine Larson
 * @version 1.4
 * First version code pasted from my initial deleted experimentation file.
 */

package main;

import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.BufferedWriter;

	/*BRAINSTORMING AREA
	 * Want user to be able to input to EXISTING data, not have the data start over each time
	 * Want ingredient to map to user quantity for just the USER, but also allow a separate place to store all ingredients compiled from all users.
	 * Would like to make the ingredients list a dropdown functionality with searchability to eliminate duplicates with plurals.
	 */

/**
 * @author Catherine Larson
 */
	public class Ingredients{
		
		public static HashMap<String, Double> ingredientsMap;
		
		public void Ingredients() {
			this.ingredientsMap = new HashMap<String, Double>();
		}

	    public static void main(String[] args){
	    	
	    	Scanner userInput = new Scanner(System.in);
	    	
	    	System.out.println("What action would you like to take? Enter 1 for adding an ingredient or 2 for adding a quantity.");
	    	int action = userInput.nextInt();
	    	
	    	if (action == 1) {
	    		
	    		System.out.println("What is the name of the ingredient you would like to add?");
	    		String userIngredient = userInput.nextLine();
	    		System.out.println("What quantity do you have of this ingredient?");
	    		String ingredientQuantity = userInput.nextLine();
				System.out.println("What is the unit this ingredient uses (ex: cups, oz, tbsp, tsp, etc.)");
				String unit = userInput.nextLine(); 

				ObjectMapper objectMapper = new ObjectMapper();
				//Need to test for if this works when we don't ask the user for alternative ingredient names - but for now, just implementing basic functionality.
				Ingredients userEntry = new Ingredients(userIngredient, ingredientQuantity, unit);
				objectMapper.writeValue(new File ("src/main/resources/Ingredients.json"), userEntry);
	    		
	    		if (!ingredientsMap.containsKey(userIngredient)) {
	    			ingredientsMap.addIngredientWithQuantity(userIngredient, ingredientQuantity);
	    		}	
	    	}
	    }


	    public void addIngredientWithQuantity(String ingredient, String quantity){
	    	
	    	Ingredients ingObj = new Ingredients();
	    	String numerator;
	    	String denominator;
	    	String[] fraction;
	    	
	    	int n;
	    	int d;
	    	
	    	double calculatableQuantity;
	    	
	    	if (quantity.contains("/")) {
	    		fraction = quantity.split("/");
	    		numerator = fraction[0];
	    		denominator = fraction[1];
	    		
	    		n = Integer.parseInt(numerator);
	    		d = Integer.parseInt(denominator);
	    		
	    		calculatableQuantity = n / d;	
	    		
	    		}	
	    	
	    	else {
	    		
	    		calculatableQuantity = Double.parseDouble(quantity);
	    	}
	    	ingObj.ingredientsMap.put(ingredient, calculatableQuantity);	

	    }
	    }
