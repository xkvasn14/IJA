/**
 * @authors: xjalak00, xkvasn14
 */
package game;

import common.CommonField;
import common.CommonMazeObject;

/**
 * Class for Pacman
 */
public class PacmanObject implements CommonMazeObject {
    //CommonField field;
    Field field;
    int lives;
    int steps;
    int victory = 0;

    /**
     * Constructor
     * @param field
     */
    public PacmanObject(Field field) {
        field.put(this);
        this.field = field;
        this.lives = 3;
        this.steps = 0;
        //this.victory = 0;
    }

    /**
     * Can Pacman move in the given direction?
     * @param dir
     * @return boolean
     */
    @Override
    public boolean canMove(CommonField.Direction dir) {
        return this.field.nextField(dir).getClass() != WallField.class;
    }

    /**
     * Move Pacman in the given direction
     * @param dir
     * @return boolean
     */
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

    /**
     * Is Pacman on the given field?
     * @return boolean
     */
    @Override
    public boolean isPacman() {
        return true;
    }

    /**
     * Get the field Pacman is on
     * @return CommonField
     */
    @Override
    public CommonField getField() {
        return this.field;
    }

    /**
     * Get the number of lives Pacman has
     * @return int
     */
    @Override
    public int getLives() {
        return this.lives;
    }

    /**
     * Get the number of steps Pacman has taken
     * @return int
     */
    public int getSteps() {
        return this.steps;
    }

    /**
     * Get the victory status of Pacman
     * @return int
     */
    public int getVictory() {
        return this.victory;
    }
}
