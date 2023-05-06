/**
 * @authors: xjalak00, xkvasn14
 */
package game;

import common.*;
import javafx.scene.effect.Light;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for Fields
 */
public class Field extends AbstractObservableField implements CommonField {
    CommonMaze maze;
    List<CommonMazeObject> entity = new ArrayList<>();
    List<Observer> observers = new ArrayList<>();

    /**
     * Next field in the given direction
     * @param direction
     */
    @Override
    public Field nextField(Direction direction) {
        return null;
    }

    /**
     * Asker if field is empty
     * @return if field is empty
     */
    @Override
    public boolean isEmpty() {
        return false;
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
     * Asker if field contains object
     * @param commonMazeObject
     * @return if field contains object
     */
    @Override
    public boolean contains(CommonMazeObject commonMazeObject) {
        return this.entity.contains(commonMazeObject);
    }

    /**
     * Adding observer
     * @param observer
     */
    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    /**
     * Removing observer
     * @param observer
     */
    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    /**
     * Notifying observers
     */
    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }

    /**
     * Putting object to field
     * @param obj
     * @return if object was put
     */
    public boolean put(CommonMazeObject obj) {
        this.entity.add(obj);
        return true;
    }

    /**
     * Removing object from field
     * @param obj
     * @return if object was removed
     */
    public boolean remove(CommonMazeObject obj) {
        if(this.entity.contains(obj)){
            this.entity.remove(obj);
            return true;
        }
        return false;
    }

    /**
     * Getter for maze
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

    ////////////////////////////////////////////////////////////////////////////////////////////////



}
