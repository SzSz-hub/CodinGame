import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

class Solution {
    private final static ArrayList<String> adjList = new ArrayList<>(Arrays.asList("Adaptable", "Adventurous", "Affectionate", "Courageous", "Creative", "Dependable", "Determined", "Diplomatic", "Giving", "Gregarious", "Hardworking", "Helpful", "Hilarious", "Honest", "Non-judgmental", "Observant", "Passionate", "Sensible", "Sensitive", "Sincere"));
    private final static ArrayList<String> goodList = new ArrayList<>(Arrays.asList("Love", "Forgiveness", "Friendship", "Inspiration", "Epic Transformations", "Wins"));
    private final static ArrayList<String> badList = new ArrayList<>(Arrays.asList("Crime", "Disappointment", "Disasters", "Illness", "Injury", "Investment Loss"));
    private final static ArrayList<Character> voewels = new ArrayList<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'y'));
    private final static ArrayList<Character> consonants = new ArrayList<>(Arrays.asList('b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'v', 'w', 'x', 'z'));


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String name = in.nextLine();
        String cleanedName = name.toLowerCase().replaceAll("[^a-zA-z]", "");

        System.out.println("Hello Lisa.");
        System.err.println(cleanedName);
        System.err.println(Arrays.toString(getIndexes(cleanedName)));
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
        if (vowelCounter != 2 && consonantsCounter != 3)
            indexes[0] = -1;
        return indexes;
    }
}