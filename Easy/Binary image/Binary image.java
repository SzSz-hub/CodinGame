import java.util.*;

class Solution {

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        int length = 0;

        Scanner in = new Scanner(System.in);
        int h = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }

        for (int i = 0; i < h; i++) {
            String row = in.nextLine();
            String decodedRow = decode(row);
            sb.append(decodedRow).append("\n");
            if (length == 0) {
                length = decodedRow.length();
            } else if (length != decodedRow.length()) {
                System.out.println("INVALID");
                return;
            }
        }
        in.close();

        System.out.print(sb.toString());
    }

    public static String decode(String row) {
        StringBuilder sb = new StringBuilder();
        String[] arr = row.split(" ");

        for (int i = 0; i < arr.length; i++) {
            int count = Integer.parseInt(arr[i]);
            if (i % 2 == 0)
                sb.append(".".repeat(count));
            else
                sb.append("O".repeat(count));

        }
        return sb.toString();
    }
}
