// PROBLEM 2: Online Learning Platform
// Concept: Method Overriding
// Create an educational content system where different course types display progress
// differently:
// ● Video courses show completion percentage and watch time
// ● Interactive courses show quiz scores and hands-on projects completed
// ● Reading courses show pages read and note-taking progress
// ● Certification courses show exam attempts and certification status
// All courses share basic info (title, instructor, enrollment date) but track and display
// progress uniquely.

// Hint: Common learning foundation, specialized progress tracking per course
// type!




import java.time.LocalDate;

public class OnlineLearningDemo {
    public static void main(String[] args) {
        Course[] courses = new Course[4];
        courses[0] = new VideoCourse("Java 101", "Dr. A", LocalDate.of(2025,1,15), 75, 120);
        courses[1] = new InteractiveCourse("Hands-on Web", "Prof. B", LocalDate.of(2025,3,1), 85, 3);
        courses[2] = new ReadingCourse("Algorithms Book", "Dr. C", LocalDate.of(2025,2,10), 120, 30);
        courses[3] = new CertificationCourse("Cloud Cert", "Ms. D", LocalDate.of(2024,11,5), 2, true);

        for (Course c : courses) {
            c.displayProgress();
            System.out.println();
        }
    }
}

class Course {
    protected String title;
    protected String instructor;
    protected LocalDate enrolled;

    public Course(String title, String instructor, LocalDate enrolled) {
        this.title = title;
        this.instructor = instructor;
        this.enrolled = enrolled;
    }

    public void displayProgress() {
        System.out.println("Course: " + title + " | Instructor: " + instructor + " | Enrolled: " + enrolled);
    }
}

class VideoCourse extends Course {
    private int completionPercent;
    private int watchTimeMins;

    public VideoCourse(String title, String instructor, LocalDate enrolled, int completionPercent, int watchTimeMins) {
        super(title, instructor, enrolled);
        this.completionPercent = completionPercent;
        this.watchTimeMins = watchTimeMins;
    }

    @Override
    public void displayProgress() {
        super.displayProgress();
        System.out.println("Video Progress: " + completionPercent + "% | Watch time: " + watchTimeMins + " mins");
    }
}

class InteractiveCourse extends Course {
    private int quizScore;
    private int projectsCompleted;

    public InteractiveCourse(String title, String instructor, LocalDate enrolled, int quizScore, int projectsCompleted) {
        super(title, instructor, enrolled);
        this.quizScore = quizScore;
        this.projectsCompleted = projectsCompleted;
    }

    @Override
    public void displayProgress() {
        super.displayProgress();
        System.out.println("Interactive Progress: Quiz " + quizScore + "% | Projects: " + projectsCompleted);
    }
}

class ReadingCourse extends Course {
    private int pagesRead;
    private int notesTaken;

    public ReadingCourse(String title, String instructor, LocalDate enrolled, int pagesRead, int notesTaken) {
        super(title, instructor, enrolled);
        this.pagesRead = pagesRead;
        this.notesTaken = notesTaken;
    }

    @Override
    public void displayProgress() {
        super.displayProgress();
        System.out.println("Reading Progress: Pages read " + pagesRead + " | Notes: " + notesTaken);
    }
}

class CertificationCourse extends Course {
    private int examAttempts;
    private boolean certified;

    public CertificationCourse(String title, String instructor, LocalDate enrolled, int examAttempts, boolean certified) {
        super(title, instructor, enrolled);
        this.examAttempts = examAttempts;
        this.certified = certified;
    }

    @Override
    public void displayProgress() {
        super.displayProgress();
        System.out.println("Certification Progress: Attempts " + examAttempts + " | Certified: " + (certified ? "Yes" : "No"));
    }
}
