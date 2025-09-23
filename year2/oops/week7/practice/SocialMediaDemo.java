public class SocialMediaDemo {
    public static void main(String[] args) {
        SocialMediaPost[] feed = new SocialMediaPost[3];
        feed[0] = new InstagramPost("Sunset vibes!", "john_doe", 245);
        feed[1] = new TwitterPost("Java is awesome!", "code_ninja", 89);
        feed[2] = new SocialMediaPost("Hello world!", "beginner");

        for (SocialMediaPost post : feed) {
            post.share();
        }
    }
}

// Base class
class SocialMediaPost {
    protected String content;
    protected String author;

    public SocialMediaPost(String content, String author) {
        this.content = content;
        this.author = author;
    }

    public void share() {
        System.out.println("Sharing: " + content + " by " + author);
    }

    // JavaBean-style accessors
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    @Override
    public String toString() {
        return "Post{author='" + author + "', content='" + content + "'}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SocialMediaPost)) return false;
        SocialMediaPost p = (SocialMediaPost) o;
        return java.util.Objects.equals(content, p.content)
            && java.util.Objects.equals(author, p.author);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(content, author);
    }
}

// InstagramPost overrides share()
class InstagramPost extends SocialMediaPost {
    private int likes;

    public InstagramPost(String content, String author, int likes) {
        super(content, author);
        this.likes = likes;
    }

    @Override
    public void share() {
        System.out.println("Instagram: " + content + " by @" + author + " - " + likes + " likes");
    }

    public int getLikes() { return likes; }
    public void setLikes(int likes) { this.likes = likes; }
}

// TwitterPost overrides share()
class TwitterPost extends SocialMediaPost {
    private int retweets;

    public TwitterPost(String content, String author, int retweets) {
        super(content, author);
        this.retweets = retweets;
    }

    @Override
    public void share() {
        System.out.println("Tweet: " + content + " by @" + author + " - " + retweets + " retweets");
    }

    public int getRetweets() { return retweets; }
    public void setRetweets(int retweets) { this.retweets = retweets; }
}