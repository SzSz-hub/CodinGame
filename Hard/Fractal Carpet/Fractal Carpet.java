import java.util.*;

//https://en.wikipedia.org/wiki/Sierpi%C5%84ski_carpet
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int L = in.nextInt();
        long x1 = in.nextLong();
        long y1 = in.nextLong();
        long x2 = in.nextLong();
        long y2 = in.nextLong();
        in.close();
        
        for (long i = y1; i <= y2; i++) {
            for (long j = x1; j <= x2; j++) {
                System.out.print(isEmpty(i, j, L) ? "+" : "0");
            }
            System.out.println();
        }
    }
    
    public static boolean isEmpty(long x, long y, int level) {
        while (level != 0) {
            if (x % 3 == 1 && y % 3 == 1) return true;
            x /= 3;
            y /= 3;
            level--;
        }
        return false;
    }
}
