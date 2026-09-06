
class Order {
    private String state;

    // Constructor initializes the state to ORDER_PLACED
    public Order() {
        this.state = "ORDER_PLACED";
    }

    // Method to cancel the order 
    // only allows cancellation if in ORDER_PLACED or PREPARING states
    public void cancelOrder() {
        if (state.equals("ORDER_PLACED") || state.equals("PREPARING")) {
            state = "CANCELLED";
            System.out.println("Order has been cancelled.");
        } else {
            System.out.println("Cannot cancel the order now.");
        }
    }

    // Method to move the order to the next state based on its current state
    public void nextState() {
        switch (state) {
            case "ORDER_PLACED":
                state = "PREPARING";
                break;
            case "PREPARING":
                state = "OUT_FOR_DELIVERY";
                break;
            case "OUT_FOR_DELIVERY":
                state = "DELIVERED";
                break;
            default:
                System.out.println("No next state from: " + state);
                return;
        }
        System.out.println("Order moved to: " + state);
    }

    // Getter for the state
    public String getState() {
        return state;
    }
}

class Main26 {
    // Main method to test the order flow
    public static void main(String[] args) {
        Order order = new Order();
        
        // Display initial state
        System.out.println("Initial State: " + order.getState());

        // Moving through states
        order.nextState(); // ORDER_PLACED -> PREPARING
        order.nextState(); // PREPARING -> OUT_FOR_DELIVERY
        order.nextState(); // OUT_FOR_DELIVERY -> DELIVERED

        // Attempting to cancel an order after it is out for delivery
        order.cancelOrder(); // Should not allow cancellation

        // Display final state
        System.out.println("Final State: " + order.getState());
    }
}
