/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Random;

/**
 *
 * @author hdbut_000
 */
public class Game {
 
    public char[][] board = new char[5][5];        // holds the state of the game
    public int turn;
    public boolean gameOver;
    
    public Game(){
        
        board = new char[5][5];
        this.turn= 0;
        gameOver = false;
        
        for (char[] a : board){             //creates an empty board
            for(char b : a){
                b = ' ';
            }
            
        }
        
       
    }
    
    private int[] randomPos(){
        Random rand = new Random();
        
        int num1 = rand.nextInt(5);
        int num2 = rand.nextInt(5);
        
        int[] array = {num1, num2};
        
        return array;
    }
    
    public void start(){
        
        (new Thread(new Player( this , 'B' , 0))).start(); //created Buggs
        (new Thread(new Player( this , 'T' , 1))).start(); //created Tweety
        (new Thread(new Player( this , 'D' , 2))).start(); //Created Tazz
        
    }
    
    public void turnOver(){
        
        if(turn>= 3){
            turn = 0;
        }
        else{
            turn++;
        }
        
    }
    
}
