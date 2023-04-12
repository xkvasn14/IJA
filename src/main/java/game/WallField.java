package game;

import common.*;

public class WallField extends Field implements CommonField {
    int row;
    int col;
    CommonMaze maze;

    public WallField(int row, int col) {
        this.row = row;
        this.col = col;
    }

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

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public boolean canMove() {
        return false;
    }

    @Override
    public CommonMazeObject get() {
        return null;
    }

    @Override
    public Field nextField(Direction dirs) {
        try{
            throw new UnsupportedOperationException();
        }
        catch (UnsupportedOperationException e){
            return null;
        }
    }

    @Override
    public boolean put(CommonMazeObject obj) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(CommonMazeObject obj) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setMaze(CommonMaze maze) {
        this.maze = maze;
    }
}
