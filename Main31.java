class Main31 {
    public static void main(String[] args) {
        // Define Runnable directly as an anonymous class
        Runnable task = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000); // Simulate delay
                    System.out.println("Task completed using direct Runnable.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        // Create and start the thread
        Thread thread = new Thread(task);
        thread.start();
    }
}