import java.util.Scanner;

class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int thickness = in.nextInt();
        int length = in.nextInt();
        int turns = in.nextInt();
        in.close();

        printTop(thickness, turns);
        printMiddle(thickness, length, turns);
        printBottom(thickness, turns);
    }

    public static void printTop(int thickness, int turns) {
        String open = " " + "_".repeat(thickness);
        String middle = " " + "_".repeat(thickness * 2 + 1);
        if (turns % 2 == 0)
            System.out.println(open + middle.repeat(turns / 2));
        else
            System.out.println(open + middle.repeat(turns / 2) + open);
    }

    public static void printMiddle(int thickness, int lenth, int turns) {
        String shortPart = "|" + " ".repeat(thickness);
        String longPart = "|" + " ".repeat(thickness * 2 + 1);
        if (turns % 2 == 0)
            System.out.println(shortPart + longPart.repeat(turns / 2) + "|");
        else
            System.out.println(shortPart + longPart.repeat(turns / 2) + shortPart + "|");
        for (int i = 1; i < lenth - 1; i++) {
            System.out.println(shortPart.repeat(turns + 1) + "|");
        }
    }

    public static void printBottom(int thickness, int turns) {
        String close = "|" + "_".repeat(thickness) + "|";
        String middle = "|" + "_".repeat(thickness * 2 + 1);
        if (turns % 2 == 0)
            System.out.println(middle.repeat(turns / 2) + close);
        else
            System.out.println(middle.repeat(turns / 2 + 1) + "|");
    }
}