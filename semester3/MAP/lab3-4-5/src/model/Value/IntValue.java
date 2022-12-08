package model.Value;

import model.Type.IntType;
import model.Type.Type;

public class IntValue implements Value{
    int val;
    public IntValue(int v){val=v;}

    public int getVal() {
        return val;
    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }

    @Override
    public Type getType() {
        return new IntType();
    }

    @Override
    public boolean equals(Object anotherValue) {
        if (!(anotherValue instanceof IntValue))
            return false;
        IntValue castValue = (IntValue) anotherValue;
        return this.val == castValue.val;
    }

    @Override
    public Value deepCopy() {
        return new IntValue(val);}

}
