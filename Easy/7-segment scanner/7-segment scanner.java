import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Read the three lines representing the 7-segment display
        String line1 = scanner.nextLine();
        String line2 = scanner.nextLine();
        String line3 = scanner.nextLine();
        
        scanner.close();
        
        int digitsCount = line1.length() / 3;
        StringBuilder result = new StringBuilder();
        
        // Create a map of 7-segment patterns to their corresponding digits
        Map<String, Character> patterns = new HashMap<>();
        patterns.put(" _ | ||_|", '0');
        patterns.put("     |  |", '1');
        patterns.put(" _  _||_ ", '2');
        patterns.put(" _  _| _|", '3');
        patterns.put("   |_|  |", '4');
        patterns.put(" _ |_  _|", '5');
        patterns.put(" _ |_ |_|", '6');
        patterns.put(" _   |  |", '7');
        patterns.put(" _ |_||_|", '8');
        patterns.put(" _ |_| _|", '9');
        
        // Process each digit
        for (int i = 0; i < digitsCount; i++) {
            int start = i * 3;
            int end = start + 3;
            
            // Extract the 3x3 pattern for this digit
            String segment1 = line1.substring(start, end);
            String segment2 = line2.substring(start, end);
            String segment3 = line3.substring(start, end);
            
            // Combine all three parts to form the complete pattern
            String pattern = segment1 + segment2 + segment3;
            
            // Look up the digit and add it to the result
            result.append(patterns.get(pattern));
        }
        
        System.out.println(result.toString());
    }
}
