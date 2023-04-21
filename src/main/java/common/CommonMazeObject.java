package common;

public interface CommonMazeObject {
    default boolean canMove(CommonField.Direction var1){
        return false;
    }

    default boolean move(CommonField.Direction var1){
        return false;
    }

    default boolean isPacman() {
        return false;
    }

    CommonField getField();

    default int getLives() {
        return 0;
    }

    default int getSteps(){
        return 0;
    }

    default int getVictory(){
        return 0;
    }
}
