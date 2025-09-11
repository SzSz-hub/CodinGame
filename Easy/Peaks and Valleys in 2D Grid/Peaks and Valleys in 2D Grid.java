import java.util.*;

class Solution {
    private static final int[][] DIRECTIONS = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, -1 },
            { 1, 0 }, { 1, 1 } };
    private static int[][] terrain;
    private static final StringBuilder peak = new StringBuilder();
    private static final StringBuilder valley = new StringBuilder();

    public static void main(String[] args) {
        terrain = getTerrain();
        for (int i = 0; i < terrain.length; i++) {
            for (int j = 0; j < terrain[0].length; j++) {
                if (isPeak(j, i))
                    addFormatedCoordinate(j, i, peak);

                if (isValley(j, i))
                    addFormatedCoordinate(j, i, valley);
            }
        }
        System.out.println(peak.isEmpty() ? "NONE" : peak.toString());
        System.out.println(valley.isEmpty() ? "NONE" : valley.toString());
    }

    private static int[][] getTerrain() {
        int[][] terrain = new int[1][1];
        Scanner in = new Scanner(System.in);
        int h = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        for (int i = 0; i < h; i++) {
            String line = in.nextLine();
            String[] split = line.split(" ");
            if (i == 0)
                terrain = new int[h][split.length];

            for (int j = 0; j < split.length; j++)
                terrain[i][j] = Integer.parseInt(split[j]);
        }
        in.close();
        return terrain;
    }

    private static boolean isPeak(int x, int y) {
        for (int[] dir : DIRECTIONS) {
            int targetX = x + dir[1];
            int targetY = y + dir[0];
            if (isValidCoordinate(targetX, targetY) && terrain[targetY][targetX] >= terrain[y][x])
                return false;
        }
        return true;
    }

    private static boolean isValley(int x, int y) {
        for (int[] dir : DIRECTIONS) {
            int targetX = x + dir[1];
            int targetY = y + dir[0];
            if (isValidCoordinate(targetX, targetY) && terrain[targetY][targetX] <= terrain[y][x])
                return false;
        }
        return true;
    }

    private static boolean isValidCoordinate(int x, int y) {
        return y >= 0 && y <= terrain.length - 1 && x >= 0 && x <= terrain[0].length - 1;
    }

    private static void addFormatedCoordinate(int x, int y, StringBuilder s) {
        String formated = "(" + x + ", " + y + ")";
        if (s.isEmpty())
            s.append(formated);
        else
            s.append(", ").append(formated);
    }
}