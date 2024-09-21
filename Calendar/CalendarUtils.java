import java.util.Scanner;
class CalendarUtils {

    // Display calendar for a given month and year
    public static void displayCalendar(int month, int year) {
        int daysInMonth = DateUtils.getDaysInMonth(month, year);
        System.out.println("\n========================");
        System.out.println("\t " + month + "/" + year);
        System.out.println("\n========================");
        System.out.println("SUN MON TUE WED THU FRI SAT");

        int startingDay = DateUtils.calculateOddDays(year, month, 1); // Get the starting day for the 1st of the month

        // Print leading spaces for the first week
        for (int i = 0; i < startingDay; i++) {
            System.out.print("    ");  // Four spaces for alignment
        }

        // Print days of the month
        for (int day = 1; day <= daysInMonth; day++) {
            System.out.print(String.format("%4d", day));
            startingDay++;
            if (startingDay % 7 == 0 || day == daysInMonth) {
                System.out.println();
            }
        }
    }
}

// Main class to run the application
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Year : ");
        int year = sc.nextInt();
        System.out.print("Enter Month : ");
        int month = sc.nextInt();
        System.out.print("Enter Day : ");
        int day = sc.nextInt();

        // Input validation
        if (month < 1 || month > 12) {
            System.out.println("Invalid month");
            return;
        }
        if (year < 0) {
            System.out.println("Invalid year");
            return;
        }
        if (day < 1 || day > DateUtils.getDaysInMonth(month, year)) {
            System.out.println("Invalid day");
            return;
        }

        // Display the day of the week and the calendar for the given month and year
        System.out.println("The day of the week is: " + DateUtils.getDayOfWeek(year, month, day));
        CalendarUtils.displayCalendar(month, year);
    }
}


       

