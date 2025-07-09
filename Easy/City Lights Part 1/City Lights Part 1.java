import java.util.*;

class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int h = in.nextInt();
        int w = in.nextInt();
        List<Light> lights = new ArrayList<>();

        if (in.hasNextLine()) {
            in.nextLine();
        }
        
        for (int i = 0; i < h; i++) {
            String s = in.nextLine();
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) != '.') {
                    int brightness = getRadiusFromChar(s.charAt(j));
                    lights.add(new Light(j, i, brightness));
                }
            }
        }

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int totalBrightness = 0;
                for (Light light : lights) {
                    double distance = Math.sqrt(Math.pow(x - light.x, 2) + Math.pow(y - light.y, 2));
                    int roundedDistance = (int) Math.round(distance);
                    totalBrightness += Math.max(0, light.brightness - roundedDistance);
                }
                System.out.print(getBrightnessChar(totalBrightness));
            }
            System.out.println();
        }
        
        in.close();
    }

    private static int getRadiusFromChar(char c) {
        if (c >= '1' && c <= '9') {
            return c - '0';
        } else if (c >= 'A' && c <= 'Z') {
            return c - 'A' + 10;
        }
        return 0;
    }

    private static char getBrightnessChar(int brightness) {
        if (brightness <= 9) {
            return (char) ('0' + brightness);
        } else if (brightness <= 35) {
            return (char) ('A' + brightness - 10);
        } else {
            return 'Z';
        }
    }
}

class Light {
    int x, y, brightness;

    Light(int x, int y, int brightness) {
        this.x = x;
        this.y = y;
        this.brightness = brightness;
    }
}