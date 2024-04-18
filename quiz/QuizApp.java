import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class QuizApp extends JFrame {
    private List<QuizQuestion> questions;
    private int currentQuestionIndex = 0;
    private int score = 0;

    private JLabel lblQuestion;
    private JPanel optionsPanel;
    private JLabel lblTimer; // JLabel to display the timer

    // Timer variables
    private Timer timer;
    private int timeLeft;

    public QuizApp() {
        setTitle("Quiz App");
        setSize(400, 350); // Increased height to accommodate the timer
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        lblQuestion = new JLabel();
        add(lblQuestion, BorderLayout.NORTH);

        optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
        add(optionsPanel, BorderLayout.CENTER);

        lblTimer = new JLabel("Time Left: ");
        add(lblTimer, BorderLayout.SOUTH);

        questions = Arrays.asList(
                new QuizQuestion("What is the capital of France?", Arrays.asList("Paris", "London", "Berlin", "Madrid"), 0, 10),
                new QuizQuestion("Which planet is known as the Red Planet?", Arrays.asList("Venus", "Mars", "Jupiter", "Saturn"), 1, 15)
                // Add more questions here
        );

        // Start the timer with the time limit of the first question
        startTimer(questions.get(currentQuestionIndex).getTimeLimit());

        // Display the first question
        displayQuestion(currentQuestionIndex);

        setVisible(true);
    }

    private void displayQuestion(int questionIndex) {
        if (questionIndex < questions.size()) {
            QuizQuestion currentQuestion = questions.get(questionIndex);
            lblQuestion.setText(currentQuestion.getQuestion());

            optionsPanel.removeAll();
            ButtonGroup group = new ButtonGroup();
            for (int i = 0; i < currentQuestion.getOptions().size(); i++) {
                JRadioButton radioButton = new JRadioButton(currentQuestion.getOptions().get(i));
                final int optionIndex = i;
                radioButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (optionIndex == currentQuestion.getCorrectOptionIndex()) {
                            score++;
                        }
                        nextQuestion();
                    }
                });
                group.add(radioButton);
                optionsPanel.add(radioButton);
            }
            
            // Start the timer with the time limit of the current question
            startTimer(currentQuestion.getTimeLimit());
        } else {
            endQuiz();
        }
    }

    private void nextQuestion() {
        currentQuestionIndex++;
        displayQuestion(currentQuestionIndex);
    }

    private void endQuiz() {
        // Stop the timer
        timer.stop();

        // Show quiz results
        JOptionPane.showMessageDialog(this, "Quiz ended. Your score: " + score + "/" + questions.size());
        System.exit(0);
    }

    // Timer method
    private void startTimer(int limit) {
        timeLeft = limit; // Set the time limit for the question
    timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (timeLeft > 0) {
                lblTimer.setText("Time Left: " + timeLeft + " seconds"); // Update the timer label
                timeLeft--; // Decrement the time left
            } else {
                endQuiz(); // End the quiz when time runs out
            }
        }
    });
    timer.start();// Start the timer
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new QuizApp();
            }
        });
    }
}

