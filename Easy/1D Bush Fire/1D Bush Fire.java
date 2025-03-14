import java.util.*;

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        for (int i = 0; i < N; i++) {
            String line = in.nextLine();
            System.out.println(dropCalculation(line));
        }
        in.close();
    }

    public static int dropCalculation(String line){
        int drops = 0;
        for(int i = 0; i < line.length(); i++){
            if(line.charAt(i) == 'f'){
                drops++;
                i += 2;
            }
        }
        return drops;
    }
}
