import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * BnBSolver for the Bears and Beds problem. Each Bear can only be compared to Bed objects and each Bed
 * can only be compared to Bear objects. There is a one-to-one mapping between Bears and Beds, i.e.
 * each Bear has a unique size and has exactly one corresponding Bed with the same size.
 * Given a list of Bears and a list of Beds, create lists of the same Bears and Beds where the ith Bear is the same
 * size as the ith Bed.
 */
public class BnBSolver {

    private List<Bear> bears;
    private List<Bed> beds;
    public BnBSolver(List<Bear> bears, List<Bed> beds) {
        // TODO: Fix me.
        this.bears = bears;
        this.beds = beds;
    }

    /**
     * Returns List of Bears such that the ith Bear is the same size as the ith Bed of solvedBeds().
     * we need to sort the bears and beds
     */
    public List<Bear> solvedBears() {
        // TODO: Fix me.
//        quickSort(bears);
        List<Bear> ans = new ArrayList<>();
//        for (Bear tmpBear: bears) {
//            int i = 0;
//            for (Bed tmpBed: beds) {
//                if (tmpBear.compareTo(tmpBed) == 0) {
//                    ans.set(i, tmpBear);
//                }
//                i += 1;
//            }
//        }
        for (Bed bed: beds) {
            Bear tmp = new Bear(bed.getSize());
            ans.add(tmp);
        }
        return ans;
    }

    /**
     * Returns List of Beds such that the ith Bear is the same size as the ith Bear of solvedBears().
     */
    public List<Bed> solvedBeds() {
        // TODO: Fix me.
//        quickSort(beds);
        return beds;
    }

    /*
     I misunderstood the meaning of the lab that I compare bears and begs separately
     */

    public <T extends Comparable> void quickSort(List<T> list) {
        quickSortHelper(list, 0, list.size()-1);
    }

    public <T extends Comparable> void quickSortHelper(List<T> list, int start, int end) {
        if (start >= end) {
            return;
        }
        int L = start + 1;
        int G = end;
        T pivot = list.get(start);
        while (L <= G) {
            if (list.get(L).compareTo(pivot) < 0) {
                L += 1;
                continue;
            }
            if (list.get(G).compareTo(pivot) > 0) {
                G -= 1;
                continue;
            }
            swap(list, L, G);
            L += 1;
            G -= 1;
        }
        swap(list, start, G);
        quickSortHelper(list, start, G-1);
        quickSortHelper(list, L, end);
    }

    public <T extends Comparable> void swap(List<T> list, int i, int j) {
        T tmp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, tmp);
    }
}
