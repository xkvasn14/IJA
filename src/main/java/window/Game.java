/**
 * Class representing game window
 * @authors xjalak00, xkvasn14
 */

package window;

import common.*;
import game.*;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
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
import util.Logging;
import util.MapReader;
import java.io.IOException;
import java.util.Random;


/**
 * Class representing game window
 */
public class Game extends Window {

    // Define the size of each field on the grid
    private static final int FIELD_SIZE = 30;
    static int rows;
    static int cols;
    private static CommonMaze maze;

    static Stage primaryStage;
    static Scene scene;

    static Logging logging;
    static String[] args;

    static Image play, arrows, steps, heart, wall, dirt, pacman, ghost, key, trapdoor;


    /***
     * Start method of game class
     * @param primaryStage primary stage
     * @throws InterruptedException if interrupted
     */
    public static void start(Stage primaryStage) throws InterruptedException {
        InitImages();

        Game.primaryStage = primaryStage;
        scene = generateMap();

        moveGhosts();

        // TODO Event handlers-example
        scene.setOnKeyPressed(Game::handleKeys);

        scene.setOnMousePressed(event -> {
            Node clickedNode = event.getPickResult().getIntersectedNode();
            Integer colIndex = GridPane.getColumnIndex(clickedNode);
            Integer rowIndex = GridPane.getRowIndex(clickedNode);
            System.out.println(colIndex + ":" + rowIndex);

            PathField currentField = (PathField) maze.pacman().getField();

            int counter = 0;
            while (!(colIndex == currentField.getCol() && rowIndex == currentField.getRow())) {

                moveInCol(currentField, colIndex);
                try {
                    moveInRow(currentField, rowIndex);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                currentField = (PathField) maze.pacman().getField();
                counter++;

                if (counter == 20) {
                    break;
                }
            }
            gameEnd();
        });

        // Set the title of the window
        Game.primaryStage.setTitle("Map Display");

        // Set the scene of the window
        Game.primaryStage.setScene(scene);

        // Show the window
        Game.primaryStage.show();

    }

    /**
     * Makes a new log and redraws scene
     */
    public static void logAndDraw() {
        try {
            logging.log();
            start(primaryStage);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Initializes images
     */
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

    /**
     * Sets image icons in menu bar
     * @param img menu icon
     * @return resized image
     */
    public static ImageView setImageView(Image img) {
        ImageView image = new ImageView(img);
        image.setFitHeight(20);
        image.setFitWidth(20);
        return image;
    }

    /**
     * Move pacman in column
     * @param currentField pacman is here
     * @param end number of column where we want to move to
     */
    public static void moveInCol(PathField currentField, int end) {
        while (!(end == currentField.getCol())) {
            boolean left = false;
            boolean right = false;
            if (end > currentField.getCol()) {
                right = maze.pacman().move(CommonField.Direction.R);
            } else if (end < currentField.getCol()) {
                left = maze.pacman().move(CommonField.Direction.L);
           }

           logAndDraw();
           currentField = (PathField) maze.pacman().getField();

           if (!right && !left) {
               break;
           }
        }
    }

    /**
     * Move pacman in row
     * @param currentField pacman is here
     * @param end number of row where we want to move to
     * @throws InterruptedException if interrupted
     */
    public static void moveInRow(PathField currentField, int end) throws InterruptedException {
        while (!(end == currentField.getRow())) {
            boolean up = false;
            boolean down = false;

            if (end > currentField.getRow()) {
                down = maze.pacman().move(CommonField.Direction.D);
            } else if (end < currentField.getRow()) {
                up = maze.pacman().move(CommonField.Direction.U);
            }

            logAndDraw();
            currentField = (PathField) maze.pacman().getField();

            if (!up && !down) {
                break;
            }
        }
    }

    /**
     * Generates map
     * @return scene with map
     */
    public static Scene generateMap() {
        // Create a new GridPane
        GridPane gridPane = new GridPane();

        // Loop through the 2D array of fields
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // Create a new Rectangle to represent the field
                Rectangle rect = new Rectangle(FIELD_SIZE, FIELD_SIZE);
                Circle circle = new Circle((double) FIELD_SIZE /2);
                Rectangle rect2 = new Rectangle(FIELD_SIZE, FIELD_SIZE);

                // Set the color of the rectangle based on the type of field
                if (maze.getField(i,j) instanceof WallField) {
                    rect.setFill(Color.GREY);
                    rect.setFill(new ImagePattern(wall));
                    gridPane.add(rect, j, i);
                } else if (maze.getField(i,j) instanceof PathField) {
                    rect.setFill(Color.WHITE);
                    rect.setFill(new ImagePattern(dirt));
                    gridPane.add(rect, j, i);
                    if(maze.getField(i,j).get() instanceof PacmanObject){
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
                    }
                }
                // Add the rectangle to the GridPane at the appropriate row and column
            }
        }

        VBox vBox = new VBox(setMenuBar(), gridPane);

        // Create a new Scene with the GridPane as the root node
        return new Scene(vBox);
    }

    /**
     * Sets menu bar of the game window
     * @return menu bar
     */
    public static MenuBar setMenuBar() {

        // Menu views
        ImageView arrowsView = setImageView(arrows);
        ImageView keyView = setImageView(key);
        ImageView heartView = setImageView(heart);
        ImageView ghostView = setImageView(ghost);
        ImageView stepsView = setImageView(steps);

        MenuBar menuBar = new MenuBar();
        Menu controls = new Menu("", arrowsView);
        menuBar.getMenus().add(controls);

        Menu keys = new Menu(Integer.toString(maze.keys().size()), keyView);
        menuBar.getMenus().add(keys);

        Menu ghost = new Menu(Integer.toString(maze.ghosts().size()), ghostView);
        menuBar.getMenus().add(ghost);

        Menu lives = new Menu(Integer.toString(maze.pacman().getLives()), heartView);
        menuBar.getMenus().add(lives);

        Menu steps = new Menu(Integer.toString(maze.pacman().getSteps()), stepsView);
        menuBar.getMenus().add(steps);

        menuBar.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        menuBar.getStyleClass().setAll("split-menu-btn");

        return menuBar;
    }


    /**
     * Moves ghosts on map
     */
    public static void moveGhosts() {
        Random rand = new Random(System.currentTimeMillis());

        for(CommonMazeObject ghost : maze.ghosts()) {
            int n = rand.nextInt(4);
            //int time = rand.nextInt(500);
            switch (n) {
                case 1 -> ghost.move(CommonField.Direction.D);
                case 2 -> ghost.move(CommonField.Direction.R);
                case 3 -> ghost.move(CommonField.Direction.L);
                default -> ghost.move(CommonField.Direction.U);
            }
        }
        generateMap();
    }

    /**
     * Event handler for key press
     * @param event pressed key
     */
    public static void handleKeys(KeyEvent event) {

        switch (event.getCode()) {
            case UP, W -> {
                maze.pacman().move(CommonField.Direction.U);
                logAndDraw();
            }
            case DOWN, S -> {
                maze.pacman().move(CommonField.Direction.D);
                logAndDraw();
            }
            case LEFT, A -> {
                maze.pacman().move(CommonField.Direction.L);
                logAndDraw();
            }
            case RIGHT, D -> {
                maze.pacman().move(CommonField.Direction.R);
                logAndDraw();
            }
        }
        gameEnd();
    }

    /**
     * Shows alert window after game ends
     */
    public static void gameEnd() {
        if (maze.pacman().getVictory() == 1) {

            primaryStage.close();
            primaryStage = null;
            GameEnd.victory();
            // close the window
        }
        else if (maze.pacman().getVictory() == -1 || maze.pacman().getLives() <= 0) {

            primaryStage.close();
            primaryStage = null;
            boolean restart = GameEnd.gameOver();
            if (restart) {
                // restart game
                Game.main(args);
            }
            // close the window
        }
    }

    /**
     * Main method of game class
     * @param args arguments of program
     */
    public static void main(String[] args) {
        //save args for restart
        Game.args = args;

        if(primaryStage != null)
            return;

        // create a map
        //MazeConfigure mazeConfigure = new MazeConfigure();

        try {
            MapReader mapReader = new MapReader();
            String path = args[0];
            maze = mapReader.readMap(path);
            rows = maze.numRows();
            cols = maze.numCols();
            createNewFileLog(path);
            start(new Stage());
        }
        catch (IOException e) {
            SystemWindow.error("Error", e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Creates new log
     * @param path path of map base
     * @throws IOException exception
     */
    public static void createNewFileLog(String path) throws IOException {
        logging = new Logging(maze, path);
        logging.createLog(path);
    }
}
