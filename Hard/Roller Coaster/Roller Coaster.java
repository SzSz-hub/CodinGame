import java.util.*;

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int L = in.nextInt(); // Available seats
        int C = in.nextInt(); // Round per day
        int N = in.nextInt(); // Number of groups
        long earning = 0;
        int[] queue = new int[N];
        int pointer = 0;
        HashMap<Integer, Cache> cache = new HashMap<>();

        for (int i = 0; i < N; i++) {
            int pi = in.nextInt();
            queue[i] = pi;
        }

        for (int i = 0; i < C; i++) {
            int remainingSeats = L;
            int groupOnRide = 0;
            int startPosition = pointer;
            int currentEarning = 0;

            if (cache.containsKey(pointer)) {
                var temp = cache.get(pointer);
                earning += temp.money;
                pointer = temp.endPosition;
                continue;
            }

            while (true) {
                if (queue[pointer] <= remainingSeats && groupOnRide < N) {
                    currentEarning += queue[pointer];
                    remainingSeats -= queue[pointer];
                    pointer = (pointer + 1) % N;
                    groupOnRide++;
                } else {
                    cache.put(startPosition, new Cache(currentEarning, pointer));
                    earning += currentEarning;
                    break;
                }
            }
        }
        System.out.println(earning);
    }
}

class Cache {
    int money;
    int endPosition;

    Cache(int money, int endPosition) {
        this.money = money;
        this.endPosition = endPosition;
    }
}
