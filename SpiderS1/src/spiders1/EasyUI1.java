package spiders1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.Stack;
import javax.swing.JLabel;

/**
 *
 * @author .
 */
public class EasyUI1 extends JFrame implements ActionListener {

        /**
         * Creates new form EasyUI
         */
        JButton[][] Btns;
        List<Stack<Card>> arr;
        int[] last;
        Deck deck;
        private boolean[] selections;
        List<Card> cards;

        public EasyUI1() {
                initComponents();
                initDeck();
                initBoardStack();
                initFirstLine();
        }

        void initDeck() {
                String[] ranks
                        = {"ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king",};
                String[] suits
                        = {"spades"};
                int[] pointValues
                        = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
                String[] RANKS = new String[104];
                int[] POINT_VALUES = new int[104];
                for (int i = 0; i < 104; i++) {
                        RANKS[i] = ranks[i % 8];
                        POINT_VALUES[i] = pointValues[i % 8];
                }
                deck = new Deck(RANKS, suits, POINT_VALUES);
        }

        private void initComponents() {

                LblRest = new javax.swing.JLabel();
                MainPanel = new javax.swing.JPanel();
                BtnRestart = new javax.swing.JButton();
                BtnPlace = new javax.swing.JButton();
                BtnDeal = new JButton();

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
                MainPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
                MainPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                initBtns();

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

                LblRest.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                LblRest.setText("Deck: 50 card(s) left");
                MainPanel.add(LblRest, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 510, 170, 30));

                getContentPane().add(MainPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1110, 610));
                pack();
        }

        void initBoardStack() {
                arr = new ArrayList<>();
                last = new int[10];
                for (int i = 0; i < 10; i++) {
                        Stack<Card> newStack = new Stack<>();
                        last[i] = 3;
                        for (int j = 0; j < 4; j++) {
                                Card newCard = deck.deal();
                                newStack.push(newCard);
                        }
                        if (i < 4) {
                                Card newCard = deck.deal();
                                newStack.push(newCard);
                                last[i]++;
                        }
                        arr.add(newStack);
                }
        }

        void initFirstLine() {
                for (int i = 0; i < 10; i++) {
                        Card newCard = deck.deal();
                        last[i]++;
                        Stack<Card> newStack = new Stack<>();
                        newStack = arr.get(i);
                        newStack.push(newCard);
                        arr.set(i, newStack);
                        FlipCard(i, last[i], newCard, false);
                }
        }

        

        private JLabel LblRest;
        private JButton BtnDeal;
        private JButton BtnPlace;
        private JButton BtnRestart;
        private JPanel MainPanel;

        @Override
        public void actionPerformed(ActionEvent e) {
                Object obj = e.getSource();
                if (obj.equals(BtnRestart))
                        RestartPressed();
                else if (obj.equals(BtnDeal))
                        DealPressed();

        }

        void DealPressed() {

        }

        void RestartPressed() {
                initComponents();
                initDeck();
                initBoardStack();
                initFirstLine();
        }
        //Done Methods

        void FlipCard(int col, int lasti, Card card, boolean isSelected) {
                Btns[col][lasti].setIcon(new ImageIcon(getClass().getResource(imageFileName(card, isSelected))));
                Btns[col][lasti].setOpaque(false);
                Btns[col][lasti].setVisible(true);
        }
        void initBtns() {
                Btns = new JButton[10][16];
                int y = 10;
                for (int i = 0; i < 10; i++) {
                        int x = 15 * 20;
                        for (int j = 15; j >= 0; j--) {
                                Btns[i][j] = new JButton();
                                Btns[i][j].setIcon(new ImageIcon(getClass().getResource("/cards/back2.jpg")));
                                Btns[i][j].setOpaque(false);
                                Btns[i][j].setVisible(false);
                                if (j < 4 && i > 3)
                                        Btns[i][j].setVisible(true);
                                if (j < 5 && i <= 3)
                                        Btns[i][j].setVisible(true);
                                MainPanel.add(Btns[i][j], new org.netbeans.lib.awtextra.AbsoluteConstraints(y, x, 70, 100));
                                x -= 20;
                                Btns[i][j].addActionListener(this);
                        }
                        y += 100;
                }
        }

        public static void main(String args[]) {
                /* Create and display the form */
                java.awt.EventQueue.invokeLater(new Runnable() {
                        public void run() {
                                new EasyUI1().setVisible(true);
                        }
                });
        }

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
}
