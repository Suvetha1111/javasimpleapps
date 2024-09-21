import java.util.Scanner;

// Utility class for handling date-related operations
class DateUtils {

    // Check if a year is a leap year
    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    // Get number of days in a month
    public static int getDaysInMonth(int month, int year) {
        int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (month == 2 && isLeapYear(year)) {
            return 29;
        } else {
            return days[month - 1];
        }
    }

    // Calculate number of odd days from 1900 to the given year
    public static int calculateOddDays(int year, int month, int day) {
        int oddDays = 0;

        // Count odd days for each year from 1900 to year-1
        for (int y = 1900; y < year; y++) {
            oddDays += isLeapYear(y) ? 2 : 1;
        }

        // Count odd days for each month in the given year up to the previous month
        for (int m = 1; m < month; m++) {
            oddDays += getDaysInMonth(m, year);
        }

        // Add odd days for the days in the current month
        oddDays += day;

        return oddDays % 7;
    }

    // Get the day of the week for the given date
    public static String getDayOfWeek(int year, int month, int day) {
        int oddDays = calculateOddDays(year, month, day);
        String[] daysOfWeek = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        return daysOfWeek[oddDays];
    }
}

// Utility class for handling calendar display operations
