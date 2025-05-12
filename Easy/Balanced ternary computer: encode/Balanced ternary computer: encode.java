import java.util.*;

class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        in.close();
        System.out.print(toBalancedTernary(N));
    }

    public static String toBalancedTernary(int number) {
        StringBuilder sb = new StringBuilder();

        while (number != 0) {
            int remainder = number % 3;

            if (remainder == 2) {
                sb.append('T');
                number = (number / 3) + 1;
            } else if (remainder == -2) {
                sb.append('1');
                number = (number / 3) - 1;
            } else {
                sb.append(remainder == -1 ? 'T' : remainder == 1 ? '1' : '0');
                number /= 3;
            }
        }

        return sb.length() == 0 ? "0" : sb.reverse().toString();
    }
}
