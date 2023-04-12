package com.example.project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.IOException;



public class HelloApplication extends Application {

    // Define the size of each field on the grid
    private static final int FIELD_SIZE = 50;
    // Define the 2D array of fields
    private Field[][] fields;

    @Override
    public void start(Stage primaryStage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        // Create a new GridPane
        GridPane gridPane = new GridPane();

        // Loop through the 2D array of fields
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[i].length; j++) {
                // Create a new Rectangle to represent the field
                Rectangle rect = new Rectangle(FIELD_SIZE, FIELD_SIZE);

                // Set the color of the rectangle based on the type of field
                if (fields[i][j] instanceof WallField) {
                    rect.setFill(Color.GREY);
                } else if (fields[i][j] instanceof PathField) {
                    rect.setFill(Color.WHITE);
                }

                // Add the rectangle to the GridPane at the appropriate row and column
                gridPane.add(rect, j, i);
            }
        }

        // Create a new Scene with the GridPane as the root node
        Scene scene = new Scene(gridPane);

        // Set the title of the window
        primaryStage.setTitle("Map Display");

        // Set the scene of the window
        primaryStage.setScene(scene);

        // Show the window
        primaryStage.show();


    }

    // A helper method to create a sample 2D array of fields
    // This method will be replaced by our functionality
    private static Field[][] createMap() {
        // Create a map
        int rows = 10;
        int cols = 10;
        Field[][] map = new Field[rows][cols];


        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i == 0 || i == 9 || j == 0 || j == 9) {
                    map[i][j] = new WallField();
                } else {
                    map[i][j] = new PathField();
                }
            }
        }

        return map;
    }

    public static void main(String[] args) {
        launch();
    }
}