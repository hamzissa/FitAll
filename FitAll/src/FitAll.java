import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Main class to manage sessions at FitAll Club.
 */
public class FitAll {
    private ArrayList<Session> sessions = new ArrayList<>();
    private ArrayList<Integer> registeredSessions = new ArrayList<>();

    public FitAll() {
        initializeSessions();
    }

    private void initializeSessions() {
        sessions.add(new Session("Pilates", "Low", "Monday", "13:00", 55, 15));
        sessions.add(new Session("Yoga", "Low", "Friday", "18:00", 55, 15));
        sessions.add(new Session("Core", "Medium", "Tuesday", "19:00", 55, 20));
        sessions.add(new Session("Pump", "Medium", "Tuesday", "10:00", 55, 20));
        sessions.add(new Session("Yoga", "Medium", "Wednesday", "12:00", 55, 15));
        sessions.add(new Session("Core", "High", "Thursday", "18:00", 45, 20));
        sessions.add(new Session("Cycling", "High", "Wednesday", "09:00", 45, 10));
    }

    private void displayTimetable() {
        System.out.println("\nTimetable:");
        System.out.printf("%-10s %-15s %-10s %-10s %-10s %-10s %-10s\n",
                "SessionID", "Name", "Level", "Day", "Time", "Duration", "Spaces");
        sessions.forEach(Session::displaySession);
    }

    private void registerCustomer(int sessionID) {
        for (Session session : sessions) {
            if (session.getSessionID() == sessionID) {
                if (registeredSessions.contains(sessionID)) {
                    System.out.println("You are already registered for this session.");
                    return;
                }
                if (session.regCustomer()) {
                    registeredSessions.add(sessionID);
                    System.out.println("Successfully registered for " + session.getSessionName());
                } else {
                    System.out.println("No available spaces.");
                }
                return;
            }
        }
        System.out.println("Invalid session ID.");
    }

    private void cancelCustomer(int sessionID) {
        if (!registeredSessions.contains(sessionID)) {
            System.out.println("No registration found for this Session ID.");
            return;
        }

        for (Session session : sessions) {
            if (session.getSessionID() == sessionID) {
                session.cancelReg();
                registeredSessions.remove(Integer.valueOf(sessionID));
                System.out.println("Cancellation successful for " + session.getSessionName());
                return;
            }
        }
    }

    private void displayRegisteredSessions() {
        System.out.println("\nRegistered Sessions:");
        if (registeredSessions.isEmpty()) {
            System.out.println("No sessions registered.");
        } else {
            System.out.printf("%-10s %-15s %-10s %-10s %-10s %-10s %-10s\n",
                    "SessionID", "Name", "Level", "Day", "Time", "Duration", "Spaces");
            for (int sessionID : registeredSessions) {
                for (Session session : sessions) {
                    if (session.getSessionID() == sessionID) {
                        session.displaySession();
                    }
                }
            }
        }
    }

    private void handleRegistration(Scanner scanner) {
        while (true) {
            System.out.print("Enter Session ID to register (0 to cancel): ");
            int input = safeReadInt(scanner);
            if (input == 0) return;
            registerCustomer(input);
            break;
        }
    }

    private void handleCancellation(Scanner scanner) {
        System.out.print("Enter Session ID to cancel: ");
        int input = safeReadInt(scanner);
        cancelCustomer(input);
    }

    private int safeReadInt(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException ex) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }



    public static void main(String[] args) {
        FitAll fitAll = new FitAll();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nWelcome to FitAll Club");
            System.out.println("1. View Timetable");
            System.out.println("2. Register for a Session");
            System.out.println("3. Cancel a Registration");
            System.out.println("4. View Registered Sessions");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = fitAll.safeReadInt(scanner);

            switch (choice) {
                case 1 -> fitAll.displayTimetable();
                case 2 -> fitAll.handleRegistration(scanner);
                case 3 -> fitAll.handleCancellation(scanner);
                case 4 -> fitAll.displayRegisteredSessions();
                case 5 -> System.out.println("Thank you for using FitAll!");
                default -> System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 5);
    }
}
