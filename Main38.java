// ── Contract: defines what the client needs, not how it is done
interface NotificationService {
    void send(String message);
}

// ── Concrete implementation of the contract
class EmailNotificationService implements NotificationService {
    @Override
    public void send(String message) {
        System.out.println("Email sent: " + message);
    }
}

// ── Client that depends on the abstraction, not the implementation
class UserService {
    // Dependency held as an interface, promoting loose coupling
    private final NotificationService notificationService;

    // Constructor Injection: forces the caller to supply the dependency up-front
    public UserService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    // Business logic uses the injected service
    public void register(String user) {
        System.out.println("User registered: " + user);
        notificationService.send("Welcome " + user);
    }
}

// ── Composition Root: the only place where “new” keywords appear
class Main38 {
    public static void main(String[] args) {
        // Create the concrete dependency
        NotificationService service = new EmailNotificationService();

        // Inject it into the client
        UserService userService = new UserService(service);

        // Execute business operation
        userService.register("raj");
    }
}