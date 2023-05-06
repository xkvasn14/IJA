package game;

import common.*;
import javafx.scene.effect.Light;

import java.util.ArrayList;
import java.util.List;

public class Field extends AbstractObservableField implements CommonField {
    CommonMaze maze;
    List<CommonMazeObject> entity = new ArrayList<>();
    List<Observer> observers = new ArrayList<>();

    @Override
    public Field nextField(Direction direction) {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public CommonMazeObject get() {
        return null;
    }

    @Override
    public boolean contains(CommonMazeObject commonMazeObject) {
        return this.entity.contains(commonMazeObject);
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }

    public boolean put(CommonMazeObject obj) {
        this.entity.add(obj);
        return true;
    }

    public boolean remove(CommonMazeObject obj) {
        if(this.entity.contains(obj)){
            this.entity.remove(obj);
            return true;
        }
        return false;
    }

    public void setMaze(CommonMaze maze) {
        this.maze = maze;
    }

    public CommonMaze getMaze() {
        return this.maze;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////



}
