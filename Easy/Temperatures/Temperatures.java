import java.util.*;

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // the number of temperatures to analyse
        int closestToZero = 5527;

        for (int i = 0; i < n; i++) {
            int t = in.nextInt(); // a temperature expressed as an integer ranging from -273 to 5526
            if (Math.abs(closestToZero) > Math.abs(t))
                closestToZero = t;
            else if (Math.abs(closestToZero) == Math.abs(t))
                closestToZero = Math.max(closestToZero, t);
        }

        System.out.println(closestToZero == 5527 ? 0 : closestToZero);
    }
}
