import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<Sphere> spheres = new ArrayList<>();
        int N = in.nextInt();
        for (int i = 0; i < N; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            int z = in.nextInt();
            int r = in.nextInt();
            spheres.add(new Sphere(x, y, z, r));
        }
        in.close();

        expandSpheres(spheres);
        System.out.println(sumOfRadianCubes(spheres));
    }

    public static void expandSpheres(List<Sphere> spheres) {
        for (int i = 0; i < spheres.size(); i++) {
            double minDistance = Double.MAX_VALUE;
            Sphere current = spheres.get(i);

            for (int j = 0; j < spheres.size(); j++) {
                if (i != j)
                    minDistance = Math.min(minDistance, current.distance(spheres.get(j)));
            }
            current.radius += minDistance;
        }
    }

    public static long sumOfRadianCubes(List<Sphere> spheres) {
        double sumOfRadianCubes = 0;
        for(var sphere : spheres) {
            sumOfRadianCubes += Math.pow(sphere.radius, 3);
        }
        return Math.round(sumOfRadianCubes);
    }
}

class Sphere {
    int x, y, z;
    double radius;

    Sphere(int x, int y, int z, int radius) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.radius = radius;
    }

    double distance(Sphere other) {
        double dx = other.x - x;
        double dy = other.y - y;
        double dz = other.z - z;
        return Math.sqrt(dx * dx + dy * dy + dz * dz) - (other.radius + radius);
    }
}