package hw4.puzzle;

import edu.princeton.cs.algs4.Queue;

import java.util.Arrays;

public class Board implements WorldState {
    private static final int BLANK = 0;
    private int N;
    private int[][] tiles;

    public Board(int[][] tiles) {
        this.N = tiles.length;
        this.tiles = new int[N][N];
        for (int r = 0; r < N; r += 1) {
            for (int c = 0; c < N; c += 1) {
                this.tiles[r][c] = tiles[r][c];
            }
        }
    }

    public int tileAt(int i, int j) {
        if (i < 0 || i > N - 1 || j < 0 || j > N - 1) { // Corner case check
            throw new IndexOutOfBoundsException("Invalid index given: i == " + i + " j == " + j);
        }
        return tiles[i][j];
    }

    public int size() {
        return N;
    }

    /**
     * Returns the neighbors of the current board
     *
     * @author SPOILERZ (Cited from http://joshh.ug/neighbors.html)
     */
    @Override
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == BLANK) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = BLANK;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = BLANK;
                }
            }
        }
        return neighbors;
    }

    public int hamming() {
        int estimatedDistance = 0;
        int expectedValue = 1;
        for (int r = 0; r < N; r += 1) {
            for (int c = 0; c < N; c += 1) {
                if (expectedValue == N * N) { // Break when hit the BLANK tile
                    break;
                }
                if (tileAt(r, c) != expectedValue) {
                    estimatedDistance += 1;
                }
                expectedValue += 1;
            }
        }
        return estimatedDistance;
    }

    public int manhattan() {
        int estimatedDistance = 0;
        for (int r = 0; r < N; r += 1) {
            for (int c = 0; c < N; c += 1) {
                int actualValue = tileAt(r, c);
                if (actualValue == 0) { // Continue when hit the BLANK tile
                    continue;
                }
                int expectedRow = (actualValue - 1) / N;
                int expectedColumn = (actualValue - 1) % N;
                estimatedDistance += Math.abs(expectedRow - r);
                estimatedDistance += Math.abs(expectedColumn - c);
            }
        }
        return estimatedDistance;
    }

    public int estimatedDistanceToGoal() {
        return manhattan();
    }

    @Override
    public int hashCode() {
        int result = N;
        result = 31 * result + Arrays.deepHashCode(tiles);
        return result;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || other.getClass() != this.getClass()) {
            return false;
        }

        Board otherBoard = (Board) other;
        if (N != otherBoard.N) {
            return false;
        }
        for (int r = 0; r < N; r += 1) {
            for (int c = 0; c < N; c += 1) {
                if (this.tileAt(r, c) != otherBoard.tileAt(r, c)) {
                    return false;
                }
            }
        }
        return true;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        int n = size();
        s.append(n + "\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                s.append(String.format("%2d ", tileAt(i, j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
