import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Calculates the total price of the items in a given shopping cart.
 * The total is calculated by summing up the price of each item, taking
 * into account any applicable discounts (Bulk or Buy-One-Get-One-Free).
 * If the cart is null or empty, 0.0 is returned.
 * @author 66721601184 || Tanakorn Trongkington
 */
public class ShoppingCartCalculator {

    private static final Map<String, DiscountRule> discountRules = new HashMap<>();
    static {
        discountRules.put("bogo", new DiscountRule(true, 0, 0));
        discountRules.put("bulk", new DiscountRule(false, 6, 0.1));
        // Can add more if needed
    }

    /**
     * Calculates the total price of the items in the given shopping cart.
     * The total is calculated by summing up the price of each item, taking
     * into account any applicable discounts (Bulk or Buy-One-Get-One-Free).
     * If the cart is null or empty, 0.0 is returned.
     *
     * @param cart the shopping cart items to calculate the total for
     * @return the total price of the items in the shopping cart
     */
    
    public static double calculateTotalPrice(List<CartItem> cart) {
        if (cart == null || cart.isEmpty()) {
            return 0.0;
        }

        double total = 0.0;

        for (CartItem item : cart) {
            DiscountRule rule = discountRules.get(item.sku());

            int effectiveQuantity = item.quantity();
            double itemPrice = item.price();

            if (rule != null) {
                if (rule.bogo) {
                    effectiveQuantity = (effectiveQuantity / 2) + (effectiveQuantity % 2);
                }
                if (rule.bulkThreshold > 0 && item.quantity() >= rule.bulkThreshold) {
                    itemPrice *= (1 - rule.bulkDiscount);
                }
            }

            total += effectiveQuantity * itemPrice;
        }

        return total;
    }

    /**
     * Represents a discount rule that can be applied to cart items.
     */
    public static class DiscountRule {
        boolean bogo;
        int bulkThreshold;
        double bulkDiscount;

        public DiscountRule(boolean bogo, int bulkThreshold, double bulkDiscount) {
            this.bogo = bogo;
            this.bulkThreshold = bulkThreshold;
            this.bulkDiscount = bulkDiscount;
        }
    }
}
