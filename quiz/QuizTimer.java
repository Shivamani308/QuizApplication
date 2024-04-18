import java.util.Timer;
import java.util.TimerTask;

public class QuizTimer {
    private Timer timer;
    private int timeRemaining; // in seconds

    public QuizTimer(int seconds) {
        timeRemaining = seconds;
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                if (timeRemaining > 0) {
                    timeRemaining--;
                } else {
                    timer.cancel();
                    System.out.println("Time's up!");
                }
            }
        }, 0, 1000);
    }

    public int getTimeRemaining() {
        return timeRemaining;
    }
}
