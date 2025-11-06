// Package declaration for spring boot
package project.springbootproject.model;

import java.io.*;
import java.util.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;

/**
 * Class for handling reading and writing ingredients to a JSON file
 */
public class IngredientStorage {

    // File where ingredients are stored
    private static final String FILE_PATH = "ingredients.json";
    // List to hold all the ingredients in memory
    private List<Ingredient> ingredients = new ArrayList<>();

    /**
     * Loads ingredients from the JSON file into memory
     * If the file doesn't exist, starts with an empty list
     */
    public void loadIngredients() {
        try (Reader reader = new FileReader(FILE_PATH)) {
            // Deserialize JSON file into List<Ingredient>
            ingredients = new Gson().fromJson(reader, new TypeToken<List<Ingredient>>(){}.getType());
        } catch (IOException e) {
            // If file doesn't exist or error happens, start empty
            ingredients = new ArrayList<>();
        }
    }

    // Saves the current list of ingredients to the JSON file
    public void saveIngredients() {
        try (Writer writer = new FileWriter(FILE_PATH)) {
            // Convert ingredients list to JSON and write to file
            new Gson().toJson(ingredients, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Returns the list of all ingredients in memory
    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    /**
     * Adds a new ingredient if it doesn't already exist
     * Automatically saves the updated list to the JSON file
     */
    public void addIngredient(Ingredient ingredient) {
        if (!ingredients.contains(ingredient)) {
            ingredients.add(ingredient);
            saveIngredients();
        }
    }
}
