package callTaxi;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TaxiBookingSystem system = new TaxiBookingSystem(4); // Example: 4 taxis initially

        boolean loop = true;
        Scanner s = new Scanner(System.in);

        while (loop) {
            System.out.println("Choose any one\n1. Book Taxi\n2. Display Details\n3. Exit");
            int n = s.nextInt();

            switch (n) {
                case 1: {
                    System.out.println("Enter Customer ID:");
                    int customerId = s.nextInt();
                    System.out.println("Enter Pickup Location (A-F):");
                    char pickupLocation = s.next().charAt(0);
                    System.out.println("Enter Drop Location (A-F):");
                    char dropLocation = s.next().charAt(0);
                    System.out.println("Enter Pickup Time (hours):");
                    int pickupTime = s.nextInt();
                    System.out.println(TaxiBookingSystem.bookTaxi(customerId, pickupLocation, dropLocation, pickupTime));
                    break;
                }
                case 2: {
                    TaxiBookingSystem.displayTaxiDetails();
                    break;
                }
                case 3: {
                    loop = false;
                    System.out.println("Thank You!!!");
                    s.close();
                    break;
                }
                default: {
                    System.out.println("Invalid Option. Try Again.");
                }
            }
        }
    }
}
