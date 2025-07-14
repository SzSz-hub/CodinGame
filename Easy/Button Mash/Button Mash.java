import java.util.*;

class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int target = in.nextInt();
        in.close();

        Hashtable<Integer, Integer> steps = new Hashtable<>();
        Hashtable<Integer, Integer> newSteps = new Hashtable<>();
        newSteps.put(0, 0);

        while (true){
            Hashtable<Integer, Integer> currentLayer = new Hashtable<>(newSteps);
            newSteps.clear();
            for (var entry : currentLayer.entrySet()) {
                int key = entry.getKey();
                int value = entry.getValue();
                if (key == target) {
                    System.out.println(value);
                    return;
                }
                if (key + 1 <= target && (!steps.containsKey(key + 1) || steps.get(key + 1) > value + 1)){
                    newSteps.put(key + 1, value + 1);
                }
                if(key - 1 >= 0 && (!steps.containsKey(key - 1) || steps.get(key - 1) > value + 1)){
                    newSteps.put(key - 1, value + 1);
                }
                if(key * 2 <= target * 2 && (!steps.containsKey(key * 2) || steps.get(key * 2) > value + 1)){
                    newSteps.put(key * 2, value + 1);
                }
            }
            steps.putAll(currentLayer);
        }
    }
}