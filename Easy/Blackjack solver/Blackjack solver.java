import java.util.*;

public class Solution  {
    private static final List<String> FACE_CARDS = List.of("J", "Q", "K", "10");
    private static final int BLACKJACK_VALUE = 21;
    private static final int ACE_HIGH_VALUE = 11;
    private static final int ACE_LOW_VALUE = 1;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String[] bankCards = scanner.nextLine().split(" ");
            String[] playerCards = scanner.nextLine().split(" ");
            
            System.out.println(evaluateWinner(bankCards, playerCards));
        }
    }

    private static int calculateHandValue(String[] cards) {
        int value = 0;
        int aceCount = 0;
        
        for (String card : cards) {
            if (FACE_CARDS.contains(card)) 
                value += 10;
             else if (card.equals("A")) 
                aceCount++;
             else 
                value += Integer.parseInt(card);
        }

        for (int i = 0; i < aceCount; i++)
            value += (value + ACE_HIGH_VALUE <= BLACKJACK_VALUE) ? ACE_HIGH_VALUE : ACE_LOW_VALUE;

        return value;
    }

    private static boolean isNaturalBlackjack(String[] cards) {
        if (cards.length != 2)
            return false;
        
        return (cards[0].equals("A") && FACE_CARDS.contains(cards[1])) ||
               (cards[1].equals("A") && FACE_CARDS.contains(cards[0]));
    }

    public static String evaluateWinner(String[] bankCards, String[] playerCards) {
        boolean bankHasBlackjack = isNaturalBlackjack(bankCards);
        boolean playerHasBlackjack = isNaturalBlackjack(playerCards);
        
        // Handle blackjack cases
        if (bankHasBlackjack && playerHasBlackjack) {
            return "Draw";
        }
        if (playerHasBlackjack) {
            return "Blackjack!";
        }
        
        int bankValue = calculateHandValue(bankCards);
        int playerValue = calculateHandValue(playerCards);
        
        // Handle bust cases
        if (playerValue > BLACKJACK_VALUE) {
            return "Bank";
        }
        if (bankValue > BLACKJACK_VALUE) {
            return "Player";
        }
        
        // Compare values
        if (playerValue == bankValue) {
            return "Draw";
        }
        return playerValue > bankValue ? "Player" : "Bank";
    }
}
