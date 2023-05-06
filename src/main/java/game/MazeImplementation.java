/**
 * @authors: xjalak00, xkvasn14
 */
package game;

import common.CommonMaze;
import common.CommonMazeObject;
import common.CommonField;

import java.util.ArrayList;
import java.util.Collections;
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
     * @param rows
     * @param cols
     * @param fields
     * @param ghosts
     * @param keys
     * @param pacman
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
     * Set pacmann to the maze
     * @param pacman
     */
    public void setPacman(CommonMazeObject pacman) {
        this.pacman = pacman;
    }

    /**
     * Get the field
     * @param row
     * @param col
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
        return new ArrayList<CommonMazeObject>(this.ghosts);
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
     * Get the field
     * @param field
     * @param row
     * @param col
     */
    public void setField(CommonField field, int row, int col) {
        this.fields[row][col] = field;
    }

    /**
     * Get the pacmann
     * @return pacmann
     */
    @Override
    public CommonMazeObject pacman() {
        return this.pacman;
    }
}
