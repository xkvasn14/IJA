/**
 * Interface representing common field
 * @author xjalak00, xkvasn14
 */

package common;


/**
 * Interface representing common field
 */
public interface CommonField {
    /**
     * Returns next field in given direction
     * @param var1 direction
     * @return next field
     */
    CommonField nextField(Direction var1);

    /**
     * Gets object laying on field
     * @return object
     */
    CommonMazeObject get();

    /**
     * Compares given object with the one on field
     * @param var1 object
     * @return true if objects are the same, false otherwise
     */
    boolean contains(CommonMazeObject var1);

    /**
     * Enum representing directions
     */
    enum Direction {
        /**
         * Directions Left
         */
        L(),
        /**
         * Directions Up
         */
        U(),
        /**
         * Directions Right
         */
        R(),
        /**
         * Directions Down
         */
        D();

        /**
         * Constructor
         */
        Direction() {
        }
    }
}
