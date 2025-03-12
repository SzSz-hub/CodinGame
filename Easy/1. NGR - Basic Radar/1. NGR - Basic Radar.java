import java.util.*;

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        final float HOURinMILLISECONDS = 3_600_000f;
        SortedMap<String, Long> radar = new TreeMap<String, Long>();
        for (int i = 0; i < N; i++) {
            String plate = in.next();
            in.next();  //radar name, not needed as data coming in order
            long timestamp = in.nextLong();
            if (radar.containsKey(plate)) {
                radar.put(plate, timestamp - radar.get(plate));
            } else
                radar.put(plate, timestamp);
        }
        in.close();

        for (var entry : radar.entrySet()) {
            int speed = (int)(13 / (entry.getValue() / HOURinMILLISECONDS));
            if (speed > 130)
                System.out.println(entry.getKey() + " " + speed);
        }
    }
}
