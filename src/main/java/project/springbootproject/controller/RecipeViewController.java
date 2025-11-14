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
     *  - /recipes?diet=vegetarian -> shows only vegetarian recipes
     *  - /recipes?cuisine=Italian&diet=vegan -> shows Italian vegan recipes
     */
    @GetMapping("/recipes")
    public String viewRecipes(
            @RequestParam(required = false) String cuisine,
            @RequestParam(required = false) String diet,
            Model model) {
        
        // Load or refresh the recipe data
        storage.loadRecipes();
        // Apply filters (both, one, or none)
        model.addAttribute("recipes", storage.getFilteredRecipes(cuisine, diet));
        // Pass available cuisine types for the dropdown
        model.addAttribute("cuisineTypes", storage.getAllCuisineTypes());
        // Pass current filter values back to the view to maintain selection
        model.addAttribute("selectedCuisine", cuisine != null ? cuisine : "all");
        model.addAttribute("selectedDiet", diet != null ? diet : "none");
        return "recipes";
    }
}
