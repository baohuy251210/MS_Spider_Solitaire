package spiders1;

import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author HuyBTran
 */
public class EasyBoard extends Board{

        private Deck[] stack = new Deck[10];
        
        private static final int BOARD_SIZE = 10;
        
        private static final String[] RANKS = 
        {"ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king"};
        
        private static final String[] SUITS = 
        {"spades"};
        
        private static final int[] POINT_VALUES =
        {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        
        
        public EasyBoard(int size, String[] ranks, String[] suits, int[] pointValues) {
                super(size, ranks, suits, pointValues);
        }


        @Override
        public boolean isLegal(List<Integer> selectedCards) {
                Integer selectedCard1 = selectedCards.get(0);
                Integer selectedCard2 = selectedCards.get(1);
                return cardAt(selectedCard1).IsLegalBelow(cardAt(selectedCard2));
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
        
}
