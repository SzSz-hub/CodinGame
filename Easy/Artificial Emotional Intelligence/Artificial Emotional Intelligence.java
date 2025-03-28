import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

class Solution {
    private final static ArrayList<String> adjList = new ArrayList<>(Arrays.asList("adaptable", "adventurous", "affectionate", "courageous", "creative", "dependable", "determined", "diplomatic", "giving", "gregarious", "hardworking", "helpful", "hilarious", "honest", "non-judgmental", "observant", "passionate", "sensible", "sensitive", "sincere"));
    private final static ArrayList<String> goodList = new ArrayList<>(Arrays.asList("love", "forgiveness", "friendship", "inspiration", "epic transformations", "wins"));
    private final static ArrayList<String> badList = new ArrayList<>(Arrays.asList("crime", "disappointment", "disasters", "illness", "injury", "investment loss"));
    private final static ArrayList<Character> voewels = new ArrayList<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'y'));
    private final static ArrayList<Character> consonants = new ArrayList<>(Arrays.asList('b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'v', 'w', 'x', 'z'));


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String name = in.nextLine();
        in.close();
        String cleanedName = name.toLowerCase().replaceAll("[^a-zA-z]", "");

        int[] indexes = getIndexes(cleanedName);
        printMessages(name, indexes);
    }

    private static int[] getIndexes(String name) {
        int[] indexes = new int[5];
        HashSet<Character> foundConsonants = new HashSet<>();
        int consonantsCounter = 0;
        int vowelCounter = 0;

        for (char c : name.toCharArray()) {
            if (vowelCounter < 2 && voewels.contains(c)) {
                indexes[3 + vowelCounter] = voewels.indexOf(c);
                vowelCounter++;
            }
            if (consonantsCounter < 3 && !foundConsonants.contains(c) && consonants.contains(c)) {
                indexes[consonantsCounter] = consonants.indexOf(c);
                consonantsCounter++;
                foundConsonants.add(c);
            }
        }
        if (vowelCounter != 2 || consonantsCounter != 3)
            indexes[0] = -1;
        return indexes;
    }

    private static void printMessages(String name, int[] indexes) {
        if (indexes[0] == -1) {
            System.out.println("Hello " + name + ".");
            return;
        }
        System.out.println("It's so nice to meet you, my dear " + adjList.get(indexes[0]) + " " + name + ".");
        System.out.println("I sense you are both " + adjList.get(indexes[1]) + " and " + adjList.get(indexes[2]) + ".");
        System.out.println("May our future together have much more " + goodList.get(indexes[3]) + " than " + badList.get(indexes[4]) + ".");
    }
}
