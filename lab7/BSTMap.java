import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>{

    private Node root;

    public class Node {
        private K key;
        private V value;
        private Node left;
        private Node right;
        private int size;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            this.size = 1;
        }
    }

    public BSTMap() {
        root = null;
    }

    public void clear() {
        root = null;
    }

    public boolean containsKey(K key) {
        return (get(key) != null);
    }

    public V get(K key) {
        return get(root, key);
    }

    public V get(Node x, K key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            return x.value;
        } else if (cmp < 0) {
            return get(x.left, key);
        } else {
            return get(x.right, key);
        }
    }


    public int size() {
        return size(root);
    }

    public int size(Node x) {
        if (x == null) {
            return 0;
        }
        return x.size;
    }

    public void put(K key, V value) {
        root = put(root, key, value);
    }

    public Node put(Node x, K key, V value) {
        if (x == null) {
            return new Node(key, value);
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = put(x.left, key, value);
        } else if (cmp > 0) {
            x.right = put(x.right, key, value);
        } else {
            x.value = value;
        }
        x.size = size(x.left) + size(x.right) + 1;
//        x.size += 1;  // also right
        return x;
    }

    public void printInOrder() {
        printInOrder(root);
    }

    public void printInOrder(Node x) {
        if (x != null) {
            printInOrder(x.left);
            System.out.println(x.key);
            printInOrder(x.right);
        }
    }

    @Override
    public Iterator<K> iterator() {
        return new BSTMapIterator();
    }

    public class BSTMapIterator implements Iterator<K> {
        Set<K> tmp = keySet();
        Iterator<K> tmpIter = tmp.iterator();
        @Override
        public boolean hasNext() {
            if (tmpIter.hasNext()) {
                return true;
            }
            return false;
        }

        @Override
        public K next() {
            return tmpIter.next();
        }
    }

    @Override
    public Set<K> keySet() {
        Set<K> tmp = new HashSet<>();
        addAllKeysToSet(root, tmp);
        return tmp;
    }

    public void addAllKeysToSet(Node p, Set<K> tmp) {
        if (p != null) {
            tmp.add(p.key);
            addAllKeysToSet(p.left, tmp);
            addAllKeysToSet(p.right, tmp);
        }
    }

    @Override
    public V remove(K key, V value) {
        if (get(key).equals(value)) {
            root = remove(root, key, value);
            return value;
        }
        return null;
    }

    public Node remove(Node x, K key, V value) {
        return remove(x, key);
    }

    @Override
    public V remove(K key) {
        V tmp = get(key);
        if (tmp != null) {
            root = remove(root, key);
            return tmp;
        }
        return null;
    }

    public Node remove(Node x, K key) {
        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            if (x.left == null && x.right == null) {
                return null;
            } else if (x.left == null && x.right != null) {
                return x.right;
            } else if (x.left != null && x.right == null) {
                return x.left;
            } else {
                K tmp = findLeftMax(x);
                x = remove(x, tmp);
                x.key = tmp;
                return x;
            }

        } else if (cmp < 0) {
            x.left = remove(x.left, key);
        } else {
            x.right = remove(x.right, key);
        }
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    public K findLeftMax(Node x) {
        x = x.left;
        while (x.right !=  null) {
            x = x.right;
        }
        return x.key;
    }

//    public static void main(String[] args) {
//        BSTMap<String, Integer> ex = new BSTMap<>();
//        ex.put("m", 1);
//        ex.put("a", 2);
//        ex.put("b", 3);
//        ex.put("z", 4);
//        ex.printInOrder();
//    }

}
