import java.util.*;

class Solution {

public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        Map<Character, Integer> dic = new HashMap<>();
        dic.put('2', 4); dic.put('3', 4); dic.put('4', 4); dic.put('5', 4);
        dic.put('6', 4); dic.put('7', 4); dic.put('8', 4); dic.put('9', 4);
        dic.put('T', 4); dic.put('J', 4); dic.put('Q', 4); dic.put('K', 4);
        dic.put('A', 4);
        
        String streamOfConsciousness = scanner.nextLine();
        int bustThreshold = Integer.parseInt(scanner.nextLine());
        scanner.close();

        String[] splitted = streamOfConsciousness.split("\\.");
        
        for (String s : splitted) {
            boolean allCharsInDic = true;
            for (char c : s.toCharArray()) {
                if (!dic.containsKey(c)) {
                    allCharsInDic = false;
                    break;
                }
            }
            
            if (allCharsInDic) {
                for (char c : s.toCharArray()) {
                    dic.put(c, Math.max(dic.get(c) - 1, 0));
                }
            }
        }
        
        int total = 0;
        int good = 0;
        
        for (int value : dic.values()) {
            total += value;
        }
        
        for (int i = 2; i < bustThreshold; i++) {
            good += dic.get(Character.forDigit(i, 10));
        }
        good += dic.get('A');
        
        System.out.println(Math.round((good * 100.0) / total) + "%");        
    }
}
