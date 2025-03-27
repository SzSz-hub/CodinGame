import java.util.Scanner;

class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String N = in.next();
        in.close();

        int previousChamp = 1;
        for (int i = 2; i <= 9; i++) {
            int currentChamps = countClamps(N, i);
            if (currentChamps < previousChamp) {
                System.out.println(i);
                return;
            }
            previousChamp = currentChamps;
        }
        System.out.println("Normal");
    }

    public static int countClamps(String N, int modular) {
        char[] number = N.toCharArray();
        int champs = 0;
        int modul = -1;
        for (char c : number) {
            int remainder = (c - '0') % modular;
            if (remainder != modul) {
                champs++;
                modul = remainder;
            }
        }
        return champs;
    }
}