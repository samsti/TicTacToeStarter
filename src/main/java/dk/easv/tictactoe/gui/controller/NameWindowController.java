package dk.easv.tictactoe.gui.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class NameWindowController {


    @FXML
    private TextField user1;

    @FXML
    private TextField user2;


    @FXML
    private void clickOpenWindow() throws IOException {
        // Get the input from both user fields
        String inputText1 = user1.getText();
        String inputText2 = user2.getText();

        // Validate both usernames are not empty
        if (!inputText1.trim().isEmpty() && !inputText2.trim().isEmpty()) {
            // Load the TicTacView.fxml window
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/TicTacView.fxml"));
            Parent root = loader.load();

            // Get the controller of the TicTacView.fxml
            TicTacViewController tictacviewcontroller = loader.getController();

            // Pass the player names to the TicTacViewController
            tictacviewcontroller.setPlayer(inputText1, inputText2);

            // Create a new stage for the TicTacToe game
            Stage stage = new Stage();
            stage.setTitle("Tic Tac Game");
            stage.setScene(new Scene(root));
            stage.show();

            // Close the current name input window
            Stage currentStage = (Stage) user1.getScene().getWindow();
            currentStage.close();
        } else {
            // Print message if either username is empty
            System.out.println("Enter valid username!");
        }
    }
}
