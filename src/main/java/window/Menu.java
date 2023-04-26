package window;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import util.PlayRecord;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Menu extends Application {

    public TextField textBox_path_map;
    public Button button_go;
    public Button button_choose_map;
    public Button button_map2;
    public Button button_map1;
    public Button button_map3;
    public Button button_play;
    public TextField textBox_path_view_record;
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
        //scene.getStylesheets().add(Objects.requireNonNull(Menu.class.getResource("file:style.css")).toString());
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void checkBox_reverse_click(ActionEvent actionEvent) {
    }

    public void button_choose_view_record_click(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Play File");
        File file = fileChooser.showOpenDialog(null);
        if(file != null) {
            textBox_path_view_record.setText(file.getAbsolutePath());
        }
    }

    public void button_choose_map_click(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Map File");
        File file = fileChooser.showOpenDialog(null);
        if(file != null) {
            textBox_path_map.setText(file.getAbsolutePath());
        }
    }

    public void button_play_click(ActionEvent actionEvent) {
        // read from textBox_path_map
        // SystemWindow open info
        String path = textBox_path_map.getText();
        try {
            Game.main(new String[]{path});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void button_map1_click(ActionEvent actionEvent) {
        File file = new File("data/map/map1.txt");
        textBox_path_map.setText(file.getAbsolutePath());
        button_play_click(actionEvent);
    }

    public void button_map2_click(ActionEvent actionEvent) {
        File file = new File("data/map/map2.txt");
        textBox_path_map.setText(file.getAbsolutePath());
        button_play_click(actionEvent);
    }

    public void button_map3_click(ActionEvent actionEvent) {
        File file = new File("data\\map\\map3.txt");
        textBox_path_map.setText(file.getAbsolutePath());
        button_play_click(actionEvent);
    }

    public void button_view_record_click(ActionEvent actionEvent) throws IOException {
        String path = textBox_path_view_record.getText();

        //PlayRecord.readRecord(path);
        RecordPlayer.main(new String[]{path, checkBox_reverse.isSelected() ? "true" : "false"});

    }
}
