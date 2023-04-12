package game;

import common.CommonField;
import common.CommonMazeObject;

public class GhostObject implements CommonMazeObject {
    Field field;

    public GhostObject(Field field) {
        field.put(this);
        this.field = field;
    }
    @Override
    public boolean canMove(CommonField.Direction dir) {
        return this.field.nextField(dir).getClass() != WallField.class;
    }

    @Override
    public boolean move(Field.Direction dir) {
        if(this.canMove(dir)){
            this.field.remove(this);
            this.field.notifyObservers();
            this.field = this.field.nextField(dir);
            this.field.put(this);
            this.field.notifyObservers();
            CommonMazeObject nextObject = this.field.get();
            if(nextObject instanceof PacmanObject){
                ((PacmanObject) nextObject).lives -= 1;
            }

            return true;
        }
        return false;
    }

    @Override
    public CommonField getField() {
        return this.field;
    }

}
