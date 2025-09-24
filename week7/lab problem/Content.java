// Parent class
class Content {
    String title;

    Content(String title) {
        this.title = title;
    }

    public void play() {
        System.out.println("Playing: " + title);
    }
}

// Movies
class Movie extends Content {
    double rating;
    int duration; // in minutes
    boolean subtitles;

    Movie(String title, double rating, int duration, boolean subtitles) {
        super(title);
        this.rating = rating;
        this.duration = duration;
        this.subtitles = subtitles;
    }

    public void showMovieDetails() {
        System.out.println("🎬 Movie: " + title);
        System.out.println("Rating: " + rating + "⭐");
        System.out.println("Duration: " + duration + " minutes");
        System.out.println("Subtitles: " + (subtitles ? "Available" : "Not Available"));
    }
}

// TV Series
class TVSeries extends Content {
    int seasons;
    int episodes;

    TVSeries(String title, int seasons, int episodes) {
        super(title);
        this.seasons = seasons;
        this.episodes = episodes;
    }

    public void suggestNextEpisode() {
        System.out.println("📺 TV Series: " + title);
        System.out.println("Seasons: " + seasons + ", Episodes: " + episodes);
        System.out.println("Suggested: Watch the next episode!");
    }
}

// Documentaries
class Documentary extends Content {
    String educationalTags;
    String relatedContent;

    Documentary(String title, String educationalTags, String relatedContent) {
        super(title);
        this.educationalTags = educationalTags;
        this.relatedContent = relatedContent;
    }

    public void showEducationalInfo() {
        System.out.println("🎥 Documentary: " + title);
        System.out.println("Tags: " + educationalTags);
        System.out.println("Related Content: " + relatedContent);
    }
}

// Main class
public class StreamingPlatform {
    public static void main(String[] args) {
        // Upcasting: store all types as Content
        Content[] watchlist = {
            new Movie("Inception", 8.8, 148, true),
            new TVSeries("Stranger Things", 4, 34),
            new Documentary("Our Planet", "Nature, Environment", "Blue Planet")
        };

        // Loop through content and use downcasting
        for (Content c : watchlist) {
            c.play();  // general feature

            // Downcasting for specific features
            if (c instanceof Movie) {
                Movie m = (Movie) c;
                m.showMovieDetails();
            } else if (c instanceof TVSeries) {
                TVSeries t = (TVSeries) c;
                t.suggestNextEpisode();
            } else if (c instanceof Documentary) {
                Documentary d = (Documentary) c;
                d.showEducationalInfo();
            }
            System.out.println("-------------------------");
        }
    }
}

