import java.util.concurrent.atomic.AtomicInteger;

/**
 * Represents a fitness session with unique ID and registration logic.
 */
public class Session {
    private static final AtomicInteger counter = new AtomicInteger(0);
    private int sessionID;
    private String sessionName;
    private String fitnessLevel;
    private String dayOfWeek;
    private String startTime;
    private int duration;
    private int availableSpaces;

    public Session(String sessionName, String fitnessLevel, String dayOfWeek, String startTime, int duration, int availableSpaces) {
        this.sessionID = counter.incrementAndGet();
        this.sessionName = sessionName;
        this.fitnessLevel = fitnessLevel;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.duration = duration;
        this.availableSpaces = availableSpaces;
    }

    public void displaySession() {
        System.out.printf("%-10d %-15.15s %-10s %-10s %-10s %-10d %-10d%n",
                sessionID, sessionName, fitnessLevel, dayOfWeek, startTime, duration, availableSpaces);
    }

    public boolean regCustomer() {
        if (availableSpaces > 0) {
            availableSpaces--;
            return true;
        }
        return false;
    }

    public void cancelReg() {
        availableSpaces++;
    }

    public int getSessionID() { return sessionID; }
    public String getSessionName() { return sessionName; }
}
