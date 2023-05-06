/**
 * @authors: xjalak00, xkvasn14
 */
package game;

import common.CommonMaze;
import common.CommonMazeObject;
import common.CommonField;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for representing the maze
 */
public class MazeImplementation implements CommonMaze  {

    CommonField[][] fields;
    List<CommonMazeObject> ghosts;

    List<CommonMazeObject> keys;
    CommonMazeObject pacman;
    int rows;
    int cols;

    /**
     * Constructor
     * @param rows rows of maze
     * @param cols columns of maze
     * @param fields list of fields
     * @param ghosts list of ghost
     * @param keys list of keys
     * @param pacman pacman object
     */
    public MazeImplementation(int rows, int cols, CommonField[][] fields, List<CommonMazeObject> ghosts, List<CommonMazeObject> keys, CommonMazeObject pacman) {
        this.fields = fields;
        this.rows = rows + 2;
        this.cols = cols + 2;
        this.ghosts = ghosts;
        this.keys = keys;
        this.pacman = pacman;
    }

    /**
     * Set pacman to the maze
     * @param pacman given pacman
     */
    public void setPacman(CommonMazeObject pacman) {
        this.pacman = pacman;
    }

    /**
     * Get the field
     * @param row row
     * @param col column
     * @return field
     */
    @Override
    public CommonField getField(int row, int col) {
        return fields[row][col];
    }

    /**
     * Get number of rows
     * @return number of rows
     */
    @Override
    public int numRows() {
        return this.rows;
    }

    /**
     * Get number of columns
     * @return number of columns
     */
    @Override
    public int numCols() {
        return this.cols;
    }

    /**
     * Get the list of ghosts
     * @return list of ghosts
     */
    @Override
    public List<CommonMazeObject> ghosts() {
        return new ArrayList<>(this.ghosts);
    }

    /**
     * Get the list of keys
     * @return list of keys
     */
    @Override
    public List<CommonMazeObject> keys() {
        return this.keys;
    }

    /**
     * Get the pacman
     * @return pacman
     */
    @Override
    public CommonMazeObject pacman() {
        return this.pacman;
    }
}
