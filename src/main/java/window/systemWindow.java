package window;

import javafx.application.Application;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class systemWindow extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
    }

    public static void info(String header, String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(header);
        alert.setContentText(text);

        alert.showAndWait();
    }

    public static void warning(String header, String text)
    {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning Dialog");
        alert.setHeaderText(header);
        alert.setContentText(text);

        alert.showAndWait();
    }

    public static void error(String header, String text){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setHeaderText(header);
        alert.setContentText(text);

        alert.showAndWait();
    }

    public static void success(String header, String text){
        ImageView view = new ImageView("file:data/img/accept.png");
        view.setFitWidth(40);
        view.setFitHeight(40);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setGraphic(view);
        alert.setTitle("Success Dialog");
        alert.setHeaderText(header);
        alert.setContentText(text);

        alert.showAndWait();
    }
}
