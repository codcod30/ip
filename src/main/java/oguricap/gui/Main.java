package oguricap.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import oguricap.OguriCap;

/**
 * A GUI for OguriCap using FXML.
 */
public class Main extends Application {

    private OguriCap oguriCap = new OguriCap("data/oguri_cap_tasks.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane root = fxmlLoader.load();
            Scene scene = new Scene(root);

            // inject OguriCap instance
            fxmlLoader.<MainWindow>getController().setOguriCap(oguriCap);

            stage.setScene(scene);
            stage.setTitle("Oguri Cap");
            stage.setMinHeight(600);
            stage.setMinWidth(400);
            stage.setResizable(true);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
