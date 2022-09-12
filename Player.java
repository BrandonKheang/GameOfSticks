import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//inherits from the move class
public class Player extends Moves {
	
	private int sticks;
	
	//Creates a player with the name entered
	public Player(String playerName) {
		super(playerName);
	}
	
	//Prompts the user to enter a move and returns it
	public Move getMove(Scanner console, Board board, int totalMoves, int i) {
		System.out.println("There are " + board.getBoardSize() + " sticks on the board.");
		System.out.print(playerName + ": How many sticks do you take? (1-3) ");
		Move move = new Move(console.nextInt());
		System.out.println();
		return move;
	}
	
	//Prompts the user to choose a move and returns it
	public Move getMove(JFrame frame, Board board, int totalMoves, int i) {
		sticks = 0;
		//set up panel
		JPanel getMovePanel = new JPanel();
		frame.add(getMovePanel);
		getMovePanel.setLayout(new BoxLayout(getMovePanel, BoxLayout.Y_AXIS));
		getMovePanel.setVisible(true);
		//set up label
		JLabel sticksLeft = new JLabel("There are " + board.getBoardSize() + " sticks on the board.");
		sticksLeft.setAlignmentX(Component.CENTER_ALIGNMENT);
		getMovePanel.add(sticksLeft);
		JLabel label = new JLabel(playerName + ", how many sticks do you take?");
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		getMovePanel.add(label);
		//set up radio buttons
		JRadioButton oneStick = new JRadioButton("1 stick");
		oneStick.setAlignmentX(Component.CENTER_ALIGNMENT);
		getMovePanel.add(oneStick);
		JRadioButton twoSticks = new JRadioButton("2 sticks");
		twoSticks.setAlignmentX(Component.CENTER_ALIGNMENT);
		getMovePanel.add(twoSticks);
		JRadioButton threeSticks = new JRadioButton("3 sticks");
		threeSticks.setAlignmentX(Component.CENTER_ALIGNMENT);
		getMovePanel.add(threeSticks);
		ButtonGroup bg = new ButtonGroup();
		bg.add(oneStick);
		bg.add(twoSticks);
		bg.add(threeSticks);
		//set up submit button
		JButton button = new JButton("Submit");
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		getMovePanel.add(button);
		getMovePanel.revalidate();
		while(true) {
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(oneStick.isSelected()) {
						sticks = 1;
					} else if(twoSticks.isSelected()) {
						sticks = 2;
					} else if(threeSticks.isSelected()) {
						sticks = 3;
					}
				}
			});
			if(sticks != 0) {
				break;
			}
		}
		//hide the frame once the user chooses amount of sticks
		getMovePanel.setVisible(false);
		return new Move(sticks);
	}
}