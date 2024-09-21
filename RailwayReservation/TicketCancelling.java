package railwayReservation;

import java.util.List;

public class TicketCanceling {
    public static String cancelTicket(int id) {
        for (Passenger p : TicketBooking.confirmedList) {
            if (p.getId() == id) {
                cancel(p);
                return "Ticket cancelled successfully.";
            }
        }
        // Add checks for RAC and Waiting List here if necessary
        return "Invalid ID.";
    }

    private static void cancel(Passenger p) {
        TicketBooking.confirmedList.remove(p);
        // Logic to handle RAC and Waiting List would go here
    }
}
