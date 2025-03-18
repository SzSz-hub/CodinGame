import java.util.*;

class Solution {
    private static final int EATEN = 1;
    private static final int NOT_EATEN = 0;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int M = in.nextInt();
        int N = in.nextInt();
        int[][] matrix = new int[M][N];

        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            int choice = in.nextInt();
            eatCarrot(matrix, choice - 1);
            System.out.println(countPerimeters(matrix));
        }
    }

    private static void eatCarrot(int[][] matrix, int coloumn) {
        for (int i = matrix.length - 1; i >= 0; i--) {
            if (matrix[i][coloumn] == NOT_EATEN) {
                matrix[i][coloumn] = EATEN;
                break;
            }
        }
    }

    private static int countPerimeters(int[][] matrix) {
        int perimeters = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                perimeters += countperimeter(matrix, i, j);
            }
        }
        return perimeters;
    }

    private static int countperimeter(int[][] matrix, int row, int col) {
        int perimeter = 0;
        if (matrix[row][col] == NOT_EATEN) {
            if (row == 0 || matrix[row - 1][col] == EATEN)
                perimeter++;

            if (col == matrix[0].length - 1 || matrix[row][col + 1] == EATEN)
                perimeter++;

            if (row == matrix.length - 1 || matrix[row + 1][col] == EATEN)
                perimeter++;

            if (col == 0 || matrix[row][col - 1] == EATEN)
                perimeter++;

        }
        return perimeter;
    }
}
