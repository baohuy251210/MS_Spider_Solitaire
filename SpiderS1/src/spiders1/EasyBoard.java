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
                
        }

        @Override
        public boolean anotherPlayIsPossible() {
        }
        
        
        
}
