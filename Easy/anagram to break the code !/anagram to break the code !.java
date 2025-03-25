import java.util.*;

class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String w = in.nextLine();
        String s = in.nextLine().replaceAll("[:.,?!]", " ").replaceAll(" {2,}", " ");
        in.close();

        String[] words = s.split(" ");
        int anagramLocation = findAnagram(w, words);
        if (anagramLocation == -1){
            System.out.println("IMPOSSIBLE");
            return;
        }

        int charCount = countChars(words, 0, anagramLocation);
        System.out.print(anagramLocation % 10 + "." + (words.length - anagramLocation - 1) % 10 + "." + charCount % 10 + ".");

        charCount = 0;
        for (int i = anagramLocation + 1; i < words.length; i++) {
            charCount += words[i].length();
        }
        System.out.println(charCount % 10);
    }

    private static boolean isAnagram(String s1, String s2) {
        if (s1.length() != s2.length() || s1.equals(s2))
            return false;

        char[] c1 = s1.toLowerCase().toCharArray();
        char[] c2 = s2.toLowerCase().toCharArray();
        Arrays.sort(c1);
        Arrays.sort(c2);
        return Arrays.equals(c1, c2);
    }

    private static int findAnagram(String w, String[] words) {
        for (int i = 0; i < words.length; i++) {
            if (isAnagram(w, words[i])) {
                return i;
            }
        }
        return -1;
    }

    private static int countChars(String[] words, int start, int end) {
        int count = 0;
        for (int i = start; i < end; i++) {
            count += words[i].length();
        }
        return count;
    }
}
