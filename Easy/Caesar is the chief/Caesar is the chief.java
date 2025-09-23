import java.util.*;

class Solution {
    private static String[] words;
    private static final String SECRET_WORD = "CHIEF";
    private static final int NO_RESULT = -100;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String textToDecode = scanner.nextLine();
        words = textToDecode.split(" ");
        scanner.close();

        int key = getKey();
        System.out.println(key == NO_RESULT ? "WRONG MESSAGE" : shift(key));
    }

    public static int getKey() {
        for (String word : words) {
            if (word.length() == SECRET_WORD.length()) {
                int firstDifference = getDifference(word.charAt(0), SECRET_WORD.charAt(0));
                boolean isValidKey = true;

                for (int i = 1; i < SECRET_WORD.length(); i++) {
                    int difference = getDifference(word.charAt(i), SECRET_WORD.charAt(i));
                    if (difference != firstDifference) {
                        isValidKey = false;
                        break;
                    }
                }

                if (isValidKey) {
                    return firstDifference;
                }
            }
        }
        return NO_RESULT;
    }

    public static int getDifference(char a, char b) {
        return b - a;
    }

    public static String shift(int shiftAmount) {
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            for (char c : word.toCharArray()) {
                int newChar = (c - 'A' + shiftAmount + 26) % 26;
                result.append((char) ('A' + newChar));
            }
            result.append(' ');
        }

        if (result.length() > 0) {
            result.deleteCharAt(result.length() - 1);
        }

        return result.toString();
    }
}
