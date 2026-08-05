class PlainPizza {}
class CheesePizza extends PlainPizza {}
class OlivePizza extends PlainPizza {}
class StuffedPizza extends PlainPizza {}
class CheeseStuffedPizza extends CheesePizza {}
class CheeseOlivePizza extends CheesePizza {}
class CheeseOliveStuffedPizza extends CheeseOlivePizza {}

public class Main13 {
    public static void main(String[] args) {
        // Base pizza
        PlainPizza plainPizza = new PlainPizza();

        // Pizzas with individual toppings
        CheesePizza cheesePizza = new CheesePizza();
        OlivePizza olivePizza = new OlivePizza();
        StuffedPizza stuffedPizza = new StuffedPizza();

        // Combinations of toppings require separate classes
        CheeseStuffedPizza cheeseStuffedPizza = new CheeseStuffedPizza();
        CheeseOlivePizza cheeseOlivePizza = new CheeseOlivePizza();

        // Further combinations increase complexity exponentially
        CheeseOliveStuffedPizza cheeseOliveStuffedPizza = new CheeseOliveStuffedPizza();

    }
}
