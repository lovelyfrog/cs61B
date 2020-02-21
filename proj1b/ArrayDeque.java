public class ArrayDeque<T> implements Deque<T>{
    /**
     * add and remove must take constant time
     * get and size must take constant time
     * The starting size should be 8
     */

    public int size;
    public int nextFirst;
    public int nextLast;
    public T[] items;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        nextFirst = 0;
        nextLast = 1;
        size = 0;

    }

    public ArrayDeque(ArrayDeque other) {
        items = (T[]) new Object[other.items.length];
        nextFirst = other.nextFirst;
        nextLast = other.nextLast;
        size = other.size;
        System.arraycopy(other.items, 0, items, 0, items.length);
    }

    @Override
    public void addFirst(T item){
        if (size < items.length - 1) {
            items[nextFirst] = item;
            nextFirst = position(nextFirst - 1);
        }
        else {
            resizeMore();
            items[nextFirst] = item;
            nextFirst = nextFirst - 1;
        }
        size += 1;
    }

    @Override
    public void addLast(T item) {
        if (size < items.length - 1){
            items[nextLast] = item;
            nextLast = position(nextLast + 1);
        }
        else{
            resizeMore();
            items[nextLast] = item;
            nextLast = nextLast + 1;
        }
        size += 1;
    }

    public void resizeMore() {
        T[] resizeItems = (T[]) new Object[items.length * 2];
        resize(resizeItems);
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        if (size == 0) {
            return;
        }
        int startPosition = nextFirst + 1;
        int endPosition = nextLast - 1;
        int curPosition = position(startPosition);
        while (curPosition != endPosition) {
            System.out.print(items[curPosition] + " ");
            curPosition = position(curPosition + 1);
        }
        System.out.println(items[curPosition]);
    }

    public int position(int p) {
        if (p == items.length) {
            p = 0;
        }
        if (p == -1) {
            p += items.length;
        }
        return p;
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        int curPosition = position(nextFirst + 1);
        T item = items[curPosition];
        items[curPosition] = null;
        nextFirst = curPosition;
        size -= 1;
        if ((float)size / items.length < 0.25 && items.length >= 16) {
            resizeLess();
        }
        return item;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        int curPosition = position(nextLast - 1);
        T item = items[curPosition];
        items[curPosition] = null;
        nextLast = curPosition;
        size -= 1;
        if ((float)size / items.length < 0.25 && items.length >= 16) {
            resizeLess();
        }
        return item;
    }

    public void resizeLess() {
        T[] resizeItems = (T[]) new Object[items.length / 2];
        resize(resizeItems);
    }

    public void resize(T[] resizeItems) {
        if (nextFirst >= nextLast) {
            System.arraycopy(items, nextFirst + 1, resizeItems, 0, items.length - nextFirst - 1);
            System.arraycopy(items, 0, resizeItems, items.length - nextFirst - 1, nextLast);
        }
        else {
            System.arraycopy(items, nextFirst + 1, resizeItems, 0, size);
        }
        nextFirst = resizeItems.length - 1;
        nextLast = size;
        items = resizeItems;
    }

    @Override
    public T get(int index) {
        int curPostion = position(nextFirst + 1);
        int i = 0;
        while (i != index) {
            curPostion = position(curPostion + 1);
            i += 1;
        }
        return items[curPostion];
    }
}
