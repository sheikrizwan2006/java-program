class Bird {
    public void fly() {
        System.out.println("Bird is flying");
    }
}

class Penguin extends Bird {
    @Override
    public void fly() {
        System.out.println("Penguin can't fly, but it can swim");
    }
}

class Eagle extends Bird {
    @Override
    public void fly() {
        System.out.println("Eagle is soaring high");
    }
}

public class LabProblem3 {
    public static void main(String[] args) {
        Bird[] birds = { new Bird(), new Penguin(), new Eagle() };

        for (Bird bird : birds) {
            bird.fly();
        }
    }
}
