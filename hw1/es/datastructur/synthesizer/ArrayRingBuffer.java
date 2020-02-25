package es.datastructur.synthesizer;
import java.util.Iterator;

//TODO: Make sure to that this class and all of its methods are public
//TODO: Make sure to add the override tag for all overridden methods
//TODO: Make sure to make this class implement BoundedQueue<T>

public class ArrayRingBuffer<T> implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        first = 0;
        last = 0;
        fillCount = 0;
        rb = (T[] )new Object[capacity];
    }

    @Override
    public int capacity() {
        return rb.length;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayRingBufferIterator();
    }

    public class ArrayRingBufferIterator implements Iterator<T> {
        private int pos;
        public ArrayRingBufferIterator() {
            pos = first;
        }

        public boolean hasNext() {
            if (pos == last) {
                return false;
            }
            return true;
        }

        public T next() {
            T tmp = rb[pos];
            pos += 1;
            if (pos == capacity()) {
                pos = 0;
            }
            return tmp;
        }
    }

    @Override
    public int fillCount() {
        return fillCount;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */

    @Override
    public void enqueue(T x) {
        if (!isFull()) {
            rb[last] = x;
            last += 1;
            if (last == capacity()) {
                last = 0;
            }
            fillCount += 1;
        } else {
            throw new RuntimeException("Ring Buffer overflow");
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        if (!isEmpty()) {
            T tmp = rb[first];
            first += 1;
            if (first == capacity()) {
                first = 0;
            }
            fillCount -= 1;
            return tmp;
        }
        else {
            throw new RuntimeException("Ring buffer underflow");
        }
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {
        if (!isEmpty()) {
            return rb[first];
        } else {
            throw new RuntimeException("Ring buffer underflow");
        }
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (other.getClass() != this.getClass()) {
            return false;
        }
        ArrayRingBuffer<T> o = (ArrayRingBuffer<T>) other;
        if (this.fillCount() != o.fillCount()) {
            return false;
        }
        Iterator<T> oIter = o.iterator();
        for (T item :this) {
            if (!item.equals(oIter.next())) {
                return false;
            }
        }
        return true;
    }

//    public static void main(String[] args) {
//        ArrayRingBuffer<Integer> ex = new ArrayRingBuffer<>(8);
//        for (int i=0; i<8; i++) {
//            ex.enqueue(i);
//        }
//        ex.dequeue();
//        ex.enqueue(10);
//        ex.dequeue();
//        ex.dequeue();
//        ex.enqueue(12);
//    }
}
