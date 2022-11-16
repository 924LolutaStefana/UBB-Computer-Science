package model.Statement;
import Exception.MyException;
import model.ADT.Stack.MyIStack;
import model.Expression.Exp;
import model.PrgState.PrgState;
import model.Value.BoolValue;
import model.Value.Value;

public class IfStmt implements IStmt{
    Exp exp;
    IStmt thenS;
    IStmt elseS;

    public IfStmt(Exp exp, IStmt thenS, IStmt elseS) {
        this.exp = exp;
        this.thenS = thenS;
        this.elseS = elseS;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {

        Value result = this.exp.eval(state.getSymTable());
        IStmt statement = null;
        if (result instanceof BoolValue boolResult) {
            if (boolResult.isVal()) {
                statement = thenS;
            } else {
                statement = elseS;
            }

            MyIStack<IStmt> stack = state.getExeStack();
            stack.push(statement);
            state.setExeStack(stack);
            return state;
        }else
            throw new MyException("Please provide a boolean expression in an if statement.");
    }

    @Override
    public IStmt deepCopy() {
        return new IfStmt(exp.deepCopy(),thenS.deepCopy(),elseS.deepCopy());
    }

    @Override
    public String toString() {
        return " IF "+exp.toString()+" THEN "+thenS.toString()+" ELSE "+elseS.toString();
    }
}
