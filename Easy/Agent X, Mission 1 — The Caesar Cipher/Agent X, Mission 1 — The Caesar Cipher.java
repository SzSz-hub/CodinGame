import java.util.*;

class Solution {
    private static final int ASCII_MIN = 32;
    private static final int ASCII_RANGE = 95; // 126 - 32 + 1

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String ciphertext = in.nextLine();
        String word = in.next();
        in.close();

        for (int key = 1; key < ASCII_RANGE; key++) {
            String decrypted = decrypt(ciphertext, key);
            if (foundKey(decrypted, word)) {
                System.out.println(key);
                System.out.println(decrypted);
                break;
            }
        }
    }

    private static String decrypt(String ciphertext, int key) {
        StringBuilder result = new StringBuilder();
        for (char c : ciphertext.toCharArray()) {
            int decryptedAscii = c - key;
            if (decryptedAscii < ASCII_MIN) {
                decryptedAscii += ASCII_RANGE;
            }
            result.append((char) decryptedAscii);
        }
        return result.toString();
    }

    private static boolean foundKey(String text, String word){
        String[] split = text.split("[ ,.?;:!]");
        for (String s : split) {
            if (s.equals(word)) {
                return true;
            }
        }
        return false;
    }
}