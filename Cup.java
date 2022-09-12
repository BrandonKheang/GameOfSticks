import java.util.ArrayList;

public class Cup {
	
	//Array of possible moves
	private ArrayList<Move> moveList = new ArrayList<>();
	private Move lastMove;

	
	//Adds the three possible moveList to the move list
	public Cup(int sticksLeft) {
		moveList.add(new Move(1));
		if(sticksLeft >= 2) {
			moveList.add(new Move(2));
		}
		if(sticksLeft >= 3) {
			moveList.add(new Move(3));
		}
	}
	
	//sets the last move to the move entered
	public void setLastMove(Move move) {
		lastMove = new Move(move.getMove());
	}
	
	//Remove the move from the the move list if the list contains multiple
	public void removeMove(Move move) {
		//added to test cups values
		/*
      	for(int i = 0; i < moveList.size(); i++){
        	System.out.print(moveList.get(i).getMove());
      	}
      	System.out.println();*/
		
		boolean firstOccurance = true; 
		for(int i = 0; i < moveList.size(); i++){
			if(moveList.get(i).getMove() == move.getMove()){
				if(firstOccurance){
					firstOccurance = false;
				} else {
					moveList.remove(i);
				}
			}
		}
		//added to test cups values
		/*for(int i = 0; i < moveList.size(); i++){
        	System.out.print(moveList.get(i).getMove());
      	}
      	System.out.println();*/
}
	
	//Adds the move to the move list
	public void addMove(Move move) {
		moveList.add(move);
	}
	
	//Returns a random move
	public Move getMove() {
		int randomIndex = (int)(Math.random()*moveList.size());
		Move tempMove = moveList.get(randomIndex);
		lastMove = tempMove;
		return tempMove;
	}
	
	//returns a string of the cup
	public String toString() {
		String output = "";
		for(Move move: moveList) {
			output += move.getMove();
		}
		output += "\n";
		return output;
	}
}