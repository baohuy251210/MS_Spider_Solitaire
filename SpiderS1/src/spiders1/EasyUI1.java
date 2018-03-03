package spiders1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author .
 */
public class EasyUI1 extends JFrame implements ActionListener {

        /**
         * Creates new form EasyUI
         */
        JButton[][] Btns;
        int[] last;

        private boolean[] selections;

        public EasyUI1() {
                initComponents();
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
                                if (j < 5)
                                        Btns[i][j].setVisible(true);
                                MainPanel.add(Btns[i][j], new org.netbeans.lib.awtextra.AbsoluteConstraints(y, x, 70, 100));
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
                                if (j < 4)
                                        Btns[i][j].setVisible(true);

                                MainPanel.add(Btns[i][j], new org.netbeans.lib.awtextra.AbsoluteConstraints(y, x, 70, 100));
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

        private JButton BtnDeal;
        private JButton BtnPlace;
        private JButton BtnRestart;
        private JPanel MainPanel;

        @Override
        public void actionPerformed(ActionEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }


}
