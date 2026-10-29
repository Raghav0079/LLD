import java.util.concurrent.locks.ReentrantLock;

class TicketBooking{
    private int availableSeats = 1;
    private final ReentrantLock lock = new ReentrantLock();

    public void bookTicket(String user){
        System.out.println(user + " is trying to book...");

        lock.lock();

        try {
            System.out.println(user + " acquired the lock.");

            if (availableSeats > 0) {
                availableSeats--;
                System.out.println(user + " successfully booked the ticket.");
            } else {
                System.out.println(user + " could not book. No seats left.");
            }
        } finally {
            System.out.println(user + " is releasing the lock.");
            lock.unlock();
        }
    }
}

public class Main32{
    public static void main(String[] args) throws InterruptedException {
        TicketBooking bookingSystem = new TicketBooking();

        Thread user1 =
                new Thread(() -> bookingSystem.bookTicket("User 1"));

        Thread user2 =
                new Thread(() -> bookingSystem.bookTicket("User 2"));

        user1.start();
        user2.start();

        user1.join();
        user2.join();
    }
}


