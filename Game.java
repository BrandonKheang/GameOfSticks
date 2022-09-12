import java.awt.FlowLayout;
import java.awt.*;
import java.util.Scanner;
import javax.swing.*;

public class Game {
	
	public Board board;
	private int moveCount;
	private Player player1;
	private Moves player2;
	private boolean ai = true;
	//private AIPlayer player3;
	
	public static void main(String[] args) {
		Game game = new Game();
		game.go();
				
	}
	
	public void go() {
		Move move;
		Scanner console = new Scanner (System.in);
		//check if user wants to play console or gui
		System.out.print("Welcome to Game of Sticks. Do you want to play console based (0) or GUI (1)? ");
		int input = console.nextInt();
		if(input == 0) {
			//asks how many sticks there will be
			System.out.print("How many sticks do you want to start with? (10-100) ");
			int sticks = console.nextInt();
			//asks what game mode the user wants to play
			System.out.println("Options:\n Play against a friend (1)\n Play against the computer (2)\n Play against the trained computer (3)");
			System.out.print("Which option do you take (1-3)? ");
			int num = console.nextInt();
			player1 = new Player("Player 1");
			//creates a different player2 depending on what game mode the user chose
			if (num == 1) {
				player2 = new Player("Player 2");
				ai = false;
			} else if(num == 2){
				player2 = new AIPlayer(sticks, "AI Player");
			} else {
				player2 = new AIPlayer(sticks, "AI Player");
				player2.train(sticks);
			}
			boolean playAgain = true;
			//play until the user doesn't want to play again
			while(playAgain){
		   		board = new Board(sticks);
		   		boolean gameOver = false;
		   		moveCount = 1;
		   		while (!gameOver) {
		   			if(moveCount % 2 == 1) {
		   				//get user move
		   				move = player1.getMove(console, board, board.getBoardSize(), 0);
		   				board.doMove(move);
		   				gameOver = board.isGameOver(player1);
		   				if(gameOver) {
		   					System.out.println(player1.playerName + ", you lose");
		   				}
		   			} else {
		   				//get user move
		   				move = player2.getMove(console, board, board.getBoardSize(), 0);
		   				board.doMove(move);
		   				if(ai) {
		   					System.out.println("There are " + board.getBoardSize() + " sticks on the board.");
		   					System.out.println("AI selects " + move.getMove());
		   					System.out.println();
		   				}
		   				gameOver = board.isGameOver(player2);
		   				if(gameOver) {
		   					System.out.println(player2.playerName + ", you lose");
		   				}
		   			}
		   			moveCount++;
		   		}
		   		//wait until user enters a valid token
		        while(true){
		        	//ask if user wants to play again
		            System.out.println("Play again (1 = yes, 0 = no)? ");
		            int response = console.nextInt();
		            if(response == 0){
		               playAgain = false;
		               break;
		            } else if(response == 1){
		               playAgain = true;
		               break;
		            } 
		        }
		   		int losingPlayer = moveCount % 2 + 1;
		   		//update moves
		   		boolean win = true;
		   		if(losingPlayer == 2) {
		   			win = false;
		   		}
		   		if(ai) {
		   			player2.updateCups(win, sticks);
		   		}
			}
		    console.close();
		} else {
			console.close();
			UI game = new UI();
			game.playGame();
		}
	}
	
	//returns how many moves have been made
	public int getMoveCount() {
		return this.moveCount;
	}

}