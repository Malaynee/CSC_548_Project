package project.springbootproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import project.springbootproject.model.RecipeStorage;
import project.springbootproject.model.IngredientStorage;
import project.springbootproject.model.Recipe;
import project.springbootproject.model.Ingredient;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Controller for rendering the Recipes page using Thymeleaf
 * 
 * Handles browser requests to "/recipes" and passes recipe date (optionally filtered by 
 * cuisine type) into the Thymeleaf template (templates/recipes.html)
 */
@Controller
public class RecipeViewController {
    private final RecipeStorage recipeStorage = new RecipeStorage();
    private final IngredientStorage ingredientStorage = new IngredientStorage();

    /**
     * Handles GET requests to "/recipes"
     * 
     * Example URLs:
     *  - /recipes -> shows all recipes
     *  - /recipes?cuisine=Italian -> shows only Italian recipes
     *  - /recipes?diet=vegetarian -> shows only vegetarian recipes
     *  - /recipes?canMake=true -> shows only recipes user can make with current ingredients
     *  - /recipes?cuisine=Italian&diet=vegan&canMake=true -> all filters combined
     */
    @GetMapping("/recipes")
    public String viewRecipes(
            @RequestParam(required = false) String cuisine,
            @RequestParam(required = false) String diet,
            @RequestParam(required = false) String canMake,
            HttpSession session,
            Model model) {
        
        // Load recipe + ingredient data
        recipeStorage.loadRecipes();
        ingredientStorage.loadIngredients();

        // Get available ingredients
        List<Ingredient> availableIngredients = ingredientStorage.getIngredients();
        
        // If null or empty, initialize as empty list
        if (availableIngredients == null) {
            availableIngredients = new java.util.ArrayList<>();
        }
        
        List<Recipe> filteredRecipes;
        
        // Check if "Can Make" filter is active
        if ("true".equals(canMake)) {
            // Get user's available ingredients
            List<Ingredient> availableIngredients = ingredientStorage.getIngredients();
            
            // Get recipes user can make
            filteredRecipes = recipeStorage.getRecipesCanMake(availableIngredients);
            
            // Further filter by cuisine and diet if specified
            if (cuisine != null && !cuisine.isEmpty() && !cuisine.equalsIgnoreCase("all")) {
                filteredRecipes = filteredRecipes.stream()
                    .filter(recipe -> recipe.getCuisineType() != null && 
                                     recipe.getCuisineType().equalsIgnoreCase(cuisine))
                    .collect(java.util.stream.Collectors.toList());
            }     
            if (diet != null && !diet.isEmpty() && !diet.equalsIgnoreCase("none")) {
                filteredRecipes = filteredRecipes.stream()
                    .filter(recipe -> {
                        if (diet.equalsIgnoreCase("vegan")) {
                            return recipe.isVegan();
                        } else if (diet.equalsIgnoreCase("vegetarian")) {
                            return recipe.isVegetarian();
                        }
                        return true;
                    })
                    .collect(java.util.stream.Collectors.toList());
            }
            
            model.addAttribute("filterActive", true);
            model.addAttribute("canMakeCount", filteredRecipes.size());
        } else {
            // Normal filtering (no ingredient matching)
            filteredRecipes = recipeStorage.getFilteredRecipes(cuisine, diet);
            model.addAttribute("filterActive", false);
        }
        
        // Get match percentages for all recipes (for display)
        //List<Ingredient> availableIngredients = ingredientStorage.getIngredients();
        //Map<Recipe, Integer> matchPercentages = recipeStorage.getRecipesWithMatchPercentage(availableIngredients);

        // Calculate match percentages and missing ingredients for ALL filtered recipes
        Map<String, Integer> matchPercentages = new HashMap<>();
        Map<String, List<String>> missingIngredients = new HashMap<>();

        for (Recipe recipe : filteredRecipes) {
            String recipeTitle = recipe.getTitle();
            matchPercentages.put(recipeTitle, recipe.getMatchPercentage(availableIngredients));
            missingIngredients.put(recipeTitle, recipe.getMissingIngredients(availableIngredients));
        }
        
        // Add data to model
        model.addAttribute("recipes", filteredRecipes);
        model.addAttribute("matchPercentages", matchPercentages);
        model.addAttribute("cuisineTypes", recipeStorage.getAllCuisineTypes());
        model.addAttribute("selectedCuisine", cuisine != null ? cuisine : "all");
        model.addAttribute("selectedDiet", diet != null ? diet : "none");
        model.addAttribute("canMakeFilter", canMake);
        model.addAttribute("totalIngredients", availableIngredients.size());

        return "recipes";
    }
}
