class Main31 {
    public static void main(String[] args) {
        // Define Runnable using a lambda expression
        Runnable task = () -> {
            try {
                Thread.sleep(2000); // Simulate delay
                System.out.println("Task completed using Lambda expression.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        // Create and start the thread
        Thread thread = new Thread(task);
        thread.start();
    }
}