import java.util.Scanner;

class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int orbSizeMin = in.nextInt();
        int orbSizeMax = in.nextInt();
        int glowingSize1 = in.nextInt();
        int glowingSize2 = in.nextInt();
        in.close();

        double toFind = Math.pow(glowingSize1 * 0.5d, 3) + Math.pow(glowingSize2 * 0.5d, 3);

        while (orbSizeMax >= orbSizeMin) {
            double cal = Math.pow(orbSizeMax * 0.5d, 3) + Math.pow(orbSizeMin * 0.5d, 3);

            if (cal == toFind) {
                if (orbSizeMax != glowingSize2 && orbSizeMin != glowingSize1) {
                    System.out.println(orbSizeMin + " " + orbSizeMax);
                    return;
                }
                orbSizeMax--;
            } else if (cal > toFind)
                orbSizeMax--;
            else
                orbSizeMin++;
        }
        System.out.println("VALID");
    }
}
