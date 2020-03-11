package bearmaps.proj2ab;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {

    private ArrayList<PriorityNode> heapArray;
    private int nextIndex;
    private Map<T, PriorityNode> itemMap;

    /*
    heap example:
    0   1   2   3   4   5   6   7
    -   3   5   9   15  17  19  21      with only PriorityNode's item shown
     */

    public ArrayHeapMinPQ() {
        heapArray = new ArrayList<>();
        itemMap = new HashMap<>();
        heapArray.add(null);    //alway set index 0 null and heap starts from index 1.
        nextIndex = 1;
    }

    @Override
    public void add(T item, double priority) {
        PriorityNode cur = new PriorityNode(item, priority, nextIndex);
        heapArray.add(cur);
        itemMap.put(item, cur);
        nextIndex += 1;
        swim(nextIndex-1);

    }

    public void swim(int k) {
        while (k > 1 && greater(k/2, k)) {
            exch(k/2, k);
            k /= 2;
        }
    }

    public boolean greater(int i, int j) {
        return heapArray.get(i).compareTo(heapArray.get(j)) > 0;
    }

    public void exch(int i, int j) {
        PriorityNode iNode = heapArray.get(i);
        PriorityNode jNode = heapArray.get(j);
        heapArray.set(i, jNode);
        heapArray.set(j, iNode);
        jNode.setIndex(i);
        iNode.setIndex(j);
    }

    //how to make this to O(log(n))?, hahah we need a map to save nodes.
    @Override
    public boolean contains(T item) {
        return itemMap.containsKey(item);
    }

    @Override
    public T getSmallest() {
        return heapArray.get(1).getItem();
    }

    @Override
    public T removeSmallest() {
        T smallest = heapArray.get(1).getItem();
        exch(1, nextIndex-1);
        nextIndex -= 1;
//        heapArray.set(nextIndex, null);   this problem hurt me!
        heapArray.remove(nextIndex);
        itemMap.remove(smallest);
        sink(1);
        return smallest;
    }

    public void sink(int k) {
        while (2*k < nextIndex) {
            int j = 2*k;
            if (j < nextIndex-1 && greater(j, j+1)) j+=1;
            if (!greater(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    @Override
    public int size() {
        return nextIndex - 1;
    }

    @Override
    public void changePriority(T item, double priority) {
        if (!contains(item)) throw new NoSuchElementException();
        PriorityNode tmp = itemMap.get(item);
        tmp.setPriority(priority);
        int index = tmp.getIndex();
        swim(index);
        sink(index);
    }

    public class PriorityNode implements Comparable<PriorityNode>{
        private T item;
        private double priority;
        private int index;

        public PriorityNode(T item, double priority, int index) {
            this.item = item;
            this.priority = priority;
            this.index = index;
        }

        public T getItem() {
            return item;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int x) {
            index = x;
        }

        public double getPriority() {
            return priority;
        }

        public void setPriority(double priority) {
            this.priority = priority;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || o.getClass() != this.getClass()) return false;
            PriorityNode that = (PriorityNode) o;
            if (this.getItem().equals(that.getItem())) {
                return true;
            } else {
                return false;
            }
        }

        @Override
        public int compareTo(PriorityNode o) {
            if (o == null) {
                return -1;
            }
            return Double.compare(getPriority(), o.getPriority());
        }

        @Override
        public int hashCode() {
            return getItem().hashCode();
        }
    }

}
