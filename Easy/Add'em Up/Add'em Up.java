import java.util.*;

class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            numbers.add(in.nextInt());
        }
        in.close();
        System.out.println(calculateCost(numbers));
    }

    private static int calculateCost(ArrayList<Integer> numbers) {
        int sum = 0;
        while (numbers.size() > 1) {
            Collections.sort(numbers);
            int first = numbers.get(0);
            int second = numbers.get(1);
            sum += first + second;
            numbers.remove(0);
            numbers.remove(0);
            numbers.add(first + second);
        }
        return sum;
    }
}