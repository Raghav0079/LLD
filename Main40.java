import java.time.Duration;
import java.util.List;
import java.util.logging.Logger;

class Main40 {

    private static final Logger log = Logger.getLogger(Main40.class.getName());

    // ==========================================
    // 1. RECOMMENDATION SERVICE WITH CACHE FALLBACK
    // ==========================================
    public static class RecommendationService {
        private final ExternalRecommendationService recommendationService;
        private final CacheService cacheService;

        public RecommendationService(ExternalRecommendationService recommendationService, CacheService cacheService) {
            this.recommendationService = recommendationService;
            this.cacheService = cacheService;
        }

        public List<String> getRecommendedItems(String userId) {
            try {
                return recommendationService.fetchLiveRecommendations(userId);
            } catch (Exception ex) {
                log.warning("Live service failed, falling back to cache");
                return cacheService.getCachedRecommendations(userId);
            }
        }
    }

    // ==========================================
    // 2. MENU FALLBACK UI
    // ==========================================
    public static class MenuController {
        private final MenuService menuService;

        public MenuController(MenuService menuService) {
            this.menuService = menuService;
        }

        public Menu getMenu(String restaurantId) {
            try {
                return menuService.fetchMenu(restaurantId);
            } catch (Exception e) {
                return new Menu("Menu currently unavailable. Please try again later.");
            }
        }
    }

    // ==========================================
    // 3. ORDER PLACEMENT & RETRY QUEUING
    // ==========================================
    public static class OrderService {
        private final PaymentServiceMock paymentService;
        private final OrderRetryQueue orderRetryQueue;

        public OrderService(PaymentServiceMock paymentService, OrderRetryQueue orderRetryQueue) {
            this.paymentService = paymentService;
            this.orderRetryQueue = orderRetryQueue;
        }

        public void placeOrder(Order order) {
            try {
                paymentService.charge(order);
            } catch (Exception e) {
                orderRetryQueue.enqueue(order);
                log.warning("Payment failed. Queued for retry.");
            }
        }
    }

    // ==========================================
    // 4. ETA RETRY & EXPONENTIAL BACKOFF
    // ==========================================
    public static class EtaClient {
        private final EtaService etaService;

        public EtaClient(EtaService etaService) {
            this.etaService = etaService;
        }

        public String getETA() {
            int retries = 3;  
            while (retries-- > 0) {
                try {
                    return etaService.getETA();  
                } catch (Exception e) {
                    log.warning("Retrying ETA, attempts left: " + retries);
                }
            }
            return "ETA unavailable";  
        }

        public String getETAWithBackoff() {
            int retries = 3;
            int delay = 100; // Reduced for quick demo execution (originally 1000ms)
            while (retries-- > 0) {
                try {
                    return etaService.getETA();  
                } catch (Exception e) {
                    try {
                        Thread.sleep(delay);  
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                    }
                    delay *= 2;  
                }
            }
            return "ETA unavailable";  
        }
    }

    // ==========================================
    // SUPPORTING MOCK INTERFACES & CLASSES
    // ==========================================
    public interface ExternalRecommendationService {
        List<String> fetchLiveRecommendations(String userId) throws Exception;
    }

    public interface CacheService {
        List<String> getCachedRecommendations(String userId);
    }

    public interface MenuService {
        Menu fetchMenu(String restaurantId) throws Exception;
    }

    public interface OrderRetryQueue {
        void enqueue(Order order);
    }

    public interface EtaService {
        String getETA() throws Exception;
    }

    public interface PaymentServiceMock {
        void charge(Order order) throws Exception;
    }

    public static class Menu {
        private final String message;
        public Menu(String message) { this.message = message; }
        public String getMessage() { return message; }
    }

    public static class Order {
        private final String orderId;
        public Order(String orderId) { this.orderId = orderId; }
        public String getOrderId() { return orderId; }
    }

    // ==========================================
    // MAIN METHOD TO EXECUTE EXAMPLES
    // ==========================================
    public static void main(String[] args) {
        System.out.println("=== Running Resilience Pattern Examples ===\n");

        // 1. Test Recommendation Fallback (Live service throws error, uses cache)
        ExternalRecommendationService failingLiveRecs = userId -> { throw new RuntimeException("Service down"); };
        CacheService workingCache = userId -> List.of("cached-movie-1", "cached-movie-2");
        RecommendationService recService = new RecommendationService(failingLiveRecs, workingCache);
        
        System.out.println("Recommendations result: " + recService.getRecommendedItems("user-123"));

        // 2. Test ETA Retry Mechanism
        EtaService failingEta = () -> { throw new RuntimeException("Timeout"); };
        EtaClient etaClient = new EtaClient(failingEta);
        
        System.out.println("ETA Retry result: " + etaClient.getETA());
        
        System.out.println("\n=== Execution Completed Successfully ===");
    }
}