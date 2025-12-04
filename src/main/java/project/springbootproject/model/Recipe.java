// for spring boot
package project.springbootproject.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Recipe {
    private String title;
    private List<Ingredient> ingredients;
    private String instructions;
    private String source;
    private int timeRequired;
    private String timeUnit;
    private String cuisineType;
    private boolean thumbsUpRating;
    private boolean thumbsDownRating;
    private List<String> datesUsed;
    private boolean saveRecipe;

    // No-arg constructor for Gson
    public Recipe() {
        this.ingredients = new ArrayList<>();
        this.datesUsed = new ArrayList<>();
    }
    
    public Recipe(String title, List<Ingredient> ingredients, String instructions, String source, int timeRequired, String timeUnit, String cuisineType, boolean thumbsUpRating, boolean thumbsDownRating, List<String> datesUsed) {
        this.title = title;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.source = source;
        this.timeRequired = timeRequired;
        this.timeUnit = timeUnit;
        this.cuisineType = cuisineType;
        this.thumbsUpRating = thumbsUpRating;
        this.thumbsDownRating = thumbsDownRating;
        this.datesUsed = datesUsed;
        this.saveRecipe = saveRecipe;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setIngredients(List<Ingredient> ingredients){
        this.ingredients = ingredients;
    }

    public void setInstructions(String instructions){
        this.instructions = instructions;
    }

    public void setSource(String source){
        this.source = source;
    }

    public void setTimeRequired(int timeRequired){
        this.timeRequired = timeRequired;
    }

    public void setTimeUnit(String timeUnit){
        this.timeUnit = timeUnit;
    }

    public void setCuisineType(String cuisineType){
        this.cuisineType = cuisineType;
    }

    public void setSave(boolean saveRecipe){
        this.saveRecipe = saveRecipe;
    }

    public String getTitle() {
        return title;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

    public String getSource() {
        return source;
    }

    public int getTimeRequired() {
        return timeRequired;
    }

    public String getTimeUnit(){
        return timeUnit;
    }

    public String getCuisineType() {
        return cuisineType;
    }

    public boolean getThumbsUpRating(){
        return thumbsUpRating;
    }

    public boolean getThumbsDownRating(){
        return thumbsDownRating;
    }

    public boolean getSavedRecipe(){
        return saveRecipe;
    }

    public void setThumbsUpRating(boolean thumbsUpRating){
        this.thumbsUpRating = thumbsUpRating;
    }

    public void setThumbsDownRating(boolean thumbsDownRating){
        this.thumbsDownRating = thumbsDownRating;
    }

    public List<String> getDatesUsed(){
        return datesUsed;
    }

    public void setDatesUsed(String date){
        datesUsed.add(date);
    }

    public boolean hasIngredient(Ingredient ingredient){
        return ingredients.contains(ingredient);
    }

    public void addIngredient(Ingredient ingredient){
        ingredients.add(ingredient);
    }

    public void removeIngredient(Ingredient ingredient){
        ingredients.remove(ingredient);
    }

    public boolean isVegetarian() {
        // Simple check for common non-vegetarian ingredients
        String[] nonVegIngredients = {"chicken", "beef", "pork", "fish", "shrimp", 
                                      "lamb", "turkey", "bacon", "sausage", "gelatin", 
                                      "crab", "lobster"};
        for (Ingredient ingredient : ingredients) {
            String ingredientName = ingredient.getName().toLowerCase();
            for (String nonVeg : nonVegIngredients) {
                if (ingredientName.contains(nonVeg)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isVegan() {
        if (!isVegetarian()) {
            return false;
        }
        // Simple check for common non-vegan ingredients
        String[] nonVeganIngredients = {"milk", "cheese", "egg", "eggs", "honey", 
                                        "butter", "yogurt", "cream", "whey"};
        for (Ingredient ingredient : ingredients) {
            String ingredientName = ingredient.getName().toLowerCase();
            for (String nonVegan : nonVeganIngredients) {
                if (ingredientName.contains(nonVegan)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    // Checks if this recipe can be made w/ available ingredients
    /** public boolean canMakeWithIngredients(List<Ingredient> availableIngredients) {
        // Create a set of available ingredient names (case-insensitive)
        Set<String> availableNames = availableIngredients.stream()
            .map(ing -> ing.getName().toLowerCase().trim())
            .collect(Collectors.toSet());
        // Check if all recipe ingredients are available
        for (Ingredient requiredIngredient : this.ingredients) {
            String requiredName = requiredIngredient.getName().toLowerCase().trim();
            
            if (!availableNames.contains(requiredName)) {
                return false; // Missing this ingredient
            }
        }
        return true; // Has all ingredients!
    } */
    public boolean canMakeWithIngredients(List<Ingredient> availableIngredients) {
        if (availableIngredients == null || availableIngredients.isEmpty()) {
            return false;
        }
        
        if (this.ingredients == null || this.ingredients.isEmpty()) {
            return false;
        }
        
        // Create a set of available ingredient names (case-insensitive)
        java.util.Set<String> availableNames = availableIngredients.stream()
            .map(ing -> ing.getName().toLowerCase().trim())
            .collect(java.util.stream.Collectors.toSet());
        
        // Check if all recipe ingredients are available
        for (Ingredient requiredIngredient : this.ingredients) {
            String requiredName = requiredIngredient.getName().toLowerCase().trim();
            
            if (!availableNames.contains(requiredName)) {
                return false; // Missing this ingredient
            }
        }  
        return true; // Has all ingredients!
    }
    
    // Gets list of missing ingredients
    /** public List<String> getMissingIngredients(List<Ingredient> availableIngredients) {
        Set<String> availableNames = availableIngredients.stream()
            .map(ing -> ing.getName().toLowerCase().trim())
            .collect(Collectors.toSet());
        
        List<String> missing = new ArrayList<>();
        
        for (Ingredient requiredIngredient : this.ingredients) {
            String requiredName = requiredIngredient.getName().toLowerCase().trim();
            
            if (!availableNames.contains(requiredName)) {
                missing.add(requiredIngredient.getName());
            }
        }
        return missing;
    } */
    public List<String> getMissingIngredients(List<Ingredient> availableIngredients) {
        List<String> missing = new java.util.ArrayList<>();
        
        if (this.ingredients == null || this.ingredients.isEmpty()) {
            return missing;
        }
        
        if (availableIngredients == null || availableIngredients.isEmpty()) {
            // All ingredients are missing
            for (Ingredient ing : this.ingredients) {
                missing.add(ing.getName());
            }
            return missing;
        }
        
        java.util.Set<String> availableNames = availableIngredients.stream()
            .map(ing -> ing.getName().toLowerCase().trim())
            .collect(java.util.stream.Collectors.toSet());
        
        for (Ingredient requiredIngredient : this.ingredients) {
            String requiredName = requiredIngredient.getName().toLowerCase().trim();
            
            if (!availableNames.contains(requiredName)) {
                missing.add(requiredIngredient.getName());
            }
        }
        
        return missing;
    }
    
    // Calculates match percentage
    public int getMatchPercentage(List<Ingredient> availableIngredients) {
        if (this.ingredients == null || this.ingredients.isEmpty()) {
            return 0;
        }
        if (availableIngredients == null || availableIngredients.isEmpty()) {
            return 0;
        }
        java.util.Set<String> availableNames = availableIngredients.stream()
            .map(ing -> ing.getName().toLowerCase().trim())
            .collect(Collectors.toSet());
        
        int totalRequired = this.ingredients.size();
        int matchCount = 0;
        
        for (Ingredient requiredIngredient : this.ingredients) {
            String requiredName = requiredIngredient.getName().toLowerCase().trim();
            if (availableNames.contains(requiredName)) {
                matchCount++;
            }
        }
        return (int) ((matchCount * 100.0) / totalRequired);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Recipe: ").append(title).append("\nIngredients: ");
        for (Ingredient ing : ingredients) {
            sb.append(ing.toString()).append(", ");
        }
        sb.append("\nInstructions: ").append(instructions);
        return sb.toString();
    }
}
