import java.util.Scanner;

class Solution {

    public static void main(String[] args) {
        String[] directions = {"N", "NE", "E", "SE", "S", "SW", "W", "NW"};
        Scanner in = new Scanner(System.in);

        String startDirection = in.next();
        int directionID = 0;
        for (int i = 0; i < directions.length; i++) {
            if (directions[i].equals(startDirection)) {
                directionID = i;
                break;
            }
        }

        int N = in.nextInt();
        for (int i = 0; i < N; i++) {
            String turn = in.next();
            directionID += switch (turn) {
                case "LEFT" -> -1;
                case "RIGHT" -> 1;
                case "BACK" -> 4;
                default -> 0;
            };
        }
        in.close();

        System.out.println(directions[((directionID % 8) + 8) % 8]);
    }
}
