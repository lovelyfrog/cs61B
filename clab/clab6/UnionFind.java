public class UnionFind {

    // TODO - Add instance variables?
    private int[] nodeParent;
    private int nodeNumber;

    public UnionFind(int n) {
        // TODO
        nodeParent = new int[n];
        for (int i=0; i<n; i++) {
            nodeParent[i] = -1;
        }
        nodeNumber = n;
    }

    public boolean connected(int v1, int v2) {
        // TODO
        return (find(v1) == find(v2));
    }

    public void union(int v1, int v2, int n) {
        // TODO
        int v1Root = find(v1);
        int v2Root = find(v2);
        if (v1Root != v2Root) {
            if (v1Root < n && v2Root >= n) {
                nodeParent[v1Root] += nodeParent[v2Root];
                nodeParent[v2Root] = v1Root;
            } else if (v1Root >= n && v2Root < n) {
                nodeParent[v2Root] += nodeParent[v1Root];
                nodeParent[v1Root] = v2Root;
            } else {
                if (nodeParent[v1Root] < nodeParent[v2Root]) {
                    nodeParent[v1Root] += nodeParent[v2Root];
                    nodeParent[v2Root] = v1Root;
                } else {
                    nodeParent[v2Root] += nodeParent[v1Root];
                    nodeParent[v1Root] = v2Root;
                }
            }
        }
    }

    public int find(int vertex) {
        // TODO
        if (nodeParent[vertex] < 0) {
            return vertex;
        }
        int rootVertex = find(nodeParent[vertex]);
        nodeParent[vertex] = rootVertex;
        return rootVertex;
    }

}
