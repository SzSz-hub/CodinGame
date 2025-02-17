import java.util.*;
import java.awt.Point;

class Solution {
    static ArrayList<Point> unpaintedRanges = new ArrayList<>();

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int L = in.nextInt();
        int N = in.nextInt();
        
        // Initially the whole fence is unpainted
        unpaintedRanges.add(new Point(0, L));

        // Process each painted section
        for (int i = 0; i < N; i++) {
            int st = in.nextInt();
            int ed = in.nextInt();
            removeRange(st, ed);
        }
        in.close();

        // Output the remaining unpainted sections
        if (unpaintedRanges.isEmpty()) {
            System.out.println("All painted");
        } else {
            for (Point range : unpaintedRanges) {
                System.out.println(range.x + " " + range.y);
            }
        }
    }

    static void removeRange(int start, int end) {
        ArrayList<Point> newRanges = new ArrayList<>();
        
        for (Point range : unpaintedRanges) {
            // Current range is completely before or after the painted section
            if (range.y <= start || range.x >= end) {
                newRanges.add(range);
                continue;
            }
            
            // Add left unpainted portion if exists
            if (range.x < start) {
                newRanges.add(new Point(range.x, start));
            }
            
            // Add right unpainted portion if exists
            if (range.y > end) {
                newRanges.add(new Point(end, range.y));
            }
        }
        
        unpaintedRanges = newRanges;
    }
}
