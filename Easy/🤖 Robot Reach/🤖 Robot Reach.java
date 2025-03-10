import java.util.*;

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int R = in.nextInt();
        int C = in.nextInt();
        int T = in.nextInt();

        in.close();

        boolean[][] reachabilityMatrix = new boolean[R][C];
        fillMatrix(reachabilityMatrix, T);

        System.out.println(countReachableCells(reachabilityMatrix));

    }

    public static void fillMatrix(boolean[][] matrix, int limit) {
        int R = matrix.length;
        int C = matrix[0].length;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (limit >= i / 100 + (i / 10) % 10 + i % 10 + j / 100 + (j / 10) % 10 + j % 10)
                    matrix[i][j] = true;
                else
                    matrix[i][j] = false;
            }
        }
    }

    public static int countReachableCells(boolean[][] matrix){
        boolean visited[][] = new boolean[matrix.length][matrix[0].length];
        int count = 0;
        
        count += exploreCell(matrix, visited, 0, 0);
        return count;
    }

    public static int exploreCell(boolean[][] matrix, boolean[][] visited, int i, int j) {
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length || !matrix[i][j] || visited[i][j])
            return 0;

        visited[i][j] = true;
        return 1 + exploreCell(matrix, visited, i - 1, j) + exploreCell(matrix, visited, i + 1, j) + exploreCell(matrix, visited, i, j - 1) + exploreCell(matrix, visited, i, j + 1);
    }
}
