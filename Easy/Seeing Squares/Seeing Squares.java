import java.util.*;

class Solution {
    private static char[][] matrix;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int rows = in.nextInt();
        int cols = in.nextInt();
        matrix = new char[rows][cols];
        
        if (in.hasNextLine()) {
            in.nextLine();
        }
        
        for (int i = 0; i < rows; i++) {
            String row = in.nextLine();
            matrix[i] = row.toCharArray();
        }
        in.close();

        System.out.println(countSquares());
    }

    private static int countSquares() {
        int squares = 0;
        
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[0].length - 1; x++) {
                if (matrix[y][x] == '+') {
                    for (int i = x + 2; i < matrix[0].length; i += 2) {
                        int verticalSize = (i - x + 1) / 2;
                        
                        if (y + verticalSize < matrix.length && 
                            matrix[y][i] == '+' && 
                            matrix[y + verticalSize][i] == '+' && 
                            matrix[y + verticalSize][x] == '+') {
                            
                            if (isValidSquare(x, y, i - x + 1)) {
                                squares++;
                            }
                        }
                    }
                }
            }
        }
        
        return squares;
    }

    private static boolean isValidSquare(int x, int y, int size) {
        int verticalSize = (size - 1) / 2;
        int horizontalSize = size - 1;
        
        if (verticalSize < 1) {
            return false;
        }

        for (int i = 0; i <= horizontalSize; i++) {
            if (isNotABorder(matrix[y][x + i]) ||
                y + verticalSize >= matrix.length ||
                    isNotABorder(matrix[y + verticalSize][x + i])) {
                return false;
            }
        }
        
        for (int i = 0; i <= verticalSize; i++) {
            if (y + i >= matrix.length ||
                    isNotAVertical(matrix[y + i][x]) ||
                    isNotAVertical(matrix[y + i][x + horizontalSize])) {
                return false;
            }
        }
        
        return true;
    }
    
    private static boolean isNotABorder(char c) {
        return c != '-' && c != '+';
    }
    
    private static boolean isNotAVertical(char c) {
        return c != '|' && c != '+';
    }
}