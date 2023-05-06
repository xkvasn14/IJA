/**
 * @authors: xjalak00,xkvasn14
 */
package window;

import common.CommonMaze;
import game.*;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
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
import util.MapReader;
import util.PlayRecord;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class for displaying previous game
 */
public class RecordPlayer extends Window {
    private static final int FIELD_SIZE = 30;
    static int rows, cols;
    private static CommonMaze maze;
    static ArrayList<Log> logs = new ArrayList<>();
    static Stage primaryStage;
    static Scene scene;
    static Image play, arrows, wall, dirt, pacman, ghost, key, trapdoor;

    static String path;

    static int indexOfLog = 0;

    static boolean checkBoxSelected;
    static boolean isPlayingOnPlayPause;

    /**
     * Constructor
     * @param primaryStage Stage
     * @throws IOException if file cannot be created
     */
    public static void start(Stage primaryStage) throws IOException {
        InitImages();

        RecordPlayer.primaryStage = primaryStage;
        RecordPlayer.scene = generateMap();

        RecordPlayer.scene.setOnKeyPressed(RecordPlayer::handleKeyPress);

        //logs = PlayRecord.parseLogFromFile();

        // Set the title of the window
        RecordPlayer.primaryStage.setTitle("Display Previous Game");

        // Set the scene of the window
        RecordPlayer.primaryStage.setScene(RecordPlayer.scene);

        // Show the window
        RecordPlayer.primaryStage.show();

    }

    /**
     * Constructor
     * @param keyEvent keyboard event
     */
    private static void handleKeyPress(KeyEvent keyEvent) {
        System.out.println(indexOfLog);
        if (isPlayingOnPlayPause) {
            Objects.requireNonNull(keyEvent.getCode());// Play or Pause
        } else {
            switch (keyEvent.getCode()) {
                case LEFT -> {
                    indexOfLog--;
                    if (indexOfLog < 0)
                        indexOfLog = 0;
                    drawScene();
                }
                case RIGHT -> {
                    indexOfLog++;
                    if (indexOfLog >= logs.size())
                        indexOfLog = logs.size() - 1;
                    drawScene();
                }
            }
        }
    }

    /**
     * Draw Scene
     */
    public static void drawScene() {
        try {
            start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Generate Map
     * @return Scene of the map
     */
    public static Scene generateMap() {
        // Create a new GridPane
        GridPane gridPane = new GridPane();


        // Loop through the 2D array of fields
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // Create a new Rectangle to represent the field
                Rectangle rect = new Rectangle(FIELD_SIZE, FIELD_SIZE);
                Circle circle = new Circle((double) FIELD_SIZE / 2);
                Rectangle rect2 = new Rectangle(FIELD_SIZE, FIELD_SIZE);

                // Set the color of the rectangle based on the type of field
                if (maze.getField(i, j) instanceof WallField) {
                    rect.setFill(Color.GREY);
                    rect.setFill(new ImagePattern(wall));
                    gridPane.add(rect, j, i);
                } else if (maze.getField(i, j) instanceof PathField) {
                    rect.setFill(Color.WHITE);
                    rect.setFill(new ImagePattern(dirt));
                    gridPane.add(rect, j, i);
                }
                if (maze.getField(i, j).get() instanceof TargetObject) {
                    rect2.setFill(new ImagePattern(trapdoor));
                    gridPane.add(rect2, j, i);
                }
                if (indexOfLog < 0) {
                    if (maze.getField(i, j).get() instanceof PacmanObject) {
                        circle.setFill(new ImagePattern(pacman));
                        gridPane.add(circle, j, i);
                    } else if (maze.getField(i, j).get() instanceof GhostObject) {
                        circle.setFill(new ImagePattern(ghost));
                        gridPane.add(circle, j, i);
                    } else if (maze.getField(i, j).get() instanceof KeyObject) {
                        circle.setFill(new ImagePattern(key));
                        gridPane.add(circle, j, i);
                    }
                }
                // Add the rectangle to the GridPane at the appropriate row and column
            }
        }

        if (!(indexOfLog < 0)) {
            for (List<Integer> positions : logs.get(indexOfLog).getGhostPos()) {
                Circle circle = new Circle((double) FIELD_SIZE / 2);
                circle.setFill(new ImagePattern(ghost));
                gridPane.add(circle, positions.get(1), positions.get(0));
            }
            for (List<Integer> positions : logs.get(indexOfLog).getKeyPos()) {
                Circle circle = new Circle((double) FIELD_SIZE / 2);
                circle.setFill(new ImagePattern(key));
                gridPane.add(circle, positions.get(1), positions.get(0));
            }
            int[] positions = logs.get(indexOfLog).getPacmanPos();
            Circle circle = new Circle((double) FIELD_SIZE / 2);
            circle.setFill(new ImagePattern(pacman));
            gridPane.add(circle, positions[1], positions[0]);
        }


        VBox vBox = new VBox(setMenuBar(), gridPane);

        // Create a new Scene with the GridPane as the root node

        return new Scene(vBox);
    }

    /**
     * Initialize images
     */
    public static void InitImages() {
        play = new Image("file:data/img/play.png");
        arrows = new Image("file:data/img/arrows.png");
        wall = new Image("file:data/img/wall.png");
        dirt = new Image("file:data/img/dirt.png");
        pacman = new Image("file:data/img/pacman-pixel.png");
        ghost = new Image("file:data/img/ghost-green.png");
        key = new Image("file:data/img/key.png");
        trapdoor = new Image("file:data/img/trapdoor.png");
    }

    /**
     * Set the image of an ImageView
     *
     * @param img The image to set
     * @return The ImageView with the image set
     */
    public static ImageView setImageView(Image img) {
        ImageView image = new ImageView(img);
        image.setFitHeight(20);
        image.setFitWidth(20);
        return image;
    }

    /**
     * Set the menu bar
     * @return The menu bar
     */
    public static MenuBar setMenuBar() {

        // Menu views
        ImageView arrowsView = setImageView(arrows);

        MenuBar menuBar = new MenuBar();
        MenuItem mouse = new MenuItem("Mouse");
        MenuItem arrows = new MenuItem("Arrows");
        javafx.scene.control.Menu controls = new javafx.scene.control.Menu("Player Controls", arrowsView, mouse, arrows);
        menuBar.getMenus().add(controls);

        menuBar.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        menuBar.getStyleClass().setAll("split-menu-btn");

        return menuBar;
    }

    /**
     * Set the menu bar
     * @param args The arguments
     * @throws IOException If the file is not found
     */
    public static void main(String[] args) throws IOException {
        String[] paths = PlayRecord.readRecord(args[0]); //todo func return paths to new files
        if (paths.length != 2) {
            window.SystemWindow.error("Error", "File not found");
        }

        path = paths[1];
        logs = PlayRecord.parseLogFromFile(path);
        checkBoxSelected = Boolean.parseBoolean(args[1]);

        if(checkBoxSelected){
            indexOfLog = logs.size() - 1;
        }
        else
            indexOfLog = -1;
        // parse first file with map
        // create a map
        //MazeConfigure mazeConfigure = new MazeConfigure();
        try {
            MapReader mapReader = new MapReader();
            String path1 = paths[0];

            maze = mapReader.readMap(path1);
            rows = maze.numRows();
            cols = maze.numCols();
            start(new Stage());
        } catch (IOException e) {
            SystemWindow.error("Error", e.getMessage());
        }

    }
}
