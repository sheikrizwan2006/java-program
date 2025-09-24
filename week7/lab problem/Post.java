// Parent class
class Post {
    String author;
    String content;
    String time;

    // Constructor
    Post(String author, String content, String time) {
        this.author = author;
        this.content = content;
        this.time = time;
    }

    // Method to display post (will be overridden by child classes)
    void display() {
        System.out.println("Author: " + author);
        System.out.println("Content: " + content);
        System.out.println("Time: " + time);
    }
}

// Instagram post class
class InstagramPost extends Post {
    int likes;
    String hashtags;

    InstagramPost(String author, String content, String time, int likes, String hashtags) {
        super(author, content, time);
        this.likes = likes;
        this.hashtags = hashtags;
    }

    @Override
    void display() {
        System.out.println("📸 Instagram Post");
        System.out.println(author + " posted: " + content);
        System.out.println("Hashtags: " + hashtags);
        System.out.println("❤️ Likes: " + likes);
        System.out.println("⏰ Posted at: " + time);
        System.out.println("-----------------------------");
    }
}

// Twitter post class
class TwitterPost extends Post {
    int retweets;

    TwitterPost(String author, String content, String time, int retweets) {
        super(author, content, time);
        this.retweets = retweets;
    }

    @Override
    void display() {
        System.out.println("🐦 Twitter Post");
        System.out.println(author + " tweeted: " + content);
        System.out.println("Characters: " + content.length());
        System.out.println("🔁 Retweets: " + retweets);
        System.out.println("⏰ Posted at: " + time);
        System.out.println("-----------------------------");
    }
}

// LinkedIn post class
class LinkedInPost extends Post {
    int connections;

    LinkedInPost(String author, String content, String time, int connections) {
        super(author, content, time);
        this.connections = connections;
    }

    @Override
    void display() {
        System.out.println("💼 LinkedIn Post");
        System.out.println("👤 " + author + " shared a professional update:");
        System.out.println("\"" + content + "\"");
        System.out.println("Connections Engaged: " + connections);
        System.out.println("⏰ Posted at: " + time);
        System.out.println("-----------------------------");
    }
}

// Main class
public class SocialMediaFeed {
    public static void main(String[] args) {
        Post insta = new InstagramPost("Alice", "Enjoying sunset!", "6:30 PM", 250, "#sunset #nature");
        Post tweet = new TwitterPost("Bob", "Just learned Java Overriding!", "7:15 PM", 50);
        Post linkedin = new LinkedInPost("Charlie", "Excited to start my new job!", "9:00 AM", 200);

        // Polymorphism in action: each post displays differently
        insta.display();
        tweet.display();
        linkedin.display();
    }
}
