// for spring boot
package project.springbootproject.model;

import java.util.ArrayList;
import java.util.List;

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

    public void setTitle(){
        this.title = title;
    }

    public void setIngredients(){
        this.ingredients = ingredients;
    }

    public void setInstructions(){
        this.instructions = instructions;
    }

    public void setSource(){
        this.source = source;
    }

    public void setTimeRequired(){
        this.timeRequired = timeRequired;
    }

    public void setTimeUnit(){
        this.timeUnit = timeUnit;
    }

    public void setCuisineType(){
        this.cuisineType = cuisineType;
    }

    public void setSave(){
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

    public void setThumbsUpRating(){
        this.thumbsUpRating = thumbsUpRating;
    }

    public void setThumbsDownRating(){
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
