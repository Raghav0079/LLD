import java.util.LinkedList;
import java.util.Queue;

// Shared resource: CoffeeMachine acts as a buffer of size 1
class CoffeeMachine {
    // Flag to indicate buffer status: true → coffee ready, false → cup empty
    private boolean isCoffeeReady = false;

    // Method to be run by the producer thread
    public synchronized void makeCoffee() throws InterruptedException {
        // If coffee is already ready, producer must wait (buffer is full)
        while (isCoffeeReady) {
            // Releases lock and waits until notified by consumer
            wait();
        }

        // Simulate coffee preparation
        System.out.println("Brewing coffee…");
        Thread.sleep(1000); // Simulate time to make coffee

        // Set the buffer to full (coffee is now ready)
        isCoffeeReady = true;
        System.out.println("Coffee ready!");

        // Notify the consumer thread that coffee is available
        notifyAll();
    }

    // Method to be run by the consumer thread
    public synchronized void drinkCoffee() throws InterruptedException {
        // If no coffee is available, consumer must wait (buffer is empty)
        while (!isCoffeeReady) {
            // Releases lock and waits until notified by producer
            wait();
        }

        // Simulate coffee consumption
        System.out.println("Drinking coffee…");
        Thread.sleep(1000); // Simulate time to drink coffee

        // Set the buffer to empty (coffee has been consumed)
        isCoffeeReady = false;
        System.out.println("Cup emptied - brew next!");

        // Notify the producer thread that it can brew again
        notifyAll();
    }
}

// Driver class
class Main37 {
    public static void main(String[] args) {
        // Shared CoffeeMachine object acts as the synchronized monitor
        CoffeeMachine machine = new CoffeeMachine();

        // Producer thread
        Thread producer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    machine.makeCoffee();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        });

        // Consumer thread
        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    machine.drinkCoffee();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        });

        // Start both threads
        producer.start();
        consumer.start();

        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            producer.interrupt();
            consumer.interrupt();
            Thread.currentThread().interrupt();
        }
    }
}