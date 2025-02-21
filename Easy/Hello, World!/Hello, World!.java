import java.util.*;

class Solution {
    static class GeoLocation {
        String sign;
        String dd;
        String mm;
        String ss;
        String toString;

        GeoLocation(String coord) {
            this.sign = coord.substring(0, 1);
            this.dd = coord.length() == 7 ? coord.substring(1, 3) : coord.substring(1, 4);
            this.mm = coord.length() == 7 ? coord.substring(3, 5) : coord.substring(4, 6);
            this.ss = coord.length() == 7 ? coord.substring(5) : coord.substring(6);
            this.toString = coord;
        }
    }

    static class Capital {
        String name;
        GeoLocation lat;
        GeoLocation lon;
        String text;

        Capital(String name, GeoLocation lat, GeoLocation lon) {
            this.name = name;
            this.lat = lat;
            this.lon = lon;
            this.text = "";
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int m = Integer.parseInt(scanner.nextLine());
        
        List<Capital> capitals = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            String[] cng = scanner.nextLine().split(" ");
            capitals.add(new Capital(
                cng[0],
                new GeoLocation(cng[1]),
                new GeoLocation(cng[2])
            ));
        }

        for (int i = 0; i < n; i++) {
            capitals.get(i).text = scanner.nextLine();
        }

        for (int i = 0; i < m; i++) {
            String[] travelGeoloc = scanner.nextLine().split(" ");
            GeoLocation lat = new GeoLocation(travelGeoloc[0]);
            GeoLocation lon = new GeoLocation(travelGeoloc[1]);

            double minDistance = Double.POSITIVE_INFINITY;
            List<Integer> minIndices = new ArrayList<>();

            for (int a = 0; a < n; a++) {
                double distance = Math.round(calculD(capitals.get(a).lon, capitals.get(a).lat, lon, lat));

                if (distance < minDistance) {
                    minDistance = distance;
                    minIndices.clear();
                    minIndices.add(a);
                } else if (distance == minDistance) {
                    minIndices.add(a);
                }
            }

            StringBuilder answer = new StringBuilder();
            for (int j = 0; j < minIndices.size(); j++) {
                answer.append(capitals.get(minIndices.get(j)).text);
                if (j != minIndices.size() - 1) {
                    answer.append(" ");
                }
            }
            System.out.println(answer.toString());
        }
    }

    static double calculD(GeoLocation lon1, GeoLocation lat1, GeoLocation lon2, GeoLocation lat2) {
        double convertedLon1 = convertToRadian(lon1);
        double convertedLat1 = convertToRadian(lat1);
        double convertedLon2 = convertToRadian(lon2);
        double convertedLat2 = convertToRadian(lat2);

        return 2 * 6371 * Math.asin(Math.sqrt(
            Math.pow(Math.sin((convertedLat1 - convertedLat2) / 2), 2) +
            Math.cos(convertedLat2) * Math.cos(convertedLat1) *
            Math.pow(Math.sin((convertedLon1 - convertedLon2) / 2), 2)
        ));
    }

    static double convertToRadian(GeoLocation a) {
        // Convert to decimal degrees
        double c = rounddig(Integer.parseInt(a.dd) + 
            Double.parseDouble(a.mm) / 60 + 
            Double.parseDouble(a.ss) / 3600);
        
        // Change sign
        c = (a.sign.equals("W") || a.sign.equals("N")) ? -Math.abs(c) : Math.abs(c);
        
        // Convert to radians
        return (c * Math.PI) / 180;
    }

    static double rounddig(double i) {
        return Math.round(Math.pow(10, 5) * i) / Math.pow(10, 5);
    }
}
