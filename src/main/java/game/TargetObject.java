/**
 * @authors: xjalak00, xkvasn14
 */
package game;

import common.CommonField;
import common.CommonMazeObject;

/**
 * Class for target object
 */
public class TargetObject implements CommonMazeObject {

    Field field;

    /**
     * Constructor
     * @param field current field of target
     */
    public TargetObject(Field field) {
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
