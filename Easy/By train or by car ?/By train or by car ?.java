import java.util.*;

class Solution {
    private static Line[] lines;
    private static final int TOTRAINSTATION = 30;
    private static final int FROMTRAINSTATION = 35;
    private static final int TRAINCITYWAIT = 8;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String[] citySplit = in.nextLine().split(" ");
										  
        String departureCity = citySplit[0];
        String arrivalCity = citySplit[1];

        int N = in.nextInt();
        lines = new Line[N];
        if (in.hasNextLine()) in.nextLine();
        
		 
        for (int i = 0; i < N; i++) {
            String[] split = in.nextLine().split(" ");
											  
            lines[i] = new Line(split[0], split[1], Double.parseDouble(split[2]));
        }
        in.close();
        System.out.println(getShortestOption(departureCity, arrivalCity));
    }

    private static String getShortestOption(String start, String destination) {
        double car = 0;
        double train = TOTRAINSTATION + FROMTRAINSTATION;

        int lineId = getLineId(start);
        while (!lines[lineId].to.equals(destination)) {
            car += lines[lineId].minutesWithCar();
            train += lines[lineId].minutesWithTrains() + TRAINCITYWAIT;
            lineId = getLineId(lines[lineId].to);
        }
        car += lines[lineId].minutesWithCar();
        train += lines[lineId].minutesWithTrains();
        
						
        return car > train ? "TRAIN " + formatTime(train) : "CAR " + formatTime(car);
			
											
    }

    private static int getLineId(String station) {
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].from.equals(station)) return i;
						 
        }
        return -1;
    }

    private static String formatTime(double minutes) {
        int hours = (int) Math.floor(minutes / 60);
        int min = (int) Math.floor(minutes - hours * 60);
        return String.format("%d:%02d", hours, min);
    }
}

class Line {
    String from;
    String to;
    double distance;
    private static final int TRAINSPEED1 = 50;
    private static final int TRAINSPEED2 = 284;
    private static final int CARSPEED1 = 50;
    private static final int CARSPEED2 = 105;
    private static final double TRAINSPEED1DURATION = 3.0;
    private static final double CARSPEED1DURATION = 7.0;

    Line(String from, String to, double distance) {
        this.from = from;
        this.to = to;
        this.distance = distance;
    }

    double minutesWithTrains() {
        if (distance <= TRAINSPEED1DURATION * 2) {
            return distance * 60 / TRAINSPEED1;
        }
        return TRAINSPEED1DURATION * 2 * 60 / TRAINSPEED1 + (distance - TRAINSPEED1DURATION * 2) * 60 / TRAINSPEED2;
    }

    double minutesWithCar() {
        if (distance <= CARSPEED1DURATION * 2) {
            return distance * 60 / CARSPEED1;
        }
        return CARSPEED1DURATION * 2 * 60 / CARSPEED1 + (distance - CARSPEED1DURATION * 2) * 60 / CARSPEED2;
    }
}
