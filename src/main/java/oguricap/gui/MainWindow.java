package oguricap.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import oguricap.OguriCap;

public class MainWindow extends AnchorPane {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private OguriCap oguriCap;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image oguriImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setOguriCap(OguriCap oc) {
        this.oguriCap = oc;
        // show welcome message from OguriCap
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(oguriCap.getWelcomeMessage(), oguriImage)
        );
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = oguriCap.getResponse(input); // OguriCap parses command

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, oguriImage)
        );

        userInput.clear();

        if (oguriCap.isExitCommand(input)) {
            userInput.setDisable(true);
            sendButton.setDisable(true);
            // close window after a short delay
            userInput.getScene().getWindow().hide();
        }
    }
}
