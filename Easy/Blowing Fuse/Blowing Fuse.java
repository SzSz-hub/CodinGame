import java.util.*;

class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int c = in.nextInt();
        Device[] devices = new Device[n];
        for (int i = 0; i < n; i++) {
            int nx = in.nextInt();
            devices[i] = new Device(nx);
        }
        int maxConsumption = 0;
        for (int i = 0; i < m; i++) {
            int mx = in.nextInt();
            devices[mx - 1].turn();

            int currentConsumption = calculateConsumption(devices);
            if (currentConsumption > c) {
                System.out.println("Fuse was blown.");
                return;
            } else if (currentConsumption > maxConsumption)
                maxConsumption = currentConsumption;
        }

        System.out.println("Fuse was not blown.");
        System.out.println("Maximal consumed current was " + maxConsumption + " A.");
    }

    public static int calculateConsumption(Device[] devices) {
        int consumption = 0;
        for (Device d : devices) {
            consumption += d.getConsumption();
        }
        return consumption;
    }
}

class Device {
    int consumption = 0;
    boolean turnedOn = false;

    Device(int maxConsumption) {
        consumption = maxConsumption;
    }

    void turn() {
        turnedOn = !turnedOn;
    }

    int getConsumption() {
        return turnedOn ? consumption : 0;
    }
}
