import java.util.*;
import java.awt.Point;

class Player {
    private static final char WALL = '*';
    private static final char FLOOR = '-';
    private static final char SPAWN = 'P';
    private static final char TARGET = 'E';

    private static final int GRID_SIZE = 10;

    private static final int[] DIRECTION_Y = { -1, 1, 0, 0 }; // UP, DOWN, LEFT, RIGHT
    private static final int[] DIRECTION_X = { 0, 0, -1, 1 };
    private static final char[] DIRECTION_CHARS = { 'U', 'D', 'L', 'R' };

    private static Point player;
    private static Point target;

    private static final short[][] distanceMap = new short[GRID_SIZE][GRID_SIZE];

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            initializeGame(scanner);

            while (true) {
                target.y = scanner.nextInt();
                target.x = scanner.nextInt();

                short[][] currentDistanceMap = createMapCopy();
                calculateDistancesFromTarget(currentDistanceMap);

                char bestMove = findBestDirection(currentDistanceMap, player);
                movePlayer(bestMove);

                System.out.println(bestMove);
            }
        }
    }

    private static void initializeGame(Scanner scanner) {
        scanner.nextInt(); // Skip unused parameter
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }

        for (int row = 0; row < GRID_SIZE; row++) {
            char[] line = scanner.nextLine().toCharArray();
            for (int col = 0; col < line.length; col++) {
                switch (line[col]) {
                    case SPAWN:
                        player = new Point(col, row);
                        break;
                    case TARGET:
                        target = new Point(col, row);
                        break;
                }
                distanceMap[row][col] = (line[col] == WALL) ? Short.MAX_VALUE : 0;
            }
        }
    }

    private static short[][] createMapCopy() {
        short[][] copy = new short[GRID_SIZE][GRID_SIZE];
        for (int i = 0; i < GRID_SIZE; i++) {
            copy[i] = distanceMap[i].clone();
        }
        return copy;
    }

    private static void calculateDistancesFromTarget(short[][] map) {
        boolean[][] visited = new boolean[GRID_SIZE][GRID_SIZE];
        Queue<Point> queue = new LinkedList<>();

        queue.add(target);
        visited[target.y][target.x] = true;

        while (!queue.isEmpty()) {
            Point currentPos = queue.poll();

            for (int direction = 0; direction < 4; direction++) {
                Point nextPos = new Point(
                        currentPos.x + DIRECTION_X[direction],
                        currentPos.y + DIRECTION_Y[direction]);

                if (isValidFloorPosition(nextPos, map) && !visited[nextPos.y][nextPos.x]) {
                    map[nextPos.y][nextPos.x] = (short) (map[currentPos.y][currentPos.x] + 1);
                    visited[nextPos.y][nextPos.x] = true;
                    queue.add(nextPos);
                }
            }
        }
    }

    private static boolean isValidFloorPosition(Point position, short[][] map) {
        return position.y >= 0 && position.y < GRID_SIZE &&
                position.x >= 0 && position.x < GRID_SIZE &&
                map[position.y][position.x] != Short.MAX_VALUE;
    }

    private static char findBestDirection(short[][] map, Point position) {
        List<Character> bestDirections = new ArrayList<>();
        int minimumDistance = Integer.MAX_VALUE;

        for (int direction = 0; direction < 4; direction++) {
            Point nextPos = new Point(
                    position.x + DIRECTION_X[direction],
                    position.y + DIRECTION_Y[direction]);

            if (nextPos.y < 0 || nextPos.y >= GRID_SIZE ||
                    nextPos.x < 0 || nextPos.x >= GRID_SIZE) {
                continue;
            }

            int distance = map[nextPos.y][nextPos.x];

            if (distance < minimumDistance) {
                bestDirections.clear();
                bestDirections.add(DIRECTION_CHARS[direction]);
                minimumDistance = distance;
            } else if (distance == minimumDistance) {
                bestDirections.add(DIRECTION_CHARS[direction]);
            }
        }

        return bestDirections.isEmpty() ? 'U' : bestDirections.get(new Random().nextInt(bestDirections.size()));
    }

    private static void movePlayer(char direction) {
        switch (direction) {
            case 'U':
                player.y--;
                break;
            case 'D':
                player.y++;
                break;
            case 'L':
                player.x--;
                break;
            case 'R':
                player.x++;
                break;
        }
    }
}
