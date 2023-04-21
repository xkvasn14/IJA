package game;

import common.CommonField;
import common.CommonMazeObject;

public class PacmanObject implements CommonMazeObject {
    //CommonField field;
    Field field;
    int lives;
    int steps;
    int victory = 0;

    public PacmanObject(Field field) {
        field.put(this);
        this.field = field;
        this.lives = 3;
        this.steps = 0;
        //this.victory = 0;
    }
    @Override
    public boolean canMove(CommonField.Direction dir) {
        return this.field.nextField(dir).getClass() != WallField.class;
    }

    @Override
    public boolean move(CommonField.Direction dir) {
        if(this.canMove(dir)){
            this.field.remove(this);
            //this.field.notifyObservers();
            this.field = this.field.nextField(dir);
            this.field.put(this);
            //this.field.notifyObservers();
            CommonMazeObject nextObject = this.field.get();
            if(nextObject instanceof GhostObject){
                this.lives -= 1;
                if (this.lives <= 0){
                    this.victory = -1;
                }
            }
            if(nextObject instanceof TargetObject){
                if (this.field.getMaze().keys().isEmpty())
                {
                    this.victory = 1;
                }
            }
            if (nextObject instanceof KeyObject){
                this.field.getMaze().keys().remove(nextObject);
                this.field.remove(nextObject);
                //this.field.notifyObservers();
            }
            this.steps++;
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

    public int getSteps() {
        return this.steps;
    }

    public int getVictory() {
        return this.victory;
    }
}
