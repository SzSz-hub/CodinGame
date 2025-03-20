import java.util.*;

class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        ArrayList<coordinate> objects = new ArrayList<>();
        int n = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        for (int i = 0; i < n; i++) {
            String m = in.nextLine();
            objects.add(parseObject(m));
        }
        coordinate myPos = new coordinate(0, 0);
        int myMoney = 50;

        for (char c : s.toCharArray()) {
            myPos.move(c);
            Iterator<coordinate> iterator = objects.iterator();
            while (iterator.hasNext()) {
                coordinate obj = iterator.next();
                if (myPos.isSame(obj)) {
                    if (obj instanceof enemy) {
                        if (((enemy)obj).isGoblin() && myMoney >= 50) {
                            myMoney -= 50;
                        } else {
                            System.out.println(myPos.x + " " + myPos.y + " " + myMoney + "G" + " " + ((enemy)obj).name);
                            return;
                        }
                    } else {
                        myMoney += ((money)obj).value;
                        iterator.remove(); // Safe removal using iterator
                    }
                }
            }
        }
        System.out.println("GameClear" + " " + myPos.x + " " + myPos.y + " " + myMoney + "G");
    }

    public static coordinate parseObject(String line){
        String[] parts = line.split(" ");
        if (parts[2].equals("enemy")){
            return new enemy(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), parts[3]);
        }
        return new money(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), parts[3]);
    }
}

class coordinate {
    int x;
    int y;

    public coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isSame(coordinate c){
        return x == c.x && y == c.y;
    }

    public void move(char direction){
        this.x += direction == 'U' ? -1 : direction == 'D' ? 1 : 0;
        this.y += direction == 'L' ? -1 : direction == 'R' ? 1 : 0;
    }
}

class enemy extends coordinate{
    String name;

    public enemy(int x, int y, String name){
        super(x, y);
        this.name = name;
    }
    public boolean isGoblin(){
        return name.equals("goblin");
    }
}

class money extends  coordinate{
    int value;

    public money(int x, int y, String value){
        super(x, y);
        this.value = Integer.parseInt(value.replace("G", ""));
    }
}