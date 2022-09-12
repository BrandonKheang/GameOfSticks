public class Move {
	
	//keeps track of the move
    private int move;
    //The smallest and largest sized move you can make in a game of sticks.
    private static final int MIN_MOVE_SIZE = 1;
    private static final int MAX_MOVE_SIZE = 3;

    //Constructs a default move object with 0 sticks.
    public Move() {
        move = 0;
    }
    //Constructor with number of sticks as a parameter.
    public Move(int numSticks) {
        if (numSticks > MAX_MOVE_SIZE || numSticks < MIN_MOVE_SIZE) {
            throw new IllegalArgumentException();
        }
        move = numSticks;
    }
    //Set the number of sticks picked up.
    public void setMove(int numSticks) {
        move = numSticks;
    }
    //Returns the number of sticks picked up.
    public int getMove() {
        return move;
    }
}