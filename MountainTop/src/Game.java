import java.util.Random;

/**
 * Engine for the game
 * 
 * @author Hailey Dhanens
 * @author Jake Patzer
 */
public class Game {
    
    //create new class to hold shared data
    //lock data on thread that has accessed it.
    
    public Data data;
    Player Buggs;
    Player Tweety;
    Player Tazz;
    enemy Marvin;
    Thread ThreadBuggs;
    Thread ThreadTazz;
    Thread ThreadTweety;
    Thread ThreadMarvin; // copy and edit the player class to make a new enemy class.
    
    /**
     * Initializes a new game.
     */
    public Game() {
        this.data = new Data();
        data.board = new String[5][5];
        this.data.turn = 0;
        this.data.gameOver = false;
        this.data.stage = 0;
        this.data.stage2 = false;

        for (int i = 0; i < data.board.length; i++) {
            for (int j = 0; j < data.board[i].length; j++) {
                data.board[i][j] = " ";
            }
        }

        data.carrot1 = randomPos();
        data.board[data.carrot1[0]][data.carrot1[1]] = "C";
        data.carrot2 = randomPos();
        data.board[data.carrot2[0]][data.carrot2[1]] = "C";
        data.mountain = randomPos();
        data.board[data.mountain[0]][data.mountain[1]] = "F";

        Buggs = new Player(this, "B", 0);
        Tweety = new Player(this, "T", 1);
        Tazz = new Player(this, "D", 2);
        Marvin = new enemy(this, "M", 3);

        ThreadBuggs = (new Thread(Buggs)); //created Buggs
        ThreadTweety = (new Thread(Tweety)); //created Tweety
        ThreadTazz = (new Thread(Tazz));   //created Tazz
        ThreadMarvin = (new Thread(Marvin));

    }
    
    /**
     * Assigns a random position, making sure it is valid.
     * 
     * @return a random, unoccupied position 
     */
    public int[] randomPos() {
        boolean valid = false;
        int num1 = 0;
        int num2 = 0;

        while (!valid) {                   //assigns random spot and checks if it is a valid position
            Random rand = new Random();

            num1 = rand.nextInt(5);
            num2 = rand.nextInt(5);

            if (data.board[num1][num2].equals(" ")) {
                valid = true;
            }

        }

        int[] array = {num1, num2};
        return array;
    }
    
    /**
     * Initializes the player threads.
     */
    public void start() {
        this.printBoard();
        ThreadBuggs.start(); //created Buggs
        ThreadTweety.start(); //created Tweety
        ThreadTazz.start(); //Created Tazz
        ThreadMarvin.start();
        
    }

    /**
     * Executes when a Player finishes their turn
     */
    
    
    /**
     * Prints the current state of the board to the console
     */
    public void printBoard() {
        System.out.println();

        System.out.println("-----------------------------------------");
        for (int i = 0; i < data.board.length; i++) {
            for (int j = 0; j < data.board[i].length; j++) {
                switch (data.board[i][j]) {
                    case " ":
                        System.out.print(" \t|");
                        break;
                    case "B":
                        if (Buggs.hasCarrot == true) {
                            if (Buggs.frozen) {
                                System.out.print("B(CF)\t|");
                            } else {
                                System.out.print("B(C)\t|");
                            }
                            
                        } else {
                            if (Buggs.frozen) {
                                System.out.print("B(F)\t|");
                            } else {
                                System.out.print("B\t|");
                            }
                        }
                        //print bugs info
                        break;
                    case "T":
                        if (Tweety.hasCarrot == true) {
                            if (Tweety.frozen) {
                                System.out.print("T(F)\t|");
                            } else {
                                System.out.print("T(C)\t|");
                            }
                            
                        } else {
                            if (Tweety.frozen) {
                                System.out.print("T(F)\t|");
                            } else {
                                System.out.print("T\t|");
                            }
                        }
                        //print tweety info
                        break;
                    case "D":
                        if (Tazz.hasCarrot == true) {
                            if (Tazz.frozen) {
                                System.out.print("D(CF)\t|");
                            } else {
                                System.out.print("D(C)\t|");
                            }
                            
                        } else {
                            if (Tazz.frozen) {
                                System.out.print("D(F)\t|");
                            } else {
                                System.out.print("D\t|");
                            }
                        }
                        
                        //print tazz info
                        break;
                    case "M":
                        if (Marvin.hasCarrot == true) {
                                System.out.print("M(C)\t|");
                            }
                            
                        else {                          
                                System.out.print("M\t|");
                        }
                        
                        //print marvin info
                        break;
                    case "C":
                        System.out.print("C\t|");
                        break;
                    case "F":
                        System.out.print("F\t|");
                        break;
                    default:
                        break;
                }
                
            }
            System.out.println("\n");
        }
        System.out.println("-----------------------------------------");
    }
}
