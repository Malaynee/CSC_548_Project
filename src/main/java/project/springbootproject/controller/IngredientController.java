// package line for spring boot
package project.springbootproject.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import project.springbootproject.model.Ingredient;
import project.springbootproject.model.IngredientStorage;

/**
 * REST controller for ingredients
 * ( Handles HTTP requests from the frontend (HTML/JS) )
 */
@RestController
@RequestMapping("/api/ingredients") // Base URL: /api/ingredients (should still work without an API)
public class IngredientController {
    // // Storage object manages ingredient data
    // private IngredientStorage storage = new IngredientStorage();

    // public IngredientController() {
    //     // Load ingredients when the controller starts
    //     storage.loadIngredients();
    // }

    /**
     * GET request: returns all ingredients as JSON
     * Frontend can call this to display the pantry
     */
    @GetMapping
    public List<Ingredient> getAllIngredients(HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            username = "guest"; // fallback for testing
        }
        IngredientStorage storage = new IngredientStorage(username);
        storage.loadIngredients();
        return storage.getIngredients();
    }

    /**
     * POST request: adds a new ingredient
     * Frontend sends the ingredient data in JSON format
     */
    @PostMapping
    public void addIngredient(@RequestBody Ingredient ingredient, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            username = "guest"; // fallback for testing
        }
        IngredientStorage storage = new IngredientStorage(username);
        storage.loadIngredients();
        storage.addIngredient(ingredient);
    }
}
