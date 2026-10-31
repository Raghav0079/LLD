class RecommendationService{
    public List<String> getRecommendedItems(String userId){
        try{
            return recommendationService.fetchLiveRecommendations(userId);
        } catch ( Exception ex){
            log.warn("Live service failed, falling back to cache");
            return cacheService.getCachedRecommendations(userId);
        }
    }
}


public List<String> fetchLiveRecommendations(String userId){
    return List.of("movie-1", "movie-2");
}

// Show fallback UI
public Menu getMenu(String restaurantId) {
    try {
        // Attempt to fetch the live menu
        return menuService.fetchMenu(restaurantId);
    } catch (Exception e) {
        // If the live menu is unavailable, show a fallback message in the UI
        return new Menu("Menu currently unavailable. Please try again later.");
    }
}


// Queue request's
public void placeOrder(Order order) {
    try {
        // Attempt to charge the payment
        paymentService.charge(order);
    } catch (Exception e) {
        // If payment fails, queue the order for retry
        orderRetryQueue.enqueue(order);
        log.warn("Payment failed. Queued for retry.");
    }
}


// Naive retry example
public String getETA() {
    int retries = 3;  // Maximum retry attempts
    while (retries-- > 0) {
        try {
            return etaService.getETA();  // Attempt to fetch ETA from service
        } catch (Exception e) {
            log.warn("Retrying ETA, attempts left: " + retries);
        }
    }
    return "ETA unavailable";  // Return message if all retries fail
}


// Backoff strategy
public String getETAWithBackoff() throws InterruptedException {
    int retries = 3;
    int delay = 1000; // Initial delay is 1 second
    while (retries-- > 0) {
        try {
            return etaService.getETA();  // Attempt to fetch ETA from service
        } catch (Exception e) {
            Thread.sleep(delay);  // Wait before retrying
            delay *= 2;  // Exponential backoff: double the delay each time
        }
    }
    return "ETA unavailable";  // Return message if all retries fail
}


