// package line for spring boot
package project.springbootproject.controller;

import org.springframework.web.bind.annotation.*;
import project.springbootproject.model.Recipe;
import project.springbootproject.model.RecipeStorage;

import java.util.List;

/**
 * REST controller for recipes
 * Handles HTTP requests to view, add, or remove recipes
 */
@RestController
@RequestMapping("/api/recipes") // Base URL: /api/recipes
public class RecipeController {
    private RecipeStorage storage = new RecipeStorage();

    public RecipeController() {
        storage.loadRecipes();
    }

    // GET request: return all recipes as JSON
    @GetMapping
    public List<Recipe> getAllRecipes() {
        return storage.getRecipes();
    }

    /**
     * POST request: add a new recipe
     * Frontend sends recipe data in JSON format
     */
    @PostMapping
    public void addRecipe(@RequestBody Recipe recipe) {
        storage.addRecipe(recipe);
    }
}
