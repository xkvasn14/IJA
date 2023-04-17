package window;

import common.CommonField;
import common.CommonMaze;
import game.*;
import javafx.application.Application;
import javafx.event.EventHandler;
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
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.IOException;



public class HelloApplication extends Application {

    // Define the size of each field on the grid
    private static final int FIELD_SIZE = 50;
    int rows = 4+2;
    int cols = 3+2;
    private static CommonMaze maze;
    
    Image play = new Image("file:data/img/play.png");
    Image arrows = new Image("file:data/img/arrows.png");
    Image steps = new Image("file:data/img/steps.png");
    Image heart = new Image("file:data/img/heart.png");
    Image wall = new Image("file:data/img/wall.png");
    Image dirt = new Image("file:data/img/dirt.png");
    Image pacman = new Image("file:data/img/pacman-pixel.png");
    Image ghost = new Image("file:data/img/ghost-green.png");
    Image key = new Image("file:data/img/key.png");
    Image trapdoor = new Image("file:data/img/trapdoor.png");


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


        // Menu views
        ImageView arrowsView = setImageView(arrows);
        ImageView keyView = setImageView(key);
        ImageView heartView = setImageView(heart);
        ImageView ghostView = setImageView(ghost);
        ImageView stepsView = setImageView(steps);

        // Create Menu bar
        MenuBar menuBar = new MenuBar(new Menu("", arrowsView), new Menu("1", keyView), new Menu("3", heartView),
                new Menu("1", ghostView), new Menu("5", stepsView));
        menuBar.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());

        VBox vBox = new VBox(menuBar, gridPane);

        // Create a new Scene with the GridPane as the root node
        Scene scene = new Scene(vBox);


        //////////////
        // TODO Event handlers-example
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP, W -> {
                        maze.pacman().move(CommonField.Direction.U);
                        try {
                            start(primaryStage);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    case DOWN, S -> {
                        maze.pacman().move(CommonField.Direction.D);
                        try {
                            start(primaryStage);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    case LEFT, A -> {
                        maze.pacman().move(CommonField.Direction.L);
                        try {
                            start(primaryStage);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    case RIGHT, D -> {
                        maze.pacman().move(CommonField.Direction.R);
                        try {
                            start(primaryStage);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        });
        //////////////


        scene.setOnMousePressed(event -> {
            Node clickedNode = event.getPickResult().getIntersectedNode();
            Integer colIndex = GridPane.getColumnIndex(clickedNode);
            Integer rowIndex = GridPane.getRowIndex(clickedNode);
            System.out.println(colIndex + ":" + rowIndex);

            PathField currentField = (PathField) maze.pacman().getField();
            while (!(colIndex == currentField.getCol() && rowIndex == currentField.getRow())) {
                /* if (maze.getField(currentField.getCol(), currentField.getRow()) instanceof WallField) {
                    break;
                } */ // todo search algoritmus
                if (colIndex > currentField.getCol()) {
                    maze.pacman().move(CommonField.Direction.R);
                }
                else if (colIndex < currentField.getCol()) {
                    maze.pacman().move(CommonField.Direction.L);
                }
                else if (rowIndex > currentField.getRow()) {
                    maze.pacman().move(CommonField.Direction.D);
                }
                else if (rowIndex < currentField.getRow()) {
                    maze.pacman().move(CommonField.Direction.U);
                }
                currentField = (PathField) maze.pacman().getField();
                try {
                    start(primaryStage);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        // Set the title of the window
        primaryStage.setTitle("Map Display");

        // Set the scene of the window
        primaryStage.setScene(scene);

        // Show the window
        primaryStage.show();
    }

    public ImageView setImageView(Image img) {
        ImageView image = new ImageView(img);
        image.setFitHeight(20);
        image.setFitWidth(20);
        return image;
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