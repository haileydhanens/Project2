/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hdbut_000
 */
public class Game {
 
    char[][] board = new char[5][5];        // holds the state of the game
    int turn;
    
    public Game(){
        
        board = new char[5][5];
        this.turn= 0;
        
        for (char[] a : board){             //creates an empty board
            for(char b : a){
                b = ' ';
            }
            
        }
        
        (new Thread(new Player())).start();
        
    }
    
}
