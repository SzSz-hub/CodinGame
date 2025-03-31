import java.util.Scanner;

class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        in.nextInt();
        String packet = in.next();
        in.close();

        int pointer = 0;
        while (pointer < packet.length()) {
            String instructionID = packet.substring(pointer, pointer + 3);
            String itemIDLength = packet.substring(pointer + 3, pointer + 7);
            int itemIDDecimalLength = convertToDecimal(itemIDLength);
            String packetInfo = packet.substring(pointer + 7, pointer + 7 + itemIDDecimalLength);
            if (instructionID.equals("101")) {
                System.out.print("001" + itemIDLength + packetInfo);
            }
            pointer += itemIDDecimalLength + 7;
        }
    }

    public static int convertToDecimal(String binary) {
        return Integer.parseInt(binary, 2);
    }
}