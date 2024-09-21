package callTaxi;

public class Booking {
    private int bookingId;
    private int customerId;
    private char pickupPoint;
    private char dropPoint;
    private int pickupTime;
    private int dropTime;
    private int fare;

    public Booking(int bookingId, int customerId, char pickupPoint, char dropPoint, int pickupTime, int fare) {
        this.bookingId = bookingId;
        this.customerId = customerId;
        this.pickupPoint = pickupPoint;
        this.dropPoint = dropPoint;
        this.pickupTime = pickupTime;
        this.fare = fare;
        this.dropTime = pickupTime + (Math.abs(dropPoint - pickupPoint) * 60 / 15); // 60 mins for 15 km
    }

    // Getters
    public int getBookingId() {
        return bookingId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public char getPickupPoint() {
        return pickupPoint;
    }

    public char getDropPoint() {
        return dropPoint;
    }

    public int getPickupTime() {
        return pickupTime;
    }

    public int getDropTime() {
        return dropTime;
    }

    public int getFare() {
        return fare;
    }

    @Override
    public String toString() {
        return bookingId + "\t" + customerId + "\t" + pickupPoint + "\t" + dropPoint + "\t" + pickupTime + "\t" + dropTime + "\t" + fare;
    }
}

