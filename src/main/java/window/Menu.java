package window;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Menu extends Application {

    public TextField textBox_path_map;
    public Button button_go;
    public Button button_choose_map;
    public Button button_map2;
    public Button button_map1;
    public Button button_map3;
    public Button button_play;
    public TextField textBox_path_play;
    public Button button_choose_play;
    public CheckBox checkBox_reverse;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        // this is Menu
        FXMLLoader fxmlLoader = new FXMLLoader(Menu.class.getResource("menu.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void checkBox_reverse_click(ActionEvent actionEvent) {
    }

    public void button_choose_play_click(ActionEvent actionEvent) {
    }

    public void button_play_click(ActionEvent actionEvent) {
    }

    public void button_map1_click(ActionEvent actionEvent) {
    }

    public void button_map2_click(ActionEvent actionEvent) {
    }

    public void button_map3_click(ActionEvent actionEvent) {
    }

    public void button_choose_map_click(ActionEvent actionEvent) {
    }

    public void button_go_click(ActionEvent actionEvent) {
    }
}
