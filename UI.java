import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI {
	
	private int totalSticks;
	private int gameMode;
	private boolean playAgain;
	private Moves player1;
	private Moves player2;
	
	//creates a UI with an undecided game mode and 0 starting sticks
	public UI() {
		totalSticks = 0;
		gameMode = -1;
	}
	
	//runs the game
	public void playGame() {
		Move move;
		boolean ai = false;
		Board board;
		boolean gameOver;
		int moveCount;
		//create the jframe
		JFrame frame = new JFrame();
		frame.setSize(300, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		//ask the user how many sticks they want to play with
		getStickCount(frame);
		//ask the user what game mode they want to play
		getGameMode(frame);
		player1 = new Player("Player 1");
		if(gameMode == 1) {
			player2= new Player("Player 2");
		} else {
			ai = true;
			player2 = new AIPlayer(totalSticks, "AI Player");
			if(gameMode == 3) {
				//train the ai if player chooses to play against a trained computer
				player2.train(totalSticks);
			}
		}
		playAgain = true;
		while(playAgain) {
			board = new Board(totalSticks);
			gameOver = false;
			moveCount = 1;
			//run until the game is over
			while(!gameOver) {
				//get player 1 move
				if(moveCount % 2 == 1) {
					move = player1.getMove(frame, board, board.getBoardSize(), totalSticks);
					board.doMove(move);
					gameOver = board.isGameOver(player1);
					if(gameOver) {
						gameOver(frame, player1);
					}
				//get player 2 move
				} else {
					move = player2.getMove(frame, board, board.getBoardSize(), totalSticks);
					board.doMove(move);
					gameOver = board.isGameOver(player2);
					if(gameOver) {
						gameOver(frame, player2);
					}
				}
				moveCount++;
			}
			//ask the user if they want to play again
			playAgain(frame);
			int losingPlayer = moveCount % 2 + 1;
			//update moves
			boolean win = true;
			if(losingPlayer == 2) {
				win = false;
			}
			//update cups if the opponent is an ai
			if(ai) {
				player2.updateCups(win, totalSticks);
			}
			System.out.println(player2);
		}
	}
	
	//asks the user if they want to play again
	private void playAgain(JFrame frame) {
		String dialog = "Would you like to play again?";
		int answer = JOptionPane.showConfirmDialog(null, dialog, "Play Again?", JOptionPane.YES_NO_OPTION);
		if(answer == JOptionPane.YES_OPTION) {
			playAgain = true;
		} else if(answer == JOptionPane.NO_OPTION) {
			playAgain = false;
			System.exit(0);
		}
	}
	
	//tells the user that the game is over
	private void gameOver(Frame frame, Moves player) {
		JOptionPane.showMessageDialog(null, player.playerName + ", you lose");
	}
	
	//asks the user what game mode they want to play
	private void getGameMode(JFrame frame) {
		//set up panel
		JPanel setModePanel = new JPanel();
		frame.add(setModePanel);
		setModePanel.setLayout(new BoxLayout(setModePanel, BoxLayout.Y_AXIS));
		setModePanel.setVisible(true);
		//set up label
		JLabel whatMode = new JLabel("Which option do you take?");
		whatMode.setAlignmentX(Component.CENTER_ALIGNMENT);
		setModePanel.add(whatMode);
		//set up radio buttons
		JRadioButton friendButton = new JRadioButton("Play against a friend");
		friendButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		setModePanel.add(friendButton);
		JRadioButton computerButton = new JRadioButton("Play against the computer");
		computerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		setModePanel.add(computerButton);
		JRadioButton aiButton = new JRadioButton("Play against the trained computer");
		aiButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		setModePanel.add(aiButton);
		ButtonGroup bg = new ButtonGroup();
		bg.add(friendButton);
		bg.add(computerButton);
		bg.add(aiButton);
		//set up submit button
		JButton button = new JButton("Submit");
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		setModePanel.add(button);
		setModePanel.revalidate();
		while(true) {
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(friendButton.isSelected()) {
						gameMode = 1;
					} else if(computerButton.isSelected()) {
						gameMode = 2;
					} else if(aiButton.isSelected()) {
						gameMode = 3;
					}
				}
			});
			if(gameMode != -1) {
				break;
			}
		}
		//hide the frame once the user chooses a game mode
		setModePanel.setVisible(false);
	}
	
	
	//asks the user how many sticks they want to play with
	private void getStickCount(JFrame frame) {
		//set up panel
		JPanel setSticksPanel = new JPanel();
		setSticksPanel.setLayout(new BoxLayout(setSticksPanel, BoxLayout.Y_AXIS));
		frame.add(setSticksPanel);
		//set up label
		JLabel howManySticks = new JLabel("How many sticks do you want to start with?");
		howManySticks.setAlignmentX(Component.CENTER_ALIGNMENT);
		setSticksPanel.add(howManySticks);	
		//set up text field
		JTextField textBox = new JTextField();
		textBox.setMaximumSize(new Dimension(150, 20));
		textBox.setAlignmentX(Component.CENTER_ALIGNMENT);
		setSticksPanel.add(textBox);	
		//set up button
		JButton button = new JButton("Submit");
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		setSticksPanel.add(button);
		button.setVisible(true);
		setSticksPanel.revalidate();
		//wait until user presses the button with input
		while(true) {
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(!(textBox.getText().isEmpty())) {
						totalSticks = Integer.parseInt(textBox.getText());
					}
				}
			});
			if(totalSticks != 0) {
				break;
			}
		}
		//hide the frame once the user enters and submits a number of sticks
		setSticksPanel.setVisible(false);
	}
}