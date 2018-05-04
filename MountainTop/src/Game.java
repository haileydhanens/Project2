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
 
    public String[][] board;      // holds the state of the game
    public int turn;
    public boolean gameOver;
    public int[] carrot1;
    public int[] carrot2;
    public int[] mountain;
    Thread Buggs;
    Thread Tazz;
    Thread Tweety;
    Thread Marvin; // copy and edit the player class to make a new enemy class.
    
    public Game(){
        
        board = new String[5][5];
        this.turn= 0;
        gameOver = false;
        
        for (String[] a : board){             //creates an empty board
            for(String b : a){
                b = " ";
            }
            
        }
        carrot1 = randomPos();
        board[carrot1[0]][carrot1[1]] = "C";
        carrot2 = randomPos();
        board[carrot2[0]][carrot2[1]] = "C";
        mountain = randomPos();
        board[mountain[0]][mountain[1]] = "F";
        
        Buggs = (new Thread(new Player( this , "B" , 0))); //created Buggs
        Tweety = (new Thread(new Player( this , "T" , 1))); //created Tweety
        Tazz = (new Thread(new Player( this , "D" , 2)));   //created Tazz
        
        
        
        
        
        
       
    }
    
    public int[] randomPos(){
       boolean valid = false;
       int num1=0;
       int num2=0;
       
       while(!valid){                   //assigns random spot and checks if it is a valid position
        Random rand = new Random();
        
        num1 = rand.nextInt(5);
        num2 = rand.nextInt(5);
        
        if( board[num1][num2].equals(" ")){
            valid = true;
        }
        
       }
       
        int [] array = {num1, num2};
        return array;
    }
    
    public void start(){
        
        Buggs.start(); //created Buggs
        Tweety.start(); //created Tweety
        Tazz.start(); //Created Tazz
        
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
