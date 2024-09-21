package railwayReservation;

import java.util.LinkedList;
import java.util.Queue;

public class RACQueueManager {
    private Queue<Passenger> racQueue = new LinkedList<>();

    public boolean addToRAC(Passenger p) {
        if (racQueue.size() < 1) { // Set limit as needed
            p.setTicketType("RAC");
            racQueue.add(p);
            return true;
        }
        return false;
    }

    public void displayRAC() {
        System.out.println("-------------------------");
        for (Passenger p : racQueue) {
            System.out.println(p);
            System.out.println("-------------------------");
        }
    }
}
