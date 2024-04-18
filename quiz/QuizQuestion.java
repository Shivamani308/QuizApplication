import java.util.List;

public class QuizQuestion {
    private String question;
    private List<String> options;
    private int correctOptionIndex;
    private int timeLimit; // Time limit for the question (in seconds)

    public QuizQuestion(String question, List<String> options, int correctOptionIndex, int timeLimit) {
        this.question = question;
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
        this.timeLimit = timeLimit;
    }

    // Getters for question, options, correctOptionIndex, and timeLimit
    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectOptionIndex() {
        return correctOptionIndex;
    }

    public int getTimeLimit() {
        return timeLimit;
    }
}
