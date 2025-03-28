import java.util.*;
import java.awt.Point;

class Solution {
    private static final char[] SYMBOLS = " .o+=*BOX@%&#/^".toCharArray();
    private static final byte[][] matrix = new byte[9][17];
    private static final Point startPosition = new Point(4, 8);
    private static Point endPosition = new Point(startPosition);

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String fingerprint = in.nextLine();
        in.close();

        String[] parts = fingerprint.split(":");
        hexToBinary(parts);
        getPath(parts);
        printMatrix();
    }

    private static void hexToBinary(String[] parts){
        for(int i = 0; i < parts.length; i++){
            String binary = Integer.toBinaryString(Integer.parseInt(parts[i], 16));
            parts[i] = String.format("%8s", binary).replace(' ', '0');
        }
    }

    private static void getPath(String[] parts){
        for (String part : parts) {
            for (int j = part.length(); j >= 2; j -= 2) {
                String direction = part.substring(j - 2, j);
                endPosition = moveBishop(endPosition, direction);
                matrix[endPosition.x][endPosition.y]++;
            }
        }
    }

    private static void printMatrix(){
        System.out.println("+---[CODINGAME]---+");
        for (int i = 0; i < matrix.length; i++){
            System.out.print("|");
            for (int j = 0; j < matrix[i].length; j++)
                if(i == startPosition.x && j == startPosition.y)
                    System.out.print('S');
                else if (i == endPosition.x && j == endPosition.y)
                    System.out.print('E');
                else
                    System.out.print(SYMBOLS[matrix[i][j] % SYMBOLS.length]);
            System.out.println("|");
        }
        System.out.println("+-----------------+");
    }

    private static Point moveBishop(Point position, String direction){
        Point newPosition = new Point(position);
        newPosition = switch (direction) {
            case "00" -> new Point(Math.max(0, position.x - 1), Math.max(0,position.y - 1));
            case "01" -> new Point(Math.max(0, position.x - 1), Math.min(16,position.y + 1));
            case "10" -> new Point(Math.min(8, position.x + 1), Math.max(0,position.y - 1));
            case "11" -> new Point(Math.min(8, position.x + 1), Math.min(16,position.y + 1));
            default -> position;
        };
        return newPosition;
    }
}
