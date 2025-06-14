import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;


class Solution {
    private static boolean areBracketsBalanced(String input) {
        Stack<Character> bracketStack = new Stack<>();
        Map<Character, Character> bracketPairs = new HashMap<>();

        // Define matching bracket pairs
        bracketPairs.put('(', ')');
        bracketPairs.put('[', ']');
        bracketPairs.put('{', '}');

        for (char currentChar : input.toCharArray()) {
            if (bracketPairs.containsKey(currentChar)) {
                bracketStack.push(currentChar);
            } else if (!bracketStack.isEmpty()) {
                char lastOpeningBracket = bracketStack.peek();
                if (currentChar == bracketPairs.get(lastOpeningBracket)) {
                    bracketStack.pop();
                }
            } else if (bracketStack.empty() && bracketPairs.containsValue(currentChar)) {
                return false;
            }
        }

        return bracketStack.isEmpty();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        scanner.close();

        boolean isBalanced = areBracketsBalanced(input);
        System.out.print(isBalanced);
    }
}
