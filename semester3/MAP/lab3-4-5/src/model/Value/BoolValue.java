package model.Value;

import model.Type.BoolType;
import model.Type.Type;

public class BoolValue implements Value{
    boolean val;
    public BoolValue(boolean v){val=v;}

    public boolean isVal() {
        return val;
    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }

    @Override
    public Type getType() {
        return new BoolType();
    }
    @Override
    public boolean equals(Object anotherValue) {
        if (!(anotherValue instanceof BoolValue))
            return false;
        BoolValue castValue = (BoolValue) anotherValue;
        return this.val== castValue.val;
    }
    public boolean getValue() {
        return this.val;
    }
    @Override
    public Value deepCopy() {
        return new BoolValue(val);
    }
}
