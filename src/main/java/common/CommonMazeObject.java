/**
 * Interface representing common object
 * @author xjalak00, xkvasn14
 */

package common;

/**
 * Interface representing common object
 */
public interface CommonMazeObject {
    /**
     * Returns true if object can move in given direction
     * @param var1 direction
     * @return false if not successful
     */
    default boolean canMove(CommonField.Direction var1){
        return false;
    }

    /**
     * Moves object on maze
     * @param var1 direction
     * @return false if not successful
     */
    default boolean move(CommonField.Direction var1){
        return false;
    }

    /**
     * Returns true if object is pacman
     * @return boolean value
     */
    default boolean isPacman() {
        return false;
    }

    /**
     * Gets field on which object is on
     * @return field
     */
    CommonField getField();

    /**
     * Gets number of pacman lives
     * @return number of pacman lives
     */
    default int getLives() {
        return 0;
    }

    /**
     * Gets number of steps made
     * @return number of steps
     */
    default int getSteps(){
        return 0;
    }

    /**
     * Gets state of victory
     * @return 0 if game is not won, 1 or -1 otherwise
     */
    default int getVictory(){
        return 0;
    }
}
