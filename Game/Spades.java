import java.util.*;
public class Spades {
    private static Player player;
    private static Player doug;
    private static Player sasha;
    private static Player franklin;
    private static int pointTotal;
    private static Deck deck;
    private static Scanner input;
    public static void main(String[] args) {
        input = new Scanner(System.in);
        System.out.println("---------HELLO, WELCOME TO SPADES---------");
        System.out.println("Please enter your name: ");
        String name = input.next();
        System.out.println("\nHi " + name + "! We will be playing Spades. " +
        "\nBefore we begin, how many points would you like " +
        "to play up to? Note that a 500-point game can take at least 10 minutes! " + 
        "Enter a number: ");
        while (true) {
            pointTotal = Integer.valueOf(input.next().trim());
            if (pointTotal <= 0) {
                System.out.println("That is not a valid amount of points! Try again: ");
            } else {
                break;
            }
        }
        System.out.println("\nCool! We will begin playing a " + pointTotal + " points game!\n");
        player = new Player(name);
        doug = new Player("Doug");
        sasha = new Player("Sasha");
        franklin = new Player("Franklin");
        loop();
    }

    private static void loop() {
        deck = new Deck();
        deck.shuffle();
        player.receiveHand(deck.dealOut());
        doug.receiveHand(deck.dealOut());
        sasha.receiveHand(deck.dealOut());
        franklin.receiveHand(deck.dealOut());
        player.seeHand();
        System.out.println("Please make a bid of how many tricks you think you can win. Enter a number: ");
        int bid = 0;
        while (true) {
            bid = Integer.valueOf(input.next().trim());
            if (bid < 0 || bid > 13) {
                System.out.println("That is not a valid amount to bid! Try again: ");
            } else {
                break;
            }
        }
        player.bid(bid);
        doug.bid(Solver.howMuchToBid(doug, pointTotal));
        sasha.bid(Solver.howMuchToBid(sasha, pointTotal));
        franklin.bid(Solver.howMuchToBid(franklin, pointTotal));
        int i = 1;
        List<Player> playerOrder = new ArrayList<>();
        playerOrder.add(player);
        playerOrder.add(doug);
        playerOrder.add(sasha);
        playerOrder.add(franklin);
        boolean spadesPlayed = false;
        while (i <= 13) {
            System.out.println("\nThis is round " + i + ". Let's begin with " + playerOrder.get(0).getName() + ".\n");
            String suit = null;
            List<String> cards = new ArrayList<>();
            for (Player p : playerOrder) {
                if (p.equals(player)) {
                    System.out.println("What card would you like to play? Enter a number: ");
                    List<String> valid = p.validCards(suit, spadesPlayed);
                    player.seeValidCards(suit, spadesPlayed);
                    int x = 0;
                    while (true) {
                        x = Integer.valueOf(input.next().trim());
                        if (x <= 0 || x > valid.size()) {
                            System.out.println("That is not a valid card to play! Try again: ");
                        } else {
                            break;
                        }
                    }
                    String playedCard = valid.get(x - 1);
                    player.playsCard(playedCard);
                    cards.add(playedCard);
                    if (suit == null) {
                        suit = playedCard.split(" of ")[1];
                    }
                    if ((playedCard).endsWith("Spades")) {
                        spadesPlayed = true;
                    }
                } else {
                    List<String> valid = p.validCards(suit, spadesPlayed);
                    String playedCard = Solver.playBestCard(p, valid, suit);
                    p.playsCard(playedCard);
                    cards.add(playedCard);
                    if (suit == null) {
                        suit = playedCard.split(" of ")[1];
                    }
                    if ((playedCard).endsWith("Spades")) {
                        spadesPlayed = true;
                    }
                }
            }
            Player winner = Solver.whoWins(playerOrder, cards);
            while (!playerOrder.get(0).equals(winner)) {
                playerOrder.add(playerOrder.remove(0));
            }
            System.out.println("If you would like to check your (h)and, enter h! Otherwise, enter any other key.");
            while (true) {
                String x = input.next().trim();
                if (x.equals("h")) {
                    player.seeHand();
                } else {
                    break;
                }
            }
            ++i;
        }
        System.out.println('\n');
        player.presentPoints();
        doug.presentPoints();
        sasha.presentPoints();
        franklin.presentPoints();
        player.clear();
        doug.clear();
        sasha.clear();
        franklin.clear();
        System.out.println('\n');
        if (player.getPoints() >= pointTotal) {
            System.out.println(player.getName() + " has won! Congratulations to " + player.getName() + ".");
        } else if (doug.getPoints() >= pointTotal) {
            System.out.println(doug.getName() + " has won! Congratulations to " + doug.getName() + ".");
        } else if (sasha.getPoints() >= pointTotal) {
            System.out.println(sasha.getName() + " has won! Congratulations to " + sasha.getName() + ".");
        } else if (franklin.getPoints() >= pointTotal) {
            System.out.println(franklin.getName() + " has won! Congratulations to " + franklin.getName() + ".");
        } else {
            loop();
        }
        input.close();
    }
}