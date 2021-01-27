import java.awt.Color;

import edu.princeton.cs.algs4.Picture;
import edu.princeton.cs.algs4.Stack;

public class SeamCarver {
    private Picture p;
    private double[][] iEnergy;
    private double[][] leastEnergy;
    private int[][] edgeTo;
    private Stack<Integer> s = new Stack<>();

    public SeamCarver(Picture picture) {
        this.p = new Picture(picture);
        iEnergy = new double[p.height()][p.width()];
        for (int i = 0; i < p.height(); i++) {
            for (int j = 0; j < p.width(); j++) {
                iEnergy[i][j] = energy(j, i);
            }
        }
    }

    public Picture picture() {
        return new Picture(p);
    }

    public int width() {
        return p.width();
    }

    public int height() {
        return p.height();
    }

    // energy of pixel at column x and row y
    public double energy(int x, int y) {
        if (x < 0 || x >= width() || y < 0 || y >= height()) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        double e = 0;
        Color left = p.get((x - 1 + width()) % width(), y);
        Color right = p.get((x + 1) % width(), y);
        Color top = p.get(x, (y + 1) % height());
        Color bottom = p.get(x, (y - 1 + height()) % height());

        e += (left.getRed() - right.getRed()) * (left.getRed() - right.getRed());
        e += (left.getGreen() - right.getGreen()) * (left.getGreen() - right.getGreen());
        e += (left.getBlue() - right.getBlue()) * (left.getBlue() - right.getBlue());
        e += (top.getRed() - bottom.getRed()) * (top.getRed() - bottom.getRed());
        e += (top.getGreen() - bottom.getGreen()) * (top.getGreen() - bottom.getGreen());
        e += (top.getBlue() - bottom.getBlue()) * (top.getBlue() - bottom.getBlue());
        return e;
    }

    // sequence of indices for horizontal seam
    private int[] findSeam(int width, int height, double[][] energyMatrix) {
        leastEnergy = new double[height][width];
        edgeTo = new int[height][width];

        for (int i = 0; i < width; i++) {
            leastEnergy[0][i] = energyMatrix[0][i];
        }
        //compute the least energy
        for (int i = 1; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (j == 0) {
                    leastEnergy[i][j] = energyMatrix[i][j] + min(leastEnergy[i - 1][j],
                            leastEnergy[i - 1][j + 1]);
                    if (leastEnergy[i - 1][j] < leastEnergy[i - 1][j + 1]) {
                        edgeTo[i][j] = j;
                    } else {
                        edgeTo[i][j] = j + 1;
                    }

                } else if (j == width - 1) {
                    leastEnergy[i][j] = energyMatrix[i][j] + min(leastEnergy[i - 1][j - 1],
                            leastEnergy[i - 1][j]);
                    if (leastEnergy[i - 1][j] < leastEnergy[i - 1][j - 1]) {
                        edgeTo[i][j] = j;
                    } else {
                        edgeTo[i][j] = j - 1;
                    }
                } else {
                    leastEnergy[i][j] = energyMatrix[i][j] + min(leastEnergy[i - 1][j - 1],
                            leastEnergy[i - 1][j], leastEnergy[i - 1][j + 1]);
                    int minIndex = j - 1;
                    if (leastEnergy[i - 1][j] < leastEnergy[i - 1][j - 1]) {
                        minIndex = j;
                    }
                    if (leastEnergy[i - 1][j + 1] < leastEnergy[i - 1][minIndex]) {
                        minIndex = j + 1;
                    }
                    edgeTo[i][j] = minIndex;
                }
            }
        }
        //find the index in the bottom
        double min = leastEnergy[height - 1][0];
        int index = 0;
        for (int i = 0; i < width; i++) {
            if (leastEnergy[height - 1][i] < min) {
                min = leastEnergy[height - 1][i];
                index = i;
            }
        }
        s.push(index);
        for (int i = height - 1; i > 0; i--) {
            s.push(edgeTo[i][index]);
            index = edgeTo[i][index];
        }
        int[] res = new int[height];
        int i = 0;
        while (!s.isEmpty()) {
            res[i++] = s.pop();
        }
        return res;
    }

    private double[][] transpose(double[][] energyMatrix) {
        int h = energyMatrix.length;
        int w = energyMatrix[0].length;
        double[][] res = new double[w][h];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                res[j][i] = energyMatrix[i][j];
            }
        }
        return res;
    }

    public int[] findHorizontalSeam() {
        double[][] energyMatrix = transpose(iEnergy);
        return findSeam(height(), width(), energyMatrix);
    }

    // sequence of indices for vertical seam
    public int[] findVerticalSeam() {
        return findSeam(width(), height(), iEnergy);
    }

    // remove horizontal seam from picture
    public void removeHorizontalSeam(int[] seam) {
        p = SeamRemover.removeHorizontalSeam(p, seam);
    }

    // remove vertical seam from picture
    public void removeVerticalSeam(int[] seam) {
        p = SeamRemover.removeVerticalSeam(p, seam);
    }

    private double min(double a, double b) {
        if (a > b) {
            return b;
        }
        return a;
    }

    private double min(double a, double b, double c) {
        return min(min(a, b), c);
    }
}
