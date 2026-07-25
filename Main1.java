interface TaxCalculator {
    double amountAfterTax(double amount);
}

class IndianTax implements TaxCalculator {
    public double amountAfterTax(double amount) {
        return (amount + 0.18 * amount);
    }
}

class USTax implements TaxCalculator {
    public double amountAfterTax(double amount) {
        return (amount + 0.18 * amount);
    }
}

class InvoiceService {
    public void calculate() {
        TaxCalculator taxCalculator = new IndianTax();
        taxCalculator.amountAfterTax(100);
    }
}

public class Main1 {
    public static void main(String[] args) {
        
    }
}