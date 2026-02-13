// Interface Playable
interface Playable {
    void play();
    void pause();
}

// MusicPlayer class implementing Playable
class MusicPlayer implements Playable {
    @Override
    public void play() {
        System.out.println("Playing music...");
    }

    @Override
    public void pause() {
        System.out.println("Music paused.");
    }
}

// VideoPlayer class implementing Playable
class VideoPlayer implements Playable {
    @Override
    public void play() {
        System.out.println("Playing video...");
    }

    @Override
    public void pause() {
        System.out.println("Video paused.");
    }
}

// Main class to demonstrate polymorphism
public class PlayableDemo {
    public static void main(String[] args) {
        Playable ref;

        ref = new MusicPlayer();
        ref.play();
        ref.pause();

        System.out.println();

        ref = new VideoPlayer();
        ref.play();
        ref.pause();
    }
}
