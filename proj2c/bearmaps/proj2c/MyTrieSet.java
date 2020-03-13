package bearmaps.proj2c;
import java.util.ArrayList;
import java.util.List;

public class MyTrieSet implements TrieSet61B{

    private static final int R = 256;
    private Node root;
    private int keyNum;
    public class Node {
        public int isKey;
        public Node[] next = new Node[R];
        public Node(int isKey) {
            this.isKey = isKey;
        }
    }

    public MyTrieSet() {
        root = null;
        keyNum = 0;
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public boolean contains(String key) {
        Node tmp = root;
        for (int i=0, n=key.length(); i<n; i++) {
            if (tmp == null) return false;
            tmp = tmp.next[key.charAt(i)];
        }
        if (tmp == null) {
            return false;
        } else if (tmp.isKey == 1){
            return true;
        }
        return false;
    }

    @Override
    public void add(String key) {
        root = add(root, key, 0);
    }

    // d denote the index of key
    public Node add(Node node, String key, int d) {
        if (node == null) {
            node = new Node(0);
        }
        if (d == key.length()) {
            node.isKey = 1;
            keyNum += 1;
            return node;
        }
        char tmp = key.charAt(d);
        node.next[tmp] = add(node.next[tmp], key, d+1);
        return node;
    }

    @Override
    public List<String> keysWithPrefix(String prefix) {
        Node tmp = root;
        List<String> ans = new ArrayList<>();
        for (int i=0, n=prefix.length(); i<n; i++) {
            if (tmp == null) return null;
            tmp = tmp.next[prefix.charAt(i)];
        }
        if (tmp == null) {
            return null;
        } else if (tmp.isKey == 1) {
            ans.add(prefix);
        }
        collectHelp(tmp, ans, prefix);
        return ans;
    }

    public void collectHelp(Node node, List<String> ans, String X) {
        if (node == null) {
            return;
        }
        for (char i=0; i<R; i++) {
            if (node.next[i] != null) {
                String tmp = X + String.valueOf(i);
                if (node.next[i].isKey == 1) ans.add(tmp);
                collectHelp(node.next[i], ans, tmp);
            }
        }
    }

    public List<String> collectAllKeys() {
        return keysWithPrefix("");
    }

    @Override
    public String longestPrefixOf(String key) {
        Node tmp = root;
        StringBuffer sb = new StringBuffer();
        if (tmp == null) {
            return null;
        }
        for (int i=0, n=key.length(); i<n; i++) {
            char c = key.charAt(i);
            if (tmp.next[c] != null) {
                sb.append(c);
                tmp = tmp.next[c];
            } else {
                break;
            }
        }
        return sb.toString();
    }
}
