import java.util.Arrays;
import java.util.List;

// Class representing a Physical Product
class PhysicalProduct {
    // Method to print invoice for physical product
    void printInvoice() {
        System.out.println("Printing invoice for Physical Product...");
    }

    // Method to calculate shipping cost for physical product
    double calculateShippingCost() {
        System.out.println("Calculating shipping cost for Physical Product...");
        return 10.0; // Example shipping cost
    }
}

// Class representing a Digital Product
class DigitalProduct {
    // Method to print invoice for digital product
    void printInvoice() {
        System.out.println("Printing invoice for Digital Product...");
    }

    // No shipping cost for digital product
}

// Class representing a Gift Card Product
class GiftCard {
    // Method to print invoice for gift card
    void printInvoice() {
        System.out.println("Printing invoice for Gift Card...");
    }

    // Method to calculate discount for gift card
    double calculateDiscount() {
        System.out.println("Calculating discount for Gift Card...");
        return 5.0; // Example discount
    }
}

class Main28 {
    public static void main(String[] args) {
        // Create instances of different products
        List<Object> cart = Arrays.asList(new PhysicalProduct(), new DigitalProduct(), new GiftCard());

        // Loop through cart and perform actions based on product type
        for (Object item : cart) {
            if (item instanceof PhysicalProduct) {
                PhysicalProduct physicalProduct = (PhysicalProduct) item;
                physicalProduct.printInvoice();
                double shippingCost = physicalProduct.calculateShippingCost();
                System.out.println("Shipping cost: " + shippingCost + "\n");
            } 
            else if (item instanceof DigitalProduct) {
                DigitalProduct digitalProduct = (DigitalProduct) item;
                digitalProduct.printInvoice();
                System.out.println("No shipping cost for Digital Product." + "\n");
            } 
            else if (item instanceof GiftCard) {
                GiftCard giftCard = (GiftCard) item;
                giftCard.printInvoice();
                double discount = giftCard.calculateDiscount();
                System.out.println("Discount applied: " + discount + "\n");
            }
        }
    }
}
