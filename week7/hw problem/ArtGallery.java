// File: ArtGallery.java
class Art {
    protected String title;
    public Art(String title) { this.title = title; }
}

class Painting extends Art {
    public Painting(String title) { super(title); }
    public void showBrushTechnique() {
        System.out.println(title + " - Oil brush technique displayed.");
    }
}

class Sculpture extends Art {
    public Sculpture(String title) { super(title); }
    public void showMaterial() {
        System.out.println(title + " - Made of marble.");
    }
}

class DigitalArt extends Art {
    public DigitalArt(String title) { super(title); }
    public void showResolution() {
        System.out.println(title + " - 4K interactive resolution.");
    }
}

class Photography extends Art {
    public Photography(String title) { super(title); }
    public void showCameraSettings() {
        System.out.println(title + " - Captured with DSLR at f/1.8.");
    }
}

public class ArtGallery {
    public static void main(String[] args) {
        Art a1 = new Painting("Mona Lisa");
        Art a2 = new Sculpture("David");

        // Downcasting
        ((Painting) a1).showBrushTechnique();
        ((Sculpture) a2).showMaterial();
    }
}
