
/**
 * @author Catherine Larson
 * @version 2.0
 * New version contains alternative data structure organization.
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
	public class IngredientsDriver{
		
		// public static HashMap<String, Double> ingredientsMap;
		
		// public void Ingredients() {
		// 	this.ingredientsMap = new HashMap<String, Double>();
		// }

	    public static void main(String[] args){
	    	
			Ingredient ingredientObject = new Ingredient();
			Recipe recipeObj = new Recipe();
	    	Scanner userInput = new Scanner(System.in);
			String userIngredient;
	    	
	    	System.out.println("What action would you like to take? Enter 1 for adding an ingredient, 2 for adding a recipe, or 3 for rating a recipe.");
	    	int action = userInput.nextInt();
	    	
	    	if (action == 1) {
	    		
	    		System.out.println("What is the name of the ingredient you would like to add?");
	    		userIngredient = userInput.nextLine();
				ingredientObject.setName(userIngredient);
	    		System.out.println("What quantity do you have of this ingredient?");
	    		String ingredientQuantity = userInput.nextLine().makeQuantityDouble();
				ingredientObject.setQuantity(ingredientQuantity);
				System.out.println("What is the unit this ingredient uses (ex: cups, oz, tbsp, tsp, etc.)");
				String unit = userInput.nextLine(); 
				ingredientObject.setUnit(unit);
				ingredientObject.addIngredient();

				// ObjectMapper objectMapper = new ObjectMapper();
				// //Need to test for if this works when we don't ask the user for alternative ingredient names - but for now, just implementing basic functionality.
				// Ingredients userEntry = new Ingredients(userIngredient, ingredientQuantity, unit);
				// userEntry.
				// objectMapper.writeValue(new File ("src/main/resources/Ingredients.json"), userEntry);
	    		
	    		// if (!ingredientsMap.containsKey(userIngredient)) {
	    		// 	ingredientsMap.addIngredientWithQuantity(userIngredient, ingredientQuantity);
	    		// }	
	    	}

			if (action == 2){

				System.out.println("What is the name of the recipe you would like to add?");
				String userRecipe = userInput.nextLine();
				recipeObj.setTitle(userRecipe);
				System.out.println("What is the first ingredient in this recipe?");
				userIngredient = userInput.nextLine(); 
				ingredientObject.addIngredient();
				System.out.println("Would you like to add another ingredient (enter y for yes and n for no)?");
				String response = userInput.nextLine();

				while(response == "y"){

					System.out.println("What is the next ingredient in this recipe?");
					userIngredient = userInput.nextLine(); 
					ingredientObject.addIngredient();
					System.out.println("Would you like to add another ingredient (enter y for yes and n for no)?");
					String response = userInput.nextLine();

				}

				recipeObj.setIngredients(ingredientObject);
				
				System.out.println("Enter the instructions for the recipe.");
				String userInstructions = userInput.nextLine();
				recipeObj.setInstructions(userInstructions);

				System.out.println("Copy the URL where you found the recipe and paste it here.");
				String userSource = userInput.nextLine();
				recipeObj.setSource(userSource);


				System.out.println("How much time is required to make this recipe, excluding the units (i.e. if it is 4 hours, input just a 4)?");
				double reportedTimeRequired = userInput.nextDouble();
				



			}
	    }


		//MIGHT BE POSSIBLE TO DELETE WITH DATA RESTRUCTURING!
	    // public void addIngredientWithQuantity(String ingredient, String quantity){
	    	
	    // 	IngredientsDriver ingObj = new IngredientsDriver();
	    // 	String numerator;
	    // 	String denominator;
	    // 	String[] fraction;
	    	
	    // 	int n;
	    // 	int d;
	    	
	    // 	double calculatableQuantity;
	    	
	    // 	if (quantity.contains("/")) {
	    // 		fraction = quantity.split("/");
	    // 		numerator = fraction[0];
	    // 		denominator = fraction[1];
	    		
	    // 		n = Integer.parseInt(numerator);
	    // 		d = Integer.parseInt(denominator);
	    		
	    // 		calculatableQuantity = n / d;	
	    		
	    // 		}	
	    	
	    // 	else {
	    		
	    // 		calculatableQuantity = Double.parseDouble(quantity);
	    // 	}
	    // 	ingObj.ingredientsMap.put(ingredient, calculatableQuantity);	

	    // }


		public double makeQuantityDouble(String quantity){

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
		}
	    }
