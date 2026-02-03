package oguricap.gui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
    }

    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        dialog.getStyleClass().add("reply-label"); // keep existing flipped style
    }

    /**
     * Applies style to the dialog box based on command type and user/bot.
     * @param commandType
     * @param isUser
     */
    private void applyStyle(String commandType, boolean isUser) {
        if (isUser) {
            dialog.getStyleClass().add("user-label"); // pastel green for user
        }

        if (commandType != null) {
            switch (commandType) {
            case "AddCommand": dialog.getStyleClass().add("add-label");
            break;
            case "MarkCommand": dialog.getStyleClass().add("marked-label");
            break;
            case "UnmarkCommand": dialog.getStyleClass().add("unmarked-label");
            break;
            case "DeleteCommand": dialog.getStyleClass().add("delete-label");
            break;
            default: break;
            }
        }
    }

    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.applyStyle(null, true); // user bubble
        return db;
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.applyStyle(null, false); // bot bubble
        return db;
    }

    // Overloaded to allow passing command type
    public static DialogBox getDukeDialog(String text, Image img, String commandType) {
        var db = new DialogBox(text, img);
        db.flip();
        db.applyStyle(commandType, false);
        return db;
    }
}
