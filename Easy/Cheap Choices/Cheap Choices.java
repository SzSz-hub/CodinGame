import java.util.*;

public class Solution {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int itemCount = scanner.nextInt();
            int orderCount = scanner.nextInt();
            scanner.nextLine();
            
            List<Item> items = new ArrayList<>();
            for (int i = 0; i < itemCount; i++) {
                String[] parts = scanner.nextLine().split(" ");
                items.add(new Item(parts[0], parts[1], Integer.parseInt(parts[2])));
            }
            Collections.sort(items);
            
            for (int i = 0; i < orderCount; i++) {
                String[] parts = scanner.nextLine().split(" ");
                String itemName = parts[0];
                String itemSize = parts[1];
                
                boolean found = false;
                Iterator<Item> iterator = items.iterator();
                while (iterator.hasNext()) {
                    Item item = iterator.next();
                    if (item.name.equals(itemName) && item.size.equals(itemSize)) {
                        System.out.println(item.price);
                        iterator.remove();
                        found = true;
                        break;
                    }
                }
                
                if (!found) {
                    System.out.println("NONE");
                }
            }
        }
    }
}

class Item implements Comparable<Item> {
    String name;
    String size;
    int price;
    
    public Item(String name, String size, int price) {
        this.name = name;
        this.size = size;
        this.price = price;
    }
    
    @Override
    public int compareTo(Item other) {
        int nameComparison = this.name.compareTo(other.name);
        if (nameComparison != 0) {
            return nameComparison;
        }
        
        int sizeComparison = this.size.compareTo(other.size);
        if (sizeComparison != 0) {
            return sizeComparison;
        }
        
        return Integer.compare(this.price, other.price);
    }
}
