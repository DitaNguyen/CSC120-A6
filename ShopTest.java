import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * JUnit test suite for CSC120-A6.
 * Targets the logic errors in Computer.java and ResaleShop.java.
 */
public class ShopTest {

    ResaleShop shop;
    Computer testComp;

    @BeforeClass
    void setup() {
        shop = new ResaleShop();
        // Constructing a computer with 32GB RAM and $1200 price to detect hardcoding bugs
        testComp = new Computer("Test Desk", "AMD", 512, 32, "Windows", 2023, 1200);
    }

    // --- COMPUTER.JAVA TESTS --- //

    @Test
    void testConstructorMemoryAssignment() {
        // Targets Bug #1 (Hardcoded 16)
        assertEquals(32, testComp.memory, "Constructor should assign memory from parameter.");
    }

    @Test
    void testConstructorPriceAssignment() {
        // Targets Bug #2 (Hardcoded 0)
        assertEquals(1200, testComp.price, "Constructor should assign price from parameter.");
    }

    @Test
    void testSetOSAssignment() {
        // Targets Bug #3 (Hardcoded "None")
        testComp.setOS("macOS");
        assertEquals("macOS", testComp.operatingSystem, "setOS should update to the provided string.");
    }

    // --- RESALESHOP.JAVA TESTS --- //

    @Test
    void testInitialInventoryEmpty() {
        // Targets Bug #4 (Default MacBook in constructor)
        shop.inventory.clear(); // Ensure we are testing the list state
        assertEquals(0, shop.inventory.size(), "A new shop should start with 0 items.");
    }

    @Test
    void testBuyAddsSpecificInstance() {
        // Targets Bug #5 (Overwrites parameter with new Computer object)
        shop.buy(testComp);
        assertEquals("Test Desk", shop.inventory.get(0).description, "buy() must add the specific computer provided.");
    }

    @Test
    void testBuyDuplicateThrowsException() {
        // Targets Bug #6 (No duplicate check)
        shop.buy(testComp);
        assertThrows(RuntimeException.class, () -> shop.buy(testComp), "Should throw exception for duplicates.");
    }

    @Test
    void testSellMissingThrowsException() {
        // Targets Bug #7 (No existence check)
        assertThrows(RuntimeException.class, () -> shop.sell(testComp), "Should throw exception if computer not found.");
    }

    @Test
    void testPrintInventoryLoopBounds() {
        // Targets Bug #8 (Off-by-one error i <= size)
        shop.buy(testComp);
        assertDoesNotThrow(() -> shop.printInventory(), "Loop should not exceed array bounds.");
    }

    @Test
    void testRefurbishStringComparison() {
        // Targets Bug #9 (Uses != instead of .equals())
        shop.buy(testComp);
        String update = new String("Linux"); // Force different memory reference
        shop.refurbish(testComp, update);
        assertEquals("Linux", testComp.operatingSystem, "Refurbish should update OS using string equality.");
    }

    @Test
    void testRefurbishPriceTypo() {
        // Targets Bug #10 (Typo setting price to $2500 for old models)
        Computer oldie = new Computer("Old PC", "Intel", 128, 4, "XP", 2005, 50);
        shop.buy(oldie);
        shop.refurbish(oldie, "None");
        assertTrue(oldie.price < 1000, "Old computers should not be priced at $2500.");
    }
}