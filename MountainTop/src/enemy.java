/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hdbut_000
 */
public class enemy extends Player {
    
    
    public enemy(Game game, String name, int canPlay) {
        this.game = game;
        this.name = name;
        hasCarrot = false;
        frozen = false;
        dead = false;
        this.canPlay = canPlay;
        location = game.randomPos();

        game.data.board[location[0]][location[1]] = name;

    }
    
    
    
    public boolean runPosition(int x, int y) {
        boolean foundStep = false;
        
        
        if ((location[0] + x >= 0 && location[0] + x <= 4) && (location[1] + y >= 0 && location[1] + y <= 4)) {
            if (game.data.board[location[0] + x][location[1] + y].equals(" ")
                    || game.data.board[location[0] + x][location[1] + y].equals("C")
                    || game.data.board[location[0] + x][location[1] + y].equals("F")) 
            {

                if (game.data.board[location[0] + x][location[1] + y].equals("C") && !hasCarrot) {//if you dont have the carrot
                    game.data.stage++;
                    hasCarrot = true;
                    foundStep = true;
                    game.data.board[location[0] + x][location[1] + y]= name;
                    game.data.board[location[0]][location[1]]=" ";
                    location[0]=location[0]+x;
                    location[1]=location[1]+y;

                } else if (game.data.board[location[0] + x][location[1] + y].equals("F") && hasCarrot) {
                    this.game.data.gameOver = true;
                    System.out.println(name + " has won!");
                    foundStep = true;
                    game.data.board[location[0] + x][location[1] + y]= name;
                    game.data.board[location[0]][location[1]]=" ";
                    location[0]=location[0]+x;
                    location[1]=location[1]+y;
                    

                } else if (game.data.board[location[0] + x][location[1] + y].equals(" ")) {
                   game.data.board[location[0] + x][location[1] + y]= name;
                    game.data.board[location[0]][location[1]]=" ";
                    location[0]=location[0]+x;
                    location[1]=location[1]+y;
                    foundStep = true;

                }
                
                else if (game.data.board[location[0] + x][location[1] + y].equals("B")) {
                    game.Buggs.frozen = true;
                    if(game.Buggs.hasCarrot){
                        this.hasCarrot=true;
                        game.Buggs.hasCarrot = false;
                    }
                   game.data.board[location[0] + x][location[1] + y]= name;
                    game.data.board[location[0]][location[1]]=" ";
                    location[0]=location[0]+x;
                    location[1]=location[1]+y;
                    foundStep = true;

                }
                
                else if (game.data.board[location[0] + x][location[1] + y].equals("T")) {
                    game.Tweety.frozen = true;
                    if(game.Tweety.hasCarrot){
                        this.hasCarrot=true;
                        game.Tweety.hasCarrot = false;
                    }
                   game.data.board[location[0] + x][location[1] + y]= name;
                    game.data.board[location[0]][location[1]]=" ";
                    location[0]=location[0]+x;
                    location[1]=location[1]+y;
                    foundStep = true;

                }
                
                else if (game.data.board[location[0] + x][location[1] + y].equals("D")) {
                    game.Tazz.frozen = true;
                    if(game.Tazz.hasCarrot){
                        this.hasCarrot=true;
                        game.Tazz.hasCarrot = false;
                    }
                   game.data.board[location[0] + x][location[1] + y]= name;
                    game.data.board[location[0]][location[1]]=" ";
                    location[0]=location[0]+x;
                    location[1]=location[1]+y;
                    foundStep = true;

                }

            }
        }
        game.printBoard();
        return foundStep;
    }
    
    
    
    
    
    
}
