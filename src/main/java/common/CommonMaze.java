/**
 * Interface representing common maze
 * @author xjalak00, xkvasn14
 */

package common;
import java.util.List;

/**
 * Interface representing common maze
 */
public interface CommonMaze {

    /**
     * Gets field of given row and column
     * @param var1 row
     * @param var2 column
     * @return field object
     */
    CommonField getField(int var1, int var2);

    /**
     * Returns number of rows in maze
     * @return number of rows in maze
     */
    int numRows();

    /**
     * Returns number of columns in maze
     * @return number of columns in maze
     */
    int numCols();

    /**
     * Returns list of ghosts in maze
     * @return list of ghosts in maze
     */
    List<CommonMazeObject> ghosts();

    /**
     * Returns list of keys in maze
     * @return list of keys in maze
     */
    List<CommonMazeObject> keys();

    /**
     * Gets pacman from maze
     * @return pacman
     */
    CommonMazeObject pacman();

    /**
     * Sets pacman object
     * @param pacman pac
     */
    void setPacman(CommonMazeObject pacman);
}
