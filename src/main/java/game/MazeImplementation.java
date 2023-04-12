package game;

import common.CommonMaze;
import common.CommonMazeObject;
import common.CommonField;

import java.util.ArrayList;
import java.util.List;

public class MazeImplementation implements CommonMaze {

    CommonField[][] fields;
    List<CommonMazeObject> ghosts;
    int rows;
    int cols;
    public MazeImplementation(int rows, int cols, CommonField[][] fields, List<CommonMazeObject> ghosts) {
        this.fields = fields;
        this.rows = rows + 2;
        this.cols = cols + 2;
        this.ghosts = ghosts;
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

    public void setField(CommonField field, int row, int col) {
        this.fields[row][col] = field;
    }
}
