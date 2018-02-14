package spiders1;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HuyBTran
 */
public abstract class Board {
        
        
        private Card[] cards;
        private Deck deck;
        private int score, moves;

        public Board(int size, String[] ranks, String[] suits, int[] pointValues) {
                score = 500;
                moves = 0;
                cards = new Card[size];
                deck = new Deck(ranks, suits, pointValues);
                dealMyCards();
        }
        
        public void newGame(){
                score = 500;
                moves = 0;
                deck.shuffle();
                dealMyCards();
        }
        public int size(){
                return cards.length;
        }
        public int score(){
                return score;
        }
        public int moves(){
                return moves;
        }
        
        public boolean isEmpty(){
                for (int k = 0; k < cards.length; k++){
                        if (cards[k]!= null)
                                return false;
                }
                return true;
        }
        public void deal(int k){
                cards[k] = deck.deal();
        }
        
        public int deckSize(){
                return deck.size();
        }
        public Card cardAt(int k){
                return cards[k];
        }
        
        public void replaceSelectedCards(List<Integer> selectedCards){
                for (Integer k : selectedCards) {
			deal(k);
		}
        }
        
        public List<Integer> cardIndexes(){
                List<Integer> selected = new ArrayList<>();
                for (int k = 0; k < cards.length; k++)
                        if (cards[k] != null)
                                selected.add(k);
                return selected;
        }
        
        @Override
        public String toString(){
                String s= "";
                for (int k = 0; k < cards.length; k++){
                        s = s + k + ": " + cards[k] + "\n";
                }
                return s;
        }
        
        
        public boolean gameIsWon(){
                if (deck.isEmpty()){
                        for (Card c : cards){
                                if (c != null)
                                        return false;
                        }
                        return true;
                }
                return false;
        }
        
        private void dealMyCards() {
		for (int k = 0; k < cards.length; k++) {
			cards[k] = deck.deal();
		}
	}
        
        public abstract boolean isLegal(List<Integer> selectedCards);
        public abstract boolean anotherPlayIsPossible();
        
}
