import java.util.*;
import java.util.regex.*;

class Solution {
    static int[] occurrences = new int[10]; // total, counters 1-9
    static double[] Benford = { 0, 30.1, 17.6, 12.5, 9.7, 7.9, 6.7, 5.8, 5.1, 4.6 };

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        occurrences[0] = in.nextInt();
        buildStatistic(occurrences[0], in);
        in.close();

        System.out.println(isFraudelant());
    }

    public static void buildStatistic(int N, Scanner in) {
        if (in.hasNextLine()) {
            in.nextLine();
        }
        for (int i = 0; i < N; i++) {
            String transaction = in.nextLine();
            Pattern pattern = Pattern.compile("[1-9]");
            Matcher matcher = pattern.matcher(transaction);

            if (matcher.find()) {
                int firstDigit = Integer.valueOf(matcher.group());
                occurrences[firstDigit]++;
            }
        }
    }

    public static boolean isFraudelant() {
        for (int i = 1; i < occurrences.length; i++) {
            if (Math.abs(((double) occurrences[i] / occurrences[0] * 100) - Benford[i]) > 10)
                return true;
        }
        return false;
    }
}
