package tic.tac.toe;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;

/**
 *
 * @author asif
 */
public class Board {
    
    /**
     *
     */
    public final int initialBlockValue = 0;
    private final int initialUtility = -2;

    int[][] blocks;
    int player; // player 1 and player 2
    int utility;
    Board parent;
    ArrayList<Board> successors;
    
    public Board() {
        this.blocks = new int[3][3];
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks.length; j++) {
                this.blocks[i][j] = this.initialBlockValue;
            }
        }
        this.player=1;
        this.utility=this.initialUtility;
        this.parent=null;
        this.successors = null;
        
    }
    public Board(int[][] blocks,int player,int row,int col) {
        this.blocks = new int[blocks.length][blocks.length];
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks.length; j++) {
                this.blocks[i][j] = blocks[i][j];
            }
        }
        this.blocks[row][col] = (player%2)+1;
        this.player=player;
        this.utility=this.initialUtility;
        this.successors = null;
    }
    
    
    public int player(){
        return this.player;
    }
    
    public boolean isTerminal(){
        // check first, second, third row for computer
        for(int j=0; j<3;j++){
            int temp = 0;
            for(int i=0; i<3; i++){
              if( this.blocks[j][i] != 1 ){
                  temp = 1;
                  break;
              }
            }
            if(temp==0){
                this.utility = 1;
                return true;
            }
        }
        // check first,second,third column for computer
        for(int j=0; j<3;j++){
            int temp = 0;
            for(int i=0; i<3; i++){
              if( this.blocks[i][j] != 1 ){
                  temp = 1;
                  break;
              }
            }
            if(temp==0){
                this.utility = 1;
                return true;
            }
        }
        
        
        // check main diagonal for computer
        int t = 0;
        for(int i=0; i<3; i++){
            if(this.blocks[i][i]!=1){
                t = 1;
                break;
            }
        }
        if(t==0){
            this.utility = 1;
            return true;
        }
        // check second diagonal for computer
        t = 0;
        for(int i=0; i<3; i++){
            if(this.blocks[i][2-i]!=1){
                t = 1;
                break;
            }
        }
        if(t==0){
            this.utility = 1;
            return true;
        }
        
        // check first,second,third row against computer
        for(int j=0; j<3;j++){
            int temp = 0;
            for(int i=0; i<3; i++){
              if( this.blocks[j][i] != 2 ){
                  temp = 1;
                  break;
              }
            }
            if(temp==0){
                this.utility = -1;
                return true;
            }
        }
        
        // check first,second,third column against computer
        for(int j=0; j<3;j++){
            int temp = 0;
            for(int i=0; i<3; i++){
              if( this.blocks[i][j] != 2 ){
                  temp = 1;
                  break;
              }
            }
            if(temp==0){
                this.utility = -1;
                return true;
            }
        }
        
        // check main diagonal against computer
        t = 0;
        for(int i=0; i<3; i++){
            if(this.blocks[i][i]!=2){
                t = 1;
                break;
            }
        }
        if(t==0){
            this.utility = -1;
            return true;
        }
        
        // check second diagonal against computer
        t = 0;
        for(int i=0; i<3; i++){
            if(this.blocks[i][2-i]!=2){
                t = 1;
                break;
            }
        }
        if(t==0){
            this.utility = -1;
            return true;
        }
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if( this.blocks[i][j]== this.initialBlockValue){
                    return false;
                }
            }
        }
        this.utility = 0;
        return true;
    }
    
    public int utility(){
        if(this.utility == this.initialUtility){
            this.isTerminal();
        }
        return this.utility;
    } 

    public void copyBlocks(int a[][], int b[][]) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                b[i][j] = a[i][j];
            }
        }
    }

    public ArrayList<Board> successors() {
        ArrayList<Board> bl = new ArrayList<>();
        Board b = null;
        
        for(int i=0; i<this.blocks.length; i++){
            for(int j=0; j<this.blocks.length; j++){
                if(this.blocks[i][j]== this.initialBlockValue ){
                    b = new Board(this.blocks,((this.player%2)+1),i,j);
                    b.blocks[i][j]=this.player;
                    b.parent = this;
                    bl.add(b);
                }
            }
        }
        this.successors = bl;
        return this.successors;
    }
}
