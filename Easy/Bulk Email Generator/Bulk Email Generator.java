import java.util.*;

class Solution {
    private static StringBuilder wholeString = new StringBuilder();

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int N = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }

        for (int i = 0; i < N; i++) {
            String line = in.nextLine();
            wholeString.append(line + "\n");
        }
        in.close();
        wholeString.deleteCharAt(wholeString.length()-1);

        makeChoices();
        System.out.println(wholeString);

    }

    private static void makeChoices() {
        int choiceCounter = 0;
        while (wholeString.indexOf("(") >= 0) {
            int start = wholeString.indexOf("(");
            int end = wholeString.indexOf(")");

            String currentChoices = wholeString.substring(start + 1, end);

            String[] choices = currentChoices.split("\\|", -1);
            wholeString.replace(start, end + 1, choices[choiceCounter % choices.length]);
            choiceCounter++;
        }
    }
}
