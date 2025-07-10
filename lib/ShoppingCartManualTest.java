import java.util.ArrayList;

public class ShoppingCartManualTest {

/**
 * Executes a series of manual tests on the ShoppingCartCalculator class to verify
 * the correctness of the calculateTotalPrice method. Tests include scenarios such as 
 * null cart, empty cart, normal cart without discounts, BOGO discounts with even and 
 * odd quantities, bulk discounts, unknown SKU, and mixed discount items. Outputs the 
 * result of each test and a summary of passed and failed tests.
 */

    public static void run() {
        System.out.println("--- Starting Shopping Cart Calculator Tests ---");
        System.out.println(); // for spacing

        int passedCount = 0;
        int failedCount = 0;

        // Test 1: cart is null
        try {
            double total1 = ShoppingCartCalculator.calculateTotalPrice(null);
            if (total1 == 0.0) {
                System.out.println("PASSED: Null cart should return (0.0)");
                passedCount++;
            } else {
                System.out.println("FAILED: Null cart expected 0.0 but got " + total1);
                failedCount++;
            }
        } catch (Exception e) {
            System.out.println("FAILED: Null cart caused an exception: " + e.getMessage());
            failedCount++;
        }

        // Test 2: cart is empty
        ArrayList<CartItem> emptyCart = new ArrayList<>();
        double total2 = ShoppingCartCalculator.calculateTotalPrice(emptyCart);
        if (total2 == 0.0) {
            System.out.println("PASSED: Empty cart should return (0.0)");
            passedCount++;
        } else {
            System.out.println("FAILED: Empty cart expected 0.0 but got " + total2);
            failedCount++;
        }

        // Test 3: normal cart without discounts
        ArrayList<CartItem> simpleCart = new ArrayList<>();
        simpleCart.add(new CartItem("NORMAL", "Bread", 25.0, 2)); // 50
        simpleCart.add(new CartItem("NORMAL", "Milk", 15.0, 1));  // 15
        double total3 = ShoppingCartCalculator.calculateTotalPrice(simpleCart);
        if (total3 == 65.0) {
            System.out.println("PASSED: Simple cart total is correct (65.0)");
            passedCount++;
        } else {
            System.out.println("FAILED: Simple cart total expected 65.0 but got " + total3);
            failedCount++;
        }

        // Test 4: BOGO with even number of items
        ArrayList<CartItem> bogoCart1 = new ArrayList<>();
        bogoCart1.add(new CartItem("bogo", "Chips", 15.0, 2)); // 15
        bogoCart1.add(new CartItem("bogo", "Soda", 10.0, 2));  // 10
        double total4 = ShoppingCartCalculator.calculateTotalPrice(bogoCart1);
        if (total4 == 25.0) {
            System.out.println("PASSED: BOGO cart total is correct (25.0)");
            passedCount++;
        } else {
            System.out.println("FAILED: BOGO cart total expected 25.0 but got " + total4);
            failedCount++;
        }

        // Test 5: BOGO with odd number of item
        ArrayList<CartItem> bogoCart2 = new ArrayList<>();
        bogoCart2.add(new CartItem("bogo", "Chips", 15.0, 3)); // 30
        bogoCart2.add(new CartItem("bogo", "Soda", 10.0, 3));  // 20
        double total5 = ShoppingCartCalculator.calculateTotalPrice(bogoCart2);
        if (total5 == 50.0) {
            System.out.println("PASSED: BOGO cart total is correct (50.0)");
            passedCount++;
        } else {
            System.out.println("FAILED: BOGO cart total expected 50.0 but got " + total5);
            failedCount++;
        }

        // Test 6: Bulk discount
        ArrayList<CartItem> bulkCart = new ArrayList<>();
        bulkCart.add(new CartItem("bulk", "Eggs", 5.0, 12)); // 54
        bulkCart.add(new CartItem("bulk", "Brocoli", 15.0, 8));  // 108
        double total6 = ShoppingCartCalculator.calculateTotalPrice(bulkCart);
        if (total6 == 162.0) {
            System.out.println("PASSED: Bulk cart total is correct (162.0)");
            passedCount++;
        } else {
            System.out.println("FAILED: Bulk cart total expected 162.0 but got " + total6);
            failedCount++;
        }

        // Test 7: Bulk but did not reach the discount treshold
        ArrayList<CartItem> bulkCart2 = new ArrayList<>();
        bulkCart2.add(new CartItem("bulk", "Eggs", 5.0, 5)); // 25
        bulkCart2.add(new CartItem("bulk", "Brocoli", 15.0, 5));  // 75
        double total7 = ShoppingCartCalculator.calculateTotalPrice(bulkCart2);
        if (total7 == 100.0) {
            System.out.println("PASSED: Bulk cart total is correct (100.0)");
            passedCount++;
        } else {
            System.out.println("FAILED: Bulk cart total expected 100.0 but got " + total7);
            failedCount++;
        }

        // Test 8: Unknown SKU should get no discount
        ArrayList<CartItem> unknownCart = new ArrayList<>();
        unknownCart.add(new CartItem("unknown", "MysteryItem", 10.0, 2)); // 20.0
        double total8 = ShoppingCartCalculator.calculateTotalPrice(unknownCart);
        if (total8 == 20.0) {
            System.out.println("PASSED: Unknown SKU total is correct (20.0)");
            passedCount++;
        } else {
            System.out.println("FAILED: Unknown SKU expected 20.0 but got " + total8);
            failedCount++;
        }

        // Test 9: Mixed discounts
        ArrayList<CartItem> mixedCart = new ArrayList<>();
        mixedCart.add(new CartItem("bogo", "Chips", 15.0, 3));  // 30.0
        mixedCart.add(new CartItem("bulk", "Eggs", 5.0, 6));    // 27.0
        mixedCart.add(new CartItem("NORMAL", "Bread", 25.0, 1)); // 25.0
        double total9 = ShoppingCartCalculator.calculateTotalPrice(mixedCart);
        if (total9 == 82.0) {
            System.out.println("PASSED: Mixed cart total is correct (82.0)");
            passedCount++;
        } else {
            System.out.println("FAILED: Mixed cart total expected 82.0 but got " + total9);
            failedCount++;
        }

        // --- Test Summary ---
        System.out.println("\n--------------------");
        System.out.println("--- Test Summary ---");
        System.out.println("Passed: " + passedCount + ", Failed: " + failedCount);
        if (failedCount == 0) {
            System.out.println("Excellent! All tests passed!");
        } else {
            System.out.println("Some tests failed.");
        }
    }
}
