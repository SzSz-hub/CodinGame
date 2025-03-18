import java.util.*;
import java.awt.Point;

class Solution {
    private static final HashMap<String, Integer> visited = new HashMap<>(); // coordinate_direction, step

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int w = in.nextInt();
        int h = in.nextInt();

        char[][] map = new char[h][w];
        long n = in.nextLong();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        for (int i = 0; i < h; i++) {
            map[i] = in.nextLine().toCharArray();
        }
        in.close();

        Point start = getStartLocation(map);
        Point end = move(map, start, n);
        System.out.println(end.x + " " + end.y);
    }

    private static Point move(char[][] map, Point start, long n) {
        Point[] directions = { new Point(0, -1), new Point(1, 0), new Point(0, 1), new Point(-1, 0) };
        int direction = 0;
        Point current = start;

        for (long i = 0; i < n; i++) {
            Point next = new Point(current.x + directions[direction].x, current.y + directions[direction].y);
            if (!isWall(map, next.x, next.y)) {
                current = next;
                String key = current.x + "," + current.y + "," + direction;
                if (visited.containsKey(key)) {
                    long cycleLength = i - visited.get(key);
                    long remainingMoves = (n - i - 1) % cycleLength;
                    i = n - remainingMoves - 1;
                } else {
                    visited.put(key, (int)i);
                }
            } else {
                direction = (direction + 1) % 4;
                i--;
            }
        }
        return current;
    }

    private static boolean isWall(char[][] map, int x, int y) {
        return map[y][x] == '#';
    }

    private static Point getStartLocation(char[][] map) {
        for (int i = 0; i < map.length; i++)
            for (int j = 0; j < map[i].length; j++)
                if (map[i][j] == 'O')
                    return new Point(j, i);

        return null;
    }
}