import java.util.*;

class Solution {
    private static int[] bricks;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int maxBricksPerRow = in.nextInt();
        int availableBricks = in.nextInt();

        bricks = new int[availableBricks];
        for (int i = 0; i < availableBricks; i++) {
            bricks[i] = in.nextInt();
        }

        Arrays.sort(bricks);
        int work = 0;
        int counter = maxBricksPerRow;
        for(int i = bricks.length - maxBricksPerRow - 1; i >= 0; i--){
            int layer = Math.floorDiv(counter, maxBricksPerRow) + 1;
            counter++;
            work += (layer - 1 ) * bricks[i];
        }

        System.out.printf("%.3f", work * 0.65);
    }
}
