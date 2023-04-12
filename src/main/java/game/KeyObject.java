package game;

import common.CommonField;
import common.CommonMazeObject;

public class KeyObject implements CommonMazeObject {

    Field field;

    public KeyObject(Field field) {
        field.put(this);
        this.field = field;
    }

    @Override
    public CommonField getField() {
        return this.field;
    }
}
