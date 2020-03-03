package bearmaps;

import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayHeapMinPQTest {
    @Test
    public void testAdd() {
        ArrayHeapMinPQ<Integer> heap = new ArrayHeapMinPQ<>();
        for (int i=1; i<6; i++) {
            heap.add(6-i, 6-i);
        }
        assertEquals(1, (int) heap.getSmallest());
    }

    @Test
    public void testContains() {
        ArrayHeapMinPQ<Integer> heap = new ArrayHeapMinPQ<>();
        for (int i=1; i<6; i++) {
            heap.add(6-i, 6-i);
        }
        assertEquals(1, (int) heap.getSmallest());
        assertTrue(heap.contains(1));
        assertFalse(heap.contains(6));
    }

    @Test
    public void testRemoveSmallest() {
        ArrayHeapMinPQ<Integer> heap = new ArrayHeapMinPQ<>();
        for (int i=1; i<6; i++) {
            heap.add(6-i, 6-i);
        }
        heap.removeSmallest();
        assertEquals(2, (int) heap.getSmallest());
    }

    @Test
    public void testChangePriority() {
        ArrayHeapMinPQ<Integer> heap = new ArrayHeapMinPQ<>();
        for (int i=1; i<6; i++) {
            heap.add(6-i, 6-i);
        }
        heap.changePriority(2, 0);
        assertEquals(2, (int) heap.getSmallest());
        heap.changePriority(1, 6);
        heap.removeSmallest();
        assertEquals(3, (int) heap.getSmallest());
    }


}
