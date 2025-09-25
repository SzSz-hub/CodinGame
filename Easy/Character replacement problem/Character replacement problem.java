import java.util.*;

class Solution {
    private static Map<Character, Character> ruleMap = new HashMap<>();

    public static void main(String args[]) {
        try (Scanner in = new Scanner(System.in)) {
            String s = in.nextLine();

            try {
                processRules(s);
            } catch (IllegalArgumentException e) {
                System.out.println("ERROR");
                return;
            }

            int n = in.nextInt();
            for (int i = 0; i < n; i++) {
                String m = in.next();
                System.out.println(applyRule(m));
            }
        }
    }

    private static String applyRule(String line) {
        for (char c : line.toCharArray()) {
            if (ruleMap.containsKey(c)) {
                line = line.replace(c, ruleMap.get(c));
            }
        }
        return line;
    }

    private static void processRules(String line) throws IllegalArgumentException {
        String[] split = line.split(" ");
        for (String s : split) {
            char from = s.charAt(0);
            char to = s.charAt(1);

            if (from == to)continue;

            if (ruleMap.containsKey(from) || (ruleMap.containsKey(to) && ruleMap.get(to) == from))
                throw new IllegalArgumentException("Rule issue");

            for (Map.Entry<Character, Character> entry : ruleMap.entrySet()) {
                if (entry.getValue() == from)
                    if (ruleMap.containsKey(to))
                        ruleMap.put(entry.getKey(), ruleMap.get(to));
                    else
                        ruleMap.put(entry.getKey(), to);
            }

            if (ruleMap.containsKey(to))
                ruleMap.put(from, ruleMap.get(to));
            else
                ruleMap.put(from, to);

        }
    }
}
