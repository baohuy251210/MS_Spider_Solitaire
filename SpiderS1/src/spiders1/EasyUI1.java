package spiders1;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author .
 */
public class EasyUI1 extends JFrame implements ActionListener {

        /**
         * Creates new form EasyUI
         */
        int totalWins;
        int totalMatches;
        EasyBoard board;
        JButton[][] Btns;
        int[] last;
        Point[][] position;

        /**
         * kth element is true iff the user has selected card #k.
         */
        private boolean[] selections;

        public EasyUI1() {
                position = new Point[10][16];
                board = new EasyBoard();
                board.initStack();
                initComponents();
                selected = new ArrayList<>();
                selections = new boolean[10];
                isselected = new boolean[10][16];
                totalWins = 0;
                totalMatches = 0;
                board.setScore(500);
                board.setMoves(0);
                firstState();
        }

        public void firstState() {
                for (int i = 0; i < 10; i++) {
                        last[i] += 1; // increase last as I add more
                        Card c = board.cardAt(i);
                        board.addCardinStack(c, i);
                        Btns[i][last[i]].setIcon(new ImageIcon(getClass().getResource(imageFileName(c, false))));
                        Btns[i][last[i]].setVisible(true);
                }
        }

        private void initComponents() {

                MainPanel = new javax.swing.JPanel();
                BtnRestart = new javax.swing.JButton();
                BtnPlace = new javax.swing.JButton();
                BtnDeal = new JButton();

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
                MainPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
                MainPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                last = new int[10];
                Btns = new JButton[10][16];
                int y = 10;
                for (int i = 0; i < 4; i++) {
                        int x = 15 * 20;
                        last[i] = 4;
                        for (int j = 15; j >= 0; j--) {
                                Btns[i][j] = new JButton();
                                Btns[i][j].setIcon(new ImageIcon(getClass().getResource("/cards/back2.jpg"))); // NOI18N
                                Btns[i][j].setOpaque(false);
                                Btns[i][j].setVisible(false);
                                if (j < 5) {
                                        Btns[i][j].setVisible(true);
                                }
                                MainPanel.add(Btns[i][j], new org.netbeans.lib.awtextra.AbsoluteConstraints(y, x, 70, 100));
                                position[i][j] = new Point(x, y);
                                x -= 20;
                                //add actionListener
                                Btns[i][j].addActionListener(this);
                        }
                        y += 100;
                }
                for (int i = 4; i < 10; i++) {
                        int x = 15 * 20;
                        last[i] = 3;
                        for (int j = 15; j >= 0; j--) {
                                Btns[i][j] = new JButton();
                                Btns[i][j].setIcon(new ImageIcon(getClass().getResource("/cards/back2.jpg"))); // NOI18N
                                Btns[i][j].setOpaque(false);
                                Btns[i][j].setVisible(false);
                                if (j < 4) {
                                        Btns[i][j].setVisible(true);
                                }

                                MainPanel.add(Btns[i][j], new org.netbeans.lib.awtextra.AbsoluteConstraints(y, x, 70, 100));
                                position[i][j] = new Point(x, y);
                                x -= 20;
                                Btns[i][j].addActionListener(this);
                        }
                        y += 100;
                }

                BtnRestart.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
                BtnRestart.setText("Restart");
                MainPanel.add(BtnRestart, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 540, 100, 60));
                BtnRestart.addActionListener(this);

