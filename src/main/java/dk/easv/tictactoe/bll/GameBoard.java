
package dk.easv.tictactoe.bll;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 *
 * @author EASV
 */
public class GameBoard implements IGameBoard
{

    private int[][] board;
    private int currentPlayer = 0;

    public GameBoard() {
        board = new int[3][3];
        newGame();
    }

    /**
     *
     * Returns 0 for player 0, 1 for player 1.
     *
     * @return int Id of the next player.
     */



    public int getNextPlayer() {


        switch (currentPlayer){

            case 0:
                currentPlayer = 1;
                break;

            case 1:
                currentPlayer = 0;
                break;

        }
        System.out.println(currentPlayer);


        return currentPlayer;
    }


    /**
     * Attempts to let the current player play at the given coordinates. If the
     * attempt is successful the current player has ended his turn and it is the
     * next players turn.
     *
     * @param col column to place a marker in.
     * @param row row to place a marker in.
     * @return true if the move is accepted, otherwise false. If gameOver == true
     * this method will always return false.
     */
    public boolean play(int col, int row)
    {

        if(isGameOver()){

            return false;
        }

        if (col < 0 || col > 2 || row < 0 || row > 2) {
            return false;
        }

        if (board[row][col] != -1) { // -1 means empty

            return false;
        }
        getNextPlayer();

        board[row][col] = currentPlayer;

        return true;
    }

    /**
     * Tells us if the game has ended either by draw or by meeting the winning
     * condition.
     *
     * @return true if the game is over, else it will retun false.
     */
    public boolean isGameOver()
    {
        if (getWinner() == 1 || getWinner() == 0 || getWinner() == -1){
            return true;
        }
        else {
            return false;
        }


    }

    /**
     * Gets the id of the winner, -1 if its a draw.
     *
     * @return int id of winner, or -1 if draw.
     */
    public int getWinner()
    {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != -1 && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return board[i][0]; // Return the winner (0 or 1)
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (board[0][i] != -1 && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                return board[0][i]; // Return the winner (0 or 1)
            }
        }

        // Check diagonals
        if (board[0][0] != -1 && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return board[0][0]; // Return the winner (0 or 1)
        }
        if (board[0][2] != -1 && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return board[0][2]; // Return the winner (0 or 1)
        }

        boolean draw = true;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == -1) {
                    draw = false;
                    break;
                }

            }
        }

        if (draw){
            return -1;
        }

        return 2;



    }




    /**
     * Resets the game to a new game state.
     */
    public void newGame()
    {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = -1; // -1 indicates an empty cell
            }
        }

    }

}
