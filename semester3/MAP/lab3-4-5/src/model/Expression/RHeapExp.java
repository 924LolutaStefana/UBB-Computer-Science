package model.Expression;

import model.ADT.Dictionary.MyIDictionary;
import model.ADT.Heap.MyIHeap;
import model.Value.RefValue;
import model.Value.Value;
import Exception.MyException;

public class RHeapExp implements Exp{
    private final Exp expression;

    public RHeapExp(Exp expression) {
        this.expression = expression;
    }
    @Override
    public Value eval(MyIDictionary<String, Value> symTable, MyIHeap heap) throws  MyException {
        Value value = expression.eval(symTable, heap);
        if (!(value instanceof RefValue))
            throw new MyException(String.format("%s not of RefType", value));
        RefValue refValue = (RefValue) value;
        return heap.get(refValue.getAddress());
    }

    @Override
    public Exp deepCopy() {
        return new RHeapExp(expression.deepCopy());
    }

    @Override
    public String toString() {
        return
                "read heap expression=" + expression ;

    }
}
