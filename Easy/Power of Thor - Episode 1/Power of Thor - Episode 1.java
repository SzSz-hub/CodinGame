import java.util.*;

class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int lightX = in.nextInt(); // the X position of the light of power
        int lightY = in.nextInt(); // the Y position of the light of power
        int initialTx = in.nextInt(); // Thor's starting X position
        int initialTy = in.nextInt(); // Thor's starting Y position

        // game loop
        while (in.nextInt() > 0) {
            String order = "";
            if (lightY < initialTy) {
                order = "N";
                initialTy--;
            } else if (lightY > initialTy) {
                order = "S";
                initialTy++;
            }

            if (lightX > initialTx) {
                order += "E";
                initialTx++;
            } else if (lightX < initialTx) {
                order += "W";
                initialTx--;
            }

            System.out.println(order);
        }
        in.close();
    }
}
