import java.util.ArrayList;
import java.util.List;

class Player {
    private String name;
    private List<String> hand;
    private int points;
    private int sandbags;
    private int bid;
    private int won;
    private List<String> hearts;
    private List<String> clubs;
    private List<String> diamonds;
    private List<String> spades;
    public Player(String name) {
        this.name = name;
        this.hand = null;
        this.points = 0;
        this.sandbags = 0;
        this.bid = 0;
        this.won = 0;
    }
    public void seeHand() {
        System.out.print("This is your hand: ");
        for (int i = 0; i < hand.size(); ++i) {
            System.out.print(hand.get(i));
            if (i != hand.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("\n");
    }
    public List<String> validCards(String suit, boolean spadesPlayed) {
        if (suit == null && spadesPlayed) {
            return this.hand;
        } else if (suit == null) {
            List<String> valid = new ArrayList<>();
            valid.addAll(this.hearts);
            valid.addAll(this.clubs);
            valid.addAll(this.diamonds);
            return valid;
        }
        if (suit.equals("Hearts")) {
            if (this.hearts.isEmpty()) {
                return this.hand;
            }
            return this.hearts;
        } else if (suit.equals("Clubs")) {
            if (this.clubs.isEmpty()) {
                return this.hand;
            }
            return this.clubs;
        } else if (suit.equals("Diamonds")) {
            if (this.diamonds.isEmpty()) {
                return this.hand;
            }
            return this.diamonds;
        } else if (suit.equals("Spades")) {
            if (this.spades.isEmpty()) {
                return this.hand;
            }
            return this.spades;
        }
        return null;
    }

    public void seeValidCards(String suit, boolean spadesPlayed) {
        List<String> validCards = validCards(suit, spadesPlayed);
        System.out.print("This is your hand of valid cards: ");
        for (int i = 0; i < validCards.size(); ++i) {
            System.out.print("(" + (i + 1) + ") " + validCards.get(i));
            if (i != validCards.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("\n");
    }

    public void playsCard(String card) {
        System.out.println(this.name + " (" + this.bid + " bids, " + this.won + " wins, " + this.sandbags + " sandbags) has played " + card + "!\n");
        this.hand.remove(card);
        if (card.endsWith("Hearts")) {
            this.hearts.remove(card);
        } else if (card.endsWith("Clubs")) {
            this.clubs.remove(card);
        } else if (card.endsWith("Diamonds")) {
            this.diamonds.remove(card);
        } else {
            this.spades.remove(card);
        }
    }

    public void win() {
        ++this.won;
    }

    public void bid(int bid) {
        this.bid = bid;
        System.out.println(this.name + " has made a bid of " + this.bid + " tricks");
    }
    public void presentPoints() {
        if (this.won < this.bid) {
            points -= this.bid * 10;
        } else if (this.won == this.bid) {
            points += this.bid * 10;
        } else {
            points += (this.won - this.bid) + this.bid * 10;
            this.sandbags += (this.won - this.bid);
            if (this.sandbags >= 10) {
                System.out.println("Whoops, " + this.name + " made a small calculation error and lost 100 points.");
                this.points -= 100;
                this.sandbags -= 10;
            }
        }
        System.out.println(this.name + " has " + this.points + " points!");
    }
    public int getWon() {
        return this.won;
    }
    public int getPoints() {
        return this.points;
    }
    public void receiveHand(List<String> cards) {
        this.hand = cards;
        this.hearts = new ArrayList<>();
        this.clubs = new ArrayList<>();
        this.diamonds = new ArrayList<>();
        this.spades = new ArrayList<>();
        for (String card : cards) {
            if (card.endsWith("Hearts")) {
                hearts.add(card);
            } else if (card.endsWith("Clubs")) {
                clubs.add(card);
            } else if (card.endsWith("Diamonds")) {
                diamonds.add(card);
            } else {
                spades.add(card);
            }
        }
    }

    public List<String> getHand() {
        return this.hand;
    }

    public int getBid() {
        return this.bid;
    }

    public void clear() {
        this.bid = 0;
        this.won = 0;
    }

    public String getName() {
        return this.name;
    }
}