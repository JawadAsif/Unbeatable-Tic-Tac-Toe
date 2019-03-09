/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tic.tac.toe;

import static java.lang.Math.max;
import static java.lang.Math.min;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author jawad
 */
public class TicTacToe {

    /**
     * @param args the command line arguments
     */
    static int alpha;
    static int beta;
    public Board maxBoard;
    
    public TicTacToe(){
        maxBoard = new Board();
    }

    public static void main(String[] args) {
        TicTacToe tic = new TicTacToe();
        Board b = new Board();
        tic.alphaBeta(b);
        LabelFrame frame = new LabelFrame(tic.maxBoard);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 350);
        frame.setResizable(false);
        frame.setVisible(true);

    }

    int alphaBeta(Board s) {
        int v = this.maxValue(s, -5555, 5555);
        ArrayList<Board>bl;
        if( s.successors == null ){
            s.successors();
        }
        bl = s.successors;
        for(int i=0; i<bl.size();i++){
            if(bl.get(i).utility == v){
                this.maxBoard = bl.get(i);
                break;
            }
        }
        return v;
    }

    int maxValue(Board s, int alpha, int beta) {
        if( s.isTerminal()){
            return s.utility();
        }
        int v = -5555;
        ArrayList<Board>bl;
        if( s.successors == null ){
            s.successors();
        }
        bl = s.successors;
        for(int i=0; i<bl.size();i++){
            v = max(v,this.minValue(bl.get(i), alpha, beta));
            if (v>=beta){
                s.utility = v;
                return v;
            }
            alpha = max(alpha,v);
        }
        s.utility = v;
        return v;
    }


    int minValue(Board s, int alpha, int beta) {
        if( s.isTerminal()){
//            System.out.println(s.utility());
            return s.utility();
        }
        int v = 5555;
        ArrayList<Board>bl;
        if( s.successors == null ){
            s.successors();
        }
        bl = s.successors;
        for(int i=0; i<bl.size();i++){
            v = min(v,this.maxValue(bl.get(i), alpha, beta));
            if (v<=alpha){
                s.utility = v;
                return v;
            }
            beta = min(beta,v);
        }
        s.utility = v;
        return v;
    }

}
