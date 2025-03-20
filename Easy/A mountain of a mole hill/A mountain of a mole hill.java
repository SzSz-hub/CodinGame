import java.util.Arrays;
import java.util.Scanner;

class Solution {
    private static final int SIZE = 16;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        char[][] map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            map[i] = in.nextLine().toCharArray();
        }
        in.close();

        straightFenceDemoler(map);
        edgeDeMoler(map);

        int current = countMole(map);
        int previous = -1;
        while (current != previous) {
            moldUpdater(map);
            previous = current;
            current = countMole(map);
        }
        System.out.println(countMole(map));
    }

    private static void moldUpdater(char[][] map) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == 'o' && isMoleOut(map, i, j)) {
                    map[i][j] = '.';
                }
            }
        }
    }

    private static boolean isMoleOut(char[][] map, int y, int x) {
        if (x - 1 >= 0 && map[y][x - 1] == '.') return true;
        if (x + 1 < SIZE && map[y][x + 1] == '.') return true;
        if (y - 1 >= 0 && map[y - 1][x] == '.') return true;
        return y + 1 < SIZE && map[y + 1][x] == '.';
    }

    private static void edgeDeMoler(char[][] map) {
        for (int i = 0; i < SIZE; i++) {
            if (map[0][i] == 'o') {
                map[0][i] = '.';
            }
            if (map[SIZE - 1][i] == 'o') {
                map[SIZE - 1][i] = '.';
            }
            if (map[i][0] == 'o') {
                map[i][0] = '.';
            }
            if (map[i][SIZE - 1] == 'o') {
                map[i][SIZE - 1] = '.';
            }
        }
    }

    private static int countMole(char[][] map) {
        int count = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == 'o') {
                    count++;
                }
            }
        }
        return count;
    }

    private static void straightFenceDemoler(char[][] map) {
        for (int i = 0; i < SIZE; i++) {
            if (Arrays.toString(map[i]).contains("|") && !Arrays.toString(map[i]).contains("+")){
                int fences = 0;
                for (int j = 0; j < SIZE; j++) {
                    if (map[i][j] == '|') fences++;
                    if (map[i][j] == 'o' && fences % 2 == 0) {
                        map[i][j] = '.';
                    }
                }
            }
        }
    }
}