/**
 * @authors: xjalak00, xkvasn14
 */
package game;

import common.CommonField;
import common.CommonMazeObject;

/**
 * Class for the key object
 */
public class KeyObject implements CommonMazeObject {

    Field field;

    /**
     * Constructor
     * @param field current field of key
     */
    public KeyObject(Field field) {
        field.put(this);
        this.field = field;
    }

    /**
     * Gets field on which object is on
     * @return field
     */
    @Override
    public CommonField getField() {
        return this.field;
    }
}
