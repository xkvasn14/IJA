/**
 * Interface representing common field
 * @author xjalak00, xkvasn14
 */

package common;


/**
 * Interface representing common field
 */
public interface CommonField extends Observable {
    /**
     * Returns next field in given direction
     * @param var1 direction
     * @return next field
     */
    CommonField nextField(Direction var1);

    /**
     * Returns if field is empty
     * @return true if field is empty, false otherwise
     */
    default boolean isEmpty() {
        return true;
    }

    /**
     * Gets object laying on field
     * @return object
     */
    CommonMazeObject get();

    /**
     * Return boolean value depending on ability of object to move to field
     * @return true if object can move here, false otherwise
     */
    default boolean canMove() {
        return false;
    }

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
        L(0, -1),
        U(-1, 0),
        R(0, 1),
        D(1, 0);

        private final int r;
        private final int c;

        /**
         * Constructor
         * @param dr row direction
         * @param dc column direction
         */
        Direction(int dr, int dc) {
            this.r = dr;
            this.c = dc;
        }

    }
}
