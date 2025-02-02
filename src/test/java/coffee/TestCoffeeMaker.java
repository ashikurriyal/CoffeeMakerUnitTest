package coffee;

import coffee.exceptions.InventoryException;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for CoffeeMaker class.
 */
public class TestCoffeeMaker {

    private CoffeeMaker cm;
    private Recipe r1;
    private Recipe r2;
    private Recipe r3;
    private Recipe r4;

    @BeforeEach
    public void setUp() throws Exception {
        cm = new CoffeeMaker();

        // Set up for r1
        r1 = new Recipe();
        r1.setName("Coffee");
        r1.setAmtChocolate("0");
        r1.setAmtCoffee("3");
        r1.setAmtMilk("1");
        r1.setAmtSugar("1");
        r1.setPrice("50");

        // Set up for r2
        r2 = new Recipe();
        r2.setName("Mocha");
        r2.setAmtChocolate("20");
        r2.setAmtCoffee("3");
        r2.setAmtMilk("1");
        r2.setAmtSugar("1");
        r2.setPrice("75");

        // Set up for r3
        r3 = new Recipe();
        r3.setName("Latte");
        r3.setAmtChocolate("0");
        r3.setAmtCoffee("3");
        r3.setAmtMilk("3");
        r3.setAmtSugar("1");
        r3.setPrice("100");

        // Set up for r4
        r4 = new Recipe();
        r4.setName("Hot Chocolate");
        r4.setAmtChocolate("4");
        r4.setAmtCoffee("0");
        r4.setAmtMilk("1");
        r4.setAmtSugar("1");
        r4.setPrice("65");
    }

    @Test
    public void testAddInventory_Normal() {
        try {
            cm.addInventory("4", "7", "0", "9"); // Coffee, Milk, Sugar, Chocolate
        } catch (InventoryException e) {
            fail("InventoryException should not be thrown");
        }
        String inventory = cm.checkInventory();
        String expected = "Coffee: 19\nMilk: 22\nSugar: 15\nChocolate: 24\n";
        assertEquals(expected, inventory);
    }

    @Test
    public void testAddInventoryException() {
        Throwable exception = assertThrows(InventoryException.class, () -> {
            cm.addInventory("4", "-1", "asdf", "3"); // Should throw an InventoryException
        });
    }

    @Test
    public void testMakeCoffee_Normal() {
        cm.addRecipe(r1);
        assertEquals(25, cm.makeCoffee(0, 75)); // Price is 50, paid 75, change should be 25
    }

    @Test
    public void testAddInventory_ZeroValues() throws InventoryException {
        cm.addInventory("0", "0", "0", "0"); // Add zero values to inventory
        String inventory = cm.checkInventory();
        String expected = "Coffee: 15\nMilk: 15\nSugar: 15\nChocolate: 15\n"; // Inventory should remain unchanged
        assertEquals(expected, inventory);
    }

    @Test
    public void testMakeCoffee_ExactPayment() {
        cm.addRecipe(r1); // Add a recipe with price 50
        assertEquals(0, cm.makeCoffee(0, 50)); // Pay exactly 50, change should be 0
    }
    @Test
    public void testMakeCoffee_InsufficientPayment() {
        cm.addRecipe(r1);
        assertEquals(40, cm.makeCoffee(0, 40)); // Price is 50, paid 40, change should be 40 (insufficient payment)
    }

    @Test
    public void testMakeCoffee_RecipeNotFound() {
        assertEquals(100, cm.makeCoffee(0, 100)); // No recipe added, should return full payment
    }

    @Test
    public void testMakeCoffee_InsufficientIngredients() {
        cm.addRecipe(r2); // Mocha requires 20 chocolate, but inventory starts with 15
        assertEquals(75, cm.makeCoffee(0, 75)); // Insufficient ingredients, should return full payment
    }

    @Test
    public void testAddRecipe_Normal() {
        assertTrue(cm.addRecipe(r1)); // Adding a valid recipe should return true
    }

    @Test
    public void testAddRecipe_Duplicate() {
        cm.addRecipe(r1);
        assertFalse(cm.addRecipe(r1)); // Adding a duplicate recipe should return false
    }

    @Test
    public void testDeleteRecipe_Normal() {
        cm.addRecipe(r1);
        assertEquals("Coffee", cm.deleteRecipe(0)); // Deleting the first recipe should return its name
    }

    @Test
    public void testDeleteRecipe_InvalidIndex() {
        assertNull(cm.deleteRecipe(5)); // Deleting a recipe at an invalid index should return null
    }

    @Test
    public void testEditRecipe_Normal() {
        cm.addRecipe(r1);
        assertEquals("Coffee", cm.editRecipe(0, r2)); // Editing the first recipe should return its name
    }

    @Test
    public void testEditRecipe_InvalidIndex() {
        assertNull(cm.editRecipe(5, r2)); // Editing a recipe at an invalid index should return null
    }

    @Test
    public void testCheckInventory() {
        String inventory = cm.checkInventory();
        String expected = "Coffee: 15\nMilk: 15\nSugar: 15\nChocolate: 15\n"; // Initial inventory
        assertEquals(expected, inventory);
    }
}