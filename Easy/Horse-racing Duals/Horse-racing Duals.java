import java.util.*;

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int[] horses = new int[N];
        for (int i = 0; i < N; i++) {
            horses[i]= in.nextInt();
        }
        Arrays.sort(horses);
        int minDifference = Integer.MAX_VALUE;
        for (int i = 0; i < N - 1; i++) {
            int difference = horses[i + 1] - horses[i];
            if (difference < minDifference) {
                minDifference = difference;
            }
        }

        System.out.println(minDifference);
    }
}
