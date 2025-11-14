// for spring boot
package project.springbootproject.model;

/**
 * Object class that represents a single ingredient used in recipes;
 * 
 * Each ingredient has a name, quantity, + unit of measurement
 * Equality is based on the ingredient name (case-insensitive) so
 * duplicate ingredients (ex. Sugar vs sugar) are treated as the same
 *
 * ex:
 *     Ingredient flour = new Ingredient("Flour", 2.5, "cups");
 *     System.out.println(flour); // Output: 2.5 cups Flour
 *
 * Extra brainstorming(?):
 * - Later, we might add categories (e.g., "Dairy", "Spice", "Produce").
 * - Could also link ingredients to a 'pantry' or user inventory table.
 */

public class Ingredient {
    private String name;
    private double quantity;
    private String unit;

    // No-arg constructor for Json
    public Ingredient() {
    }
    
    public Ingredient(String name, double quantity, String unit) {
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
    }

    // Getters + Setters
    public String getName() {
      return name; 
    }
    public double getQuantity() {
      return quantity;
    }
    public String getUnit() {
      return unit; 
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setQuantity(double quantity) {
      this.quantity = quantity; 
    }
    public void setUnit(String unit) {
      this.unit = unit; 
    }

    // Object Overrides
    @Override
    public String toString() {
        return quantity + " " + unit + " " + name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ingredient)) return false;
        Ingredient i = (Ingredient) o;
        return name.equalsIgnoreCase(i.name);
    }

    @Override
    public int hashCode() {
        return name.toLowerCase().hashCode();
    }
}
