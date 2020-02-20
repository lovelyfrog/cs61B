public class LinkedListDeque<T> {

    public class Node {
        public T item;
        public Node prev;
        public Node next;
        public Node(T item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    public int size;
    public Node sentinel;

    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public LinkedListDeque(LinkedListDeque other) {
        sentinel = new Node(null, null, null);
        Node p = sentinel;
        Node pOther = other.sentinel.next;
        while (pOther != other.sentinel) {
            Node tmp = new Node(pOther.item, null, null);
            p.next = tmp;
            tmp.prev = tmp;
            p = p.next;
            pOther = pOther.next;
        }
        p.next = sentinel;
        sentinel.prev = p;
    }

    public void addFirst(T item) {
        Node tmp = new Node(item, null, null);
        tmp.next = sentinel.next;
        tmp.prev = sentinel;
        tmp.next.prev = tmp;
        sentinel.next = tmp;
        size += 1;
    }

    public void addLast(T item) {
        Node tmp = new Node(item, null, null);
        tmp.next = sentinel;
        tmp.prev = sentinel.prev;
        tmp.next.prev = tmp;
        tmp.prev.next = tmp;
        size += 1;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node p = sentinel.next;
        while (p != sentinel) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println();
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        Node tmp = sentinel.next;
        T item = tmp.item;
        sentinel.next = tmp.next;
        tmp.next = null;
        tmp.item = null;
        sentinel.next.prev = tmp.prev;
        tmp.prev = null;
        size -= 1;
        return item;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        Node tmp = sentinel.prev;
        T item = tmp.item;
        sentinel.prev = tmp.prev;
        tmp.prev = null;
        tmp.item = null;
        sentinel.prev.next = tmp.next;
        tmp.next = null;
        size -= 1;
        return item;
    }

    /** Iterative version */
    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        Node p = sentinel.next;
        while (index != 0) {
            p = p.next;
            index -= 1;
        }
        return p.item;
    }

    public T getRecursive(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        return getHelper(sentinel.next, index);
    }

    private T getHelper(Node p, int index) {
        if (index == 0) {
            return p.item;
        }
        return getHelper(p.next, index-1);
    }

    /**
     Test

    public static void main(String[] args) {
        LinkedListDeque<Integer> x = new LinkedListDeque<>();
        x.addFirst(1);
        x.addFirst(2);
        x.addLast(3);
        x.addLast(4);
        x.addFirst(5);
        x.removeFirst();
        x.removeLast();
        x.printDeque();
        System.out.println(x.get(1));
        System.out.println(x.getRecursive(1));
    }
     */
}
