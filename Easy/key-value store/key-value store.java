import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Store store = new Store();
        int operationCount = sc.nextInt();
        sc.nextLine();

        while (operationCount-- > 0) {
            evaluate(sc.nextLine(), store);
        }
        sc.close();
    }

    private static void evaluate(String line, Store store) {
        String[] split = line.split("[ =]");
        switch (split[0]) {
            case "SET" -> {
                for (int i = 1; i < split.length; i = i + 2) {
                    store.set(split[i], split[i + 1]);
                }
            }
            case "GET" -> {
                for (int i = 1; i < split.length; i++) {
                    System.out.print(store.get(split[i]));
                    if (i < split.length - 1) {
                        System.out.print(" ");
                    }
                }
                System.out.println();
            }
            case "EXISTS" -> {
                for (int i = 1; i < split.length; i++) {
                    System.out.print(store.exists(split[i]));
                    if (i < split.length - 1) {
                        System.out.print(" ");
                    }
                }
                System.out.println();
            }
            case "KEYS" -> store.printKeys();
        }
    }
}

class Store {
    private final Map<String, String> store;

    Store() {
        store = new LinkedHashMap<>();
    }

    void set(String key, String value) {
        store.put(key, value);
    }

    String get(String key) {
        return store.get(key);
    }

    boolean exists(String key) {
        return store.containsKey(key);
    }

    void printKeys() {
        if (store.isEmpty()) {
            System.out.println("EMPTY");
        } else {
            int count = 0;
            int size = store.size();
            for (Map.Entry<String, String> entry : store.entrySet()) {
                System.out.print(entry.getKey());
                count++;
                if (count < size) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}
