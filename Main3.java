interface Uber {
    void bookRide();
    void payRide();
}

interface DriverInterface {
    void acceptRide();
    void drive();
    void endRide();
}

class Rider implements Uber {
    public void bookRide() {
    }
    public void payRide() {
    }
}

class Drive implements DriverInterface {
    public void acceptRide() {
    }
    public void drive() {
    }
    public void endRide() {
    }
}

public class Main3 {
    public static void main(String[] args) {
    }
}