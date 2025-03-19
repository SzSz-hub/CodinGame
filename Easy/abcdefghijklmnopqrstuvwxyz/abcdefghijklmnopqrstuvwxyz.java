import java.util.*;

class Solution {
    static int depth = 0;
    static char[][] matrix;
    static List<int[]> visited = new ArrayList<>();
    static List<int[]> solution = new ArrayList<>();
    static int n;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        in.nextLine();

        matrix = new char[n][n];
        for (int i = 0; i < n; i++) {
            matrix[i] = in.nextLine().toCharArray();
        }
        in.close();

        for (int y = 0; y < n && solution.size() != 26; y++) {
            for (int x = 0; x < n && solution.size() != 26; x++) {
                if (matrix[y][x] == 'a' && solution.size() != 26) {
                    backtrack(y, x);
                }
            }
        }

        for (int y = 0; y < n; y++) {
            StringBuilder out = new StringBuilder();
            for (int x = 0; x < n; x++) {
                out.append(inSolution(y, x) ? matrix[y][x] : '-');
            }
            System.out.println(out);
        }
    }

    static void backtrack(int y, int x) {
        visited.add(new int[] { y, x });
        solution.add(new int[] { y, x });
        if (depth == 26)
            return;
        depth++;

        if (y - 1 >= 0 && !isVisited(y - 1, x) && matrix[y - 1][x] == (char) ('a' + depth)) {
            backtrack(y - 1, x);
        }
        if (x - 1 >= 0 && !isVisited(y, x - 1) && matrix[y][x - 1] == (char) ('a' + depth)) {
            backtrack(y, x - 1);
        }
        if (y + 1 < n && !isVisited(y + 1, x) && matrix[y + 1][x] == (char) ('a' + depth)) {
            backtrack(y + 1, x);
        }
        if (x + 1 < n && !isVisited(y, x + 1) && matrix[y][x + 1] == (char) ('a' + depth)) {
            backtrack(y, x + 1);
        }
        if (depth != 26) {
            solution.remove(solution.size() - 1);
            depth--;
        }
    }

    static boolean isVisited(int y, int x) {
        for (int[] pos : visited) {
            if (pos[0] == y && pos[1] == x)
                return true;
        }
        return false;
    }

    static boolean inSolution(int y, int x) {
        for (int[] pos : solution) {
            if (pos[0] == y && pos[1] == x)
                return true;
        }
        return false;
    }
}