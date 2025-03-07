import java.util.*;

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        Double LON = Double.valueOf(in.next().replace(",", "."));
        Double LAT = Double.valueOf(in.next().replace(",", "."));
        System.err.println(LON + " " + LAT);
        int N = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        List<DefibrilatorStation> stations = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String DEFIB = in.nextLine();
            stations.add(new DefibrilatorStation(DEFIB));
        }
        in.close();

        DefibrilatorStation nearestStation = null;
        double minDistance = Double.MAX_VALUE;
        
        for (DefibrilatorStation station : stations) {
            double distance = station.distanceTo(LON, LAT);
            if (distance < minDistance) {
                minDistance = distance;
                nearestStation = station;
            }
        }
        
        System.out.println(nearestStation.name);

    }
}

class DefibrilatorStation{
    int ID;
    String name;
    String address;
    String phoneNumber;
    Double longtitude;
    double latitude;

    DefibrilatorStation(String defib){
        String[] splitted = defib.split(";");
        this.ID = Integer.valueOf(splitted[0]);
        this.name = splitted[1];
        this.address = splitted[2];
        this.phoneNumber = splitted[3];
        this.longtitude = Double.valueOf(splitted[4].replace(",", "."));
        this.latitude = Double.valueOf(splitted[5].replace(",", "."));
    }
    @Override
    public String toString(){
        return "Name: " + this.name + " Address: " + this.address + " Phone: " + this.phoneNumber + "location: " + this.longtitude + " " + this.latitude;
    }

    public double distanceTo(Double lon, Double lat){
        double x = (lon - this.longtitude) * Math.cos((lat + this.latitude) / 2);
        double y = lat - this.latitude;
        return Math.sqrt(x * x + y * y) * 6371;
    }
}
