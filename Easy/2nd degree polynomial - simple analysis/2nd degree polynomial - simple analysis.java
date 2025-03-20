import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        float a = in.nextFloat();
        float b = in.nextFloat();
        float c = in.nextFloat();
        in.close();

        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, c));

        if (a == 0 && b == 0) {
        } else if (a == 0) {
            float x = -c / b;
            points.add(new Point(x, 0));
        } else {
            float delta = b * b - 4 * a * c;
            if (delta > 0) {
                float x1 = (-b - (float) Math.sqrt(delta)) / (2 * a);
                float x2 = (-b + (float) Math.sqrt(delta)) / (2 * a);
                points.add(new Point(x1, 0));
                points.add(new Point(x2, 0));
            } else if (delta == 0) {
                float x = -b / (2 * a);
                points.add(new Point(x, 0));
            }
        }

        points.sort((p1, p2) -> Float.compare(p1.x, p2.x));

        System.out.println(points.stream()
                .map(Point::toString)
                .collect(Collectors.joining(",")));
    }
}

class Point {
    public float x;
    public float y;

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return String.format("(%s,%s)",
                String.format("%.2f", x).replaceAll("\\.?0+$", ""),
                String.format("%.2f", y).replaceAll("\\.?0+$", ""));
    }
}