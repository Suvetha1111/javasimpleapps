package callTaxi;

import java.util.ArrayList;
import java.util.List;

public class Taxi {
    private int id;
    private char currentLocation;
    private int totalEarnings;
    private int availableTime;
    private List<Booking> bookings;

    public Taxi(int id) {
        this.id = id;
        this.currentLocation = 'A'; // All taxis initially start at A
        this.totalEarnings = 0;
        this.availableTime = 0;
        this.bookings = new ArrayList<>();
    }

    // Getters
    public int getId() {
        return id;
    }

    public char getCurrentLocation() {
        return currentLocation;
    }

    public int getTotalEarnings() {
        return totalEarnings;
    }

    public int getAvailableTime() {
        return availableTime;
    }

    // Setters
    public void setAvailableTime(int availableTime) {
        this.availableTime = availableTime;
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
        totalEarnings += booking.getFare();
        currentLocation = booking.getDropPoint();
        availableTime = booking.getDropTime();
    }

    public boolean isAvailable(int pickupTime) {
        return availableTime <= pickupTime;
    }

    public int calculateFare(char pickupPoint, char dropPoint) {
        int distance = Math.abs(dropPoint - pickupPoint) * 15;
        int fare = 100; // Base fare for the first 5 km
        if (distance > 5) {
            fare += (distance - 5) * 10;
        }
        return fare;
    }

    @Override
    public String toString() {
        StringBuilder details = new StringBuilder("Taxi-" + id + " Total Earnings: Rs. " + totalEarnings + "\n");
        for (Booking booking : bookings) {
            details.append(booking).append("\n");
        }
        return details.toString();
    }
}
