package game;

import common.*;

import java.util.ArrayList;
import java.util.List;

public class MazeConfigure extends Object {

    boolean ok = true;
    int rows;
    int cols;
    int readRow = 0;
    CommonMaze maze;
    Field[][] fields;
    String[][] mazeString;

    List<CommonMazeObject> ghosts = new ArrayList<>();
    public MazeConfigure() {
    }

    public CommonMaze createMaze() {
        if(!ok)
            return null;
        if(readRow < rows)
            return null;

        this.maze = new MazeImplementation(rows, cols, fields, ghosts);

        // first wall
        for(int row = 0; row < rows +2; row++){
            for(int col = 0; col < cols +2; col++){
                if(this.mazeString[row][col].equals("X")){
                    Field field = new WallField(row, col);
                    field.setMaze(maze);
                    //maze.setField(field, row, col);
                   // this.maze[row][col] = field;
                    fields[row][col] = field;
                }
                if (this.mazeString[row][col].equals(".")){
                    Field field = new PathField(row, col);
                    field.setMaze(maze);
                    //maze.setField(field, row, col);
                    //this.maze[row][col] = field;
                    fields[row][col] = field;
                }
                if(this.mazeString[row][col].equals("S")){
                    Field field = new PathField(row, col);
                    field.setMaze(maze);
                    //maze.setField(field, row, col);
                    //this.maze[row][col] = field;
                    fields[row][col] = field;
                    // put pacman on start field
                    //maze.getField(row, col).put(new PacmanObject(maze.getField(row, col)));
                    CommonMazeObject pacman = new PacmanObject(field);
                    field.put(pacman);
                }
                if(this.mazeString[row][col].equals("G")){
                    Field field = new PathField(row, col);
                    field.setMaze(maze);
                    //maze.setField(field, row, col);
                    //this.maze[row][col] = field;
                    fields[row][col] = field;
                    // put ghost on field
                    GhostObject ghost = new GhostObject(field);
                    //field.put(ghost);
                    ghosts.add(ghost);


                    //maze.getField(row, col).put(new GhostObject(maze.getField(row, col)));
                }
            }
        }
        return maze;
    }

    public boolean stopReading() {
        // last wall
        for(int col = 0; col < cols + 2; col++){
            this.mazeString[rows+1][col] = "X";
        }
        return true;
    }

    public boolean processLine(String s) {
        if(s.length() != cols) {
            this.ok = false;
            return false;
        }
        this.mazeString[readRow][0] = "X";
        for(int i = 0; i < cols; i++){
            this.mazeString[readRow][i+1] = s.substring(i, i+1);
        }
        this.mazeString[readRow][cols+1] = "X";
        readRow++;
        return true;
    }

    public void startReading(int row, int column) {
        this.rows = row;
        this.cols = column;
        this.mazeString = new String[rows + 2][cols + 2];
        this.fields = new Field[rows + 2][cols + 2];

        // first wall
        for(int col = 0; col < cols + 2; col++){
            this.mazeString[0][col]= "X";
        }
        readRow = 1;
    }
}
