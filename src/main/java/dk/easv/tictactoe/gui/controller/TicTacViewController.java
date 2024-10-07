
package dk.easv.tictactoe.gui.controller;

// Java imports
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

// Project imports
import dk.easv.tictactoe.bll.GameBoard;
import dk.easv.tictactoe.bll.IGameBoard;



/**
 *
 * @author EASV
 */
public class TicTacViewController implements Initializable
{
    @FXML
    private Label lblPlayer;

    @FXML
    private Button btnNewGame;

    @FXML
    private GridPane gridPane;

    @FXML
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;

    public Button[][] buttonGrid = new Button[3][3];
    
    private static final String TXT_PLAYER = "Player: ";
    private IGameBoard game;



    /**
     * Event handler for the grid buttons
     *
     * @param event
     */
    @FXML
    private void handleButtonAction(ActionEvent event)
    {
        try
        {
            Integer row = GridPane.getRowIndex((Node) event.getSource());
            Integer col = GridPane.getColumnIndex((Node) event.getSource());
            int r = (row == null) ? 0 : row;
            int c = (col == null) ? 0 : col;
            int player = game.getNextPlayer();
            String xOrO = player == 0 ? "X" : "O";
            Button btn = (Button) event.getSource();
            if (game.play(c, r))
            {
                btn.setText(xOrO);

                if (game.isGameOver())
                {
                    int winner = game.getWinner();
                    displayWinner(winner);
                }
                else
                {
                    btn.setText(xOrO);
                    setPlayer();
                }
            }
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Event handler for starting a new game
     *
     * @param event
     */
    @FXML
    private void handleNewGame(ActionEvent event)
    {
        game.newGame();
        setPlayer();
        clearBoard();
    }

    /**
     * Initializes a new controller
     *
     * @param url
     * The location used to resolve relative paths for the root object, or
     * {@code null} if the location is not known.
     *
     * @param rb
     * The resources used to localize the root object, or {@code null} if
     * the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

        buttonGrid[0][0] = btn1;  // First row, first column
        buttonGrid[0][1] = btn2;  // First row, second column
        buttonGrid[0][2] = btn3;  // First row, third column
        buttonGrid[1][0] = btn4;  // Second row, first column
        buttonGrid[1][1] = btn5;  // Second row, second column
        buttonGrid[1][2] = btn6;  // Second row, third column
        buttonGrid[2][0] = btn7;  // Third row, first column
        buttonGrid[2][1] = btn8;  // Third row, second column
        buttonGrid[2][2] = btn9;  // Third row, third column

        game = new GameBoard();
        setPlayer();


    }

    /**
     * Set the next player
     */
    private void setPlayer()
    {
        lblPlayer.setText(TXT_PLAYER + game.getNextPlayer());
    }


    /**
     * Finds a winner or a draw and displays a message based
     * @param winner
     */
    private void displayWinner(int winner)
    {
        String message = "";
        switch (winner)
        {
            case -1:
                message = "It's a draw :-(";
                break;
            default:
                message = "Player " + winner + " wins!!!";
                break;
        }
        lblPlayer.setText(message);
    }

    /**
     * Clears the game board in the GUI
     */
    private void clearBoard()
    {
        for(Node n : gridPane.getChildren())
        {
            Button btn = (Button) n;
            btn.setText("");
        }
    }
}
