document.addEventListener('DOMContentLoaded', function() {
    // Get references to HTML elements we need
    const form = document.getElementById('recipeForm');
    const addIngredientBtn = document.getElementById('addIngredient');
    const ingredientsContainer = document.getElementById('ingredientsContainer'); // ← Fixed: added 's'
    const formMessage = document.getElementById('formMessage');
    
    // Add new ingredient fields when the "Add Another Ingredient" button is pressed
    addIngredientBtn.addEventListener('click', function () {
        // Create a new div for ingredient inputs
        const newIngredient = document.createElement('div');
        newIngredient.className = 'ingredient-entry';
        
        // Add the HTML for ingredient inputs
        newIngredient.innerHTML = `
            <input type="text" name="ingredientName[]" placeholder="Ingredient Name" required>
            <input type="number" name="ingredientQuantity[]" placeholder="Quantity" step="0.01" required>
            <select name="ingredientUnit[]" required>
              <option value="grams">grams</option>
              <option value="cups">cups</option>
              <option value="tbsp">tablespoons</option>
              <option value="tsp">teaspoons</option>
              <option value="pieces">pieces</option>
              <option value="oz">ounces</option>
              <option value="lb">pounds</option>
            </select>
            <button type="button" class="remove-ingredient">Remove</button>`;
        
        // Add the new ingredient fields before the "Add Another Ingredient" button
        ingredientsContainer.insertBefore(newIngredient, addIngredientBtn);
    });
    
    // Remove ingredient fields when remove button is pressed
    ingredientsContainer.addEventListener('click', function(e) {
        if (e.target.classList.contains('remove-ingredient')) {  // ← Fixed: 'remove' not 'removed'
            const entries = document.querySelectorAll('.ingredient-entry');
            // Keep at least one ingredient entry
            if (entries.length > 1) {
                e.target.parentElement.remove();
            } else {
                alert('You must have at least one ingredient!');
            }
        }
    });
    
    // Handle form submission
    form.addEventListener('submit', async function(e) {
        // Prevent the form from submitting normally
        e.preventDefault();
        
        // Collect all ingredients
        const ingredients = [];
        const ingredientEntries = document.querySelectorAll('.ingredient-entry');
        
        ingredientEntries.forEach(entry => {
            ingredients.push({
                name: entry.querySelector('[name="ingredientName[]"]').value,
                quantity: parseFloat(entry.querySelector('[name="ingredientQuantity[]"]').value),
                unit: entry.querySelector('[name="ingredientUnit[]"]').value
            });
        });
        
        // Create the recipe object with all form data
        const recipeData = {
            title: document.getElementById('recipeTitle').value,
            ingredients: ingredients,
            instructions: document.getElementById('recipeInstructions').value,
            source: document.getElementById('recipeSource').value || 'User Submitted',
            timeRequired: parseInt(document.getElementById('recipeTime').value),
            cuisineType: document.getElementById('recipeCuisine').value
        };
        
        try {
            // Send the data to server
            const response = await fetch('/api/recipes', {  // ← Fixed: 'recipes' not 'recipies'
                method: 'POST',  // ← Fixed: 'method' not 'methos'
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(recipeData)
            });
            
            if (response.ok) {
                // Show success message
                formMessage.textContent = 'Recipe added successfully!';
                formMessage.className = 'success';  // ← Fixed: 'success' not 'sucess'
                formMessage.style.display = 'block';
                
                // Clear the form
                form.reset();
                
                // Reload page after 1.5 seconds to show new recipe
                setTimeout(() => location.reload(), 1500);
            } else {
                throw new Error('Failed to add recipe');
            }
        } catch (error) {
            // Show error message if something goes wrong
            formMessage.textContent = 'Error adding recipe: ' + error.message;
            formMessage.className = 'error';
            formMessage.style.display = 'block';
        }
    });
    
    // Optional: Client-side recipe search functionality
    const searchInput = document.getElementById('recipeSearch');
    if (searchInput) {
        searchInput.addEventListener('input', function(e) {
            const searchTerm = e.target.value.toLowerCase();
            const recipeCards = document.querySelectorAll('.recipe-card');
            
            recipeCards.forEach(card => {
                const title = card.querySelector('h3').textContent.toLowerCase();
                if (title.includes(searchTerm)) {
                    card.style.display = 'block';
                } else {
                    card.style.display = 'none';
                }
            });
        });
    }
});

// New function to add dates to recipes
function addDateToRecipe(button, recipeTitle) {
    const dateInput = prompt(`Enter date for "${recipeTitle}" (YYYY-MM-DD):`);
    if (dateInput) {
        // Validate date format
        if (/^\d{4}-\d{2}-\d{2}$/.test(dateInput)) {
            // TODO: Send to backend API when you create an endpoint
            console.log('Date to add:', dateInput);
            alert('Date added! (Backend integration coming soon)');
        } else {
            alert('Please enter date in YYYY-MM-DD format');
        }
    }
}