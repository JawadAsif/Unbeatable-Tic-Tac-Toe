package tic.tac.toe;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author asif
 */
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class LabelFrame extends JFrame implements MouseListener {

    private JLabel[][] label;
    private JButton next;
    private Icon[] ic;
    private int count;
    private JLabel movesLeft;
    Board board;

    public LabelFrame(Board b) {
        super("tic-tac-toe");
        this.board = b;
        setLayout(new FlowLayout());
        ic = new Icon[3];

        ic[0] = new ImageIcon(this.getClass().getResource("0.png"));
        ic[1] = new ImageIcon(this.getClass().getResource("cr.png"));
        ic[2] = new ImageIcon(this.getClass().getResource("ci.png"));

        label = new JLabel[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                label[i][j] = new JLabel();
                label[i][j].setIcon(ic[2]);
                add(label[i][j]);
                label[i][j].addMouseListener(this);
            }

        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                label[i][j].setIcon(ic[b.blocks[i][j]]);
            }
        }

    } // end labelframe constructor

    void draw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                label[i][j].setIcon(ic[board.blocks[i][j]]);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        int row = 0;
        int col = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (me.getSource() == label[i][j]) {
                    row = i;
                    col = j;
                    break;
                }
            }
        }
        if (board.blocks[row][col] == 0) {
            board = new Board(board.blocks, 1, row, col);
            draw();
            TicTacToe tic = new TicTacToe();
            tic.alphaBeta(board);
            board = tic.maxBoard;
            draw();
            if (board.isTerminal()) {
                if (board.utility == 1) {
                    JOptionPane.showMessageDialog(null, "Computer Wins");
                } else if (board.utility == 0) {
                    JOptionPane.showMessageDialog(null, "Match Draw");
                } else {
                    JOptionPane.showMessageDialog(null, "Player wins");
                }
                board = new Board();
                tic.alphaBeta(board);
                board = tic.maxBoard;
                draw();
            }
        }

//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent me) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent me) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent me) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent me) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}// end class labelfrme
