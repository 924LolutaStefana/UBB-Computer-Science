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
        return state;
    }

    @Override
    public String toString() {
        return  first.toString() +
                 second.toString() ;

    }
}
