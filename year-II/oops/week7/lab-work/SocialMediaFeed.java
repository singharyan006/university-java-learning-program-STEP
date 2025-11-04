// PROBLEM 2: Social Media Feed
// Concept: Method Overriding
// Build a social media post system where different platforms display posts differently:
// ● Instagram posts show with hashtags and likes
// ● Twitter posts show with character count and retweets
// ● LinkedIn posts show with professional formatting and connections
// All posts share common info (author, content, time) but display uniquely for each
// platform.
// Hint: Parent class defines the structure, child classes customize the display!


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SocialMediaFeed {
    public static void main(String[] args) {
        SocialMediaPost[] feed = new SocialMediaPost[4];
        feed[0] = new InstagramPost("Loving the sunset #sunset #vibes", "john_doe", 245);
        feed[1] = new TwitterPost("Java 17 features are great!", "code_ninja", 89);
        feed[2] = new LinkedInPost("Excited to announce my promotion to Senior Engineer.", "pro_user", 350);
        feed[3] = new SocialMediaPost("Hello from a generic post", "anon_user");

        for (SocialMediaPost post : feed) {
            post.display();
            System.out.println();
        }
    }
}

class SocialMediaPost {
    protected String content;
    protected String author;
    protected String time;
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public SocialMediaPost(String content, String author) {
        this.content = content;
        this.author = author;
        this.time = LocalDateTime.now().format(FMT);
    }

    public void display() {
        System.out.println("[Generic Post] " + time);
        System.out.println("Author: " + author);
        System.out.println("Content: " + content);
    }
}

class InstagramPost extends SocialMediaPost {
    private int likes;

    public InstagramPost(String content, String author, int likes) {
        super(content, author);
        this.likes = likes;
    }

    @Override
    public void display() {
        System.out.println("[Instagram] " + time);
        System.out.println("@" + author + " • " + likes + " likes");
        System.out.println(content);
        System.out.println("Hashtags: " + extractHashtags(content));
    }

    private String extractHashtags(String text) {
        StringBuilder sb = new StringBuilder();
        for (String token : text.split("\\s+")) {
            if (token.startsWith("#")) {
                if (sb.length() > 0) sb.append(", ");
                sb.append(token);
            }
        }
        return sb.length() > 0 ? sb.toString() : "(none)";
    }
}

class TwitterPost extends SocialMediaPost {
    private int retweets;

    public TwitterPost(String content, String author, int retweets) {
        super(content, author);
        this.retweets = retweets;
    }

    @Override
    public void display() {
        System.out.println("[Twitter] " + time);
        System.out.println("@" + author + " • " + content.length() + " chars" + " • " + retweets + " retweets");
        System.out.println(truncate(content));
    }

    private String truncate(String text) {
        int limit = 280;
        return text.length() <= limit ? text : text.substring(0, limit - 3) + "...";
    }
}

class LinkedInPost extends SocialMediaPost {
    private int connections;

    public LinkedInPost(String content, String author, int connections) {
        super(content, author);
        this.connections = connections;
    }

    @Override
    public void display() {
        System.out.println("[LinkedIn] " + time);
        System.out.println(author + " — " + connections + " connections");
        System.out.println(formatProfessional(content));
    }

    private String formatProfessional(String text) {
        return "***\n" + text + "\n***";
    }
}
