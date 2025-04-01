import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numRobbers = scanner.nextInt();
        int numVaults = scanner.nextInt();
        
        List<Integer> crackingTimes = calculateVaultCrackingTimes(scanner, numVaults);
        scanner.close();

        System.out.println(calculateTotalTime(numRobbers, crackingTimes));
    }

    private static List<Integer> calculateVaultCrackingTimes(Scanner scanner, int numVaults) {
        List<Integer> times = new ArrayList<>();
        for (int i = 0; i < numVaults; i++) {
            int digitsInCode = scanner.nextInt();
            int numericDigits = scanner.nextInt();
            int combinations = (int) (Math.pow(10, numericDigits) * Math.pow(5, digitsInCode - numericDigits));
            times.add(combinations);
        }
        return times;
    }

    private static int calculateTotalTime(int numRobbers, List<Integer> crackingTimes) {
        int timeElapsed = 0;
        while (!crackingTimes.isEmpty()) {
            timeElapsed++;
            for (int i = 0; i < numRobbers && i < crackingTimes.size(); i++) {
                crackingTimes.set(i, crackingTimes.get(i) - 1);
            }
            crackingTimes.removeIf(time -> time == 0);
        }
        return timeElapsed;
    }
}
