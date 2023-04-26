package window;

import common.CommonMaze;
import game.*;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.kordamp.bootstrapfx.BootstrapFX;
import util.Log;
import util.Logging;
import util.MapReader;
import util.PlayRecord;

import java.io.IOException;
import java.util.ArrayList;

public class RecordPlayer extends Window {
    private static final int FIELD_SIZE = 30;
    static int rows, cols;
    private static CommonMaze maze;
    static ArrayList<Log> logs = new ArrayList<>();
    //static Logging logging;
    static Stage primaryStage;
    static Scene scene;
    static Image play, arrows, steps, heart, wall, dirt, pacman, ghost, key, trapdoor;

    public static void start(Stage primaryStage) {
        InitImages();

        RecordPlayer.primaryStage = primaryStage;
        RecordPlayer.scene = generateMap();

        // Set the title of the window
        RecordPlayer.primaryStage.setTitle("Display Previous Game");

        // Set the scene of the window
        RecordPlayer.primaryStage.setScene(RecordPlayer.scene);

        // Show the window
        RecordPlayer.primaryStage.show();

    }

    public static Scene generateMap() {
        // Create a new GridPane
        GridPane gridPane = new GridPane();

        // Loop through the 2D array of fields
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // Create a new Rectangle to represent the field
                Rectangle rect = new Rectangle(FIELD_SIZE, FIELD_SIZE);
                //Circle circle = new Circle((double) FIELD_SIZE /2);
                //Rectangle rect2 = new Rectangle(FIELD_SIZE, FIELD_SIZE);

                // Set the color of the rectangle based on the type of field
                if (maze.getField(i,j) instanceof WallField) {
                    rect.setFill(Color.GREY);
                    rect.setFill(new ImagePattern(wall));
                    gridPane.add(rect, j, i);
                } else if (maze.getField(i,j) instanceof PathField) {
                    rect.setFill(Color.WHITE);
                    rect.setFill(new ImagePattern(dirt));
                    gridPane.add(rect, j, i);
                    /*if(maze.getField(i,j).get() instanceof PacmanObject){
                        circle.setFill(new ImagePattern(pacman));
                        gridPane.add(circle, j, i);
                    } else if(maze.getField(i,j).get() instanceof GhostObject){
                        circle.setFill(new ImagePattern(ghost));
                        gridPane.add(circle, j, i);
                    } else if(maze.getField(i,j).get() instanceof KeyObject){
                        circle.setFill(new ImagePattern(key));
                        gridPane.add(circle, j, i);
                    } else if(maze.getField(i,j).get() instanceof TargetObject){
                        rect2.setFill(new ImagePattern(trapdoor));
                        gridPane.add(rect2, j, i);
                    }*/
                }
                // Add the rectangle to the GridPane at the appropriate row and column
            }
        }

        VBox vBox = new VBox(setMenuBar(), gridPane);

        // Create a new Scene with the GridPane as the root node

        return new Scene(vBox);
    }

    public static void InitImages() {
        play = new Image("file:data/img/play.png");
        arrows = new Image("file:data/img/arrows.png");
        steps = new Image("file:data/img/steps.png");
        heart = new Image("file:data/img/heart.png");
        wall = new Image("file:data/img/wall.png");
        dirt = new Image("file:data/img/dirt.png");
        pacman = new Image("file:data/img/pacman-pixel.png");
        ghost = new Image("file:data/img/ghost-green.png");
        key = new Image("file:data/img/key.png");
        trapdoor = new Image("file:data/img/trapdoor.png");
    }

    public static ImageView setImageView(Image img) {
        ImageView image = new ImageView(img);
        image.setFitHeight(20);
        image.setFitWidth(20);
        return image;
    }

    public static MenuBar setMenuBar() {

        // Menu views
        ImageView arrowsView = setImageView(arrows);
        ImageView keyView = setImageView(key);
        ImageView heartView = setImageView(heart);
        ImageView ghostView = setImageView(ghost);
        ImageView stepsView = setImageView(steps);

        MenuBar menuBar = new MenuBar();
        MenuItem mouse = new MenuItem("Mouse");
        MenuItem arrows = new MenuItem("Arrows");
        javafx.scene.control.Menu controls = new javafx.scene.control.Menu("Player Controls", arrowsView, mouse, arrows);
        //MenuButton controls = new MenuButton("Player Controls", arrowsView);
        menuBar.getMenus().add(controls);



        //MenuButton menuButton = new MenuButton("Options", imageView, menuItem1, menuItem2, menuItem3);
/*
        javafx.scene.control.Menu keys = new javafx.scene.control.Menu(Integer.toString(maze.keys().size()), keyView);
        menuBar.getMenus().add(keys);

        javafx.scene.control.Menu ghost = new javafx.scene.control.Menu(Integer.toString(maze.ghosts().size()), ghostView);
        menuBar.getMenus().add(ghost);

        javafx.scene.control.Menu lives = new javafx.scene.control.Menu(Integer.toString(maze.pacman().getLives()), heartView);
        menuBar.getMenus().add(lives);

        javafx.scene.control.Menu steps = new Menu(Integer.toString(maze.pacman().getSteps()), stepsView);
        menuBar.getMenus().add(steps); */

        menuBar.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        menuBar.getStyleClass().setAll("split-menu-btn");

        return menuBar;
    }

    public static void main(String[] args) throws IOException {
        String[] paths = PlayRecord.readRecord(args[0]); //todo func return paths to new files
        if (paths.length != 2) {
            window.SystemWindow.error("Error", "File not found");
        }


        // parse first file with map
        // create a map
        MazeConfigure mazeConfigure = new MazeConfigure();
        try {
            MapReader mapReader = new MapReader();
            String path = paths[0];
            maze = mapReader.readMap(path);
            rows = maze.numRows();
            cols = maze.numCols();
            start(new Stage());
        }
        catch (IOException e) {
            SystemWindow.error("Error", e.getMessage());
        }


        // parse second file with logs
        try {
            logs = PlayRecord.parseLogFromFile(paths[1]);
        }
        catch (IOException e) {
            SystemWindow.error("Error", e.getMessage());
        }

    }
}
