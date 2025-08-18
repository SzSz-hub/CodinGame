import java.util.*;

class Solution {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int unsolvedCells = 0;
        char[][] matrix = new char[n][n];

        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }

        for (int i = 0; i < n; i++) {
            String row = scanner.nextLine();
            unsolvedCells += row.length() - row.replaceAll("\\.", "").length();
            matrix[i] = row.toCharArray();
        }
        scanner.close();

        while (unsolvedCells > 0) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n - 1; j++) {
                    // Check horizontal neighbors
                    if (matrix[i][j] != '.' && matrix[i][j] == matrix[i][j + 1]) {
                        if (j - 1 >= 0 && matrix[i][j - 1] == '.') {
                            matrix[i][j - 1] = (matrix[i][j] == '0') ? '1' : '0';
                            unsolvedCells--;
                        }
                        if (j + 2 < n && matrix[i][j + 2] == '.') {
                            matrix[i][j + 2] = (matrix[i][j] == '0') ? '1' : '0';
                            unsolvedCells--;
                        }
                    }

                    // Check vertical neighbors
                    if (matrix[j][i] != '.' && matrix[j][i] == matrix[j + 1][i]) {
                        if (j - 1 >= 0 && matrix[j - 1][i] == '.') {
                            matrix[j - 1][i] = (matrix[j][i] == '0') ? '1' : '0';
                            unsolvedCells--;
                        }
                        if (j + 2 < n && matrix[j + 2][i] == '.') {
                            matrix[j + 2][i] = (matrix[j][i] == '0') ? '1' : '0';
                            unsolvedCells--;
                        }
                    }

                    // Check horizontal pattern with one gap
                    if (matrix[i][j] != '.' && matrix[i][j + 1] == '.' &&
                            j + 2 < n && matrix[i][j] == matrix[i][j + 2]) {
                        matrix[i][j + 1] = (matrix[i][j] == '0') ? '1' : '0';
                        unsolvedCells--;
                    }

                    // Check vertical pattern with one gap
                    if (matrix[j][i] != '.' && matrix[j + 1][i] == '.' &&
                            j + 2 < n && matrix[j][i] == matrix[j + 2][i]) {
                        matrix[j + 1][i] = (matrix[j][i] == '0') ? '1' : '0';
                        unsolvedCells--;
                    }
                }
            }
        }

        printMatrix(matrix);
    }

    public static void printMatrix(char[][] matrix) {
        for (char[] row : matrix) {
            for (char cell : row) {
                System.out.print(cell);
            }
            System.out.println();
        }
    }
}
