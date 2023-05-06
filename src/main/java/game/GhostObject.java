/**
 * @authors: xjalak00, xkvasn14
 */
package game;

import common.CommonField;
import common.CommonMazeObject;

/**
 * Class for ghost object
 */
public class GhostObject implements CommonMazeObject {
    Field field;

    /**
     * Constructor
     * @param field current field of ghost
     */
    public GhostObject(Field field) {
        field.put(this);
        this.field = field;
    }

    /**
     * Returns true if ghost can move in given direction
     * @param dir direction
     * @return false if not successful
     */
    @Override
    public boolean canMove(CommonField.Direction dir) {
        return this.field.nextField(dir).getClass() != WallField.class;
    }

    /**
     * Move ghost on maze
     * @param dir direction
     * @return false if not successful
     */
    @Override
    public boolean move(Field.Direction dir) {
        if(this.canMove(dir)){
            this.field.remove(this);
            //this.field.notifyObservers();
            this.field = this.field.nextField(dir);
            this.field.put(this);
            //this.field.notifyObservers();
            CommonMazeObject nextObject = this.field.get();
            if(nextObject instanceof PacmanObject) {
                ((PacmanObject) nextObject).lives -= 1;
            }
            return true;
        }
        return false;
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
