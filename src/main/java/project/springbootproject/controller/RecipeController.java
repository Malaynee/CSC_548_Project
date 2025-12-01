package project.springbootproject.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import project.springbootproject.model.Recipe;
import project.springbootproject.model.RecipeStorage;

/**
 * REST controller for recipes
 * Handles HTTP requests to view, add, or remove recipes
 */
@RestController
@RequestMapping("/api/recipes") // Base URL: /api/recipes
public class RecipeController {

    // GET request: return all recipes as JSON
    @GetMapping
    public List<Recipe> getAllRecipes(HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            username = "guest"; // fallback for testing
        }
        RecipeStorage storage = new RecipeStorage(username);
        storage.loadRecipes();
        return storage.getRecipes();
    }

    /**
     * POST request: add a new recipe
     * Frontend sends recipe data in JSON format
     */
    @PostMapping
    public void addRecipe(@RequestBody Recipe recipe, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            username = "guest"; // fallback for testing
        }
        RecipeStorage storage = new RecipeStorage(username);
        storage.loadRecipes();
        storage.addRecipe(recipe);
    }

    /**
     * POST request: add a date to a recipe
     * Endpoint: /api/recipes/{title}/add-date
     */
    @PostMapping("/{title}/add-date")
    public void addDateToRecipe(@PathVariable String title, @RequestBody Map<String, String> payload, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            username = "guest";
        }
        RecipeStorage storage = new RecipeStorage(username);
        storage.loadRecipes();
        
        System.out.println("Looking for recipe: '" + title + "'");
        System.out.println("Available recipes: " + storage.getRecipes().stream().map(Recipe::getTitle).toList());
        
        // Find the recipe by title (case-insensitive)
        Recipe recipe = storage.getRecipes().stream()
            .filter(r -> r.getTitle().equalsIgnoreCase(title))
            .findFirst()
            .orElse(null);
        
        if (recipe != null) {
            String date = payload.get("date");
            if (date != null && !date.isEmpty()) {
                recipe.setDatesUsed(date); // Append the date to datesUsed list
                storage.saveRecipes(); // Save changes to JSON file
                System.out.println("Date added to recipe: " + title + ", date: " + date);
            }
        } else {
            System.out.println("Recipe not found: " + title);
        }
    }

    /**
     * POST request: rate a recipe (thumbs up or down)
     * Endpoint: /api/recipes/{title}/rating
     */
    @PostMapping("/{title}/rating")
    public void rateRecipe(@PathVariable String title, @RequestBody Map<String, Boolean> payload, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            username = "guest";
        }
        RecipeStorage storage = new RecipeStorage(username);
        storage.loadRecipes();
        
        System.out.println("Looking for recipe: '" + title + "'");
        System.out.println("Available recipes: " + storage.getRecipes().stream().map(Recipe::getTitle).toList());
        
        // Find the recipe by title (case-insensitive)
        Recipe recipe = storage.getRecipes().stream()
            .filter(r -> r.getTitle().equalsIgnoreCase(title))
            .findFirst()
            .orElse(null);
        
        if (recipe != null) {
            Boolean isThumbsUp = payload.get("isThumbsUp");
            if (isThumbsUp != null) {
                recipe.setThumbsUpRating(isThumbsUp);
                recipe.setThumbsDownRating(!isThumbsUp);
                storage.saveRecipes(); // Save changes to JSON file
                System.out.println("Recipe rated: " + title + ", thumbsUp: " + isThumbsUp);
            }
        } else {
            System.out.println("Recipe not found: " + title);
        }
    }
}
