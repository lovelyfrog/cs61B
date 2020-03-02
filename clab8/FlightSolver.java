import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Solver for the Flight problem (#9) from CS 61B Spring 2018 Midterm 2.
 * Assumes valid input, i.e. all Flight start times are >= end times.
 * If a flight starts at the same time as a flight's end time, they are
 * considered to be in the air at the same time.
 */
public class FlightSolver {

    PriorityQueue<Flight> startHeap;
    PriorityQueue<Flight> endHeap;
    public FlightSolver(ArrayList<Flight> flights) {
        /* FIX ME */
        startComparator comparatorStart = new startComparator();
        endComparator comparatorEnd = new endComparator();
        startHeap = new PriorityQueue<>(flights.size(), comparatorStart);
        endHeap = new PriorityQueue<>(flights.size(), comparatorEnd);
        for (int i=0; i<flights.size(); i++) {
            startHeap.add(flights.get(i));
            endHeap.add(flights.get(i));
        }
    }

    public class startComparator implements Comparator<Flight> {

        @Override
        public int compare(Flight o1, Flight o2) {
            if (o1.startTime > o2.startTime) {
                return 1;
            } else if (o1.startTime < o2.startTime) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    public class endComparator implements Comparator<Flight> {

        @Override
        public int compare(Flight o1, Flight o2) {
            if (o1.endTime > o2.endTime) {
                return 1;
            } else if (o1.endTime < o2.endTime) {
                return -1;
            } else {
                return 0;
            }
        }
    }



    public int solve() {
        /* FIX ME */
        int max = 0;
        int record = 0;
        Flight tmp;
        while (endHeap.size() != 0) {
            if (startHeap.size() !=0 && startHeap.peek().startTime < endHeap.peek().endTime) {
                tmp = startHeap.poll();
                record += tmp.passengers;
                if (record > max) {
                    max = record;
                }
            } else {
                tmp = endHeap.poll();
                record -= tmp.passengers;
            }
        }
        return max;
    }

}
