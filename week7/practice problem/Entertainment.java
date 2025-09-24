// File: EntertainmentHub.java
public class Entertainment {
    protected String title;

    public Entertainment(String title) {
        this.title = title;
    }

    public void start() {
        System.out.println("Starting " + title);
    }

    public void stop() {
        System.out.println("Stopping " + title);
    }
}

class Movie extends Entertainment {
    private String genre;

    public Movie(String title, String genre) {
        super(title);
        this.genre = genre;
    }

    public void showSubtitles() {
        System.out.println("Showing subtitles for " + title + " (" + genre + ")");
    }

    public void adjustQuality() {
        System.out.println("Adjusting video quality for " + title);
    }
}

class Game extends Entertainment {
    private String platform;

    public Game(String title, String platform) {
        super(title);
        this.platform = platform;
    }

    public void saveProgress() {
        System.out.println("Saving " + title + " progress on " + platform);
    }

    public void showLeaderboard() {
        System.out.println(title + " leaderboard on " + platform);
    }
}

public class EntertainmentHub {
    public static void main(String[] args) {
        // Upcasting Movie to Entertainment
        Entertainment e1 = new Movie("Avengers", "Action");
        e1.start();

        // ✅ Safe Downcasting
        Movie m = (Movie) e1;
        m.showSubtitles();
        m.adjustQuality();

        // Upcasting Game to Entertainment
        Entertainment e2 = new Game("FIFA 24", "PlayStation");
        e2.start();

        // ✅ Safe Downcasting
        Game g = (Game) e2;
        g.saveProgress();
        g.showLeaderboard();

        // ❌ Wrong Downcast (will throw ClassCastException at runtime)
        try {
            Movie wrongCast = (Movie) e2; // e2 is actually a Game, not Movie
            wrongCast.showSubtitles();
        } catch (ClassCastException ex) {
            System.out.println("⚠️ Wrong downcast! Cannot cast Game to Movie.");
        }
    }
}
