
package dk.easv.tictactoe.bll;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 *
 * @author EASV
 */
public class GameBoard implements IGameBoard
{

    @FXML
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;

    int [][] board = new int[3][3];

    /**
     * Returns 0 for player 0, 1 for player 1.
     *
     * @return int Id of the next player.
     */
    public int getNextPlayer()
    {
        //TODO Implement this method
        return 0;
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

        if (col < 0 || col > 2 || row < 0 || row > 2) {
            return false;
        }

        if(board[col][row] != -1){
            return false;
        }
        else {
            board[col][row] = getNextPlayer();
        }

        if(isGameOver() == true){

            return false;
        } //

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
        //TODO Implement this method
        return false;
    }

    /**
     * Gets the id of the winner, -1 if its a draw.
     *
     * @return int id of winner, or -1 if draw.
     */
    public int getWinner()
    {
        //TODO Implement this method
        return -1;
    }

    /**
     * Resets the game to a new game state.
     */
    public void newGame()
    {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = -1;  // Set all elements to -1, to represent the empty cells
            }
        }

    }
}
