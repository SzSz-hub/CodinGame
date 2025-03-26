import java.util.*;

class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int roadLength = in.nextInt(); // The total kilometers of the route.
        int zoneQuantity = in.nextInt(); // The number of road construction sites.
        double timeDifference = 0;
        int zoneStart = 0;
        int previousSpeed = 0;
        for (int i = 0; i < zoneQuantity; i++) {
            int zoneKm = in.nextInt(); // The kilometer in which the road construction site begins.
            int zoneSpeed = in.nextInt(); // The speed limit of the road construction site.

            if(zoneStart != 0 || zoneKm == 0){
                double targetTime = calculateTimeDifference(zoneKm - zoneStart, 130);
                double realTime = calculateTimeDifference(zoneKm - zoneStart, previousSpeed);
                timeDifference += realTime - targetTime;
            }

            if(i == zoneQuantity - 1){
                double targetTime = calculateTimeDifference(roadLength - zoneKm, 130);
                double realTime = calculateTimeDifference(roadLength - zoneKm, zoneSpeed);
                timeDifference += realTime - targetTime;
            }

            zoneStart = zoneKm;
            previousSpeed = zoneSpeed;
        }

        System.out.println(Math.round(timeDifference));
    }

    private static double calculateTimeDifference(int zoneKm, int zoneSpeed) {
        return zoneKm / (double) zoneSpeed * 60f;
    }
}
