// Logistics Interface
interface Logistics {
    void send();
}

// Class implementing the Logistics Interface
class Road implements Logistics {
    @Override
    public void send() {
        System.out.println("Sending by road logic");
    }
}

// Class implementing the Logistics Interface
class Air implements Logistics {
    @Override
    public void send() {
        System.out.println("Sending by air logic");
    }
}

// Factory Class
class LogisticsFactory {
    public static Logistics getLogistics(String mode) {
        // Use equalsIgnoreCase to safely handle "Road", "road", "Air", etc.
        if ("road".equalsIgnoreCase(mode)) {
            return new Road();
        }
        // Defaulting to Air if it's not road
        return new Air();
    }
}

// Class implementing Logistics Service
class LogisticsService {
    // Added 'void' return type
    public void send(String mode) { 
        Logistics logistics = LogisticsFactory.getLogistics(mode); // Added semicolon
        logistics.send(); // Actually calling the method to trigger the print statement
    }
}

// Driver code
class Main8 {
    public static void main(String[] args) {
        LogisticsService service = new LogisticsService();
        service.send("Air");  // Output: Sending by air logic
        service.send("Road"); // Output: Sending by road logic
    }
}