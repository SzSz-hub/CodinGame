import java.util.Scanner;

public class Solution {
    private static final int ROWS = 25;
    private static final int COLS = 19;
    private static final String GRASS = "{}";
    private static final String EMPTY = "  ";
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[][] matrix = initializeMatrix();
        
        String[] instructions = scanner.nextLine().split(" ");
        scanner.close();

        
        for (String instruction : instructions) {
            processInstruction(matrix, instruction);
        }
        
        printMatrix(matrix);
    }
    
    private static String[][] initializeMatrix() {
        String[][] matrix = new String[ROWS][COLS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                matrix[i][j] = GRASS;
            }
        }
        return matrix;
    }
    
    private static void processInstruction(String[][] matrix, String instruction) {
        ActionType action = getActionType(instruction);
        instruction = removeActionPrefix(instruction);
        
        int centerX = instruction.charAt(0) - 'a';
        int centerY = instruction.charAt(1) - 'a';
        int diameter = Integer.parseInt(instruction.substring(2));
        double radius = diameter / 2.0;
        
        applyActionToCircle(matrix, centerX, centerY, radius, action);
    }
    
    private static ActionType getActionType(String instruction) {
        if (instruction.contains("PLANTMOW")) return ActionType.PLANT_MOW;
        if (instruction.contains("PLANT")) return ActionType.PLANT;
        return ActionType.MOW;
    }
    
    private static String removeActionPrefix(String instruction) {
        return instruction.replace("PLANTMOW", "").replace("PLANT", "");
    }
    
    private static void applyActionToCircle(String[][] matrix, int centerX, int centerY, 
                                          double radius, ActionType action) {
        for (int y = 0; y < ROWS; y++) {
            for (int x = 0; x < COLS; x++) {
                double distance = calculateDistance(x, y, centerX, centerY);
                
                if (distance <= radius) {
                    matrix[y][x] = getNewCellValue(matrix[y][x], action);
                }
            }
        }
    }
    
    private static double calculateDistance(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }
    
    private static String getNewCellValue(String currentValue, ActionType action) {
        switch (action) {
            case PLANT:
                return GRASS;
            case PLANT_MOW:
                return currentValue.equals(EMPTY) ? GRASS : EMPTY;
            case MOW:
            default:
                return EMPTY;
        }
    }
    
    private static void printMatrix(String[][] matrix) {
        for (String[] row : matrix) {
            StringBuilder sb = new StringBuilder();
            for (String cell : row) {
                sb.append(cell);
            }
            System.out.println(sb.toString());
        }
    }
    
    private enum ActionType {
        PLANT, PLANT_MOW, MOW
    }
}
