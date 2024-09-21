package railwayReservation;

import java.util.LinkedList;
import java.util.Queue;

public class WaitingListManager {
    private Queue<Passenger> waitingQueue = new LinkedList<>();

    public boolean addToWaitingList(Passenger p) {
        if (waitingQueue.size() < 1) { // Set limit as needed
            p.setTicketType("Waiting List");
            waitingQueue.add(p);
            return true;
        }
        return false;
    }

    public void displayWaiting() {
        System.out.println("-------------------------");
        for (Passenger p : waitingQueue) {
            System.out.println(p);
            System.out.println("-------------------------");
        }
    }
}
