import java.util.concurrent.*;

class PurchaseCounter{
    private int count =0;

    public  void increment(){
        count++;
    }

    public int getCount() {
        return count;
    }

}

class RaceConditionDemo{
    public  static  void main(String[] args) throws InterruptedException{
        PurchaseCounter counter = new PurchaseCounter();

        Runnable task = () -> {
            for(int i=0 ; i < 1000 ; i++){
                counter.increment();
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        // Expect 2000, but rarely get it
        System.out.println("Final Count: " + counter.getCount());


    }
}