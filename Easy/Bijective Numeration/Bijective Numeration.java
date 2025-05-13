import java.util.*;

class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int total = 0;

        int count = in.nextInt();
        for (int i = 0; i < count; i++) {
            String decimary = in.next();
            total += decimaryToDecimal(decimary);
        }

        System.out.println(decimalToDecimary(total));
    }

    public static int decimaryToDecimal(String Decimary) {
        int result = 0;

        for (int i = Decimary.length() - 1; i >= 0; i--) {
            char ch = Decimary.charAt(i);
            if (ch == 'A')
                result += 10 * Math.pow(10, Decimary.length() - 1 - i);
            else
                result += Integer.parseInt(String.valueOf(ch)) * Math.pow(10, Decimary.length() - 1 - i);
        }
        return result;
    }

    private static String decimalToDecimary(int decimal) {
        StringBuilder result = new StringBuilder();
        
        while (decimal > 0) {
            int remainder = decimal % 10;
            decimal /= 10;
            
            if (remainder == 0) {
                result.insert(0, 'A');
                decimal--;
            } else {
                result.insert(0, remainder);
            }
        }
        
        return result.toString();
    }
}
