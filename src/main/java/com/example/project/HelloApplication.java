package com.example.project;

import common.CommonMaze;
import game.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.IOException;



public class HelloApplication extends Application {

    // Define the size of each field on the grid
    private static final int FIELD_SIZE = 50;
    int rows = 4+2;
    int cols = 3+2;
    private static CommonMaze maze;

    @Override
    public void start(Stage primaryStage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        // Create a new GridPane
        GridPane gridPane = new GridPane();

        // Loop through the 2D array of fields
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // Create a new Rectangle to represent the field
                Rectangle rect = new Rectangle(FIELD_SIZE, FIELD_SIZE);
                Circle circle = new Circle(FIELD_SIZE/2);

                // Set the color of the rectangle based on the type of field
                if (maze.getField(i,j) instanceof WallField) {
                    rect.setFill(Color.GREY);
                    gridPane.add(rect, j, i);
                } else if (maze.getField(i,j) instanceof PathField) {
                    rect.setFill(Color.WHITE);
                    gridPane.add(rect, j, i);
                    if(maze.getField(i,j).get() instanceof PacmanObject){
                        circle.setFill(Color.RED);
                        gridPane.add(circle, j, i);
                    } else if(maze.getField(i,j).get() instanceof GhostObject){
                        circle.setFill(Color.BLUE);
                        gridPane.add(circle, j, i);
                    } else if(maze.getField(i,j).get() instanceof KeyObject){
                        circle.setFill(Color.YELLOW);
                        gridPane.add(circle, j, i);
                    } else if(maze.getField(i,j).get() instanceof TargetObject){
                        circle.setFill(Color.GREEN);
                        gridPane.add(circle, j, i);
                    }
                }


                // Add the rectangle to the GridPane at the appropriate row and column

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



    public static void main(String[] args) {
        // create a map
        MazeConfigure mazeConfigure = new MazeConfigure();
        mazeConfigure.startReading(4,3);
        mazeConfigure.processLine("..G");
        mazeConfigure.processLine(".XK");
        mazeConfigure.processLine(".XT");
        mazeConfigure.processLine(".S.");
        mazeConfigure.stopReading();
        maze = mazeConfigure.createMaze();
        launch();
    }
}