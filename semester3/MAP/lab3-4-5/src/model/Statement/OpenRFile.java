package model.Statement;

import model.ADT.Dictionary.MyIDictionary;
import model.Expression.Exp;
import model.PrgState.PrgState;
import Exception.MyException;
import model.Type.StringType;
import model.Value.StringValue;
import model.Value.Value;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class OpenRFile implements IStmt{
    private Exp exp;

    public OpenRFile(Exp exp) {
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        Value value = exp.eval(state.getSymTable(), state.getHeap());
        if (value.getType().equals(new StringType())) {
            StringValue fileName = (StringValue) value;
            MyIDictionary<String, BufferedReader> fileTable = state.getFileTable();
            if (!fileTable.isVarDef(fileName.getVal())) {
                BufferedReader br;
                try {
                    br = new BufferedReader(new FileReader(fileName.getVal()));
                } catch ( FileNotFoundException e) {
                    throw new MyException(String.format("%s could not be opened", fileName.getVal()));
                }
                fileTable.put(fileName.getVal(), br);
                state.setFileTable(fileTable);
            } else {
                throw new MyException(String.format("%s is already opened", fileName.getVal()));
            }
        } else {
            throw new MyException(String.format("%s does not evaluate to StringType", exp.toString()));
        }
        return state;
    }


    @Override
    public IStmt deepCopy() {
        return new OpenRFile(exp.deepCopy());
    }

    @Override
    public String toString() {
        return String.format("OpenReadFile(%s)", exp.toString());
    }
}
