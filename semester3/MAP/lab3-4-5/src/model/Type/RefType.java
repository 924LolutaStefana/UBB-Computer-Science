package model.Type;

import model.Value.RefValue;
import model.Value.Value;

public class RefType implements Type {
    private Type inner;

    public RefType(Type inner) {
        this.inner = inner;
    }

    public Type getInner() {
        return inner;
    }


    @Override
    public boolean equals(Object another) {
        if (another instanceof RefType)
            return inner.equals(((RefType) another).getInner());
        else
            return false;
    }
    @Override
    public Value defaultValue() {
        return new RefValue(0, inner);
    }

    @Override
    public Type deepCopy() {
        return new RefType(inner.deepCopy());
    }

    @Override
    public String toString() {
        return "RefType{" +
                "inner " + inner.toString() +
                '}';
    }
}
