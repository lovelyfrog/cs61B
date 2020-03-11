import edu.princeton.cs.algs4.Queue;

import org.junit.Test;

public class TestSortAlgs {

    @Test
    public void testQuickSort() {
        Queue<String> tmp = new Queue<>();
        tmp.enqueue("b");
        tmp.enqueue("a");
        tmp.enqueue("z");
        tmp.enqueue("g");
        Queue res = QuickSort.quickSort(tmp);
        assert isSorted(res) == true;
    }

    @Test
    public void testMergeSort() {
        Queue<String> tmp = new Queue<>();
        tmp.enqueue("b");
        tmp.enqueue("a");
        tmp.enqueue("z");
        tmp.enqueue("g");
        Queue res = MergeSort.mergeSort(tmp);
        assert isSorted(res) == true;
    }

    /**
     * Returns whether a Queue is sorted or not.
     *
     * @param items  A Queue of items
     * @return       true/false - whether "items" is sorted
     */
    private <Item extends Comparable> boolean isSorted(Queue<Item> items) {
        if (items.size() <= 1) {
            return true;
        }
        Item curr = items.dequeue();
        Item prev = curr;
        while (!items.isEmpty()) {
            prev = curr;
            curr = items.dequeue();
            if (curr.compareTo(prev) < 0) {
                return false;
            }
        }
        return true;
    }
}
