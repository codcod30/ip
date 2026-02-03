package oguricap.gui;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import oguricap.OguriCap;

/**
 * Controller for the main GUI.
 */
public class MainWindow {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private OguriCap oguriCap;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/oguri.png"));

    /** Called by FXML loader after fields are injected */
    @FXML
    public void initialize() {
        // Scroll automatically to the bottom
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        sendButton.setOnMouseClicked(event -> handleUserInput());
        userInput.setOnAction(event -> handleUserInput());
    }

    /** Injects the OguriCap instance */
    public void setOguriCap(OguriCap oc) {
        this.oguriCap = oc;

        // Show welcome message now that oguriCap is injected
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(oguriCap.getWelcomeMessage(), dukeImage)
        );
    }

    /** Handle user input, append dialogs, and clear the input */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.isEmpty()) {
            return;
        }

        String response = oguriCap.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );

        userInput.clear();

        if (oguriCap.isExitCommand(input)) {
            // Use PauseTransition to delay closing
            javafx.animation.PauseTransition delay = new javafx.animation.PauseTransition(javafx.util.Duration.seconds(1));
            delay.setOnFinished(event -> System.exit(0));
            delay.play();
        }
    }

    // ===== Helper methods for testing or other use =====

    public VBox getDialogContainer() {
        return dialogContainer;
    }

    public ScrollPane getScrollPane() {
        return scrollPane;
    }

    public TextField getUserInput() {
        return userInput;
    }

    public Button getSendButton() {
        return sendButton;
    }

    public Stage getWindow() {
        return (Stage) scrollPane.getScene().getWindow();
    }

    public Scene getScene() {
        return scrollPane.getScene();
    }

}

