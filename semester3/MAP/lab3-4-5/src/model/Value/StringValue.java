package model.Value;

import model.Type.StringType;
import model.Type.Type;

public class StringValue implements Value{

    String val;

    public StringValue(String val) {
        this.val = val;
    }

    public String getVal() {
        return val;
    }

    @Override
    public Type getType() {
        return new StringType();
    }

    @Override
    public Value deepCopy() {
        return new StringValue(val);
    }
    @Override
    public boolean equals(Object anotherValue) {
        if (!(anotherValue instanceof StringValue))
            return false;
        StringValue castValue = (StringValue) anotherValue;
        return this.val.equals(castValue.val);
    }


    @Override
    public String toString() {
        return "\"" + this.val + "\"";
    }
}
