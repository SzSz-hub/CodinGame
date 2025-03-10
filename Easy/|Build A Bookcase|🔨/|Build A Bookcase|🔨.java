import java.util.*;

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int height = in.nextInt();
        int width = in.nextInt();
        int numberOfShelves = in.nextInt();
        printDecoration(width);
        height--;
        int remainingShalves = numberOfShelves;

        for(int i = 0; i < numberOfShelves-1; i++){
            int shelvesSize = height/remainingShalves;
            printShelve(shelvesSize, width);
            remainingShalves--;
            height -= shelvesSize;
        }
        printShelve(height, width);
    }

    public static void printDecoration(int n) {
        if(n % 2 == 1)
            System.out.println("/".repeat(n / 2) + "^" + "\\".repeat(n / 2));
        else
            System.out.println("/".repeat(n / 2) + "\\".repeat(n / 2));
    }

    public static void printShelve(int size, int width){
        for(int i = 0; i < size -1; i++)
            printShelveMiddle(width);
        printShelveDivider(width);
    }

    public static void printShelveMiddle(int n) {
        System.out.println("|" + " ".repeat(n - 2) + "|");
    }

    public static void printShelveDivider(int n){
        System.out.println("|" + "_".repeat(n - 2) + "|");
    }
}
