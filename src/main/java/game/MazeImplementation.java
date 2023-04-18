package game;

import common.CommonMaze;
import common.CommonMazeObject;
import common.CommonField;
import pathfinding.Network;
import pathfinding.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MazeImplementation extends Network implements CommonMaze  {

    CommonField[][] fields;
    List<CommonMazeObject> ghosts;

    List<CommonMazeObject> keys;
    CommonMazeObject pacman;
    int rows;
    int cols;
    public MazeImplementation(int rows, int cols, CommonField[][] fields, List<CommonMazeObject> ghosts, List<CommonMazeObject> keys, CommonMazeObject pacman) {
        this.fields = fields;
        this.rows = rows + 2;
        this.cols = cols + 2;
        this.ghosts = ghosts;
        this.keys = keys;
        this.pacman = pacman;
    }

    public void setPacman(CommonMazeObject pacman) {
        this.pacman = pacman;
    }

    @Override
    public CommonField getField(int row, int col) {
        return fields[row][col];
    }

    @Override
    public int numRows() {
        return this.rows;
    }

    @Override
    public int numCols() {
        return this.cols;
    }

    @Override
    public List<CommonMazeObject> ghosts() {
        return new ArrayList<CommonMazeObject>(this.ghosts);
    }

    @Override
    public List<CommonMazeObject> keys() {
        return this.keys;
    }

    public void setField(CommonField field, int row, int col) {
        this.fields[row][col] = field;
    }

    @Override
    public CommonMazeObject pacman() {
        return this.pacman;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public Iterable<Node> getNodes() {
        ArrayList<Node> nodes = new ArrayList<>();
        // todo for
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                nodes.add((Node)this.fields[i][j]);
            }
        }
        return nodes;
    }
}
