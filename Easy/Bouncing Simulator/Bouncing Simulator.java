import java.util.*;

class Solution {
    private static int[][] matrix;
    private static int width, height;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        width = in.nextInt();
        height = in.nextInt();
        int bounces = in.nextInt();
        in.close();
        
        matrix = new int[height][width];
        simulateBounces(bounces);
        drawMatrix();
    }

    private static void drawMatrix() {
        String border = "#".repeat(width + 2);
        System.out.println(border);
        
        for (int[] row : matrix) {
            System.out.print("#");
            for (int cell : row) {
                System.out.print(cell == 0 ? " " : cell);
            }
            System.out.println("#");
        }
        
        System.out.println(border);
    }

    private static void simulateBounces(int maxBounces) {
        if (width == 1 && height == 1) {
            matrix[0][0] = 1;
            return;
        }

        int x = 0, y = 0;
        int dx = width > 1 ? 1 : 0;
        int dy = height > 1 ? 1 : 0;
        
        matrix[y][x] = 1;
        int bounceCount = 0;
        
        while (bounceCount < maxBounces) {
            int nextX = x + dx;
            int nextY = y + dy;
            
            boolean hitWall = false;
            
            if (width > 1 && (nextX < 0 || nextX >= width)) {
                dx = -dx;
                nextX = x + dx;
                hitWall = true;
            }
            
            if (height > 1 && (nextY < 0 || nextY >= height)) {
                dy = -dy;
                nextY = y + dy;
                hitWall = true;
            }
            
            if (hitWall || dx == 0 || dy == 0) {
                bounceCount++;
                if (bounceCount >= maxBounces) break;
            }
            
            x = nextX;
            y = nextY;
            matrix[y][x]++;
        }
    }
}
