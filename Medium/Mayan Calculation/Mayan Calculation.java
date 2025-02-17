import java.util.*;

class Solution {
    private static int columnWidth;
    private static int rowHeight;
    private static String[] numberPatterns;

    public static void main(String[] args) {
        numberPatterns = new String[20];
        Scanner scanner = new Scanner(System.in);

        readDimensions(scanner);
        readNumberPatterns(scanner);

        int number1 = readNumber(scanner);
        int number2 = readNumber(scanner);

        String operation = scanner.next();
        long result = performOperation(number1, number2, operation);

        printResult(result);
    }

    private static void readDimensions(Scanner scanner) {
        columnWidth = scanner.nextInt();
        rowHeight = scanner.nextInt();
    }

    private static void readNumberPatterns(Scanner scanner) {
        for (int row = 0; row < rowHeight; row++) {
            String numeral = scanner.next();
            for (int digit = 0; digit < 20; digit++) {
                if (row == 0) {
                    numberPatterns[digit] = numeral.substring(digit * columnWidth, (digit + 1) * columnWidth);
                } else {
                    numberPatterns[digit] += numeral.substring(digit * columnWidth, (digit + 1) * columnWidth);
                }
            }
        }
    }

    private static int readNumber(Scanner scanner) {
        int size = scanner.nextInt();
        int number = 0;
        int digits = size / rowHeight;

        for (int i = digits; i > 0; i--) {
            StringBuilder digitPattern = new StringBuilder();
            for (int j = 0; j < rowHeight; j++) {
                digitPattern.append(scanner.next());
            }

            for (int k = 0; k < 20; k++) {
                if (numberPatterns[k].equals(digitPattern.toString())) {
                    number += k * Math.pow(20, i - 1);
                    break;
                }
            }
        }
        return number;
    }

    private static long performOperation(int number1, int number2, String operation) {
        return switch (operation) {
            case "+" -> (long) number1 + number2;
            case "-" -> (long) number1 - number2;
            case "*" -> (long) number1 * number2;
            case "/" -> (long) number1 / number2;
            default -> throw new IllegalArgumentException("Invalid operation: " + operation);
        };
    }

    private static void printResult(long result) {
        if (result == 0) {
            writeNumber(0);
            return;
        }

        long remaining = result;
        for (int power = 7; power >= 0; power--) {
            double base = Math.pow(20, power);
            if (result > base && remaining >= base) {
                int digit = (int) (remaining / base);
                writeNumber(digit);
                remaining -= digit * base;
            } else if (result > base) {
                writeNumber(0);
            }
        }
    }

    private static void writeNumber(int number) {
        char[] numberChars = numberPatterns[number].toCharArray();
        for (int i = 0; i < rowHeight; i++) {
            for (int j = 0; j < columnWidth; j++) {
                System.out.print(numberChars[i * columnWidth + j]);
            }
            System.out.println();
        }
    }
}
