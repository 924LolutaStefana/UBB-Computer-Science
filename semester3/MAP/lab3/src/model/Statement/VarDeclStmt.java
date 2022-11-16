package model.Statement;
import Exception.MyException;
import model.ADT.Dictionary.MyIDictionary;
import model.PrgState.PrgState;
import model.Type.BoolType;
import model.Type.IntType;
import model.Type.Type;
import model.Value.BoolValue;
import model.Value.Value;

public class VarDeclStmt implements IStmt{
    String name;
    Type typ;

    public VarDeclStmt(String name, Type typ) {
        this.name = name;
        this.typ = typ;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, Value> symTbl=state.getSymTable();
        if(symTbl.isVarDef(name)){
            throw new MyException(name+"already exists");
        }

        symTbl.put(name,typ.defaultValue());
        state.setSymTable(symTbl);
        return state;
    }

    @Override
    public String toString() {
        return name.toString()+" "+typ.toString();
    }

    @Override
    public IStmt deepCopy() {
        return new VarDeclStmt(name,typ);
    }
}
