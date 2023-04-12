package game;

import common.CommonField;
import common.CommonMazeObject;

public class TargetObject implements CommonMazeObject {

    Field field;

    public TargetObject(Field field) {
        field.put(this);
        this.field = field;
    }

    @Override
    public CommonField getField() {
        return this.field;
    }
}
