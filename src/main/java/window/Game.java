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

import static java.lang.Thread.sleep;

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


    public static void start(Stage primaryStage) throws InterruptedException {
        InitImages();

        Game.primaryStage = primaryStage;
        Game.scene = generateMap();

        moveGhosts();  // todo ak bude cas thread

        // TODO Event handlers-example
        Game.scene.setOnKeyPressed(Game::handleKeys);


        Game.scene.setOnMousePressed(event -> {
            Node clickedNode = event.getPickResult().getIntersectedNode();
            Integer colIndex = GridPane.getColumnIndex(clickedNode);
            Integer rowIndex = GridPane.getRowIndex(clickedNode);
            System.out.println(colIndex + ":" + rowIndex);

            PathField currentField = (PathField) maze.pacman().getField();

            // Pathfinding path = new Pathfinding();

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
/*
                try {
                    start(Game.primaryStage);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } */
            }
        });

        // Set the title of the window
        Game.primaryStage.setTitle("Map Display");

        // Set the scene of the window
        Game.primaryStage.setScene(Game.scene);

        // Show the window
        Game.primaryStage.show();

    }

    public static void logAndDraw() {
        try {
            logging.log();
            start(primaryStage);
            //generateMap();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
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

    public static Scene generateMap() {
        // Create a new GridPane
        GridPane gridPane = new GridPane();

        // Loop through the 2D array of fields
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // Create a new Rectangle to represent the field
                Rectangle rect = new Rectangle(FIELD_SIZE, FIELD_SIZE);
                Circle circle = new Circle(FIELD_SIZE/2);
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


    public static void moveGhosts() throws InterruptedException {
        Random rand = new Random(System.currentTimeMillis());

        for(CommonMazeObject ghost : maze.ghosts()) {
            int n = rand.nextInt(4);
            int time = rand.nextInt(500);
            switch (n) {
                case 1 -> ghost.move(CommonField.Direction.D);
                case 2 -> ghost.move(CommonField.Direction.R);
                case 3 -> ghost.move(CommonField.Direction.L);
                default -> ghost.move(CommonField.Direction.U);
            }
            //Thread.sleep(time);
        }
        // Thread.sleep(1000);
        // start(primaryStage);
        generateMap();
    }


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

    public static void gameEnd() {
        if (maze.pacman().getVictory() == 1) {
            //todo stylesheet
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
                //main(Game.args);
                Game.main(args);
            }
            else {
                // close the window
            }
        }
    }

    public static void main(String[] args) {
        //save args for restart
        Game.args = args;

        if(primaryStage != null)
            return;

        // create a map
        MazeConfigure mazeConfigure = new MazeConfigure();

        try {
            MapReader mapReader = new MapReader();
            //String path = "data\\maps\\map2.txt";
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

    public static void createNewFileLog(String path) throws IOException {
        logging = new Logging(maze, path);
        logging.createLog(path);
    }
}
