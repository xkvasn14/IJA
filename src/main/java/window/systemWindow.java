package window;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class systemWindow extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        GridPane gridPane = new GridPane();
        Button button = new Button("Start");
        button.setPrefSize(100, 50);
        gridPane.add(button, 100, 100);

        Scene scene = new Scene(gridPane, 300, 250);

        primaryStage.setTitle("Hello World!");

        primaryStage.setScene(scene);

        primaryStage.show();

    }
}
