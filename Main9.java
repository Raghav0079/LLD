import java.util.ArrayList;
import java.util.List;

class BurgerMeal {
    private String bun;
    private String patty;
    private String sides;
    private List<String> toppings;


    public BurgerMeal(String bun, String patty, String sides, List<String> toppings) {
        this.bun = bun;
        this.patty = patty;
        this.sides = sides;
        this.toppings = toppings;
    }
}

public class Main9 {
    public static void main(String[] args) {
        
        BurgerMeal burgerMeal = new BurgerMeal("Brioche", "Beef", "Fries", new ArrayList<>());
    }
}