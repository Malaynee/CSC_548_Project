package project.springbootproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.springbootproject.model.RecipeStorage;

/**
 * Controller for rendering the Recipes page using Thymeleaf
 * 
 * Handles browser requests to "/recipes" and passes recipe date (optionally filtered by 
 * cuisine type) into the Thymeleaf template (templates/recipes.html)
 */
@Controller
public class RecipeViewController {
    // Manages and provides recipe data
    private final RecipeStorage storage = new RecipeStorage();
    /**
     * Handles GET requests to "/recipes"
     * 
     * Example URLs:
     *  - /recipes -> shows all recipes
     *  - /recipes?cuisine=Italian -> shows only Italian recipes
     */
    @GetMapping("/recipes")
    public String viewRecipes(@RequestParam(required = false) String cuisine, Model model) {
        // Load or refresh the recipe data
        storage.loadRecipes();
        // Check if a cuisine filter was provided
        if (cuisine != null && !cuisine.isEmpty()) {
            // Custom method filters recipes by cuisine type
            model.addAttribute("recipes", storage.getRecipesByCuisine(cuisine));
        } else {
            // No filter -> show all recipes
            model.addAttribute("recipes", storage.getRecipes());
        }
        // The key "recipes" is now available inside recipes.html
        // Return the name of the template to render
        return "recipes";
    }
}
