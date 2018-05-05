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
    public int stage;
    public boolean gameOver;
    public int[] carrot1;
    public int[] carrot2;
    public int[] mountain;
    Player Buggs;
    Player Tweety;
    Player Tazz;
    Player Marvin;
    Thread ThreadBuggs;
    Thread ThreadTazz;
    Thread ThreadTweety;
    Thread ThreadMarvin; // copy and edit the player class to make a new enemy class.

    public Game() {

        board = new String[5][5];
        this.turn = 0;
        gameOver = false;
        stage = 1;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = " ";
            }
        }

        carrot1 = randomPos();
        board[carrot1[0]][carrot1[1]] = "C";
        carrot2 = randomPos();
        board[carrot2[0]][carrot2[1]] = "C";
        mountain = randomPos();
        board[mountain[0]][mountain[1]] = "F";

        Buggs = new Player(this, "B", 0);
        Tweety = new Player(this, "T", 1);
        Tazz = new Player(this, "D", 2);
        Marvin = new Player(this, "M", 3);

        ThreadBuggs = (new Thread(Buggs)); //created Buggs
        ThreadTweety = (new Thread(Tweety)); //created Tweety
        ThreadTazz = (new Thread(Tazz));   //created Tazz
        ThreadMarvin = (new Thread(Marvin));

    }

    public int[] randomPos() {
        boolean valid = false;
        int num1 = 0;
        int num2 = 0;

        while (!valid) {                   //assigns random spot and checks if it is a valid position
            Random rand = new Random();

            num1 = rand.nextInt(5);
            num2 = rand.nextInt(5);

            if (board[num1][num2].equals(" ")) {
                valid = true;
            }

        }

        int[] array = {num1, num2};
        return array;
    }

    public void start() {

        ThreadBuggs.start(); //created Buggs
        ThreadTweety.start(); //created Tweety
        ThreadTazz.start(); //Created Tazz
        ThreadMarvin.start();
    }

    public void nextStage() {
        if (stage == 1) {
            this.stage++;
        }
    }

    public void turnOver() {

        if (turn >= 3) {
           
            printBoard();
            turn = 0;
        } else {
            turn++;
        }

    }

    public void printBoard() {

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].equals(" ")) {
                    System.out.print(" \t|");
                } else if (board[i][j].equals("B")) {
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

                } else if (board[i][j].equals("T")) {
                    if (Tweety.hasCarrot == true) {
                        if (Tweety.frozen) {
                            System.out.print("T(CF)\t|");
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
                } else if (board[i][j].equals("D")) {

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
                } else if (board[i][j].equals("M")) {

                    if (Marvin.hasCarrot == true) {
                        if (Buggs.frozen) {
                            System.out.print("M(CF)\t|");
                        } else {
                            System.out.print("M(C)\t|");
                        }

                    } else {
                        if (Marvin.frozen) {
                            System.out.print("M(F)\t|");
                        } else {
                            System.out.print("M\t|");
                        }
                    }

                    //print marvin info
                }
                else if(board[i][j].equals("C")){
                    System.out.print("C\t|");
                }
                else if(board[i][j].equals("F")){
                    System.out.print("F\t|");
                }
                
            }
            System.out.println("\n");
        }

    }
}
