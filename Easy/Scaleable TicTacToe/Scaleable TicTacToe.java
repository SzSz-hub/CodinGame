import java.util.*;

class Solution {
    private static final char EMPTY_CELL = ' ';
    private static final char[] WIN_MARKERS = {'-', '|', '\\', '/'};
    private static final int[][] DIRECTIONS = {{0,1}, {1,0}, {1,1}, {1,-1}};
    
    private static char[][] matrix;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int boardDimension = in.nextInt();
        matrix = new char[boardDimension][boardDimension];
        int winningLength = in.nextInt();
        
        boolean gameEnded = true;
        if (in.hasNextLine()) in.nextLine();
        
        for (int i = 0; i < boardDimension; i++) {
            String row = in.nextLine();
            if (gameEnded && row.contains(" ")) gameEnded = false;
            matrix[i] = row.toCharArray();
        }
        in.close();
        
        char winner = findWinner(winningLength);
        printMatrix();
        
        if (winner != 0) {
            System.out.println("The winner is " + winner + ".");
        } else if (gameEnded) {
            System.out.println("The game ended in a draw!");
        } else {
            System.out.println("The game isn't over yet!");
        }
    }

    private static char findWinner(int length) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] != EMPTY_CELL) {
                    char currentCell = matrix[i][j];
                    for (int dir = 0; dir < 4; dir++) {
                        if (checkDirection(j, i, length, dir)) {
                            return currentCell;
                        }
                    }
                }
            }
        }
        return 0;
    }
    
    private static boolean checkDirection(int x, int y, int length, int dir) {
        int dx = DIRECTIONS[dir][1], dy = DIRECTIONS[dir][0];
        
        // Special case for anti-diagonal bounds checking
        if (dir == 3 && (y + length > matrix.length || x - length + 1 < 0)) return false;
        if (dir != 3 && (y + (length-1)*dy >= matrix.length || x + (length-1)*dx >= matrix[y].length)) return false;
        
        char player = matrix[y][x];
        for (int i = 1; i < length; i++) {
            int nx = x + i * dx, ny = y + i * dy;
            if (player != matrix[ny][nx]) return false;
        }
        
        for (int i = 0; i < length; i++) {
            int nx = x + i * dx, ny = y + i * dy;
            matrix[ny][nx] = WIN_MARKERS[dir];
        }
        return true;
    }

    private static void printMatrix() {
        for (char[] row : matrix) {
            for (char c : row) System.out.print(c);
            System.out.println();
        }
    }
}
