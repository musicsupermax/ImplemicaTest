package task2;

import java.util.Arrays;

public class GraphModel {

    private int[][] matrix;

    GraphModel(int[][] matrix) {
        this.matrix = matrix;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setEdge(int i, int j, int weight) {
        matrix[i][j] = weight;
    }

    public int getCost(int i, int j) {
        if (i == j) {
            return 0;
        }
        if (matrix[i][j] == 0) {
            return 10000;
        }
        return matrix[i][j];
    }

    @Override
    public String toString() {
        return "GraphModel{" +
                "matrix=" + Arrays.deepToString(matrix) +
                '}';
    }
}
