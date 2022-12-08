package model.Statement;
import Exception.MyException;
import model.ADT.Stack.MyIStack;
import model.PrgState.PrgState;

public class CompStmt implements IStmt{
    IStmt first;
    IStmt second;

    public CompStmt(IStmt first, IStmt second) {
        this.first = first;
        this.second = second;
    }


    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk=state.getExeStack()  ;
        stk.push(second);
        stk.push(first);
        state.setExeStack(stk);
        return state;
    }

    @Override
    public String toString() {
        return String.format("(%s|%s)", first.toString(), second.toString());

    }

    @Override
    public IStmt deepCopy() {
        return new CompStmt(first.deepCopy(),second.deepCopy());
    }
}
