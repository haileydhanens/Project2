/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.lang.Runnable;
/**
 *
 * @author hdbut_000
 */
public class Player implements Runnable {

    Game game;
    String name;
    boolean hasCarrot;
    boolean frozen;
    boolean dead;
    int canPlay;
    int[] location;
    
    public Player(){
        
    }
    //needs to be changed if making tweety and tazz class
    public Player(Game game){
       this.game = game;
       name= "B";
       canPlay=0;
       location = game.randomPos();
    }
    
    public Player(Game game, String name, int canPlay ){
       this.game= game;
       this.name= name;
       hasCarrot= false;
       frozen= false;
       dead= false;
       this.canPlay = canPlay;
       location = game.randomPos();
       
       game.board[location[0]][location[1]]= name;
       
    }
    
    
    public void run(){
        
        while(this.game.gameOver == false){
     
        takeTurn();    
            
            
            
            
            
            
        game.turnOver(); // character ends their turn
        }
    }
    
    protected void takeTurn(){
        
        if(frozen || dead){             //if the character is frozen spend turn unfreezing
            
            frozen = false;
        }
        
        else{
         
            boolean foundStep = false;
            
            while (!foundStep){
                
                
                
                //use a rand(7) to pick which direction to go. if direction is valid, foundstep = true and then move there.
            }
            
            
            
            
        }   
    }
    
}