                BtnPlace.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
                BtnPlace.setText("Place");
                MainPanel.add(BtnPlace, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 540, 100, 60));
                BtnPlace.addActionListener(this);

                BtnDeal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
                BtnDeal.setText("Deal Cards");
                MainPanel.add(BtnDeal, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 540, 150, 60));
                BtnDeal.addActionListener(this);

                getContentPane().add(MainPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1110, 610));

                pack();
        }// </editor-fold>                        

        private String imageFileName(Card c, boolean isSelected) {
                String str = "/cards/";
                if (c == null) {
                        return "cards/back1.GIF";
                }
                str += c.rank() + c.suit();
                if (isSelected) {
                        str += "S";
                }
                str += ".GIF";
                return str;
        }

        /**
         * @param args the command line arguments
         */
        public static void main(String args[]) {

                /* Create and display the form */
                java.awt.EventQueue.invokeLater(new Runnable() {
                        public void run() {
                                new EasyUI1().setVisible(true);
                        }
                });
        }

        // Variables declaration                     
        private javax.swing.JButton BtnDeal;
        private javax.swing.JButton BtnPlace;
        private javax.swing.JButton BtnRestart;
        private javax.swing.JPanel MainPanel;

        // End of variables declaration                   
        @Override
        public void actionPerformed(ActionEvent e) {
                Object evt = e.getSource();
                if (evt.equals(BtnDeal)) {
                        BtnDealEvent();
                } else if (evt.equals(BtnPlace)) {
                        BtnPlaceEvent();
                } else if (evt.equals(BtnRestart)) {
                        BtnRestartEvent();
                } else {
                        CardsHandling(evt);
                }
        }

        private ArrayList<Card> selected;
        private boolean[][] isselected;

        private void CardsHandling(Object evt) {
                boolean needTwo = false;
                int posi = -1, posj = -1;
                /*
                 last card Only handling
                 */
                for (int i = 0; i < Btns.length; i++) {
                        int j = last[i];
                        if (j < 0) {
                                continue; //escape the error when 1 stack is empty
                        }
                        if (evt.equals(Btns[i][j])) {
                                posi = i;
                                posj = j;
                                break;
                        }
                }
                if (posi != -1 && posj != -1) {
                        Card c = board.getCard(posi);
                        if (IsOverSelections() && !isselected[posi][posj]) {
                                return;
                        }
                        isselected[posi][posj] = !isselected[posi][posj];
                        selections[posi] = !selections[posi];
                        Btns[posi][posj].setIcon(new ImageIcon(getClass().getResource(imageFileName(c, isselected[posi][posj]))));

                } else {
                        needTwo = true;
                }

                /**
                 * multiple cards handling
                 */
                if (needTwo) {
                        posi = -1;
                        posj = -1; //reinitialize
                        for (int i = 0; i < Btns.length && posi == -1; i++) {
                                for (int j = 0; j <= last[i]; j++) {
                                        if (evt.equals(Btns[i][j])) {
                                                posi = i;
                                                posj = j;
                                                break;
                                        }
                                }
                        }
                        //check if from posj to last is valid
                        boolean isValid = true;
                        int SSize = board.SizeofStack(posi);
                        for (int j = last[posi]; j > posj; j--) {
                                Card card1 = board.getCard(posi, SSize - 1);
                                Card card2 = board.getCard(posi, SSize - 2);
                                if (!card1.IsLegalBelow(card2)) {
                                        isValid = false;
                                }
                                SSize--;
                        }
                        //validation ends
                        //select cards
                        if (isValid) {
                                selections[posi] = !selections[posi];
                                SSize = board.SizeofStack(posi);
                                int tempj = -1;
                                for (int j = last[posi]; j >= posj; j--) {
                                        Card card = board.getCard(posi, SSize - 1);
                                        Btns[posi][j].setIcon(new ImageIcon(getClass().getResource(imageFileName(card, selections[posi]))));
                                        SSize--;
                                        tempj = j;
                                }
                                isselected[posi][tempj] = !isselected[posi][tempj];
                        }

                }
                //end multiple cards (oh it killed me)
        }

        private boolean IsOverSelections2() {
                int count = 0;
                for (int i = 0; i < Btns.length; i++) {
                        if (selections[i]) {
                                count++;
                        }
                }
                return (count >= 2);
        }

        private boolean IsOverSelections() {
                int count = 0;
                for (int i = 0; i < Btns.length; i++) {
                        int j = last[i];
                        if (isselected[i][j]) {
                                count++;
                        }
                }
                return (count >= 2);
        }

        private void BtnDealEvent() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        private void BtnPlaceEvent() {
                boolean isValid = false;
                List<Integer> selectedCards = new ArrayList<>();
                int c1 = -1, c2 = -1;
                int y1 = -1, y2 = -1;
                for (int i = 0; i < Btns.length; i++) {
                        for (int j = 0; j <= last[i]; j++) {
                                if (isselected[i][j]) {
                                        if (c1 == -1) {
                                                c1 = i;
                                                y1 = j;
                                        } else if (c2 == -1) {
                                                c2 = i;
                                                y2 = j;
                                        }
                                }
                        }
                }

                Card card1 = board.getCard(c1, y1 - 1 - (last[c1] - board.SizeofStack(c1)));
                System.out.println(card1 + " " + y1);
                Card card2 = board.getCard(c2, y2 - 1 - (last[c2] - board.SizeofStack(c2)));
                System.out.println(card2 + " " + y2);
                selectedCards.add(c1);
                selectedCards.add(c2);
                if (board.isLegal(selectedCards)) {
                        isValid = true;
                        int order = board.getOrder();
                        if (order == 12) {
                                PlaceProcess(c1, c2, card1, card2);
                        } else if (order == 21) {
                                PlaceProcess(c2, c1, card2, card1);
                        }
                }
                //init
                if (isValid) {
                        selected = new ArrayList<>();
                        selections = new boolean[10];
                        isselected = new boolean[10][16];
                }
        }

        /**
         * @param c2 destination
         * @param card2 destination
         */
        private void PlaceProcess(int c1, int c2, Card card1, Card card2) {
                //card 1 is legal below card 2 
                board.addCardinStack(card1, c2); // add card1 into stack 2
                board.removeCardinStack(c1); // remove from stack 1
                last[c1]--; //c1 is out ==> last c1 should be decreased
                last[c2]++; //c2 got one ==> last c2 should be increased
                //image manip
                Btns[c1][last[c1] + 1].setIcon(new ImageIcon(getClass().getResource("/cards/back2.jpg")));
                Btns[c1][last[c1] + 1].setVisible(false); // hide card1 
                Btns[c2][last[c2]].setIcon(new ImageIcon(getClass().getResource((imageFileName(card1, false))))); // set below card 2 is card 1 
                Btns[c2][last[c2]].setVisible(true); //make it visible
                Btns[c2][last[c2] - 1].setIcon(new ImageIcon(getClass().getResource((imageFileName(card2, false))))); // set card 2 as not selected

                //card1 is already hidden
                //now the random card in the stack should appear
                if (last[c1] >= 0) {
                        board.deal(c1);
                        Card newC1 = board.cardAt(c1);
                        board.addCardinStack(newC1, c1);
                        Btns[c1][last[c1]].setIcon(new ImageIcon(getClass().getResource((imageFileName(newC1, false)))));
                }
        }

        private void BtnRestartEvent() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

}
