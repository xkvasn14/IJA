package window;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class SystemWindow extends Application {

    public static void main(String[] args) {

        //launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
    }

    public static void info(String header, String text) {
        Platform.runLater(() -> {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(header);
        alert.setContentText(text);

        alert.showAndWait();
        });
    }

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

    public static void error(String header, String text){
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(header);
            alert.setContentText(text);

            alert.showAndWait();
        });
    }

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
