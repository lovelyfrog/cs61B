import java.util.*;
import java.util.HashSet;

public class MyHashMap<K extends Object, V> implements Map61B<K, V>{

    public class Node {
        private K key;
        private V value;
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private int initialSize;
    private double loadFactor;
    private int size;
    private LinkedList<Node>[] listArray;
    public MyHashMap() {
        this(16, 0.75);
    }

    public MyHashMap(int initialSize) {
        this(initialSize, 0.75);
    }

    public MyHashMap(int initialSize, double loadFactor) {
        this.initialSize = initialSize;
        this.loadFactor = loadFactor;
        this.size = 0;
        listArray = new LinkedList[initialSize];
        for (int i=0; i<initialSize; i++) {
            listArray[i] = null;
        }
    }

    public void resize() {
        MyHashMap<K, V> tmp = new MyHashMap<>(initialSize*2, loadFactor);
        for (int i=0; i<initialSize; i++) {
            LinkedList<Node> p = listArray[i];
            if (p != null) {
                int k = 0;
                Node pLast = p.getLast();
                while (p.get(k) != pLast) {
                    Node tmpNode = p.get(k);
                    tmp.put(tmpNode.key, tmpNode.value);
                    k += 1;
                }
                if (pLast != null) {
                    tmp.put(pLast.key, pLast.value);
                }
            }
        }
        this.initialSize = tmp.initialSize;
        this.size = tmp.size;
        this.listArray = tmp.listArray;
    }

    @Override
    public void clear() {
        size = 0;
        listArray = null;
    }

    @Override
    public boolean containsKey(K key) {
        if (get(key) != null) {
            return true;
        }
        return false;
    }

    @Override
    public V get(K key) {
        if (listArray != null) {
            int keyHash = hash(key);
            LinkedList<Node> p = listArray[keyHash];
            //how to find p's next is a quesion, here I use a stupid solution
            if (p != null) {
                int i = 0;
                Node pLast = p.getLast();
                while (p.get(i) != pLast) {
                    Node tmp = p.get(i);
                    if (tmp.key.equals(key)) {
                        return tmp.value;
                    }
                    i += 1;
                }
                if (pLast != null && pLast.key.equals(key)) {
                    return pLast.value;
                }
            }
            return null;
        }
        return null;
    }

    public int hash(K key) {
        return Math.floorMod(key.hashCode(), initialSize);
    }

    @Override
    public int size() {
        return size;
    }


    @Override
    public Set<K> keySet() {
        Set<K> tmp = new HashSet<>();
        for (int i=0; i<initialSize; i++) {
            LinkedList<Node> tmpList = listArray[i];
            if (tmpList != null) {
                Node pLast = tmpList.getLast();
                if (pLast != null) {
                    int k = 0;
                    while (tmpList.get(k) != pLast) {
                        Node tmpNode = tmpList.get(k);
                        tmp.add(tmpNode.key);
                        k++;
                    }
                    tmp.add(pLast.key);
                }
            }
        }
        return tmp;
    }

    @Override
    public void put(K key, V value) {
        int keyHash = hash(key);
        LinkedList<Node> p = listArray[keyHash];
        int flag = 0;
        if (p != null) {
            int i = 0;
            Node pLast = p.getLast();
            while (p.get(i) != pLast) {
                Node tmp = p.get(i);
                if (tmp.key.equals(key)) {
                    tmp.value = value;
                    flag = 1;
                    break;
                }
                i += 1;
            }
            if (pLast.key.equals(key)) {
                flag = 1;
                pLast.value = value;
            }
            if (flag == 0) {
                Node tmp = new Node(key, value);
                size += 1;
                p.add(tmp);
            }
        } else {
            p = new LinkedList<>();
            Node tmp = new Node(key, value);
            p.add(tmp);
            size += 1;
            listArray[keyHash] = p;
        }

        if ((float)size / initialSize > loadFactor) {
            resize();
        }
    }

    public Iterator<K> iterator() {
        Set<K> myKeySet = keySet();
        return myKeySet.iterator();
    }


    @Override
    public V remove(K key) {
        V value = get(key);
        if (value == null) {
            return null;
        }
        return remove(key, value);
    }

    @Override
    public V remove(K key, V value) {
        int keyHash = hash(key);
        LinkedList<Node> tmpList = listArray[keyHash];
        Node pLast = tmpList.getLast();
        for (int i=0; tmpList.get(i) != pLast; i++) {
            Node tmp = tmpList.get(i);
            if (tmp.key.equals(key)) {
                tmpList.remove(tmp);
                return tmp.value;
            }
        }
        if (pLast.key.equals(key)) {
            tmpList.remove(pLast);
            return pLast.value;
        }
        return null;
    }


    public static void main(String[] args) {
        LinkedList<String> ex = new LinkedList<>();
        ex.add("a");
        ex.add("b");
        int a = 1;
        int b = 4;
        System.out.println(ex.removeFirst());

    }
}
