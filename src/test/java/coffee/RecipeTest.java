package coffee;

import coffee.exceptions.RecipeException;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the Recipe class.
 */
public class RecipeTest {

    private Recipe recipe;

    @BeforeEach
    public void setUp() {
        recipe = new Recipe();
    }

    @Test
    public void testSetAndGetName() {
        recipe.setName("Espresso");
        assertEquals("Espresso", recipe.getName());
    }

    @Test
    public void testSetName_Null() {
        recipe.setName(null);
        assertEquals("", recipe.getName()); // Name should be set to empty string if null
    }

    @Test
    public void testSetAndGetPrice() throws RecipeException {
        recipe.setPrice("100");
        assertEquals(100, recipe.getPrice());
    }

    @Test
    public void testSetPrice_InvalidInput() {
        assertThrows(RecipeException.class, () -> {
            recipe.setPrice("invalid"); // Should throw RecipeException
        });
    }

    @Test
    public void testSetPriceNegative() {
        Recipe recipe = new Recipe();
        Exception exception = assertThrows(RecipeException.class, () -> {
            recipe.setPrice("-50"); // Negative price
        });
        assertEquals("Price must be a positive integer", exception.getMessage());
    }

    @Test
    public void testSetAndGetAmtCoffee() throws RecipeException {
        recipe.setAmtCoffee("10");
        assertEquals(10, recipe.getAmtCoffee());
    }

    // Tests setting an invalid (non-numeric) amount of coffee, expecting a RecipeException.
    @Test
    public void testSetAmtCoffee_InvalidInput() {
        assertThrows(RecipeException.class, () -> {
            recipe.setAmtCoffee("invalid"); // Should throw RecipeException
        });
    }

    @Test
    public void testSetAmtCoffee_NegativeValue() {
        assertThrows(RecipeException.class, () -> {
            recipe.setAmtCoffee("-5"); // Should throw RecipeException
        });
    }

    @Test
    public void testSetAndGetAmtMilk() throws RecipeException {
        recipe.setAmtMilk("5");
        assertEquals(5, recipe.getAmtMilk());
    }

    @Test
    public void testSetAmtMilk_InvalidInput() {
        assertThrows(RecipeException.class, () -> {
            recipe.setAmtMilk("invalid"); // Should throw RecipeException
        });
    }

    @Test
    public void testSetAmtMilk_NegativeValue() {
        assertThrows(RecipeException.class, () -> {
            recipe.setAmtMilk("-3"); // Should throw RecipeException
        });
    }

    @Test
    public void testSetAndGetAmtSugar() throws RecipeException {
        recipe.setAmtSugar("2");
        assertEquals(2, recipe.getAmtSugar());
    }

    @Test
    public void testSetAmtSugar_InvalidInput() {
        assertThrows(RecipeException.class, () -> {
            recipe.setAmtSugar("invalid"); // Should throw RecipeException
        });
    }

    @Test
    public void testSetAmtSugar_NegativeValue() {
        assertThrows(RecipeException.class, () -> {
            recipe.setAmtSugar("-1"); // Should throw RecipeException
        });
    }

    @Test
    public void testSetAndGetAmtChocolate() throws RecipeException {
        recipe.setAmtChocolate("4");
        assertEquals(4, recipe.getAmtChocolate());
    }

    @Test
    public void testSetAmtChocolate_InvalidInput() {
        assertThrows(RecipeException.class, () -> {
            recipe.setAmtChocolate("invalid"); // Should throw RecipeException
        });
    }

    @Test
    public void testSetAmtChocolate_NegativeValue() {
        assertThrows(RecipeException.class, () -> {
            recipe.setAmtChocolate("-2"); // Should throw RecipeException
        });
    }

    @Test
    public void testToString() {
        recipe.setName("Cappuccino");
        assertEquals("Cappuccino", recipe.toString());
    }

    @Test
    public void testEquals_SameRecipe() {
        Recipe recipe1 = new Recipe();
        recipe1.setName("Latte");
        Recipe recipe2 = new Recipe();
        recipe2.setName("Latte");
        assertTrue(recipe1.equals(recipe2));
    }

}