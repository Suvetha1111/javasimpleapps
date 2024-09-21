package railwayReservation;

public class Passenger {
    private static int idProvider = 0;
    private int id;
    private String name;
    private int age;
    private char preference;
    private String ticketType;
    private int seatNumber;

    public Passenger(String name, int age, char preference) {
        this.id = ++idProvider;
        this.name = name;
        this.age = age;
        this.preference = preference;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public char getPreference() {
        return preference;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    @Override
    public String toString() {
        return "Passenger Ticket ID: " + id + "\nPassenger Name: " + name +
                "\nPassenger Age: " + age + "\nPassenger Seat No: " + seatNumber +
                "\nPassenger Preference: " + preference;
    }
}
