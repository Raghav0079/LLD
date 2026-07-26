class Notification{
    public void sendNotification(){
        System.out.println("email send");
    }
}

class TextNotification extends Notification{

    @Override
    public void sendNotification(){
        System.out.println("text");
    }
}


class WpNotification extends Notification{

    @Override
    public void sendNotification(){
        System.out.println("Wp");
    }

}


public class Main2 {
    public static void main(String[] args) {
        Notification notification = new Notification();
        notification.sendNotification();
        
    }
    
}
