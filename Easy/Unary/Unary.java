import java.util.*;

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        var message = in.nextLine().toCharArray();
        in.close();

        char previous = '2';
        for (char character : message) {
            for (char current : String.format("%7s", Integer.toBinaryString(character)).replace(' ', '0')
                    .toCharArray()) {
                if (previous == current) {
                    System.out.print('0');
                } else {
                    if (previous != '2')
                        System.out.print(' ');

                    System.out.print(current == '1' ? "0 0" : "00 0");
                    previous = current;
                }
            }
        }
    }
}
