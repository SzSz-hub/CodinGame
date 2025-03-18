import java.util.*;

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        SpreadSheet[] spreadSheets = new SpreadSheet[N];
        for (int i = 0; i < N; i++) {
            String operation = in.next();
            String arg1 = in.next();
            String arg2 = in.next();
            spreadSheets[i] = new SpreadSheet(operation, arg1, arg2);
            spreadSheets[i].evaluate();
        }
        in.close();
        resolveReferences(spreadSheets);
        for (int i = 0; i < N; i++) {
            System.out.println(spreadSheets[i].result);
        }
    }

    public static void resolveReferences(SpreadSheet[] spreadSheets) {
        int unresolvedCount = 0;
        for (int i = 0; i < spreadSheets.length; i++) {
            if (!spreadSheets[i].isEvaluatable()) {
                if (spreadSheets[i].isNumber1Reference()
                        && spreadSheets[Integer.parseInt(spreadSheets[i].number1.substring(1))].isEvaluatable()) {
                    spreadSheets[i].number1 = spreadSheets[Integer
                            .parseInt(spreadSheets[i].number1.substring(1))].result + "";
                }
                if (spreadSheets[i].isNumber2Reference()
                        && spreadSheets[Integer.parseInt(spreadSheets[i].number2.substring(1))].isEvaluatable()) {
                    spreadSheets[i].number2 = spreadSheets[Integer
                            .parseInt(spreadSheets[i].number2.substring(1))].result + "";
                }
                if(!spreadSheets[i].isEvaluatable())
                    unresolvedCount++;
            }
            spreadSheets[i].evaluate();
        }
        if (unresolvedCount > 0)
            resolveReferences(spreadSheets);
    }
}

class SpreadSheet {
    public String operation;
    public String number1;
    public String number2;
    public int result;

    public SpreadSheet(String operation, String number1, String number2) {
        this.operation = operation;
        this.number1 = number1;
        this.number2 = number2;
    }

    public boolean isNumber1Reference() {
        return number1.contains("$");
    }

    public boolean isNumber2Reference() {
        return number2.contains("$");
    }

    public boolean isEvaluatable() {
        return !(isNumber1Reference() || isNumber2Reference());
    }

    public void evaluate() {
        if (!this.isEvaluatable())
            return;
        this.result = switch (operation) {
            case "VALUE" -> Integer.parseInt(number1);
            case "ADD" -> Integer.parseInt(number1) + Integer.parseInt(number2);
            case "SUB" -> Integer.parseInt(number1) - Integer.parseInt(number2);
            case "MULT" -> Integer.parseInt(number1) * Integer.parseInt(number2);
            default -> 0;
        };
    }
}
