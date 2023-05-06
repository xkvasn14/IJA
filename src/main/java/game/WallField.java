/**
 * @authors: xjalak00, xkvasn14
 */
package game;

import common.*;

/**
 * Class for a wall field
 */
public class WallField extends Field implements CommonField {
    int row;
    int col;
    CommonMaze maze;

    /**
     * Constructor
     * @param row
     * @param col
     */
    public WallField(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Compares two objects
     * @param obj
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        WallField other = (WallField) obj;
        return other.row == this.row && other.col == this.col;
    }

    /**
     * Returns if the field is empty
     * @return boolean
     */
    @Override
    public boolean isEmpty() {
        return true;
    }

    /**
     * Returns if the field is walkable
     * @return boolean
     */
    @Override
    public boolean canMove() {
        return false;
    }

    /**
     * Gets the object on the field
     * @return CommonMazeObject
     */
    @Override
    public CommonMazeObject get() {
        return null;
    }

    /**
     * Gets the next field in the direction
     * @param dirs
     * @return Field
     */
    @Override
    public Field nextField(Direction dirs) {
        try{
            throw new UnsupportedOperationException();
        }
        catch (UnsupportedOperationException e){
            return null;
        }
    }


    /**
     * Put an object on the field
     * @param obj
     * @return boolean
     */
    @Override
    public boolean put(CommonMazeObject obj) {
        throw new UnsupportedOperationException();
    }

    /**
     * Remove an object from the field
     * @param obj
     * @return
     */
    @Override
    public boolean remove(CommonMazeObject obj) {
        throw new UnsupportedOperationException();
    }

    /**
     * Sets the maze
     * @param maze
     */
    @Override
    public void setMaze(CommonMaze maze) {
        this.maze = maze;
    }
}
