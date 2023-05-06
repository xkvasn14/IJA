/**
 * @authors: xjalak00, xkvasn14
 */
package window;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * Class for displaying system windows
 */
public class SystemWindow extends Application {

    /**
     * Main method
     * @param args
     */
    public static void main(String[] args) {

        //launch(args);
    }

    /**
     * Start method
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
    }

    /**
     * Method for displaying information window
     * @param header
     * @param text
     */
    public static void info(String header, String text) {
        Platform.runLater(() -> {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(header);
        alert.setContentText(text);

        alert.showAndWait();
        });
    }

    /**
     * Method for displaying warning window
     * @param header
     * @param text
     */
    public static void warning(String header, String text)
    {
        Platform.runLater(() -> {

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning Dialog");
        alert.setHeaderText(header);
        alert.setContentText(text);

        alert.showAndWait();
    });
    }

    /**
     * Method for displaying error window
     * @param header
     * @param text
     */
    public static void error(String header, String text){
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(header);
            alert.setContentText(text);

            alert.showAndWait();
        });
    }

    /**
     * Method for displaying success window
     * @param header
     * @param text
     */
    public static void success(String header, String text){
        Platform.runLater(() -> {
        ImageView view = new ImageView("file:data/img/accept.png");
        view.setFitWidth(40);
        view.setFitHeight(40);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setGraphic(view);
        alert.setTitle("Success Dialog");
        alert.setHeaderText(header);
        alert.setContentText(text);

        alert.showAndWait();
        });
    }
}
