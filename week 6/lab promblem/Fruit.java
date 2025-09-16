class Fruit {
    protected String color;
    protected String taste;

    public Fruit(String color, String taste) {
        this.color = color;
        this.taste = taste;
    }
}

class Apple extends Fruit {
    protected String variety;

    public Apple(String color, String taste, String variety) {
        super(color, taste);
        this.variety = variety;
    }

    public void displayInfo() {
        System.out.println("Apple variety: " + variety);
        System.out.println("Color: " + color);
        System.out.println("Taste: " + taste);
    }
}

public class LabProblem1 {
    public static void main(String[] args) {
        Apple myApple = new Apple("Red", "Sweet", "Fuji");
        myApple.displayInfo();
    }
}
