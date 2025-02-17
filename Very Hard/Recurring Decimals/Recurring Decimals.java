import java.util.*;
import java.math.*;

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.close();

        StringBuilder sb = new StringBuilder();
        sb.append("0.");

        long dividend = 10;
        List<Long> dividentHistory = new ArrayList<>();
        dividentHistory.add(dividend);

        while (dividend != 0) {
            long newdividend = dividend % n * 10;
            if (dividentHistory.contains(newdividend)) {
                dividentHistory.add(newdividend);
                
                sb.insert(2 + dividentHistory.indexOf(newdividend), '(');
                sb.append(dividend / n);
                sb.append(')');
                break;
            }
            sb.append(dividend / n);
            dividentHistory.add(newdividend);
            dividend = newdividend;
        }
        
        if (dividend == 0)
            System.out.println(BigDecimal.ONE.divide(new BigDecimal(n)).toPlainString());
        else
            System.out.println(sb.toString());
    }
}
