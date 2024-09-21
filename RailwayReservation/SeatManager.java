package railwayReservation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class SeatManager {
    private static final int berthLimit = 2; // Change this value as per requirement
    private List<Passenger> upperList = new ArrayList<>();
    private List<Passenger> middleList = new ArrayList<>();
    private List<Passenger> lowerList = new ArrayList<>();
    private Map<Integer, Character> seatNumberWithBerth = new HashMap<>();

    private int upperSeatNumber = 1;
    private int middleSeatNumber = 2;
    private int lowerSeatNumber = 3;

    public boolean allocateSeat(Passenger p) {
        if (p.getPreference() == 'U' && upperList.size() < berthLimit) {
            p.setSeatNumber(upperSeatNumber);
            upperSeatNumber += 3;
            upperList.add(p);
            return true;
        } else if (p.getPreference() == 'M' && middleList.size() < berthLimit) {
            p.setSeatNumber(middleSeatNumber);
            middleSeatNumber += 3;
            middleList.add(p);
            return true;
        } else if (p.getPreference() == 'L' && lowerList.size() < berthLimit) {
            p.setSeatNumber(lowerSeatNumber);
            lowerSeatNumber += 3;
            lowerList.add(p);
            return true;
        }
        return false;
    }

    public boolean isFullyBooked() {
        return upperList.size() >= berthLimit && middleList.size() >= berthLimit && lowerList.size() >= berthLimit;
    }

    public int getBerthLimit() {
        return berthLimit;
    }

    public int getUpperListSize() {
        return upperList.size();
    }

    public int getMiddleListSize() {
        return middleList.size();
    }

    public int getLowerListSize() {
        return lowerList.size();
    }
}
