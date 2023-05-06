/**
 * @authors: xjalak00, xkvasn14
 */
package game;

import common.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for Fields
 */
public class Field implements CommonField {
    CommonMaze maze;
    List<CommonMazeObject> entity = new ArrayList<>();

    /**
     * Returns next field in given direction
     * @param direction direction
     */
    @Override
    public Field nextField(Direction direction) {
        return null;
    }


    /**
     * Getter for entity
     * @return entity
     */
    @Override
    public CommonMazeObject get() {
        return null;
    }

    /**
     * Compares given object with the one on field
     * @param commonMazeObject object
     * @return true if objects are the same, false otherwise
     */
    @Override
    public boolean contains(CommonMazeObject commonMazeObject) {
        return this.entity.contains(commonMazeObject);
    }


    /**
     * Puts object on field
     * @param obj given object
     */
    public void put(CommonMazeObject obj) {
        this.entity.add(obj);
    }

    /**
     * Removing object from field
     * @param obj given object
     */
    public void remove(CommonMazeObject obj) {
        this.entity.remove(obj);
    }

    /**
     * Setter for maze
     * @param maze current maze
     */
    public void setMaze(CommonMaze maze) {
        this.maze = maze;
    }

    /**
     * Getter for maze
     * @return maze
     */
    public CommonMaze getMaze() {
        return this.maze;
    }

}
