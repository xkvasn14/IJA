/**
 * @authors: xjalak00, xkvasn14
 */
package window;

import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Window;
import java.util.Optional;
import org.kordamp.bootstrapfx.BootstrapFX;

/**
 * Class for displaying game end windows
 */
public class GameEnd extends Window {

    public static DialogPane dialog;

    /**
     * Method for displaying victory window
     */
    public static void victory() {
        ImageView view = new ImageView("file:data/img/win2.png");
        view.setFitWidth(400);
        view.setFitHeight(300);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setGraphic(view);
        alert.setTitle("VICTORY");
        //alert.setHeaderText("Victory");
        alert.setHeaderText(null);
        alert.setContentText("Congratulations! You won!");

        dialog = alert.getDialogPane();
        dialog.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        dialog.getStyleClass().setAll("alert", "alert-success");

        ButtonType buttonMenu = new ButtonType("Return to Main Menu");
        alert.getButtonTypes().setAll(buttonMenu);

        alert.showAndWait();
        /*
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == buttonMenu) {

        } */
    }

    /**
     * Method for displaying game over window
     * @return true if restart game, false if return to main menu
     */
    public static boolean gameOver() {
        ImageView view = new ImageView("file:data/img/gameover.png");
        view.setFitWidth(400);
        view.setFitHeight(300);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setGraphic(view);
        alert.setTitle("GAME OVER");
        dialog = alert.getDialogPane();
        //dialog.setHeaderText("GameOver");
        dialog.setHeaderText(null);
        dialog.setContentText("You Lost! Try again");

        //dialog.getStylesheets().add(Objects.requireNonNull(GameEnd.class.getResource("../data/css/style.css")).toExternalForm());
        //dialog.getStylesheets().add((GameEnd.class.getResource("style.css")).toExternalForm());
        //dialog.getStylesheets().add("file:style.css");

        dialog.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        dialog.getStyleClass().setAll("alert", "alert-danger");
        //dialog.getHeader().getStyleClass().setAll("h1","lbl", "lbl-danger");
        ButtonType buttonRestart = new ButtonType("Restart Game", ButtonBar.ButtonData.OK_DONE);
        ButtonType buttonMenu = new ButtonType("Main Menu", ButtonBar.ButtonData.CANCEL_CLOSE);

        //ButtonType buttonRestart = ButtonType.OK;
        //ButtonType buttonMenu = ButtonType.CANCEL;
        //((Button) dialog.lookupButton(ButtonType.OK)).setText("Not OK Anymore");
        //((Button) dialog.lookupButton(ButtonType.OK)).getStyleClass().setAll("btn","btn-danger");
        //((Button) dialog.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("Not Cancel Anymore");

        dialog.getButtonTypes().clear();
        dialog.getButtonTypes().addAll(buttonRestart, buttonMenu);

        (dialog.lookupButton(buttonMenu)).getStyleClass().setAll("btn","btn-danger"); //todo

        dialog.getButtonTypes().setAll(buttonRestart, buttonMenu);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent()) {
            if (result.get() == buttonRestart) {
                // restart game
                return true;
            } else if (result.get() == buttonMenu) {
                // close window
                return false;
            }
        }
        return false;
    }

}
