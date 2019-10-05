import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
	private List<String> deck = new ArrayList<>();
	public Deck() {
		char[] nums = {'A', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K'};
		String[] suits = {"Hearts", "Spades", "Diamonds", "Clubs"};
		for (int i = 0; i < nums.length; ++i) {
			for (int j = 0; j < suits.length; ++j) {
				StringBuilder card = new StringBuilder();
				card.append(nums[i]);
				card.append(" of ");
				card.append(suits[j]);
				deck.add(card.toString());
			}
		}
	}
	public void shuffle() {
		Collections.shuffle(deck);
	}
	public List<String> dealOut() {
		List<String> hand = new ArrayList<>();
		for (int i = 0; i < 13; ++i) {
			hand.add(deck.remove(0));
		}
		return hand;
	}
}