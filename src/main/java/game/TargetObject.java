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
     * @param field
     */
    public TargetObject(Field field) {
        field.put(this);
        this.field = field;
    }

    /**
     * Method for getting field
     * @return field
     */
    @Override
    public CommonField getField() {
        return this.field;
    }
}
