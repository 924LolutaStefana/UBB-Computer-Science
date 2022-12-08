package model.Expression;
import Exception.MyException;
import model.ADT.Dictionary.MyIDictionary;
import model.ADT.Heap.MyIHeap;
import model.Value.Value;

public class ValueExp implements Exp{
    Value val;

    public ValueExp(Value val) {
        this.val = val;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap heap) throws MyException {
        return this.val;
    }
    @Override
    public String toString() {
        return this.val.toString();
    }

    @Override
    public Exp deepCopy() {
        return new ValueExp(val);
    }
}
