class Notification{
    public void sendNotification(){
        System.out.println("email send");
    }
}



public class Main2 {
    public static void main(String[] args) {
        Notification notification = new Notification();
        notification.sendNotification();
        
    }
    
}
