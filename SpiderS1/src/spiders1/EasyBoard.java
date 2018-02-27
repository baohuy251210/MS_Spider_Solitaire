package spiders1;

import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author HuyBTran
 */
public class EasyBoard extends Board{

        //each stack[i] is a deck.
        private Deck[] stack;
        
        //10 stacks
        private static final int BOARD_SIZE = 10;
        
        //double boards (104 cards)
        private static final String[] RANKS = 
        {"ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king",};
        
        //easy board ==> only spades
        private static final String[] SUITS = 
        {"spades"};
        
        //
        private static final int[] POINT_VALUES =
        {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        
       
       
        
        public EasyBoard() {
                super(BOARD_SIZE, RANKS, SUITS, POINT_VALUES);
        }
        public int getOrder(){
                return tempOrder;
        }

        private int tempOrder;
        @Override
        public boolean isLegal(List<Integer> selectedCards) {
                Integer selectedCard1 = selectedCards.get(0);
                Integer selectedCard2 = selectedCards.get(1);
                if (getCard(selectedCard1).IsLegalBelow(getCard(selectedCard2)))
                        tempOrder = 12;
                else if (getCard(selectedCard2).IsLegalBelow(getCard(selectedCard1)))
                        tempOrder = 21;
                return  getCard(selectedCard2).IsLegalBelow(getCard(selectedCard1))
                        ||
                        getCard(selectedCard1).IsLegalBelow(getCard(selectedCard2));
        }

        @Override
        public boolean anotherPlayIsPossible() {
                for (int i = 0; i < BOARD_SIZE; i++){
                        Card lasti = stack[i].lastCard();
                        for (int j = 0; j < BOARD_SIZE; j++){
                                Card lastj = stack[j].lastCard();
                                if (lastj.IsLegalBelow(lasti))
                                        return true;
                        }
                }
                return false;
        }
        
        public int AnyStackDone(){
                for (int i = 0; i < BOARD_SIZE; i++){
                        if ( stack[i].size() == 13 )
                                return i;
                }
                return -1;
        }
        
        /**
         * stack manipulations
         * @param card
         * @param position 
         */
        public void initStack(){
                stack = new Deck[10];
                for (int i = 0; i < 10; i++)
                        stack[i] = new Deck();
        }
        public void addCardinStack(Card card, int position){
                stack[position].addCard(card);
        }
        public void removeCardinStack(int position){
                stack[position].removeCard();
        }
        public Card getCard(int position){
                return stack[position].lastCard();
        }
        public Card getCard(int posx, int posy){
                return stack[posx].getCard(posy);
        }
        public int SizeofStack(int position){
                return stack[position].size();
        }
        /*
        
        */
        
}
