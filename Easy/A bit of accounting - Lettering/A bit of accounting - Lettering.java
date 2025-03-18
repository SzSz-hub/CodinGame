import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int N = scanner.nextInt(); // Number of invoices
        int M = scanner.nextInt(); // Number of payment entries
        
        List<Integer> invoices = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            invoices.add(scanner.nextInt());
        }
        
        List<Integer> paymentEntries = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            paymentEntries.add(scanner.nextInt());
        }
        scanner.close();

        char letter = 'A';
        boolean[] usedInvoices = new boolean[N];
        
        for (int payment : paymentEntries) {
            List<Integer> matchedIndices = findSubsetSum(invoices, usedInvoices, payment);
            
            System.out.print(letter + " " + payment + " - ");
            
            for (int i = 0; i < matchedIndices.size(); i++) {
                int idx = matchedIndices.get(i);
                usedInvoices[idx] = true; 
                
                System.out.print(invoices.get(idx));
                if (i < matchedIndices.size() - 1) {
                    System.out.print(" ");
                }
            }
            
            System.out.println();
            letter++;
        }
    }
    
    private static List<Integer> findSubsetSum(List<Integer> invoices, boolean[] usedInvoices, int targetSum) {
        return findSubsetSumHelper(invoices, usedInvoices, targetSum, 0, new ArrayList<>());
    }
    
    private static List<Integer> findSubsetSumHelper(List<Integer> invoices, boolean[] usedInvoices, int targetSum, int currentIndex, List<Integer> currentIndices) {
        if (targetSum == 0) {
            return new ArrayList<>(currentIndices);
        }
        
        if (currentIndex >= invoices.size() || targetSum < 0) {
            return null;
        }
        
        if (!usedInvoices[currentIndex]) {
            currentIndices.add(currentIndex);
            List<Integer> include = findSubsetSumHelper(invoices, usedInvoices, targetSum - invoices.get(currentIndex), currentIndex + 1, currentIndices);
            if (include != null) {
                return include;
            }
            currentIndices.remove(currentIndices.size() - 1);
        }
        
        return findSubsetSumHelper(invoices, usedInvoices, targetSum, currentIndex + 1, currentIndices);
    }
}
