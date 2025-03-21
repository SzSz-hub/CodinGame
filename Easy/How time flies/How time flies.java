import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate begin = LocalDate.parse(in.nextLine(), formatter);
        LocalDate end = LocalDate.parse(in.nextLine(), formatter);
        in.close();

        Period period = Period.between(begin, end);
        long totalDays = end.toEpochDay() - begin.toEpochDay();

        StringBuilder result = new StringBuilder();

        if (period.getYears() > 0) {
            result.append(period.getYears())
                    .append(" year")
                    .append(period.getYears() > 1 ? "s" : "")
                    .append(", ");
        }

        if (period.getMonths() > 0) {
            result.append(period.getMonths())
                    .append(" month")
                    .append(period.getMonths() > 1 ? "s" : "")
                    .append(", ");
        }

        result.append("total ")
                .append(totalDays)
                .append(" days");

        System.out.println(result);
    }
}