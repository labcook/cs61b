package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] pool;
    private int N;
    private int openedSites;
    private int top, bottom;
    private WeightedQuickUnionUF u;
    private WeightedQuickUnionUF uu;

    private int xyTo1D(int row, int col) {
        return N * row + col;
    }

    public Percolation(int N) {
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        pool = new boolean[N][N];
        this.N = N;
        u = new WeightedQuickUnionUF(N * N + 2);
        uu = new WeightedQuickUnionUF(N * N + 1);
        openedSites = 0;
        //union top and bottom
        top = N * N;
        bottom = N * N + 1;
        for (int i = 0; i < N; i++) {
            u.union(top, i);
            uu.union(top, i);
            u.union(bottom, N * (N - 1) + i);
        }
    }

    public void open(int row, int col) {
        if (!pool[row][col]) {
            pool[row][col] = true;
            openedSites++;

            if (col > 0 && pool[row][col - 1]) {
                u.union(xyTo1D(row, col), xyTo1D(row, col - 1));
                uu.union(xyTo1D(row, col), xyTo1D(row, col - 1));
            }
            if (col < N - 1 && pool[row][col + 1]) {
                u.union(xyTo1D(row, col), xyTo1D(row, col + 1));
                uu.union(xyTo1D(row, col), xyTo1D(row, col + 1));
            }
            if (row > 0 && pool[row - 1][col]) {
                u.union(xyTo1D(row, col), xyTo1D(row - 1, col));
                uu.union(xyTo1D(row, col), xyTo1D(row - 1, col));
            }
            if (row < N - 1 && pool[row + 1][col]) {
                u.union(xyTo1D(row, col), xyTo1D(row + 1, col));
                uu.union(xyTo1D(row, col), xyTo1D(row + 1, col));
            }
        }
    }

    public boolean isOpen(int row, int col) {
        return pool[row][col];
    }

    public boolean isFull(int row, int col) {
        return isOpen(row, col) && uu.connected(top, xyTo1D(row, col));
    }

    public int numberOfOpenSites() {
        return openedSites;
    }

    public boolean percolates() {
        if (N == 1) {
            return numberOfOpenSites() == 1 && u.connected(top, bottom);
        }
        return u.connected(top, bottom);
    }

    public static void main(String[] args) {
        return;
    }
}
