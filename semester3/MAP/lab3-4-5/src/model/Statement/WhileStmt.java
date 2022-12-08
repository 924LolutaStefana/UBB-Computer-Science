package model.Statement;

import model.ADT.Stack.MyIStack;
import model.Expression.Exp;
import model.PrgState.PrgState;
import Exception.MyException;
import model.Type.BoolType;
import model.Value.BoolValue;
import model.Value.Value;

public class WhileStmt implements IStmt{
    private  Exp expression;
    private  IStmt statement;

    public WhileStmt(Exp expression, IStmt statement) {
        this.expression = expression;
        this.statement = statement;
    }



    @Override
    public PrgState execute(PrgState state) throws MyException {
        Value value = expression.eval(state.getSymTable(), state.getHeap());
        MyIStack<IStmt> stack = state.getExeStack();
        if (!value.getType().equals(new BoolType()))
            throw new MyException(String.format("%s is not of BoolType", value));
        BoolValue boolValue = (BoolValue) value;
        if (boolValue.getValue()) {
            state.getExeStack().push(this);
            state.getExeStack().push(statement);
        }
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new WhileStmt(expression.deepCopy(), statement.deepCopy());
    }

    @Override
    public String toString() {
        return String.format("while(%s){%s}", expression, statement);
    }
}
