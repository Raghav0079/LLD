import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

class CooperativeTimedLock {
    private final ReentrantLock lock = new ReentrantLock();

    private final ScheduledExecutorService scheduler =
            Executors.newSingleThreadScheduledExecutor();

    public boolean tryLock() {
        return lock.tryLock();
    }

    public ScheduledFuture<?> scheduleCancellation(
            Thread owner,
            long timeoutMillis
    ) {
        return scheduler.schedule(() -> {
            System.out.println(
                    "Timeout reached - interrupting the owner."
            );

            // This is only a cancellation request.
            // The scheduler does not unlock the ReentrantLock.
            owner.interrupt();
        }, timeoutMillis, TimeUnit.MILLISECONDS);
    }

    public void unlockByOwner() {
        if (!lock.isHeldByCurrentThread()) {
            throw new IllegalMonitorStateException(
                    "Only the owning thread may unlock."
            );
        }

        lock.unlock();

        System.out.println(
                "Lock released by " +
                Thread.currentThread().getName()
        );
    }

    public void shutdown() {
        scheduler.shutdownNow();
    }
}

public class Main33 {
    public static void main(String[] args)
            throws InterruptedException {

        CooperativeTimedLock timedLock =
                new CooperativeTimedLock();

        Thread idleUser = new Thread(() -> {
            if (!timedLock.tryLock()) {
                System.out.println(
                        "IdleUser could not acquire the lock."
                );
                return;
            }

            ScheduledFuture<?> timeoutTask =
                    timedLock.scheduleCancellation(
                            Thread.currentThread(),
                            3000
                    );

            try {
                System.out.println(
                        "IdleUser acquired the lock and is now idle."
                );

                // Simulates interruptible work lasting 5 seconds.
                Thread.sleep(5000);

                System.out.println(
                        "IdleUser completed before the timeout."
                );
            } catch (InterruptedException e) {
                System.out.println(
                        "IdleUser observed the timeout request."
                );

                // Restore the interrupt status after handling it.
                Thread.currentThread().interrupt();
            } finally {
                // Prevent a pending timeout from affecting later work.
                timeoutTask.cancel(false);

                // Executed by IdleUser, the actual lock owner.
                timedLock.unlockByOwner();
            }
        }, "IdleUser");

        Thread activeUser = new Thread(() -> {
            try {
                Thread.sleep(1000);

                while (!timedLock.tryLock()) {
                    System.out.println(
                            "ActiveUser still waiting..."
                    );
                    Thread.sleep(1000);
                }

                try {
                    System.out.println(
                            "ActiveUser acquired the lock and booked!"
                    );
                } finally {
                    // Executed by ActiveUser, which owns the lock here.
                    timedLock.unlockByOwner();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "ActiveUser");

        idleUser.start();
        activeUser.start();

        idleUser.join();
        activeUser.join();

        timedLock.shutdown();
    }
}