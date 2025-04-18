package hotelmanagement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MenuTest {

    @Before
    public void setUp() {
        // Clear data before each test to ensure a clean environment
        Menu.menuDatabase.clear();
        Menu.menuCounter = 1;
    }

    @After
    public void tearDown() {
        // Clear data after each test
        Menu.menuDatabase.clear();
        Menu.menuCounter = 1;
    }

    @Test
    public void testAddMenuItemSuccess() {
        Menu.addMenuItem("Cheeseburger", "Food", 150.0, true);
        assertTrue(Menu.menuDatabase.containsKey(1));
    }

    @Test
    public void testAddMultipleMenuItems() {
        Menu.addMenuItem("Cheeseburger", "Food", 150.0, true);
        Menu.addMenuItem("Latte", "Beverage", 80.0, false);
        assertTrue(Menu.menuDatabase.containsKey(1));
        assertTrue(Menu.menuDatabase.containsKey(2));
    }

    @Test
    public void testDeleteMenuItemSuccess() {
        Menu.addMenuItem("Cheeseburger", "Food", 150.0, true);
        Menu.deleteMenuItem(1);
        assertFalse(Menu.menuDatabase.containsKey(1));
    }

    @Test
    public void testDeleteMenuItemNotFound() {
        Menu.deleteMenuItem(99);
        // Ensure output indicates "Menu item not found."
    }

    @Test
    public void testUpdateMenuItemDetailsSuccess() {
        Menu.addMenuItem("Cheeseburger", "Food", 150.0, true);
        Menu.updateMenuItemDetails(1, "Veggie Burger", "Food", 140.0, true);
        Menu item = Menu.menuDatabase.get(1);
        assertEquals("Veggie Burger", item.itemName);
        assertEquals(140.0, item.price, 0.0);
    }

    @Test
    public void testUpdateMenuItemDetailsNotFound() {
        Menu.updateMenuItemDetails(99, "New Item", "Food", 100.0, false);
        // Ensure output indicates "Menu item not found."
    }

    @Test
    public void testGetMenuItemDetailsSuccess() {
        Menu.addMenuItem("Cheeseburger", "Food", 150.0, true);
        Menu.getMenuItemDetails(1);
        // Ensure details are printed without exceptions
    }

    @Test
    public void testGetMenuItemDetailsNotFound() {
        Menu.getMenuItemDetails(99);
        // Ensure output indicates "Menu item not found."
    }

    @Test
    public void testSearchMenuItemSuccess() {
        Menu.addMenuItem("Cheeseburger", "Food", 150.0, true);
        Menu.searchMenuItem("cheese");
        // Ensure results are printed correctly
    }

    @Test
    public void testSearchMenuItemNoResults() {
        Menu.searchMenuItem("Pizza");
        // Ensure output indicates no results found
    }

    @Test
    public void testGenerateMenuReport() {
        Menu.addMenuItem("Cheeseburger", "Food", 150.0, true);
        Menu.addMenuItem("Latte", "Beverage", 80.0, false);
        Menu.generateMenuReport();
        // Ensure report contains all menu items
    }

    @Test
    public void testApplyMenuDiscount() {
        Menu.addMenuItem("Cheeseburger", "Food", 150.0, true);
        Menu.applyMenuDiscount(10);
        Menu item = Menu.menuDatabase.get(1);
        assertEquals(135.0, item.price, 0.0);
    }

    @Test
    public void testApplyMenuDiscountToMultipleItems() {
        Menu.addMenuItem("Cheeseburger", "Food", 150.0, true);
        Menu.addMenuItem("Latte", "Beverage", 80.0, false);
        Menu.applyMenuDiscount(10);
        Menu foodItem = Menu.menuDatabase.get(1);
        Menu beverageItem = Menu.menuDatabase.get(2);
        assertEquals(135.0, foodItem.price, 0.0);
        assertEquals(72.0, beverageItem.price, 0.0);
    }

    @Test
    public void testGetPopularItemsSuccess() {
        Menu.addMenuItem("Cheeseburger", "Food", 150.0, true);
        Menu.addMenuItem("Latte", "Beverage", 80.0, false);
        Menu.menuDatabase.get(1).popularity = 10;
        Menu.menuDatabase.get(2).popularity = 5;
        Menu.getPopularItems(6);
        // Ensure only popular items (popularity > 6) are listed
    }

    @Test
    public void testValidateMenuDataSuccess() {
        Menu.addMenuItem("Cheeseburger", "Food", 150.0, true);
        assertTrue(Menu.validateMenuData(1));
    }

    @Test
    public void testValidateMenuDataNotFound() {
        assertFalse(Menu.validateMenuData(99));
    }

    @Test
    public void testGetDailySpecials() {
        Menu.addMenuItem("Cheeseburger", "Food", 150.0, true);
        Menu.addMenuItem("Latte", "Beverage", 80.0, false);
        Menu.getDailySpecials();
        // Ensure daily specials are listed correctly
    }

    @Test
    public void testMenuDataPersistence() {
        Menu.addMenuItem("Cheeseburger", "Food", 150.0, true);
        assertEquals(1, Menu.menuDatabase.size());
    }
}
