package model.Statement;
import Exception.MyException;
import model.ADT.Dictionary.MyIDictionary;
import model.ADT.List.MyIList;
import model.ADT.Stack.MyIStack;
import model.Expression.Exp;
import model.PrgState.PrgState;
import model.Value.Value;

public class PrintStmt implements IStmt{
    Exp exp;

    public PrintStmt(Exp exp) {
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIList<Value> out=state.getOut();

        out.add(exp.eval(state.getSymTable(), state.getHeap()));
        state.setOut(out);
        return state;
    }

    @Override
    public String toString() {
        return exp.toString();
    }

    @Override
    public IStmt deepCopy() {
        return new PrintStmt(exp.deepCopy());
    }
}
