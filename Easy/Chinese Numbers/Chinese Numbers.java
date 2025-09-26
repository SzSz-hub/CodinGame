import java.util.*;

class Solution {
    private static final List<String> line3Pattern = Arrays.asList("0***0", "00000", "*****", "*000*", "00*00", "*0000",
            "00000", "00000", "*0*0*", "0000*");
    private static final String line4_1 = "*****";
    private static final String line4_6 = "*0*0*";

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            scanner.nextLine(); // line1 ignored
            scanner.nextLine(); // line2 ignored
            String line3 = scanner.nextLine();
            String line4 = scanner.nextLine(); // 1,6,7 have same line3
            scanner.nextLine(); // line5 ignored

            String[] line3parts = line3.split(" ");
            String[] line4parts = line4.split(" ");
            for (int i = 0; i < line3parts.length; i++) {
                if (line3parts[i].equals("00000"))
                    if(line4parts[i].equals(line4_1))
                        System.out.print(1);
                    else if(line4parts[i].equals(line4_6))
                        System.out.print(6);
                    else
                        System.out.print(7);
                else
                    System.out.print(line3Pattern.indexOf(line3parts[i]));
            }
        }
    }
}
