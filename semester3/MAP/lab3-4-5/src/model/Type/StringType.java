package model.Type;

import model.Value.StringValue;
import model.Value.Value;

public class StringType implements Type{

    @Override
    public boolean equals(Object anotherType) {
        return anotherType instanceof StringType;
    }
    @Override
    public Value defaultValue() {
        return new StringValue("");
    }

    @Override
    public Type deepCopy() {
        return new StringType();
    }

    @Override
    public String toString() {
        return "string";
    }
}
