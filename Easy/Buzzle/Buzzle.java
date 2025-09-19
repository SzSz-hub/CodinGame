import java.util.*;

class Solution {
    private static int[] buzzleNumbers;
    private static int base = 10;
    private static final int MAX_ITERATIONS = 64;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            base = scanner.nextInt();
            int fromNumber = scanner.nextInt();
            int tillNumber = scanner.nextInt();
            int k = scanner.nextInt();

            buzzleNumbers = new int[k];
            for (int i = 0; i < k; i++) {
                buzzleNumbers[i] = scanner.nextInt();
            }
            
            printBuzzleResults(fromNumber, tillNumber);
        }
    }

    private static void printBuzzleResults(int from, int till) {
        for (int i = from; i <= till; i++) {
            System.out.println(isBuzzle(i) ? "Buzzle" : i);
        }
    }

    private static boolean isBuzzle(int number) {
        int current = number;

        for (int iteration = 0; iteration < MAX_ITERATIONS; iteration++) {
            if (hasMatchingLastDigit(current) || isDivisibleByBuzzleNumber(current)) {
                return true;
            }

            int digitSum = calculateDigitSum(current);
            if (digitSum == current) {
                break;
            }
            current = digitSum;
        }

        return false;
    }

    private static boolean hasMatchingLastDigit(int number) {
        int lastDigit = Math.floorMod(number, base);
        for (int buzzleNumber : buzzleNumbers) {
            if (lastDigit == buzzleNumber) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDivisibleByBuzzleNumber(int number) {
        for (int buzzleNumber : buzzleNumbers) {
            if (buzzleNumber != 0 && number % buzzleNumber == 0) {
                return true;
            }
        }
        return false;
    }

    private static int calculateDigitSum(int number) {
        number = Math.abs(number);
        int sum = 0;
        while (number > 0) {
            sum += (number % base);
            number /= base;
        }
        return sum;
    }
}
