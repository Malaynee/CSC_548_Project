// package line for spring boot
package project.springbootproject.model;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors; // for filtering lists easier
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;

/**
 * RecipeStorage class handles reading and writing recipes to a JSON file
 */
public class RecipeStorage {
    
    private String username;
    //File where recipes are stored
    private String filePath;
    // List to hold all recipes in memory
    private List<Recipe> recipes = new ArrayList<>();


    //Constructor to 
    public RecipeStorage(String username){
        this.username = username;
        this.filePath = "data/recipes/" + username + "/recipes.json";

        // Make sure folder exists
        try {
            Files.createDirectories(Paths.get("data/recipes/" + username));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads recipes from JSON file into memory
     * If the file is missing or empty, starts with an empty list
     */
    public void loadRecipes() {
        try (Reader reader = new FileReader(filePath)) {
            recipes = new Gson().fromJson(reader, new TypeToken<List<Recipe>>(){}.getType());
            if (recipes == null) {
                recipes = new ArrayList<>();
            }
        } catch (IOException e) {
            recipes = new ArrayList<>();
        }
    }

    // Saves the current list of recipes to the JSON file
    public void saveRecipes() {
        try (Writer writer = new FileWriter(filePath)) {
            new Gson().toJson(recipes, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Returns all recipes in memory
    public List<Recipe> getRecipes() {
        return recipes;
    }

    // Adds a new recipe and saves it to the JSON file
    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
        saveRecipes();
    }

    // Removes a recipe and updates the JSON file
    public void removeRecipe(Recipe recipe) {
        recipes.remove(recipe);
        saveRecipes();
    }

    /**
     * Filters recipes by cuisine type:
     * - Used by the Thymeleaf controller to show only recipes of a specific cuisine
     * - Example: getRecipesByCuisine("Italian") -> returns only Italian recipes
     * - Ignores case (ex "italian" and "Italian" both work)
     */
    public List<Recipe> getRecipesByCuisine(String cuisine) {
        if (recipes == null || recipes.isEmpty()) {
            return Collections.emptyList();
        }
        return recipes.stream()
                .filter(recipe -> recipe.getCuisineType() != null &&
                                  recipe.getCuisineType().equalsIgnoreCase(cuisine))
                .collect(Collectors.toList());
    }

    // Filters recipes by dietary restriction
    public List<Recipe> getRecipesByDietaryRestriction(String restriction) {
        if (recipes == null || recipes.isEmpty()) {
            return Collections.emptyList();
        }
        if (restriction == null || restriction.equalsIgnoreCase("none")) {
            return new ArrayList<>(recipes);
        }
        return recipes.stream()
                .filter(recipe -> {
                    if (restriction.equalsIgnoreCase("vegan")) {
                        return recipe.isVegan();
                    } else if (restriction.equalsIgnoreCase("vegetarian")) {
                        return recipe.isVegetarian();
                    }
                    return true;
                })
                .collect(Collectors.toList());
    }

    /**
     * Filters recipes by both cuisine and dietary restriction!!
     * (pass null or empty string to skip a filter)
     */
    public List<Recipe> getFilteredRecipes(String cuisine, String dietaryRestriction) {
        if (recipes == null || recipes.isEmpty()) {
            return Collections.emptyList();
        }
        return recipes.stream()
                .filter(recipe -> {
                    // Cuisine filter
                    boolean cuisineMatch = (cuisine == null || cuisine.isEmpty() || 
                                           cuisine.equalsIgnoreCase("all") ||
                                           (recipe.getCuisineType() != null && 
                                            recipe.getCuisineType().equalsIgnoreCase(cuisine)));
                    // Dietary restriction filter
                    boolean dietMatch = true;
                    if (dietaryRestriction != null && !dietaryRestriction.isEmpty() && 
                        !dietaryRestriction.equalsIgnoreCase("none")) {
                        if (dietaryRestriction.equalsIgnoreCase("vegan")) {
                            dietMatch = recipe.isVegan();
                        } else if (dietaryRestriction.equalsIgnoreCase("vegetarian")) {
                            dietMatch = recipe.isVegetarian();
                        }
                    }
                    return cuisineMatch && dietMatch;
                })
                .collect(Collectors.toList());
    }
    
    /**
     * Gets all unique cuisine types from loaded recipes
     * (Could use for populating dropdown menu(s)?
     */
    public List<String> getAllCuisineTypes() {
        if (recipes == null || recipes.isEmpty()) {
            return Collections.emptyList();
        }
        
        return recipes.stream()
                .map(Recipe::getCuisineType)
                .filter(Objects::nonNull)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }
}
