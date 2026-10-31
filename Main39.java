class ProductServiceFailFirst {
    public Product getProduct(String productId) {
        if (productId == null) throw new IllegalArgumentException("Product ID cannot be null");
        // fail-fast for invalid input
        return productRepo.find(productId);
    }
}

// Search Product in any of the websites..
class ProductServiceFailSafe {
    public Product getProduct(String productId) {
        try {
            return productRepo.find(productId);
        } catch (Exception e) {
            // fail-safe: return default
            return new Product("default", "Fallback Product");
        }
    }
}


