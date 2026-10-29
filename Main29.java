import java.util.*;

// Ride Matching Service Class
class RideMatchingService {

    // Method handling ride request
    public void requestRide(String riderId) {

        // Creating a new thread for the ride
        Thread matchThread = new Thread(() -> {
            System.out.println("Matching rider " + riderId + " to a driver...");
            // Simulate some processing
            try {
                Thread.sleep(1000); // Simulate a 1-second matching process
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("Ride matched for rider " + riderId);
        });
        matchThread.start();
    }
}

class Main29 {
    public static void main(String[] args) {
        RideMatchingService rideService1 = new RideMatchingService();
        RideMatchingService rideService2 = new RideMatchingService();

        rideService1.requestRide("Raj");
        System.out.println("task1 running...");

        rideService2.requestRide("John Doe");
        System.out.println("task2 running...");
    }
}