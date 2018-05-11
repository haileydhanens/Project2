import java.lang.*;
import java.util.Random;

/**
 * Player class for the game
 * 
 * @author Hailey Dhanens
 * @author Jake Patzer
 */
public class Player implements Runnable {

    Game game;
    String name;
    boolean hasCarrot;
    boolean frozen;
    boolean dead;
    int canPlay;
    int[] location;

    public Player() {

    }

    //needs to be changed if making tweety and tazz class
    public Player(Game game) {
        this.game = game;
        name = "B";
        canPlay = 0;
        location = game.randomPos();
    }
    
    /**
     * Creates a new Player object, initializing their name and turn number,
     * and assigning them to a Game.
     * 
     * @param game the Game this Player is assigned to
     * @param name  the Player's name
     * @param canPlay value which determines when the Player takes their turn
     */
    public Player(Game game, String name, int canPlay) {
        this.game = game;
        this.name = name;
        hasCarrot = false;
        frozen = false;
        dead = false;
        this.canPlay = canPlay;
        location = game.randomPos();

        game.data.board[location[0]][location[1]] = name;

    }
    
    /**
     * Runs when a thread of this Player is created, and stops when the game ends.
     */
    public void run() {

        while (this.game.data.gameOver == false) {

            if (game.data.turn % 4 == canPlay) {

                takeTurn();
                game.data.turn++;
                game.turnOver(); // character ends their turn
            }
            try {
                Thread.sleep(100);
            } catch (Exception e) {
            }
        }

    }
    
    /**
     * Runs when it is this Player's turn to move. Determines if the Player is
     * able to move, and if so, the Player moves one space in a random direction.
     */
    protected void takeTurn() {

        synchronized (game.data) {

            System.out.print(name + "'s turn.   Frozen? :" + frozen + "    carrot? :" + hasCarrot + "\n");
            if (frozen || dead) {             //if the character is frozen spend turn unfreezing

                try {
                    Thread.sleep(100);
                } catch (Exception e) {

                }
            } else {

                boolean foundStep = false;
                Random rand = new Random();
                int[] newPos = {0, 0};
                int stepCode = 0;

                while (!foundStep) {

                    stepCode = rand.nextInt(8);

                    switch (stepCode) {

                        case 0:
                            foundStep = runPosition(0, 1);
                            newPos[0] = location[0];
                            newPos[1] = location[1] + 1;
                            break;
                        case 1:
                            foundStep = runPosition(1, 1);
                            newPos[0] = location[0] + 1;
                            newPos[1] = location[1] + 1;
                            break;
                        case 2:
                            foundStep = runPosition(1, 0);
                            newPos[0] = location[0] + 1;
                            newPos[1] = location[1];
                            break;
                        case 3:
                            foundStep = runPosition(1, -1);
                            newPos[0] = location[0] + 1;
                            newPos[1] = location[1] - 1;
                            break;
                        case 4:
                            foundStep = runPosition(0, -1);
                            newPos[0] = location[0];
                            newPos[1] = location[1] - 1;
                            break;
                        case 5:
                            foundStep = runPosition(-1, -1);
                            newPos[0] = location[0] - 1;
                            newPos[1] = location[1] - 1;
                            break;
                        case 6:
                            foundStep = runPosition(-1, 0);
                            newPos[0] = location[0] - 1;
                            newPos[1] = location[1];
                            break;

                        case 7:
                            foundStep = runPosition(-1, +1);
                            newPos[0] = location[0] - 1;
                            newPos[1] = location[1] + 1;
                            break;
                        default:
                            break;

                    }

                    //use a rand(7) to pick which direction to go. if direction is valid, foundstep = true and then move there.
                }
            }
        }
        game.turnOver();
    }
    
    /**
     * Determines if the Player is able to move in the specified direction.
     * 
     * @param x the change in x position to be attempted
     * @param y the change in y position to be attempted
     * @return true if the attempted movement is valid, return false otherwise
     */
    public boolean runPosition(int x, int y) {
        boolean foundStep = false;

        if ((location[0] + x >= 0 && location[0] + x <= 4) && (location[1] + y >= 0 && location[1] + y <= 4)) {
            if (game.data.board[location[0] + x][location[1] + y].equals(" ")
                    || game.data.board[location[0] + x][location[1] + y].equals("C")
                    || game.data.board[location[0] + x][location[1] + y].equals("F")) {

                if (game.data.board[location[0] + x][location[1] + y].equals("C") && !hasCarrot) {//if you dont have the carrot
                    //game.data.stage++;        stage++ already happens in turnOver()
                    hasCarrot = true;
                    foundStep = true;
                    game.data.board[location[0] + x][location[1] + y] = name;
                    game.data.board[location[0]][location[1]] = " ";
                    location[0] = location[0] + x;
                    location[1] = location[1] + y;

                } else if (game.data.board[location[0] + x][location[1] + y].equals("F") && hasCarrot) {
                    this.game.data.gameOver = true;
                    System.out.println(name + " has won!");
                    foundStep = true;
                    game.data.board[location[0] + x][location[1] + y] = name;
                    game.data.board[location[0]][location[1]] = " ";
                    location[0] = location[0] + x;
                    location[1] = location[1] + y;

                } else if (game.data.board[location[0] + x][location[1] + y].equals(" ")) {
                    game.data.board[location[0] + x][location[1] + y] = name;
                    game.data.board[location[0]][location[1]] = " ";
                    location[0] = location[0] + x;
                    location[1] = location[1] + y;
                    foundStep = true;

                }

            }
        }
        game.printBoard();
        return foundStep;
    }

}
