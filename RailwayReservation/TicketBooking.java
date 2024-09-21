package railwayReservation;

import java.util.*;

public class TicketBooking {
    private SeatManager seatManager = new SeatManager();
    private RACQueueManager racQueueManager = new RACQueueManager();
    private WaitingListManager waitingListManager = new WaitingListManager();

    static List<Passenger> confirmedList = new ArrayList<>();

    public void bookTicket(Passenger p) {
        if (seatManager.isFullyBooked()) {
            if (racQueueManager.addToRAC(p)) {
                System.out.println("Added to RAC. Your ticket ID is " + p.getId());
            } else if (waitingListManager.addToWaitingList(p)) {
                System.out.println("Added to Waiting List. Your ticket ID is " + p.getId());
            } else {
                System.out.println("Tickets not available.");
            }
        } else if (seatManager.allocateSeat(p)) {
            System.out.println("Booking confirmed. Your ticket ID is " + p.getId());
            confirmedList.add(p);
        } else {
            System.out.println(p.getPreference() + " is not available.");
            availableList();
        }
    }

    private void availableList() {
        System.out.println("Available seats:");
        System.out.println("Upper Berth: " + (seatManager.getBerthLimit() - seatManager.getUpperListSize()));
        System.out.println("Middle Berth: " + (seatManager.getBerthLimit() - seatManager.getMiddleListSize()));
        System.out.println("Lower Berth: " + (seatManager.getBerthLimit() - seatManager.getLowerListSize()));
    }

    public void displayConfirmed() {
        System.out.println("-------------------------");
        for (Passenger p : confirmedList) {
            System.out.println(p);
            System.out.println("-------------------------");
        }
    }

    public void displayRAC() {
        racQueueManager.displayRAC();
    }

    public void displayWaiting() {
        waitingListManager.displayWaiting();
    }
}
