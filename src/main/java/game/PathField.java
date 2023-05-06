/**
 * @authors: xjalak00, xkvasn14
 */
package game;

import common.*;


import java.util.ArrayList;
import java.util.List;

/**
 * Class for the path field
 */
public class PathField extends Field implements CommonField {
    int row;
    int col;
    List<CommonMazeObject> entity = super.entity;
    CommonMaze maze = super.maze;

    /**
     * Constructor
     * @param row
     * @param col
     */
    public PathField(int row, int col) {
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
        PathField other = (PathField) obj;
        return other.row == this.row && other.col == this.col;
    }

    /**
     * Checks if the field is empty
     * @return boolean
     */
    @Override
    public boolean isEmpty() {
        if(this.get() == null){
            return true;
        }
        return false;
    }

    /**
     * Checks if the field is walkable
     * @return boolean
     */
    @Override
    public boolean canMove() {
        return true;
    }

    /**
     * Gets the object on the field
     * @return CommonMazeObject
     */
    @Override
    public CommonMazeObject get() {
        if(this.entity.size() == 0){
            return null;
        }
        return this.entity.get(0);
    }

    /**
     * Gets the next field
     * @param dirs
     * @return Field
     */
    @Override
    public Field nextField(Direction dirs) {
        CommonField result = null;
        if(dirs == Direction.D){
            result = this.maze.getField(this.row + 1, this.col);
        }
        if(dirs == Direction.L){
            result = this.maze.getField(this.row, this.col - 1);
        }
        if(dirs == Direction.R){
            result = this.maze.getField(this.row, this.col + 1);
        }
        if(dirs == Direction.U){
            result = this.maze.getField(this.row - 1, this.col);
        }
        return (Field) result;
    }

    /**
     * Put an object on the field
     * @param obj
     * @return boolean
     */
    @Override
    public boolean put(CommonMazeObject obj) {
        this.entity.add(obj);
        return true;
    }

    /**
     * Remove an object from the field
     * @param obj
     * @return boolean
     */
    @Override
    public boolean remove(CommonMazeObject obj) {
        if(this.entity.size() == 0){
            return false;
        }
        this.entity.remove(obj);
        return true;
    }

    /**
     * Sets the maze
     * @param maze
     */
    @Override
    public void setMaze(CommonMaze maze) {
        this.maze = maze;
    }

    /**
     * Gets the maze
     * @return CommonMaze
     */
    @Override
    public CommonMaze getMaze() {
        return this.maze;
    }

    /**
     * Gets the column
     * @return int
     */
    public int getCol() {
        return this.col;
    }

    /**
     * Gets the row
     * @return int
     */
    public int getRow() {
        return this.row;
    }
}
