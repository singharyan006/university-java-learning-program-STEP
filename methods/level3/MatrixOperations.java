import java.util.Random;

public class MatrixOperations {

    public static int[][] generateRandomMatrix(int rows, int cols) {
        Random rand = new Random();
        int[][] matrix = new int[rows][cols];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                matrix[i][j] = rand.nextInt(10); // 0 to 9
        return matrix;
    }

    public static int[][] addMatrices(int[][] A, int[][] B) {
        int[][] result = new int[A.length][A[0].length];
        for (int i = 0; i < A.length; i++)
            for (int j = 0; j < A[0].length; j++)
                result[i][j] = A[i][j] + B[i][j];
        return result;
    }

    public static int[][] subtractMatrices(int[][] A, int[][] B) {
        int[][] result = new int[A.length][A[0].length];
        for (int i = 0; i < A.length; i++)
            for (int j = 0; j < A[0].length; j++)
                result[i][j] = A[i][j] - B[i][j];
        return result;
    }

    public static int[][] multiplyMatrices(int[][] A, int[][] B) {
        int[][] result = new int[A.length][B[0].length];
        for (int i = 0; i < A.length; i++)
            for (int j = 0; j < B[0].length; j++)
                for (int k = 0; k < A[0].length; k++)
                    result[i][j] += A[i][k] * B[k][j];
        return result;
    }

    public static int[][] transposeMatrix(int[][] A) {
        int[][] transposed = new int[A[0].length][A.length];
        for (int i = 0; i < A.length; i++)
            for (int j = 0; j < A[0].length; j++)
                transposed[j][i] = A[i][j];
        return transposed;
    }

    public static int determinant2x2(int[][] matrix) {
        return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
    }

    public static int determinant3x3(int[][] matrix) {
        return matrix[0][0] * (matrix[1][1] * matrix[2][2] - matrix[1][2] * matrix[2][1]) -
               matrix[0][1] * (matrix[1][0] * matrix[2][2] - matrix[1][2] * matrix[2][0]) +
               matrix[0][2] * (matrix[1][0] * matrix[2][1] - matrix[1][1] * matrix[2][0]);
    }

    public static double[][] inverse2x2(int[][] matrix) {
        double det = determinant2x2(matrix);
        if (det == 0) return null;
        double[][] inverse = new double[2][2];
        inverse[0][0] = matrix[1][1] / det;
        inverse[0][1] = -matrix[0][1] / det;
        inverse[1][0] = -matrix[1][0] / det;
        inverse[1][1] = matrix[0][0] / det;
        return inverse;
    }

    public static double[][] inverse3x3(int[][] m) {
        double det = determinant3x3(m);
        if (det == 0) return null;

        double[][] inverse = new double[3][3];
        inverse[0][0] = (m[1][1]*m[2][2] - m[1][2]*m[2][1]) / det;
        inverse[0][1] = -(m[0][1]*m[2][2] - m[0][2]*m[2][1]) / det;
        inverse[0][2] = (m[0][1]*m[1][2] - m[0][2]*m[1][1]) / det;
        inverse[1][0] = -(m[1][0]*m[2][2] - m[1][2]*m[2][0]) / det;
        inverse[1][1] = (m[0][0]*m[2][2] - m[0][2]*m[2][0]) / det;
        inverse[1][2] = -(m[0][0]*m[1][2] - m[0][2]*m[1][0]) / det;
        inverse[2][0] = (m[1][0]*m[2][1] - m[1][1]*m[2][0]) / det;
        inverse[2][1] = -(m[0][0]*m[2][1] - m[0][1]*m[2][0]) / det;
        inverse[2][2] = (m[0][0]*m[1][1] - m[0][1]*m[1][0]) / det;
        return inverse;
    }

    public static void displayMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row)
                System.out.print(val + "\t");
            System.out.println();
        }
    }

    public static void displayMatrix(double[][] matrix) {
        for (double[] row : matrix) {
            for (double val : row)
                System.out.printf("%.2f\t", val);
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] A = generateRandomMatrix(3, 3);
        int[][] B = generateRandomMatrix(3, 3);

        System.out.println("Matrix A:");
        displayMatrix(A);
        System.out.println("Matrix B:");
        displayMatrix(B);

        System.out.println("A + B:");
        displayMatrix(addMatrices(A, B));

        System.out.println("A - B:");
        displayMatrix(subtractMatrices(A, B));

        System.out.println("A * B:");
        displayMatrix(multiplyMatrices(A, B));

        System.out.println("Transpose of A:");
        displayMatrix(transposeMatrix(A));

        System.out.println("Determinant of A (3x3): " + determinant3x3(A));

        double[][] inverse = inverse3x3(A);
        if (inverse != null) {
            System.out.println("Inverse of A:");
            displayMatrix(inverse);
        } else {
            System.out.println("Matrix A is not invertible.");
        }
    }
}
