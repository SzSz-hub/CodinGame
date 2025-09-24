import java.util.*;

class Solution {
    private static int lastNumber = 0;
    private static String lastOperation = "";
    private static int currentNumber = 0;
    private static double total = 0;
    private static boolean operationJustPressed = false;
    private static boolean justCalculated = false;

    public static void main(String args[]) {
        final List<String> operations = Arrays.asList("+", "-", "/", "x", "=", "AC");

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }

        for (int i = 0; i < n; i++) {
            String key = in.nextLine();
            if (!operations.contains(key)) {
                if (operationJustPressed || justCalculated) {
                    currentNumber = Integer.parseInt(key);
                    operationJustPressed = false;
                    if (justCalculated) {
                        lastOperation = "";
                        justCalculated = false;
                    }
                } else {
                    currentNumber = currentNumber * 10 + Integer.parseInt(key);
                }
                System.out.println(currentNumber);
            } else {
                if (key.equals("AC")) {
                    total = 0;
                    currentNumber = 0;
                    lastNumber = 0;
                    lastOperation = "";
                    operationJustPressed = false;
                    justCalculated = false;
                    formatedPrinter(total);
                } else if (key.equals("=")) {
                    if (!lastOperation.isEmpty()) {
                        if (operationJustPressed) {
                            doOperation(lastOperation, lastNumber);
                        } else {
                            lastNumber = currentNumber;
                            doOperation(lastOperation, currentNumber);
                        }
                    } else {
                        total = currentNumber;
                    }
                    operationJustPressed = true;
                    justCalculated = true;
                    formatedPrinter(total);
                } else {
                    if (!operationJustPressed && !lastOperation.isEmpty()) {
                        doOperation(lastOperation, currentNumber);
                    } else if (operationJustPressed) {
                        
                    } else {
                        total = currentNumber;
                    }
                    lastOperation = key;
                    operationJustPressed = true;
                    justCalculated = false;
                    formatedPrinter(total);
                }
            }
        }
        in.close();
    }

    private static void formatedPrinter(double number) {
        if (number == (int)number) {
            System.out.printf("%.0f%n", number);
        } else {
            String formatted = String.format("%.3f", number).replaceAll("0+$", "").replaceAll("\\.$", "");
            System.out.println(formatted);
        } 
    }

    private static void doOperation(String op, int operand) {
        total = switch (op) {
            case "+" -> total + operand;
            case "-" -> total - operand;
            case "x" -> total * operand;
            case "/" -> total / (double)operand;
            default -> total;
        };
    }
}
