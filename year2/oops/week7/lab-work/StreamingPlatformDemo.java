// PROBLEM 5: Movie Streaming Platform
// Concept: Downcasting
// Build a streaming service that handles different content types:

// ● Movies have ratings, duration, and subtitle options
// ● TV Series have seasons, episodes, and next episode suggestions
// ● Documentaries have educational tags and related content
// Sometimes you need to access specific features based on what the user is actually
// watching.
// Hint: Go from general to specific - but be careful, not everything is what it seems!




public class StreamingPlatformDemo {
    public static void main(String[] args) {
        Content[] library = new Content[3];
        library[0] = new Movie("Inception", 148, "PG-13", true);
        library[1] = new TVSeries("Strange Shows", 3, 10);
        library[2] = new Documentary("Planet Earth", new String[]{"Nature", "Wildlife"}, "Related: Planet Earth II");

        for (Content c : library) {
            c.play();
            if (c instanceof Movie) {
                Movie m = (Movie) c;
                System.out.println("Subtitle available: " + m.hasSubtitles());
            } else if (c instanceof TVSeries) {
                TVSeries t = (TVSeries) c;
                t.suggestNextEpisode();
            } else if (c instanceof Documentary) {
                Documentary d = (Documentary) c;
                d.showEducationalTags();
            }
            System.out.println();
        }

        // Danger check: downcasting wrong type
        Content maybeMovie = library[1];
        if (maybeMovie instanceof Movie) {
            Movie m = (Movie) maybeMovie; // safe only if instanceof true
        } else {
            System.out.println(maybeMovie.getTitle() + " is not a Movie - cannot access movie-specific features.");
        }
    }
}

class Content {
    protected String title;

    public Content(String title) {
        this.title = title;
    }

    public void play() {
        System.out.println("Now playing: " + title);
    }

    public String getTitle() { return title; }
}

class Movie extends Content {
    private int durationMinutes;
    private String rating;
    private boolean subtitles;

    public Movie(String title, int durationMinutes, String rating, boolean subtitles) {
        super(title);
        this.durationMinutes = durationMinutes;
        this.rating = rating;
        this.subtitles = subtitles;
    }

    @Override
    public void play() {
        super.play();
        System.out.println("Movie | Duration: " + durationMinutes + " mins | Rating: " + rating);
    }

    public boolean hasSubtitles() { return subtitles; }
}

class TVSeries extends Content {
    private int seasons;
    private int episodesPerSeason;

    public TVSeries(String title, int seasons, int episodesPerSeason) {
        super(title);
        this.seasons = seasons;
        this.episodesPerSeason = episodesPerSeason;
    }

    @Override
    public void play() {
        super.play();
        System.out.println("TV Series | Seasons: " + seasons + " | Episodes/season: " + episodesPerSeason);
    }

    public void suggestNextEpisode() {
        System.out.println("Suggested next episode: S1:E1 (placeholder)");
    }
}

class Documentary extends Content {
    private String[] tags;
    private String related;

    public Documentary(String title, String[] tags, String related) {
        super(title);
        this.tags = tags;
        this.related = related;
    }

    @Override
    public void play() {
        super.play();
        System.out.println("Documentary | Tags: " + String.join(",", tags));
    }

    public void showEducationalTags() {
        System.out.println("Educational tags: " + String.join(", ", tags) + "; " + related);
    }
}
