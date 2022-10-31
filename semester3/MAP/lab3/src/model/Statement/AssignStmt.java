package model.Statement;
import Exception.MyException;
import model.ADT.Dictionary.MyIDictionary;
import model.ADT.Stack.MyIStack;
import model.Expression.Exp;
import model.PrgState.PrgState;
import model.Type.Type;
import model.Value.Value;

public class AssignStmt implements IStmt{
    String id;
    Exp exp;

    public AssignStmt(String id,Exp exp) {
        this.id = id;
        this.exp=exp;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk=state.getExeStack();
        MyIDictionary<String, Value> symTbl=state.getSymTable();
        if (symTbl.isVarDef(id)){
            Value val=exp.eval(symTbl);
            Type typId=(symTbl.lookup(id)).getType();
            if(val.getType().equals(typId)){
                symTbl.update(id,val);
            }else
                throw new MyException("declared type of variable"+id+" and type of  the assigned expression do not match");

        }else throw new MyException("the used variable" +id + " was not declared before");
        return state;
    }

    @Override
    public String toString() {
        return id.toString()+exp.toString();
    }
}
