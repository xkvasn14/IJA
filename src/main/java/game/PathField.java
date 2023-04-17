package game;

import common.*;


import java.util.ArrayList;
import java.util.List;

public class PathField extends Field implements CommonField {
    int row;
    int col;
    List<CommonMazeObject> entity = super.entity;
    CommonMaze maze = super.maze;

    public PathField(int row, int col) {
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
        PathField other = (PathField) obj;
        return other.row == this.row && other.col == this.col;
    }

    @Override
    public boolean isEmpty() {
        if(this.get() == null){
            return true;
        }
        return false;
    }

    @Override
    public boolean canMove() {
        return true;
    }

    @Override
    public CommonMazeObject get() {
        if(this.entity.size() == 0){
            return null;
        }
        return this.entity.get(0);
    }

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

    @Override
    public boolean put(CommonMazeObject obj) {
        this.entity.add(obj);
        return true;
    }

    @Override
    public boolean remove(CommonMazeObject obj) {
        if(this.entity.size() == 0){
            return false;
        }
        this.entity.remove(obj);
        return true;
    }

    @Override
    public void setMaze(CommonMaze maze) {
        this.maze = maze;
    }

    @Override
    public CommonMaze getMaze() {
        return this.maze;
    }

    public int getCol() {
        return this.col;
    }

    public int getRow() {
        return this.row;
    }

}



