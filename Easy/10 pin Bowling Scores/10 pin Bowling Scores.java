import java.util.*;

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        for (int i = 0; i < N; i++) {
            String[] game = in.nextLine().replace("-", "0").split(" ");
            printScores(game);
            System.out.println();
        }
    }
  
    public static void printScores(String[] game) {
        int score = 0;
        for (int i = 0; i < game.length; i++){
            String prefix = i == 0 ? "" : " ";
            if (game[i].equals("X")){
                score += 10;
                if (i < game.length - 2 && game[i+1].equals("X") && game[i+2].charAt(0) == 'X')
                    score += 20;
                if (i < game.length - 2 && game[i+1].equals("X") && !(game[i+2].charAt(0) == 'X'))
                    score += 10 + Integer.parseInt(game[i+2].substring(0,1));
                if (i < game.length - 1 && game[i+1].length() == 2 && game[i+1].charAt(1) == '/')
                    score += 10;
                if (i < game.length - 1 && game[i+1].length() == 2 && game[i+1].charAt(1) != '/')
                    score += Integer.parseInt(game[i+1].substring(0,1)) + Integer.parseInt(game[i+1].substring(1));
                if (i < game.length - 1 && game[i+1].length() == 3){
                    score += game[i+1].charAt(0) == 'X' ? 10 : 0;
                    score += game[i+1].charAt(1) == 'X' || game[i+1].charAt(1) == '/' ? 10 : Integer.parseInt(game[i+1].substring(1,2));
                    score += game[i+1].charAt(0) != 'X' && game[i+1].charAt(1) != '/' ? Integer.parseInt(game[i+1].substring(2)) : 0;
                }
            } else if (game[i].charAt(1) == '/'){
                score += 10;
                if ( i == game.length - 1 && game[i].charAt(2) == 'X')
                    score += 10;
                if ( i == game.length - 1 && !(game[i].charAt(2) == 'X'))
                    score += Integer.parseInt(game[i].substring(2));
                if (i < game.length - 1 && game[i+1].charAt(0) == 'X')
                    score += 10;
                if (i < game.length - 1 && !(game[i+1].charAt(0) == 'X'))
                    score += Integer.parseInt(game[i+1].substring(0,1));
            } else if (game[i].length() == 3){
                score += 10;
                score += game[i].charAt(1) == 'X' ? 10 : 0;
                score += game[i].charAt(2) == 'X' || game[i].charAt(2) == '/' ? 10 : Integer.parseInt(game[i].substring(2));
            } else {
                score += Integer.parseInt(game[i].substring(0,1));
                score += Integer.parseInt(game[i].substring(1));
            }
            System.out.print(prefix + score);
        }
    }
}
