import java.util.*;

class Solution {

    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            List<Coin> coins = new ArrayList<>();
            int valueToReach = in.nextInt();
            
            int N = in.nextInt();
            if (in.hasNextLine()) {
                in.nextLine();
            }

            String count = in.nextLine();
            String values = in.nextLine();

            String[] countArray = count.split(" ");
            String[] valuesArray = values.split(" ");
            for (int i = 0; i < N; i++) {
                coins.add(new Coin(Integer.parseInt(valuesArray[i]), Integer.parseInt(countArray[i])));
            }
            Collections.sort(coins);

            System.out.println(minCoins(coins, valueToReach));
        }
    }

    public static int minCoins(List<Coin> coins, int valueToReach) {
        int coinsUsed = 0;
        for (Coin coin : coins) {
            if (valueToReach == coin.getTotalValue()) {
                return coinsUsed + coin.count;
            }
            if (valueToReach < coin.getTotalValue()) {
                return coinsUsed + coin.getCoinsNeeded(valueToReach);
            }
            coinsUsed += coin.count;
            valueToReach -= coin.getTotalValue();
        }
        return -1;
    }
}

class Coin implements Comparable<Coin> {
    int value;
    int count;

    Coin(int value, int count) {
        this.value = value;
        this.count = count;
    }

    int getTotalValue() {
        return value * count;
    }

    int getCoinsNeeded(int target) {
        return Math.ceil((double) target / value) <= count ? (int) Math.ceil((double) target / value) : count;
    }

    @Override
    public int compareTo(Coin other) {
        return Integer.compare(this.value, other.value);
    }
}
