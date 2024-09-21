package railwayReservation;

import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {
        boolean loop = true;
        TicketBooking ticketBooking = new TicketBooking();
        Scanner scanner = new Scanner(System.in);

        while (loop) {
            System.out.println("\nChoose any one \n 1.Book ticket \n 2.Cancel ticket"
                    + " \n 3.Display Confirmed list\n 4.Display RAC list"
                    + "\n 5.Display Waiting list\n 6.Exit");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter name: ");
                    String name = scanner.next();
                    System.out.println("Enter age: ");
                    int age = scanner.nextInt();
                    System.out.println("Enter berth preference (U/M/L): ");
                    char preference = scanner.next().charAt(0);
                    if (preference == 'U' || preference == 'M' || preference == 'L') {
                        ticketBooking.bookTicket(new Passenger(name, age, preference));
                    } else {
                        System.out.println("Invalid berth preference");
                    }
                    break;

                case 2:
                    System.out.println("Enter your Ticket ID: ");
                    int id = scanner.nextInt();
                    System.out.println(TicketCanceling.cancelTicket(id));
                    break;

                case 3:
                    ticketBooking.displayConfirmed();
                    break;

                case 4:
                    ticketBooking.displayRAC();
                    break;

                case 5:
                    ticketBooking.displayWaiting();
                    break;

                case 6:
                    System.out.println("\tThank you!");
                    loop = false;
                    break;

                default:
                    System.out.println("Invalid choice, please try again.");
                    break;
            }
        }
        scanner.close();
    }
}
