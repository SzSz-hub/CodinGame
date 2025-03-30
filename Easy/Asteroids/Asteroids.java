import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int w = in.nextInt();
        int h = in.nextInt();
        int t1 = in.nextInt();
        int t2 = in.nextInt();
        int t3 = in.nextInt();

        Map<Character, int[]> dic1 = new HashMap<>();
        Map<Character, int[]> dic2 = new HashMap<>();
        char[][] result = new char[h][w];

        for (char[] row : result) {
            Arrays.fill(row, '.');
        }

        for (int i = 0; i < h; i++) {
            String firstPictureRow = in.next();
            String secondPictureRow = in.next();
            for (int j = 0; j < w; j++) {
                if (firstPictureRow.charAt(j) != '.') {
                    dic1.put(firstPictureRow.charAt(j), new int[]{i, j});
                }
                if (secondPictureRow.charAt(j) != '.') {
                    dic2.put(secondPictureRow.charAt(j), new int[]{i, j});
                }
            }
        }
        in.close();

        double multiplier = (double) (t3 - t2) / (t2 - t1);

        for (int abc = 0; abc < 26; abc++) {
            char c = (char) ('Z' - abc);
            int[] p1 = dic1.get(c);
            int[] p2 = dic2.get(c);

            if (p1 != null && p2 != null) {
                int y = (int) Math.floor(p2[0] + ((p2[0] - p1[0]) * multiplier));
                int x = (int) Math.floor(p2[1] + ((p2[1] - p1[1]) * multiplier));

                if (x >= 0 && x < w && y >= 0 && y < h) {
                    result[y][x] = c;
                }
            }
        }

        StringBuilder output = new StringBuilder();
        for (char[] row : result) {
            output.append(new String(row)).append("\n");
        }
        System.out.print(output);
    }
}