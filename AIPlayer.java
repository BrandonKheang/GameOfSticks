import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//inherits from the move class
public class AIPlayer extends Moves {

	private ArrayList<Cup> cups = new ArrayList<>();
	private Map<Integer, Move> currentGameMoves = new HashMap<>();
	private AIPlayer trainer;
	//number of games the ai will play to train
	private final int TRAINING_LENGTH = 300;
	//check if button is pressed for event handler
	private boolean buttonPressed;
	
	public AIPlayer(int sticks, String playerName) {
		super(playerName);
		//creates an array of cups
		for (int num = sticks; num >= 1; num--) {
			cups.add(new Cup(num));
		}
	}
	
	//returns a random move from the cup
	@Override
	public Move getMove(Scanner console, Board board, int totalMoves, int totalSticks) {
		//get a random cup depending on how many sticks there are
		Cup cup = cups.get(totalSticks - board.getBoardSize());
		//pick a random move from the cup
		Move move = cup.getMove();
		//adds the stick count and the move to a map so it remember what moves to update
		currentGameMoves.put(totalMoves, move);
		return move;
	}
	
	//returns a random move from the cup and displays it with a jpanel
	public Move getMove(JFrame frame, Board board, int totalMoves, int totalSticks) {
		//get the right cup depending on how many sticks there are
		Cup cup = cups.get(totalSticks - board.getBoardSize());
		Move move = cup.getMove();
		//adds the stick count and the move to a map so it remember what moves to update
		currentGameMoves.put(totalMoves, move);
		//for testing
		/*System.out.println(board.getBoardSize());
		System.out.println(move.getMove());*/
		//set up panel
		JPanel panel = new JPanel();
		frame.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setVisible(true);
		//set up label
		JLabel aiMove = new JLabel("AI chooses " + move.getMove());
		aiMove.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(aiMove);
		//set up submit button
		JButton button = new JButton("Submit");
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(button);
		panel.revalidate();
		buttonPressed = false;
		//wait until the button is pressed to move on
		while(true) {
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					buttonPressed = true;
				}
			});
			if(buttonPressed) {
				break;
			}
		}
		//hide the frame once the user submits
		panel.setVisible(false);
		return move;
	}
	
	//should check each move from the map and update it
	public void updateCups(boolean win, int totalStickCount) {
		//for testing
		/*for(int i: currentGameMoves.keySet()) {
			System.out.print(i + " ");
			System.out.println(currentGameMoves.get(i).getMove());
		}*/
		Object[] movesSet = currentGameMoves.keySet().toArray();
		//loops through the keys of the map
		for(int i = 0; i < movesSet.length; i++) {
			//gets the index based on how many sticks there were at the time of the move
			int moveIndex = (int) movesSet[i];
			int index = totalStickCount - moveIndex;
			Move move = currentGameMoves.get(moveIndex);
			//if the ai won add the move
			if(win) {
				cups.get(index).addMove(move);
			//if the ai lost remove the move(if it's not the only one)
			} else {
				cups.get(index).removeMove(move);
			}
		}
		//clear the map when the moves are done updating
        currentGameMoves.clear();
	}
	
	public void train(int sticks) {
		trainer = new AIPlayer(sticks, "trainer");
		//runs TRAINING_LENGTH time
		for(int i = 0; i < TRAINING_LENGTH; i++) {
			Board board = new Board(sticks);
	   		boolean gameOver = false;
	   		int moveCount = 1;
	   		//keep playing until the game is over
	   		while (!gameOver) {
	   			if(moveCount % 2 == 1) {
	   				//get a random move
	   				Move move = this.getMove(new Scanner(""), board, board.getBoardSize(), sticks);
	   				//do the move
	   				board.doMove(move);
	   				//checka if game is over
	   				gameOver = board.isGameOver(this);
	   			} else {
	   				//get a random move
	   				Move move = trainer.getMove(new Scanner(""), board, board.getBoardSize(), sticks);
	   				//do the move
	   				board.doMove(move);
	   				//check if game is over
	   				gameOver = board.isGameOver(trainer);
	   			}
	   			moveCount++;
	   		}
	   		int losingPlayer = moveCount % 2 + 1;
	   		//update moves
	   		boolean win = true;
	   		if(losingPlayer == 1) {
	   			win = false;
	   		}
	   		this.updateCups(win, sticks);
		}
	}
	
	//returns a string of all the cups
	public String toString() {
		String output = "";
		for(Cup cup: cups) {
			output += cup;
		}
		return output;
	}
}