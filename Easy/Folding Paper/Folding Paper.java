import java.util.Scanner;

class Solution {
    private static final int[] visibleLayers = {1, 1, 1, 1}; //L,U,R,D

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        char[] order = in.nextLine().toCharArray();
        String side = in.nextLine();
        in.close();

        for (char c : order) fold(c);

        printResult(side);
    }

    private static void fold(char c) {
        switch (c) {
            case 'R' -> layersUpdate(2);
            case 'D' -> layersUpdate(3);
            case 'L' -> layersUpdate(0);
            case 'U' -> layersUpdate(1);
            default -> {
            }
        }
    }

    private static void layersUpdate(int i) {
        visibleLayers[(i + 2) % visibleLayers.length] += visibleLayers[i];
        visibleLayers[i] = 1;
        visibleLayers[(i + 1) % visibleLayers.length] *= 2;
        visibleLayers[(i + 3) % visibleLayers.length] *= 2;
    }

    private static void printResult(String c) {
        switch (c) {
            case "L" -> System.out.println(visibleLayers[0]);
            case "U" -> System.out.println(visibleLayers[1]);
            case "R" -> System.out.println(visibleLayers[2]);
            case "D" -> System.out.println(visibleLayers[3]);
            default -> {
            }
        }
    }
}