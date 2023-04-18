package game;

import common.*;
import javafx.scene.effect.Light;
import pathfinding.Network;
import pathfinding.Node;

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
    public boolean canMove() {
        return false;
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

    public static class FieldNode extends  Node {

        private int row, col;

        public FieldNode(int row, int col) {
            this.row = row;
            this.col = col;
            setValid(true);
        }

        @Override
        public void calculateNeighbours(Network network) {

            CommonMaze maze = (CommonMaze) network;

            ArrayList<Node> nodes = new ArrayList<>();

            int minCol = 0;
            int minRow = 0;
            int maxCol = maze.numCols() - 1;
            int maxRow = maze.numRows() - 1;

            if (this.col > minCol) {
                nodes.add((Node) maze.getField(this.row, this.col - 1)); //west //todo find == getfield??
            }

            if (this.col < maxCol) {
                nodes.add((Node) maze.getField(this.row, this.col + 1)); //east
            }

            if (this.row > minRow) {
                nodes.add((Node) maze.getField(this.row - 1, this.col)); //north
            }

            if (this.row < maxRow) {
                nodes.add((Node) maze.getField(this.row + 1, this.col)); //south
            }

            setNeighbours(nodes);
        }

        @Override
        public double heuristic(Node dest) {
            return distanceTo(dest);
        }

        @Override
        public double distanceTo(Node dest) {
            FieldNode d = (FieldNode) dest;

            double ac = Math.abs(d.row - row);
            double cb = Math.abs(d.col - col);

            return Math.hypot(ac, cb);
            //return new Point(col, row).distance(new Point(d.col, d.row));
        }

    }


}
