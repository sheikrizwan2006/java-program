class Color {
    protected String name;

    public Color(String name) {
        this.name = name;
        System.out.println("Color constructor called: " + name);
    }

    public void showName() {
        System.out.println("Color name: " + name);
    }
}

class PrimaryColor extends Color {
    protected int intensity;

    public PrimaryColor(String name, int intensity) {
        super(name);
        this.intensity = intensity;
        System.out.println("PrimaryColor constructor called: intensity = " + intensity);
    }

    public void showIntensity() {
        System.out.println("Intensity: " + intensity);
    }
}

class RedColor extends PrimaryColor {
    private String shade;

    public RedColor(String name, int intensity, String shade) {
        super(name, intensity);
        this.shade = shade;
        System.out.println("RedColor constructor called: shade = " + shade);
    }

    public void showShade() {
        System.out.println("Shade: " + shade);
    }
}

public class LabProblem4 {
    public static void main(String[] args) {
        RedColor red = new RedColor("Red", 75, "Crimson");

        red.showName();
        red.showIntensity();
        red.showShade();
    }
}
