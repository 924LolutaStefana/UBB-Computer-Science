package model.Type;

import model.Value.Value;

public interface Type {



    boolean equals(Object anotherType);

    Value defaultValue();
    Type deepCopy();
}
