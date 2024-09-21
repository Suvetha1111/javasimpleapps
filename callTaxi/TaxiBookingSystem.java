package callTaxi;

import java.util.ArrayList;
import java.util.List;

public class TaxiBookingSystem {
    private static List<Taxi> taxis = new ArrayList<>();
    private static int bookingIdCounter = 1; // Static to track booking ID globally

    public TaxiBookingSystem(int numTaxis) {
        for (int i = 1; i <= numTaxis; i++) {
            taxis.add(new Taxi(i));
        }
    }

    public static String bookTaxi(int customerId, char pickupPoint, char dropPoint, int pickupTime) {
        Taxi assignedTaxi = null;
        int minEarnings = Integer.MAX_VALUE;
        int minDistance = Integer.MAX_VALUE;

        for (Taxi taxi : taxis) {
            if (taxi.isAvailable(pickupTime)) {
                int distance = Math.abs(taxi.getCurrentLocation() - pickupPoint);
                if (distance < minDistance || (distance == minDistance && taxi.getTotalEarnings() < minEarnings)) {
                    assignedTaxi = taxi;
                    minDistance = distance;
                    minEarnings = taxi.getTotalEarnings();
                }
            }
        }

        if (assignedTaxi != null) {
            int fare = assignedTaxi.calculateFare(pickupPoint, dropPoint);
            Booking booking = new Booking(bookingIdCounter++, customerId, pickupPoint, dropPoint, pickupTime, fare);
            assignedTaxi.addBooking(booking);
            return "Taxi can be allotted.\nTaxi-" + assignedTaxi.getId() + " is allotted";
        } else {
            return "No Taxi is available at the moment.";
        }
    }

    public static void displayTaxiDetails() {
        for (Taxi taxi : taxis) {
            System.out.println(taxi);
        }
    }
}

