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
     * @param row row
     * @param col column
     */
    public WallField(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Compares two objects
     * @param obj given object
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
     * Gets the object on the field
     * @return CommonMazeObject
     */
    @Override
    public CommonMazeObject get() {
        return null;
    }

    /**
     * Gets the next field in the direction
     * @param dirs direction
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
     *
     * @param obj given object
     */
    @Override
    public void put(CommonMazeObject obj) {
        throw new UnsupportedOperationException();
    }

    /**
     * Remove an object from the field
     *
     * @param obj given object
     */
    @Override
    public void remove(CommonMazeObject obj) {
        throw new UnsupportedOperationException();
    }

    /**
     * Sets the maze
     * @param maze current maze
     */
    @Override
    public void setMaze(CommonMaze maze) {
        this.maze = maze;
    }
}
