// package line for spring boot
package project.springbootproject.model;

import java.io.*;
import java.util.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;

/**
 * RecipeStorage class handles reading and writing recipes to a JSON file
 */
public class RecipeStorage {

    private static final String FILE_PATH = "recipes.json";
    private List<Recipe> recipes = new ArrayList<>();

    /**
     * Loads recipes from JSON file into memory
     * If the file is missing or empty, starts with an empty list
     */
    public void loadRecipes() {
        try (Reader reader = new FileReader(FILE_PATH)) {
            recipes = new Gson().fromJson(reader, new TypeToken<List<Recipe>>(){}.getType());
        } catch (IOException e) {
            recipes = new ArrayList<>();
        }
    }

    // Saves the current list of recipes to the JSON file
    public void saveRecipes() {
        try (Writer writer = new FileWriter(FILE_PATH)) {
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
}
