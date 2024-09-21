
import java.util.Scanner;

class Flight {

    private String flightName;
    private int availableSeats = 50;
    private double ticketPrice = 5000;

    public Flight(String flightName) {
        this.flightName = flightName;
    }

    public String getFlightName() {
        return flightName;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void updateTicketPrice(double amount) {
        this.ticketPrice += amount;
    }

    public Ticket[] bookTickets(Passenger passenger, int numberOfSeats) {
        if (availableSeats >= numberOfSeats) {
            Ticket[] tickets = new Ticket[numberOfSeats];
            for (int i = 0; i < numberOfSeats; i++) {
                tickets[i] = new Ticket(passenger, this, availableSeats--, ticketPrice);
                updateTicketPrice(200);  // Increase price after booking
            }
            return tickets;
        } else {
            System.out.println("Not enough seats available.");
            return null;
        }
    }

    public void cancelTicket(Ticket ticket) {
        availableSeats++;
        updateTicketPrice(-200);  // Decrease price after cancellation
    }

    public String getFlightDetails() {
        return "Flight: " + flightName + ", Available Seats: " + availableSeats + ", Ticket Price: " + ticketPrice;
    }
}

class Passenger {

    private String name;
    private int age;
    private String contactNumber;

    public Passenger(String name, int age, String contactNumber) {
        this.name = name;
        this.age = age;
        this.contactNumber = contactNumber;
    }

    public String getPassengerDetails() {
        return "Name: " + name + ", Age: " + age + ", Contact: " + contactNumber;
    }
}

class Ticket {

    private static int ticketCounter = 1;
    private String ticketID;
    private Passenger passenger;
    private Flight flight;
    private int seat;
    private double price;

    public Ticket(Passenger passenger, Flight flight, int seat, double price) {
        this.ticketID = "TICKET" + ticketCounter++;
        this.passenger = passenger;
        this.flight = flight;
        this.seat = seat;
        this.price = price;
    }

    public String printTicketDetails() {
        return "Ticket ID: " + ticketID + ", "
                + passenger.getPassengerDetails() + ", "
                + flight.getFlightName() + ", Seat: " + seat + ", Price: " + price;
    }
}

public class FlightBooking {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create a flight
        System.out.print("Enter flight name: ");
        String flightName = scanner.nextLine();
        Flight flight = new Flight(flightName);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Book Ticket");
            System.out.println("2. Cancel Ticket");
            System.out.println("3. View Flight Details");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1: // Book Ticket
                    System.out.print("Enter passenger name: ");
                    scanner.nextLine();  // Consume newline
                    String name = scanner.nextLine();
                    System.out.print("Enter passenger age: ");
                    int age = scanner.nextInt();
                    System.out.print("Enter passenger contact number: ");
                    String contactNumber = scanner.next();
                    Passenger passenger = new Passenger(name, age, contactNumber);

                    System.out.print("Enter number of seats to book: ");
                    int numberOfSeats = scanner.nextInt();
                    Ticket[] tickets = flight.bookTickets(passenger, numberOfSeats);

                    if (tickets != null) {
                        for (Ticket ticket : tickets) {
                            System.out.println(ticket.printTicketDetails());
                        }
                    }
                    break;

                case 2: // Cancel Ticket
                    System.out.print("Enter ticket ID to cancel: ");
                    String ticketID = scanner.next();
                    // In a complete implementation, you'd locate the ticket by ID
                    // Here, we'll just assume cancellation is successful
                    System.out.println("Ticket cancelled.");
                    flight.cancelTicket(null); // Modify to cancel a specific ticket
                    break;

                case 3: // View Flight Details
                    System.out.println(flight.getFlightDetails());
                    break;

                case 4: // Exit
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
