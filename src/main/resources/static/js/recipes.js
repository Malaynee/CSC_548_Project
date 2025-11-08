document.addEventListener('DOMContentLoaded', function() {
    //get referances to HTML element we need
    const form = document.getElementById('recipeForm');
    const addIngredientBtn = document.getElementById('addIngredient');
    const ingredientsContainer = document.getElementById('ingredientContainer');
    const formMessage = document.getElementById('formMessage');

    // add new ingredient fields when the add another ingredient button is presses
    addIngredientBtn.addEventListener('click', function () {
        //create a new div for ingredient inputs
        const newIngredient = document.createElement('div');
        newIngredient.className = 'ingredient-entry';

        //Add the HTML for ingredient inputs
        newIngredient.innerHTML = `
            <input type="text" name="ingredientName[]" placeholder="Ingredient Name" required>
            <input type="number" name="ingredientQuantity[]" placeholder="Quantity" step="0.01" required>
            <select name="ingredientUnit" required>
              <option value="grams">grams</option>
              <option value="cups">cups</option>
              <option value="tbsp">tablespoons</option>
              <option value="tsp">teaspoons</option>
              <option value="pieces">pieces</option>
            </select>
            <button type="button" class="remove-ingredient">Remove</button>`;
        
            //add the new ingredients fields before the "Add Another Ingredient" button
            ingredientsContainer.insertBefore(newIngredient, addIngredientBtn);
    });

    //remove ingredient fields when remove button is pressed
    ingredientsContainer.addEventListener('click', function(e) {
        if (e.target.classList.contains('removed-ingredient')){
            e.target.parentElement.remove();
        }
    });

    //handling for submission
    form.addEventListener('submit', async function(e) {
        //prevent the form from submitting normally
        e.preventDefault();

        //collect all ingredients
        const ingredients = [];
        const ingredientEntries = document.querySelectorAll('.ingredient-entry');

        ingredientEntries.forEach(entry => {
            ingredients.push({
                name: entry.querySelector('[name="ingredientName[]"]').value,
                quantity: parseFloat(entry.querySelector('[name="ingredientQuantity[]"]').value),
                unit: entry.querySelector('[name="ingredientUnit[]"]').value
            });
        });

        //create the recipe object with all form data
        const recipeData = {
            title: document.getElementById('recipeTitle').value,
            ingredients:ingredients,
            instructions: document.getElementById('recipeInstructions').value,
            source: document.getElementById('recipeSource').value,
            timeRequired: parseInt(document.getElementById('recipeTime').value),
            cuisineType: document.getElementById('recipeCuisine').value
        };

        try {
            //senf the data to server
            const response = await fetch('/api/recipies', {
                methos: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(recipeData)
            });

            if (response.ok) {
                //show sucess message
                formMessage.textContent = 'Recipe added successfully!';
                formMessage.className = 'sucess';
                form.reset(); //clear the form
            } else {
                throw new Error('Failed to add recipe');
            }
        } catch (error) {
            //show error message if something goes wrong
            formMessage.textContent = 'Error adding recipe: ' + error.message;
            formMessage.className = 'error';
        }
        
    });

});