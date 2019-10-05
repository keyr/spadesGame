import java.util.Arrays;
import java.util.List;

public class Solver {
    public static Player whoWins(List<Player> players, List<String> cards) {
        Player winner = null;
        int highestSoFar = 0;
        String suit = null;
        String[] nums = {"2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A"};
        for (int i = 0; i < cards.size(); ++i) {
            String[] strength = cards.get(i).split(" of ");
            if (suit == null) {
                winner = players.get(i);
                highestSoFar = Arrays.asList(nums).indexOf(strength[0]);
                suit = strength[1];
            } else if (suit.equals(strength[1])) {
                if (Arrays.asList(nums).indexOf(strength[0]) > highestSoFar) {
                    winner = players.get(i);
                    highestSoFar = Arrays.asList(nums).indexOf(strength[0]);
                }
            } else if (!suit.equals("Spades") && strength[1].equals("Spades")) {
                winner = players.get(i);
                suit = "Spades";
                highestSoFar = Arrays.asList(nums).indexOf(strength[0]);
            }
        }
        winner.win();
        System.out.println(winner.getName() + " has won this trick! They currently have won " +
        winner.getWon() + " trick(s).");
        return winner;
    }

    public static int howMuchToBid(Player player, int pointTotal) {
        double strength = 0;
        for (String card : player.getHand()) {
            String[] cardStrength = card.split(" of ");
            if ((cardStrength[0].equals("T") || cardStrength[0].equals("J") || 
            cardStrength[0].equals("Q")) && cardStrength[1].equals("Spades")) {
                strength += 1;
            } else if (cardStrength[0].equals("T") || cardStrength[0].equals("J") || 
            cardStrength[0].equals("Q")) {
                strength += 0.25;
            } else if (cardStrength[0].equals("K") || cardStrength[0].equals("A")) {
                strength += 1;
            } else if (cardStrength[1].equals("Spades") && Integer.valueOf(cardStrength[0]) >= 6){
                strength += 0.5;
            }
        }
        if (strength > 4 && pointTotal - player.getPoints() > 40) {
            strength -= 1;
        }
        return (int) (strength);
        
    }
    public static String playBestCard(Player player, List<String> valid, String suit) {
        if (Math.random() <= 0.35 || player.getWon() == player.getBid()) {
            return valid.get((int) Math.random() * valid.size());
        } else {
            int bestIndex = 0;
            int highestNum = 0;
            String[] nums = {"2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A"};
            for (int i = 0; i < valid.size(); ++i) {
                String[] card = valid.get(i).split(" of ");
                int high = Arrays.asList(nums).indexOf(card[0]);
                if (card[1].equals(suit)) {
                    if (high > highestNum) {
                        bestIndex = i;
                        highestNum = high;
                    }
                } else if (card[1].equals("Spades")) {
                    suit = "Spades";
                    bestIndex = i;
                    highestNum = high;
                }
            }
            return valid.get(bestIndex);
        }
    }
}