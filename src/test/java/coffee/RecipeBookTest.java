package coffee;

import coffee.exceptions.RecipeException;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the RecipeBook class.
 */
public class RecipeBookTest {

    private RecipeBook recipeBook;
    private Recipe recipe1;
    private Recipe recipe2;

    @BeforeEach
    public void setUp() throws RecipeException {
        recipeBook = new RecipeBook();

        // Set up recipe1
        recipe1 = new Recipe();
        recipe1.setName("Espresso");
        recipe1.setAmtCoffee("5");
        recipe1.setAmtMilk("0");
        recipe1.setAmtSugar("0");
        recipe1.setAmtChocolate("0");
        recipe1.setPrice("50");

        // Set up recipe2
        recipe2 = new Recipe();
        recipe2.setName("Latte");
        recipe2.setAmtCoffee("3");
        recipe2.setAmtMilk("2");
        recipe2.setAmtSugar("1");
        recipe2.setAmtChocolate("0");
        recipe2.setPrice("75");
    }

    @Test
    public void testAddRecipe_Normal() {
        assertTrue(recipeBook.addRecipe(recipe1)); // Adding a valid recipe should return true
    }

    @Test
    public void testAddRecipe_Duplicate() {
        recipeBook.addRecipe(recipe1);
        assertFalse(recipeBook.addRecipe(recipe1)); // Adding a duplicate recipe should return false
    }

    @Test
    public void testAddRecipe_FullRecipeBook() throws RecipeException {
        // Fill the recipe book with 4 recipes
        recipeBook.addRecipe(recipe1);
        recipeBook.addRecipe(recipe2);
        recipeBook.addRecipe(new Recipe());
        recipeBook.addRecipe(new Recipe());

        // Attempt to add a 5th recipe
        Recipe recipe5 = new Recipe();
        recipe5.setName("Mocha");
        assertFalse(recipeBook.addRecipe(recipe5)); // Should return false since the recipe book is full
    }

    @Test
    public void testDeleteRecipe_Normal() {
        recipeBook.addRecipe(recipe1);
        assertEquals("Espresso", recipeBook.deleteRecipe(0)); // Deleting the first recipe should return its name
    }

    @Test
    public void testDeleteRecipe_InvalidIndex() {
        assertNull(recipeBook.deleteRecipe(5)); // Deleting a recipe at an invalid index should return null
    }

    @Test
    public void testEditRecipe_Normal() {
        recipeBook.addRecipe(recipe1);
        assertEquals("Espresso", recipeBook.editRecipe(0, recipe2)); // Editing the first recipe should return its name
    }

    @Test
    public void testEditRecipe_InvalidIndex() {
        assertNull(recipeBook.editRecipe(5, recipe2)); // Editing a recipe at an invalid index should return null
    }

    @Test
    public void testGetRecipes() {
        recipeBook.addRecipe(recipe1);
        recipeBook.addRecipe(recipe2);
        Recipe[] recipes = recipeBook.getRecipes();
        assertEquals(recipe1, recipes[0]); // First recipe should be recipe1
        assertEquals(recipe2, recipes[1]); // Second recipe should be recipe2
    }
}