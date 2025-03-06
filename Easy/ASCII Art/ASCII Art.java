import java.util.*;
import java.util.stream.Collectors;

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        List<Character> abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ?".chars().mapToObj(c -> (char) c)
                .collect(Collectors.toList());
        int L = in.nextInt();
        int H = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        String T = in.nextLine();
        for (int i = 0; i < H; i++) {
            String row = in.nextLine();

            String result = "";
            for (char t : T.toCharArray()) {
                int index = abc.indexOf(Character.toUpperCase(t));
                if(index == -1)
                    result += row.substring(row.length()-L);
                else
                    result += row.substring(index * L, index * L + L);
            }
            System.out.println(result);
        }
        in.close();
    }
}
