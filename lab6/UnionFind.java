public class UnionFind {

    // TODO - Add instance variables?
    private int[] nodeParent;
    private int nodeNumber;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        // TODO
        nodeParent = new int[n];
        for (int i=0; i<n; i++) {
            nodeParent[i] = -1;
        }
        nodeNumber = n;
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        // TODO
        if (vertex >= nodeNumber || vertex < 0) {
            System.out.println("Not valid vertex");
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        // TODO
        int v1Root = find(v1);
        int size = 0;
        for (int i=0; i<nodeNumber; i++) {
            if (find(i) == v1Root) {
                size += 1;
            }
        }
        return size;
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        // TODO
        return nodeParent[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        // TODO
        return (find(v1) == find(v2));
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        // TODO
        int v1Root = find(v1);
        int v2Root = find(v2);
        if (nodeParent[v1Root] < nodeParent[v2Root]) {
            nodeParent[v1Root] += nodeParent[v2Root];
            nodeParent[v2Root] = v1Root;
        } else {
            nodeParent[v2Root] += nodeParent[v1Root];
            nodeParent[v1Root] = v2Root;
        }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        // TODO
        if (nodeParent[vertex] < 0) {
            return vertex;
        }
        int rootVertex = find(nodeParent[vertex]);
        nodeParent[vertex] = rootVertex;
        return rootVertex;
    }

//    public static void main(String[] args) {
//        UnionFind ex = new UnionFind(5);
//        ex.union(0, 1);
//        ex.union(2, 3);
//        ex.union(3, 1);
//        ex.union(2, 4);
//    }

}
