import java.util.*;

class Solution {
    static final Color BORDER = new Color((short) 0, (short) 0, (short) 0);
    static final Color BACKGROUND = new Color((short) 255, (short) 255, (short) 255);

    public static void main(String args[]) {
        List<Shape> shapes = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        int S = in.nextInt();
        int P = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        for (int i = 0; i < S; i++) {
            String[] line = in.nextLine().split(" ");
            Color color = new Color(Short.valueOf(line[4]), Short.valueOf(line[5]), Short.valueOf(line[6]));
            Shape shape = new Shape(line[0], line[1], line[2], line[3], color);
            shapes.add(shape);
        }
        for (int i = 0; i < P; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            System.out.println(getColor(shapes, x, y));
        }
        in.close();
    }

    public static Color getColor(List<Shape> shapes, int x, int y) {
        for (Shape s : shapes) {
            if (s.isOnBorder(x, y)) {
                return BORDER;
            }
        }

        int R = 0, G = 0, B = 0, shapeCount = 0;

        for (Shape s : shapes) {
            if (s.isStrictlyInside(x, y)) {
                R += s.color.R;
                G += s.color.G;
                B += s.color.B;
                shapeCount++;
            }
        }

        if (shapeCount == 0) {
            return BACKGROUND;
        }

        return new Color(
                (short) Math.round((float) R / shapeCount),
                (short) Math.round((float) G / shapeCount),
                (short) Math.round((float) B / shapeCount));
    }
}

class Color {
    short R, G, B;

    Color(Short R, Short G, Short B) {
        this.R = R;
        this.G = G;
        this.B = B;
    }

    @Override
    public String toString() {
        return "(" + R + ", " + G + ", " + B + ")";
    }
}

class Shape {
    String type;
    short x, y, len;
    Color color;

    Shape(String type, String x, String y, String len, Color color) {
        this.type = type;
        this.x = Short.valueOf(x);
        this.y = Short.valueOf(y);
        this.len = Short.valueOf(len);
        this.color = color;
    }

    boolean isOnBorder(int x, int y) {
        if (type.equals("SQUARE")) {
            boolean onXEdge = (x == this.x || x == this.x + len);
            boolean onYEdge = (y == this.y || y == this.y + len);
            boolean withinXRange = (x >= this.x && x <= this.x + len);
            boolean withinYRange = (y >= this.y && y <= this.y + len);

            return (onXEdge && withinYRange) || (onYEdge && withinXRange);
        }
        if (type.equals("CIRCLE")) {
            double distanceSquared = Math.pow(x - this.x, 2) + Math.pow(y - this.y, 2);
            double radiusSquared = Math.pow(len, 2);
            return Math.abs(distanceSquared - radiusSquared) < 0.001;
        }
        return false;
    }

    boolean isStrictlyInside(int x, int y) {
        if (type.equals("SQUARE"))
            return x > this.x && y > this.y && x < this.x + len && y < this.y + len;
        if (type.equals("CIRCLE"))
            return Math.pow(x - this.x, 2) + Math.pow(y - this.y, 2) < Math.pow(len, 2);
        return false;
    }

    boolean isIn(int x, int y) {
        if (type.equals("SQUARE"))
            return x >= this.x && y >= this.y && x <= this.x + len && y <= this.y + len;
        if (type.equals("CIRCLE"))
            return Math.pow(x - this.x, 2) + Math.pow(y - this.y, 2) <= Math.pow(len, 2);
        return false;
    }
}
