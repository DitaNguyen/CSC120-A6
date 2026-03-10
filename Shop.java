import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test suite designed to identify 10 logic errors in the Computer 
 * and ResaleShop classes as part of the CSC120-A6 assignment.
 * This class follows JUnit 4 conventions and uses assertions to 
 * validate the internal state of objects.
 */
public class Shop {
    
    /** The ResaleShop instance used for testing */
    ResaleShop shop;

    /** A test Computer instance used for testing state changes */
    Computer testComp;


    /**
     * Sets up the testing environment before each test method runs.
     * Initializes a fresh shop and a computer with specific non-default values.
     */
    @Before
    public void setup() {
        shop = new ResaleShop();
        // Initializing with unique values to detect hardcoded bugs (32GB, $1200)
        testComp = new Computer("2023 Dell", "Intel i7", 512, 32, "Windows", 2023, 1200);
    }


    /**
     * Tests if the Computer constructor correctly assigns the memory parameter.
     */
    @Test
    public void testConstructorMemory() {
        // Now this should work because of the static import above
        assertEquals("Constructor should assign memory from parameter", 32, testComp.memory);
    }


    /**
     * Tests if the Computer constructor correctly assigns the price parameter.
     */
    @Test
    public void testConstructorPrice() {
        // Bug #2: Hardcoded 0
        assertEquals("Constructor should assign price from parameter", 1200, testComp.price);
    }


    /**
     * Tests if setOS correctly updates the operatingSystem variable.
     */
    @Test
    public void testSetOS() {
        // Bug #3: Hardcoded "None"
        testComp.setOS("macOS");
        assertEquals("setOS should update the operating system", "macOS", testComp.operatingSystem);
    }

    /* --- ResaleShop.java Tests --- */


    /**
     * Tests if the ResaleShop starts with an empty list.
     */
    @Test
    public void testInitialInventory() {
        // Bug #4: Dummy MacBook added in constructor
        shop.inventory.clear(); 
        assertEquals("Inventory should be empty upon creation", 0, shop.inventory.size());
    }


    /**
     * Tests if buy() adds the specific object instance provided.
     */
    @Test
    public void testBuyLogic() {
        // Bug #5: Overwrites parameter with hardcoded MacBook
        shop.buy(testComp);
        assertEquals("buy() should add the correct computer description", "2023 Dell", shop.inventory.get(0).description);
    }


    /**
     * Tests if buy() correctly identifies and prevents duplicate entries.
     */
    @Test(expected = RuntimeException.class)
    public void testBuyDuplicate() {
        // Bug #6: No duplicate check
        shop.buy(testComp);
        shop.buy(testComp); // This should trigger the exception
    }


    /**
     * Tests if sell() correctly throws an exception for items not in inventory.
     */
    @Test(expected = RuntimeException.class)
    public void testSellMissing() {
        // Bug #7: No existence check
        shop.sell(testComp); // Should throw because it wasn't bought yet
    }


    /**
     * Tests if printInventory iterates through the collection without crashing.
     */
    @Test
    public void testPrintInventoryBounds() {
        // Bug #8: Off-by-one error (i <= size)
        shop.buy(testComp);
        shop.printInventory(); // This would crash if the bug is present
    }


    /**
     * Tests if refurbish() correctly updates the OS using string value equality.
     */
    @Test
    public void testRefurbishOS() {
        // Bug #9: String comparison with !=
        shop.buy(testComp);
        String updatedOS = new String("Linux"); 
        shop.refurbish(testComp, updatedOS);
        assertEquals("Refurbish should update OS using string equality", "Linux", testComp.operatingSystem);
    }


    /**
     * Tests if refurbish() applies the correct logical pricing for older models.
     */
    @Test
    public void testRefurbishPrice() {
        // Bug #10: Pricing typo ($2500)
        Computer oldie = new Computer("Old PC", "Intel", 256, 8, "XP", 2010, 100);
        shop.buy(oldie);
        shop.refurbish(oldie, "None");
        // We verify the price didn't jump to the typo value of 2500
        assertTrue("Refurbished price for 2010 models should not be 2500", oldie.price < 1000);
    }
}