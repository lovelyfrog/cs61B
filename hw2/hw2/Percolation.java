package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int[][] array;
    private UnionFind set;
    private UnionFind upSet;
    private int N;
    private int openSitesNumber;
    public Percolation(int N) {
        this.N = N;
        openSitesNumber = 0;
        array = new int[N][N];
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                array[i][j] = 0;
            }
        }
        // N*N: top node
        // N*N+1: bottom node
        set = new UnionFind(N*N+2);
        upSet = new UnionFind(N*N+1);
        for (int i=0; i<N; i++) {
            set.union(N*N, xy2Index(0, i));
            set.union(N*N+1, xy2Index(N-1, i));
            upSet.union(N*N, xy2Index(0, i));
        }
    }

    private int xy2Index(int x, int y) {
        return x*N+y;
    }

    public void open(int row, int col) {
        if (!isOpen(row, col)) {
            openSitesNumber += 1;
            array[row][col] = 1;
            if (col - 1 >= 0 && array[row][col - 1] == 1) {
                set.union(xy2Index(row, col), xy2Index(row, col - 1));
                upSet.union(xy2Index(row, col), xy2Index(row, col - 1));
            }
            if (col + 1 < N && array[row][col + 1] == 1) {
                set.union(xy2Index(row, col), xy2Index(row, col + 1));
                upSet.union(xy2Index(row, col), xy2Index(row, col + 1));
            }
            if (row - 1 >= 0 && array[row - 1][col] == 1) {
                set.union(xy2Index(row, col), xy2Index(row - 1, col));
                upSet.union(xy2Index(row, col), xy2Index(row - 1, col));
            }
            if (row + 1 < N && array[row + 1][col] == 1) {
                set.union(xy2Index(row, col), xy2Index(row + 1, col));
                upSet.union(xy2Index(row, col), xy2Index(row + 1, col));
            }
        }
    }

    public boolean isOpen(int row, int col) {
        if (array[row][col] == 1) {
            return true;
        }
        return false;
    }

    public boolean isFull(int row, int col) {
        if (upSet.connected(xy2Index(row, col), N*N) && isOpen(row, col)) {
            return true;
        }
        return false;
    }

    public int numberOfOpenSites() {
        return openSitesNumber;
    }

    //Backwash unsolved
    public boolean percolates() {
        if (set.connected(N*N, N*N+1) && openSitesNumber > 0) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {

    }

}
