package model.Expression;
import Exception.MyException;
import model.ADT.Dictionary.MyIDictionary;
import model.ADT.Heap.MyIHeap;
import model.Value.Value;

public interface Exp {
    Value eval(MyIDictionary<String, Value>tbl, MyIHeap heap)throws MyException;
    Exp deepCopy();
}
