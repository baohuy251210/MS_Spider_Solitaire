package spiders1;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HuyBTran
 */
public class Deck {
        
	private List<Card> cards;

	private int size;


	public Deck(String[] ranks, String[] suits, int[] values) {
		cards = new ArrayList<Card>();
		for (int j = 0; j < ranks.length; j++) {
			for (String suitString : suits) {
				cards.add(new Card(ranks[j], suitString, values[j]));
			}
		}
		size = cards.size();
		shuffle();
	}
        
        /**
        * stack manipulations
        */
        public Deck(){
                cards = new ArrayList<>();
                size = cards.size();
        }
        
        public void addCard(Card card){
                cards.add(card);
                size = cards.size();
        }
        public void removeCard(){
                size--;
                cards.remove(size);
        }
        public Card getCard(int posy){
                return cards.get(posy);
        }
        //IsEmpty ()
        //size()
        //lastCard();
        
        
        /**
         * perhaps that's it for stacking 
         */

        public Card lastCard(){
                return cards.get(size-1);
        }
        
	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	public void shuffle() {
		for (int k = cards.size() - 1; k > 0; k--) {
			int howMany = k + 1;
			int start = 0;
			int randPos = (int) (Math.random() * howMany) + start;
			Card temp = cards.get(k);
			cards.set(k, cards.get(randPos));
			cards.set(randPos, temp);
		}
		size = cards.size();
	}

        //get a Card out of Deck ==> remove
	public Card deal() {
		if (isEmpty()) {
			return null;
		}
		size--;
		Card c = cards.get(size);
		return c;
	}

	@Override
	public String toString() {
		String rtn = "size = " + size + "\nUndealt cards: \n";

		for (int k = size - 1; k >= 0; k--) {
			rtn = rtn + cards.get(k);
			if (k != 0) {
				rtn = rtn + ", ";
			}
			if ((size - k) % 2 == 0) {
				rtn = rtn + "\n";
			}
		}

		rtn = rtn + "\nDealt cards: \n";
		for (int k = cards.size() - 1; k >= size; k--) {
			rtn = rtn + cards.get(k);
			if (k != size) {
				rtn = rtn + ", ";
			}
			if ((k - cards.size()) % 2 == 0) {
				rtn = rtn + "\n";
			}
		}

		rtn = rtn + "\n";
		return rtn;
	}
}
