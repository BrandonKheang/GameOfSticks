# Game of Sticks

## A simple game where you try not to pick up the last stick.

This application was built in Java using Swing. It can be played in 3 modes:

1. Play against another player on the same device.
2. Play against a bot which randomly selects moves.
3. Play against a trained bot which picks optimal moves.

The trained bot is trained by playing games against itself. Each amount of sticks has a "cup" dedicated to it with one of each possible move. When the trained bot loses a game, it removes the moves it performed that game from its "cups", making sure to keep at least one of each move in its cups. When the trained bot wins a game, it adds the moves it performed that game to its "cups". After a large quantity of games, the bot becomes smart and almost always picks the optimal move.

## How to play
The game starts with 10 sticks(may be changed by the user) and two players take turns selecting a certain amount of sticks(1-3). When a user is forced to pick up the last stick, they lose.




