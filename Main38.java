import java.util.*;

class OrderService2 {
    private InventoryService inventory;
    private PaymentService payment;
    private NotificationService notification;

    // Constructor Injection - Dependencies are injected through the constructor
    public OrderService2(InventoryService inventory, 
                         PaymentService payment,
                         NotificationService notification) {
        this.inventory = inventory;
        this.payment = payment;
        this.notification = notification;
    }

    public void checkout(Order order) {
        inventory.blockItems(order);
        payment.process(order);
        notification.sendConfirmation(order);
    }
}

// Client-side code
class Main38 {
    public static void main(String[] args) {
        // Injecting dependencies manually (Constructor Injection)
        OrderService2 orderService2 = new OrderService2(
            new InventoryService(), 
            new RazorpayPayment(), 
            new NotificationService()
        );
        
        // Now, we can use the orderService2 to perform operations
        orderService2.checkout(order);
    }
}  
