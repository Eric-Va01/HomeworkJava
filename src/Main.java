import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<String> bookingHistory = new ArrayList<>();

        // Initialize Cinema
        System.out.print("[+] Insert row count: ");
        int rows = scanner.nextInt();
        System.out.print("[+] Insert column count: ");
        int cols = scanner.nextInt();
        String[][] seats = new String[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                seats[i][j] = (char) ('A' + j) + "-" + (i + 1) + ":AV";
            }
        }

        // Main Menu
        while (true) {
            System.out.println("\n--- Cinema Booking System ---");
            System.out.println("1. View Seats");
            System.out.println("2. Book Seats");
            System.out.println("3. Cancel a Booking");
            System.out.println("4. View Booking History");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            if (choice == 1) {
                // View Seats
                for (String[] row : seats) {
                    System.out.println(Arrays.toString(row));
                }
            } else if (choice == 2) {
                // Book Seats
                System.out.print("Enter seat codes to book (comma-separated, e.g., A-1,B-2): ");
                String input = scanner.nextLine();
                String[] seatCodes = input.toUpperCase().split(",");

                for (String seatCode : seatCodes) {
                    seatCode = seatCode.trim(); // Remove extra spaces
                    boolean booked = false;

                    for (int i = 0; i < rows; i++) {
                        for (int j = 0; j < cols; j++) {
                            if (seats[i][j].startsWith(seatCode)) if (seats[i][j].endsWith(":AV")) {
                                seats[i][j] = seatCode + ":BO";
                                String date = dateFormatter.format(new Date());
                                bookingHistory.add(date + " - Booked: " + seatCode);
                                System.out.println("Seat " + seatCode + " successfully booked.");
                                booked = true;
                                break;
                            } else {
                                System.out.println("Seat " + seatCode + " is already booked.");
                                booked = true;
                                break;
                            }
                        }
                        if (booked) break;
                    }

                    if (!booked) {
                        System.out.println("Invalid seat code: " + seatCode + ". Please try again.");
                    }
                }
            } else if (choice == 3) {
                // Cancel a Booking
                System.out.print("Enter seat code to cancel booking (e.g., A-1): ");
                String seatCode = scanner.nextLine().toUpperCase();
                boolean canceled = false;

                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        if (seats[i][j].startsWith(seatCode)) {
                            if (seats[i][j].endsWith(":BO")) {
                                seats[i][j] = seatCode + ":AV";
                                String date = dateFormatter.format(new Date());
                                bookingHistory.add(date + " - Canceled: " + seatCode);
                                System.out.println("Booking for seat " + seatCode + " successfully canceled.");
                            } else {
                                System.out.println("Seat " + seatCode + " is not booked.");
                            }
                            canceled = true;
                            break;
                        }
                    }
                    if (canceled) break;
                }

                if (!canceled) {
                    System.out.println("Invalid seat code. Please try again.");
                }
            } else if (choice == 4) {
                // View Booking History
                if (bookingHistory.isEmpty()) {
                    System.out.println("No booking history available.");
                } else {
                    System.out.println("\n--- Booking History ---");
                    for (String record : bookingHistory) {
                        System.out.println(record);
                    }
                }
            } else if (choice == 5) {
                System.out.println("Exiting... Thank you!");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
