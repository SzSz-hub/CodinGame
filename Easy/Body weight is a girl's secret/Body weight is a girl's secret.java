import java.util.*;
import java.util.stream.Collectors;

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int[] weights = new int[10];
        int girlWeights[] = new int[5];
        int totalweight = 0;
        for (int i = 0; i < 10; i++) {
            weights[i] = in.nextInt();
            totalweight += weights[i];
        }
        in.close();
        totalweight = totalweight / 4;
        girlWeights[2] = totalweight - weights[0] - weights[9];
        girlWeights[0] = weights[1] - girlWeights[2];
        girlWeights[1] = weights[0] - girlWeights[0];
        girlWeights[4] = weights[8] - girlWeights[2];
        girlWeights[3] = weights[9] - girlWeights[4];

        System.out.println(Arrays.stream(girlWeights)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(" ")));
    }
}
