import java.util.*;

class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        while (true) {
            int highestID = 0;
            int highestValue = 0;
            for (int i = 0; i < 8; i++) {
                int mountainH = in.nextInt(); // represents the height of one mountain.
                if(mountainH > highestValue){
                    highestID = i;
                    highestValue = mountainH;
                }
            }

            System.out.println(highestID); // The index of the mountain to fire on.
        }
    }
}
