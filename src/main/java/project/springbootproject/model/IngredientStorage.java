// Package declaration for spring boot
package project.springbootproject.model;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Class for handling reading and writing ingredients to a JSON file
 */
public class IngredientStorage {

    private String username;
    // File where ingredients are stored
    private String filePath;
    // List to hold all the ingredients in memory
    private List<Ingredient> ingredients = new ArrayList<>();

    /**
     * Constructor for per-user ingredient storage
     */
    public IngredientStorage(String username) {
        this.username = username;
        // Use user.dir to get the actual working directory where the app runs
        String baseDir = System.getProperty("user.dir");
        this.filePath = baseDir + File.separator + "data" + File.separator + "ingredients" + File.separator + username + File.separator + "ingredients.json";

        // Make sure folder exists
        try {
            Files.createDirectories(Paths.get(baseDir + File.separator + "data" + File.separator + "ingredients" + File.separator + username));
            
            // If ingredients.json doesn't exist, initialize it with an empty list
            File ingredientFile = new File(filePath);
            if (!ingredientFile.exists()) {
                this.ingredients = new ArrayList<>();
                saveIngredients();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads ingredients from the JSON file into memory
     * If the file doesn't exist, starts with an empty list
     */
    public void loadIngredients() {
        try (Reader reader = new FileReader(filePath)) {
            // Deserialize JSON file into List<Ingredient>
            ingredients = new Gson().fromJson(reader, new TypeToken<List<Ingredient>>(){}.getType());
            if (ingredients == null) {
                ingredients = new ArrayList<>();
            }
        } catch (IOException e) {
            // If file doesn't exist or error happens, start empty
            ingredients = new ArrayList<>();
        }
    }

    // Saves the current list of ingredients to the JSON file
    public void saveIngredients() {
        try (Writer writer = new FileWriter(filePath)) {
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
