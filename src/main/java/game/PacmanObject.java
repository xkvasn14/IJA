package game;

import common.CommonField;
import common.CommonMazeObject;

public class PacmanObject implements CommonMazeObject {
    //CommonField field;
    Field field;

    int lives;

    public PacmanObject(Field field) {
        field.put(this);
        this.field = field;
        this.lives = 3;
    }
    @Override
    public boolean canMove(CommonField.Direction dir) {
        return this.field.nextField(dir).getClass() != WallField.class;
    }

    @Override
    public boolean move(CommonField.Direction dir) {
        if(this.canMove(dir)){
            this.field.remove(this);
            this.field.notifyObservers();
            this.field = this.field.nextField(dir);
            this.field.put(this);
            this.field.notifyObservers();
            CommonMazeObject nextObject = this.field.get();
            if(nextObject instanceof GhostObject){
                this.lives -= 1;
            }

            return true;
        }
        return false;
    }

    @Override
    public boolean isPacman() {
        return true;
    }

    @Override
    public CommonField getField() {
        return this.field;
    }

    @Override
    public int getLives() {
        return this.lives;
    }
}
