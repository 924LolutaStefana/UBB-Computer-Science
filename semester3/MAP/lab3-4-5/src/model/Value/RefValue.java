package model.Value;

import model.Type.RefType;
import model.Type.Type;

public class RefValue implements Value{
    private int address;
    private Type locationType;
    public RefValue(int address, Type locationType) {
        this.address = address;
        this.locationType = locationType;
    }


    public int getAddress() {
        return address;
    }

    public Type getLocationType() {
        return locationType;
    }

    @Override
    public Value deepCopy() {
        return new RefValue(address, locationType.deepCopy());
    }
    @Override
    public Type getType() {
        return new RefType(locationType);
    }

    @Override
    public String toString() {
        return
                "address=" + address +
                ", locationType=" + locationType.toString() ;

    }

}
