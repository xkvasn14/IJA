/**
 * @authors: xjalak00, xkvasn14
 * @file Menu.java
 * @brief Menu
 * @version 0.1
 * @date 2019-12-02
 */
package window;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

/**
 * Class for displaying menu
 */
public class Menu extends Application {
    /**
     * Text field for path to map
     */
    public TextField textBox_path_map;
    /**
     * Play button
     */
    public Button button_go;
    /**
     * Text field for path to record
     */
    public Button button_choose_map;
    /**
     * Play record button
     */
    public Button button_play;
    /**
     * Text field for path to record
     */
    public TextField textBox_path_view_record;
    /**
     * Button for choosing record
     */
    public Button button_choose_play;
    /**
     * Check box for reverse
     */
    public CheckBox checkBox_reverse;


    /**
     * Method for displaying menu
     * @param args arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Method for displaying menu
     * @param primaryStage stage
     */
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

    /**
     * Method for displaying information window
     */
    public void checkBox_reverse_click() {
    }

    /**
     * Method for displaying information window
     */
    public void button_choose_view_record_click() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Play File");
        File file = fileChooser.showOpenDialog(null);
        if(file != null) {
            textBox_path_view_record.setText(file.getAbsolutePath());
        }
    }

    /**
     * Method for displaying information window
     */
    public void button_choose_map_click() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Map File");
        File file = fileChooser.showOpenDialog(null);
        if(file != null) {
            textBox_path_map.setText(file.getAbsolutePath());
        }
    }

    /**
     * Method for displaying information window
     */
    public void button_play_click() {
        // read from textBox_path_map
        // SystemWindow open info
        String path = textBox_path_map.getText();
        try {
            Game.main(new String[]{path});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for displaying information window
     * @throws IOException exception
     */
    public void button_view_record_click() throws IOException {
        String path = textBox_path_view_record.getText();

        //PlayRecord.readRecord(path);
        RecordPlayer.main(new String[]{path, checkBox_reverse.isSelected() ? "true" : "false"});

    }
}
