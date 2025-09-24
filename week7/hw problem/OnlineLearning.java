// File: OnlineLearning.java
class Course {
    protected String title, instructor, date;

    public Course(String title, String instructor, String date) {
        this.title = title;
        this.instructor = instructor;
        this.date = date;
    }

    public void showProgress() {
        System.out.println("Course: " + title + " | Instructor: " + instructor);
        System.out.println("Progress: General course progress tracking.");
    }
}

class VideoCourse extends Course {
    private int completion;
    private int watchHours;

    public VideoCourse(String title, String instructor, String date, int completion, int watchHours) {
        super(title, instructor, date);
        this.completion = completion;
        this.watchHours = watchHours;
    }

    @Override
    public void showProgress() {
        System.out.println("Video Course: " + title + " by " + instructor);
        System.out.println("Completion: " + completion + "%, Watch time: " + watchHours + " hrs");
    }
}

class InteractiveCourse extends Course {
    private int quizScore, projectsDone;

    public InteractiveCourse(String title, String instructor, String date, int quizScore, int projectsDone) {
        super(title, instructor, date);
        this.quizScore = quizScore;
        this.projectsDone = projectsDone;
    }

    @Override
    public void showProgress() {
        System.out.println("Interactive Course: " + title);
        System.out.println("Quiz Score: " + quizScore + ", Projects Completed: " + projectsDone);
    }
}

class ReadingCourse extends Course {
    private int pagesRead;

    public ReadingCourse(String title, String instructor, String date, int pagesRead) {
        super(title, instructor, date);
        this.pagesRead = pagesRead;
    }

    @Override
    public void showProgress() {
        System.out.println("Reading Course: " + title);
        System.out.println("Pages Read: " + pagesRead);
    }
}

class CertificationCourse extends Course {
    private int examAttempts;
    private boolean certified;

    public CertificationCourse(String title, String instructor, String date, int examAttempts, boolean certified) {
        super(title, instructor, date);
        this.examAttempts = examAttempts;
        this.certified = certified;
    }

    @Override
    public void showProgress() {
        System.out.println("Certification Course: " + title);
        System.out.println("Exam Attempts: " + examAttempts + ", Certified: " + certified);
    }
}

public class OnlineLearning {
    public static void main(String[] args) {
        Course[] courses = {
            new VideoCourse("Java Basics", "Alice", "2025-09-01", 70, 15),
            new InteractiveCourse("Python Projects", "Bob", "2025-08-20", 85, 3),
            new ReadingCourse("AI Research", "Clara", "2025-09-05", 120),
            new CertificationCourse("Cloud Cert", "David", "2025-09-10", 2, true)
        };

        for (Course c : courses) {
            c.showProgress();
            System.out.println("----------------------");
        }
    }
}
