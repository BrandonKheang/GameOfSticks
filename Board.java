public class Board {

    //Number of sticks in the game
    private int size;

    //Constructs a board with default size of 0;
    public Board() {
        size = 0;
    }
    //Constructs a board with the given size parameter.
    public Board(int size) {
        this.size = size;
    }
    //Returns the current number of sticks on the board.
    public int getBoardSize(){ return size; }
    //Subtracts the given size move from the total number of sticks on the board.
    public void doMove(Move move) { size -= move.getMove(); }
    
    //Checks if the game is over
    public boolean isGameOver(Moves player) {
    	if (getBoardSize() <= 0) {
    		// Losing statement
    		return true;
    	}
    	return false;
    }
}