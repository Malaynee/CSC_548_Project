package project.springbootproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import project.springbootproject.model.IngredientStorage;

/**
 * Controller responsible for rendering the Ingredients page
 * using Thymeleaf (instead of returning JSON via REST)
 * 
 * This class handles browser-based requests like "/ingredients + sends data from the backend 
 * (IngredientStorage) into the Thymeleaf HTML template (templates/ingredients.html)
 */

@Controller // Marks this class as a Thymeleaf (MVC) controller
public class IngredientViewController {
    // Storage object manages and provides ingredient data
    private final IngredientStorage storage = new IngredientStorage();
    /**
     * Handles GET requests for "/ingredients" -> loads all ingredients from storage and adds them to the
     * model, which Thymeleaf uses to display data
     */
    @GetMapping("/ingredients")
    public String viewIngredients(Model model) {
        // Load/refresh ingredient data before displaying
        storage.loadIngredients();

        // Add data to the Thymeleaf model
        // ("ingredients" is the key accessible in the HTML)
        model.addAttribute("ingredients", storage.getIngredients());
        // Return the name of the Thymeleaf template
        return "ingredients";
    }
}
