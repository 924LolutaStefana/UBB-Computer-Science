package model.Statement;

import model.ADT.Dictionary.MyIDictionary;
import model.ADT.Heap.MyIHeap;
import model.Expression.Exp;
import model.PrgState.PrgState;
import Exception.MyException;
import model.Value.RefValue;
import model.Value.Value;

public class WriteHeapStmt implements IStmt{
    private  String varName;
    private Exp expression;

    public WriteHeapStmt(String varName, Exp expression) {
        this.varName = varName;
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIHeap heap = state.getHeap();
        if (!symTable.isVarDef(varName))
            throw new MyException(String.format("%s not present in the symTable", varName));
        Value value = symTable.lookup(varName);
        if (!(value instanceof RefValue))
            throw new MyException(String.format("%s not of RefType", value));
        RefValue refValue = (RefValue) value;
        Value evaluated = expression.eval(symTable, heap);
        if (!evaluated.getType().equals(refValue.getLocationType()))
            throw new MyException(String.format("%s not of %s", evaluated, refValue.getLocationType()));
        heap.update(refValue.getAddress(), evaluated);
        state.setHeap(heap);
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new WriteHeapStmt(varName, expression.deepCopy());
    }
    @Override
    public String toString() {
        return String.format("WriteHeap(%s, %s)", varName, expression);
    }
}
