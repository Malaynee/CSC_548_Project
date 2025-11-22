// package line for spring boot
package project.springbootproject.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
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
}
