package window;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Objects;
import java.util.Optional;

public class GameEnd extends Application {

    private static DialogPane dialog;


    @Override
    public void start(Stage stage) throws Exception {

    }

    public static void victory() {
        //Platform.runLater(() -> {
            ImageView view = new ImageView("file:data/img/accept.png");
            view.setFitWidth(40);
            view.setFitHeight(40);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setGraphic(view);
            alert.setTitle("VICTORY");
            alert.setHeaderText("Victory");
            alert.setContentText("Congratulations! You won!");

            dialog = alert.getDialogPane();

            ButtonType buttonMenu = new ButtonType("Main Menu");
            ButtonType buttonQuit = new ButtonType("Quit");

            alert.getButtonTypes().setAll( buttonMenu, buttonQuit);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent()) {
                if (result.get() == buttonMenu) {
                    // ... user chose "Two"
                } else {
                    // ... user chose CANCEL or closed the dialog
                }
            }
       // });
    }

    public static void gameOver() {
        //Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("GAME OVER");
            alert.setHeaderText("GameOver");
            alert.setContentText("You Lost! Try again");

            dialog = alert.getDialogPane();
            //dialog.getStylesheets().add(Objects.requireNonNull(GameEnd.class.getResource("../data/css/style.css")).toString().toExternalForm());
            //dialog.getStylesheets().add(Objects.requireNonNull(GameEnd.class.getResource("style.css")).toExternalForm());
            //dialog.getStylesheets().add("file:style.css");

            ButtonType buttonRestart = new ButtonType("Restart Game");
            ButtonType buttonMenu = new ButtonType("Main Menu");
            ButtonType buttonQuit = new ButtonType("Quit");

            alert.getButtonTypes().setAll(buttonRestart, buttonMenu, buttonQuit);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent()) {
                if (result.get() == buttonRestart){
                    // ... user chose "One"
                } else if (result.get() == buttonMenu) {
                    // ... user chose "Two"
                } else {
                    // ... user chose CANCEL or closed the dialog
                }
            }
        //});
    }

}
