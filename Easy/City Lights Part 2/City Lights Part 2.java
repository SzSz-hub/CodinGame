import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int l = scanner.nextInt();
        int w = scanner.nextInt();
        int d = scanner.nextInt();
        int n = scanner.nextInt();
        scanner.nextLine();
        
        char[][][] grid = new char[d][w][l];
        List<LightSource> lightSources = new ArrayList<>();
        
        int currentDepth = 0;
        int currentRow = 0;
        
        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            
            if (line.trim().isEmpty()) {
                currentDepth++;
                currentRow = 0;
                continue;
            }
            
            for (int col = 0; col < line.length(); col++) {
                char c = line.charAt(col);
                grid[currentDepth][currentRow][col] = c;
                
                if (c != '.') {
                    int radius = getLightRadius(c);
                    lightSources.add(new LightSource(col, currentRow, currentDepth, radius));
                }
            }
            currentRow++;
        }
        
        char[][][] result = new char[d][w][l];
        
        for (int z = 0; z < d; z++) {
            for (int y = 0; y < w; y++) {
                for (int x = 0; x < l; x++) {
                    int totalBrightness = 0;
                    
                    for (LightSource source : lightSources) {
                        double distance = calculateEuclideanDistance3D(x, y, z, source.x, source.y, source.z);
                        int brightness = Math.max(0, source.radius - (int)Math.round(distance));
                        totalBrightness += brightness;
                    }
                    
                    result[z][y][x] = getBrightnessChar(totalBrightness);
                }
            }
        }
        
        for (int z = 0; z < d; z++) {
            if (z > 0) System.out.println();
            for (int y = 0; y < w; y++) {
                for (int x = 0; x < l; x++) {
                    System.out.print(result[z][y][x]);
                }
                System.out.println();
            }
        }
    }
    
    private static int getLightRadius(char c) {
        if (c >= '1' && c <= '9') {
            return c - '0';
        } else if (c >= 'A' && c <= 'Z') {
            return c - 'A' + 10;
        }
        return 0;
    }
    
    private static double calculateEuclideanDistance3D(int x1, int y1, int z1, int x2, int y2, int z2) {
        return Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2) + (z1-z2)*(z1-z2));
    }
    
    private static char getBrightnessChar(int brightness) {
        if (brightness <= 0) return '0';
        if (brightness <= 9) return (char)('0' + brightness);
        if (brightness <= 35) return (char)('A' + brightness - 10);
        return 'Z';
    }
    
    static class LightSource {
        int x, y, z, radius;
        
        LightSource(int x, int y, int z, int radius) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.radius = radius;
        }
    }
}
