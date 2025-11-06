// for spring boot
package project.springbootproject.model;

import java.util.List;

public class Recipe {
    private String title;
    private List<Ingredient> ingredients;
    private String instructions;
    private String source;
    private int timeRequired;
    private String cuisineType;

    public Recipe(String title, List<Ingredient> ingredients, String instructions, String source, int timeRequired, String cuisineType) {
        this.title = title;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.source = source;
        this.timeRequired = timeRequired;
        this.cuisineType = cuisineType;
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

    public String getCuisineType() {
        return cuisineType;
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

    public boolean isVegitarian(){
        // Simple check for common non-vegetarian ingredients
        String[] nonVegIngredients = {"chicken", "beef", "pork", "fish", "shrimp", "lamb", "turkey", "bacon", "sausage", "gelatin", "crab", "lobster"};
        for(Ingredient ingredient : ingredients){
            for(String nonVeg : nonVegIngredients){
                if(ingredient.toLowerCase().contains(nonVeg)){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isVegan(){
        if(isVegitarian() == false){
            return false;
        }
        else{
        // Simple check for common non-vegan ingredients
        String[] nonVeganIngredients = {"milk", "cheese", "egg", "honey", "butter", "yogurt"};
        for(String ingredient : ingredients){
            for(String nonVegan : nonVeganIngredients){
                if(ingredient.toLowerCase().contains(nonVegan)){
                    return false;
                }
            }
        }
        return true;
        }
    }

    @Override
    public String toString() {
        return "Recipe: " + title + "\nIngredients: " + String.join(", ", ingredients) + "\nInstructions: " + instructions;
    }
}
