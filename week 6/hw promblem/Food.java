abstract class Food {
    // Template method - final to prevent override
    public final void prepare() {
        wash();
        cook();
        serve();
    }

    protected abstract void wash();
    protected abstract void cook();
    protected abstract void serve();
}

class Pizza extends Food {
    @Override
    protected void wash() {
        System.out.println("Washing vegetables for pizza");
    }

    @Override
    protected void cook() {
        System.out.println("Baking the pizza in oven");
    }

    @Override
    protected void serve() {
        System.out.println("Serving hot pizza");
    }
}

class Soup extends Food {
    @Override
    protected void wash() {
        System.out.println("Washing ingredients for soup");
    }

    @Override
    protected void cook() {
        System.out.println("Boiling soup on stove");
    }

    @Override
    protected void serve() {
        System.out.println("Serving warm soup");
    }
}

public class HwProblem4 {
    public static void main(String[] args) {
        Food pizza = new Pizza();
        Food soup = new Soup();

        System.out.println("Preparing Pizza:");
        pizza.prepare();

        System.out.println("\nPreparing Soup:");
        soup.prepare();
    }
}
v