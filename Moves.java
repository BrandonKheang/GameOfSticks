import java.util.Scanner;
import javax.swing.*;

public class Moves {
	public String playerName;

	public Moves(String name){
      playerName = name;
   }
   public Move getMove(Scanner console, Board board, int moveCount, int i) {
    	return new Move(1);
   }
   
   public Move getMove(JFrame frame, Board board, int moveCount, int i) {
   	return new Move(1);
  }
   
   public void train(int sticks) {
	   
   }

	public void updateCups(boolean win, int sticks) {
		// TODO Auto-generated method stub
	}
}