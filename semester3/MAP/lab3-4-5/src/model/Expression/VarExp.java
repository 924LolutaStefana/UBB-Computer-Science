package model.Expression;
import Exception.MyException;
import model.ADT.Dictionary.MyIDictionary;
import model.ADT.Heap.MyIHeap;
import model.Value.Value;

public class VarExp implements Exp {
    String id;

    public VarExp(String id) {
        this.id = id;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap heap) throws MyException {

        return tbl.lookup(id);
    }

    @Override
    public String toString() {
        return id;
    }

    @Override
    public Exp deepCopy() {
        return new VarExp(id);
    }
}
