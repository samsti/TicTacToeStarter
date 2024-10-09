
package dk.easv.tictactoe.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import dk.easv.tictactoe.bll.GameBoard;
import dk.easv.tictactoe.bll.IGameBoard;


/**
 * @author EASV
 */
public class TicTacViewController implements Initializable {
    @FXML
    private Label lblPlayer;

    private String player1;
    private String player2;

    private int count = 0;


    @FXML
    private GridPane gridPane;

    @FXML
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;

    public Button[][] buttonGrid = new Button[3][3];

    private IGameBoard game;


    /**
     * Event handler for the grid buttons
     *
     * @param event
     */
    @FXML
    private void handleButtonAction(ActionEvent event) {
        try {
            Integer row = GridPane.getRowIndex((Node) event.getSource());
            Integer col = GridPane.getColumnIndex((Node) event.getSource());
            int r = (row == null) ? 0 : row;
            int c = (col == null) ? 0 : col;

            Button btn = (Button) event.getSource();

            if (!btn.getText().isEmpty()) {
                return;  // Do nothing if button is already clicked
            }

            int player = game.getNextPlayer();
            String xOrO = player == 0 ? "X" : "O";

            if (!btn.getText().isEmpty()) {
                return;  // Handles if the button was clicked
            }

            if (game.play(c, r)) {
                btn.setStyle("-fx-font-size: 33px;");
                btn.setText(xOrO);

                if (game.isGameOver()) {
                    int winner = game.getWinner();
                    displayWinner(winner);
                } else {
                    btn.setText(xOrO);
                    setPlayer(player1, player2);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Event handler for starting a new game
     *
     * @param event
     */
    @FXML
    private void handleNewGame(ActionEvent event) {


        game.newGame();
        setPlayer(player1, player2);
        clearBoard();
    }

    /**
     * Initializes a new controller
     *
     * @param url The location used to resolve relative paths for the root object, or
     *            {@code null} if the location is not known.
     * @param rb  The resources used to localize the root object, or {@code null} if
     *            the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        buttonGrid[0][0] = btn1;
        buttonGrid[0][1] = btn2;
        buttonGrid[0][2] = btn3;
        buttonGrid[1][0] = btn4;
        buttonGrid[1][1] = btn5;
        buttonGrid[1][2] = btn6;
        buttonGrid[2][0] = btn7;
        buttonGrid[2][1] = btn8;
        buttonGrid[2][2] = btn9;

        game = new GameBoard();
        setPlayer(player1, player2);


    }

    /**
     * Set the next player
     */
    public void setPlayer(String player1, String player2) {

        this.player1 = player1;
        this.player2 = player2;

        if (count % 2 == 0){
            lblPlayer.setText(player1);
            game.getNextPlayer();

        }
        else {
            lblPlayer.setText(player2);
            game.getNextPlayer();
        }


        count++;
    }


    /**
     * Finds a winner or a draw and displays a message based
     *
     * @param winner
     */
    private void displayWinner(int winner) {
        String message = "";
        switch (winner) {
            case -1:
                message = "It's a draw :-(";
                break;
            default:
                if (winner == 0) {
                    message = "Player " + player1 + " wins!!!";
                }
                else  {
                    message = "Player " + player2 + " wins!!!";
                }
                break;
        }
        lblPlayer.setText(message);
    }

    /**
     * Clears the game board in the GUI
     */
    private void clearBoard() {
        for (Node n : gridPane.getChildren()) {
            Button btn = (Button) n;
            btn.setText("");
        }
    }
}
