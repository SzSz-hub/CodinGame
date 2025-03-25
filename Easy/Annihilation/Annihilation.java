import java.util.*;

class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int H = in.nextInt();
        int W = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }

        HashSet<Ship> ships = new HashSet<>();
        for (int i = 0; i < H; i++) {
            String line = in.nextLine();
            for (int j = 0; j < line.length(); j++) {
                if (line.charAt(j) != '.') {
                    ships.add(new Ship(i, j, line.charAt(j), H, W));
                }
            }
        }
        in.close();

        int turns = 0;
        while (ships.size() > 1) {
            moveShips(ships);
            removeCollisions(ships);
            turns++;
        }
        System.out.println(turns);
    }

    public static void moveShips(HashSet<Ship> ships) {
        for (Ship ship : ships) {
            ship.move();
        }
    }

    public static void removeCollisions(HashSet<Ship> ships) {
        HashSet<Ship> toRemove = new HashSet<>();
        for (Ship ship : ships) {
            for (Ship other : ships) {
                if (ship != other && ship.SamePosition(other)) {
                    toRemove.add(ship);
                    toRemove.add(other);
                }
            }
        }
        for (Ship ship : toRemove) {
            ships.remove(ship);
        }
    }
}

class Ship {
    public int y;
    public int x;
    public int moveX;
    public int moveY;
    public int height;
    public int width;

    public Ship(int y, int x, char ship, int height, int width) {
        this.y = y;
        this.x = x;
        this.moveY = switch (ship) {
            case '^' -> -1;
            case 'v' -> 1;
            default -> 0;
        };
        this.moveX = switch (ship) {
            case '<' -> -1;
            case '>' -> 1;
            default -> 0;
        };
        this.height = height;
        this.width = width;
    }

    public void move() {
        this.y = (this.y + this.moveY + this.height) % this.height;
        this.x = (this.x + this.moveX + this.width) % this.width;
    }

    public boolean SamePosition(Ship other) {
        return this.y == other.y && this.x == other.x;
    }
}
