import java.util.*;

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String[] rotations = in.nextLine().split(" ");
        char face1 = in.nextLine().charAt(0);
        char face2 = in.nextLine().charAt(0);
        in.close();

        Map<Character, Character> faces = new HashMap<>() {
            {
                put('F', 'F');
                put('B', 'B');
                put('U', 'U');
                put('D', 'D');
                put('L', 'L');
                put('R', 'R');
            }
        };

        for (String rotation : rotations) {
            switch (rotation) {
                case "x'":
                    char temp = faces.get('F');
                    faces.put('F', faces.get('U'));
                    faces.put('U', faces.get('B'));
                    faces.put('B', faces.get('D'));
                    faces.put('D', temp);
                    break;
                case "x":
                    temp = faces.get('F');
                    faces.put('F', faces.get('D'));
                    faces.put('D', faces.get('B'));
                    faces.put('B', faces.get('U'));
                    faces.put('U', temp);
                    break;
                case "y'":
                    temp = faces.get('F');
                    faces.put('F', faces.get('L'));
                    faces.put('L', faces.get('B'));
                    faces.put('B', faces.get('R'));
                    faces.put('R', temp);
                    break;
                case "y":
                    temp = faces.get('F');
                    faces.put('F', faces.get('R'));
                    faces.put('R', faces.get('B'));
                    faces.put('B', faces.get('L'));
                    faces.put('L', temp);
                    break;
                case "z'":
                    temp = faces.get('U');
                    faces.put('U', faces.get('R'));
                    faces.put('R', faces.get('D'));
                    faces.put('D', faces.get('L'));
                    faces.put('L', temp);
                    break;
                case "z":
                    temp = faces.get('U');
                    faces.put('U', faces.get('L'));
                    faces.put('L', faces.get('D'));
                    faces.put('D', faces.get('R'));
                    faces.put('R', temp);
                    break;
            }
        }
        System.err.println(Arrays.toString(rotations) + " " + faces);
        printKeyFromValue(faces, face1);
        printKeyFromValue(faces, face2);
    }

    public static void printKeyFromValue(Map<Character, Character> map, char value) {
        for (Map.Entry<Character, Character> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                System.out.println(entry.getKey());
            }
        }
    }
}
