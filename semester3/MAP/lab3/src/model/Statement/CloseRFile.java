package model.Statement;

import model.ADT.Dictionary.MyIDictionary;
import model.Expression.Exp;
import model.PrgState.PrgState;
import Exception.MyException;
import model.Type.StringType;
import model.Value.StringValue;
import model.Value.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseRFile implements IStmt{
    private Exp exp;

    public CloseRFile(Exp exp) {
        this.exp = exp;
    }


    @Override
    public PrgState execute(PrgState state) throws MyException {
        Value value = exp.eval(state.getSymTable());
        if (!value.getType().equals(new StringType()))
            throw new MyException(String.format("%s does not evaluate to StringValue", exp));
        StringValue fileName = (StringValue) value;
        MyIDictionary<String, BufferedReader> fileTable = state.getFileTable();
        if (!fileTable.isVarDef(fileName.getVal()))
            throw new MyException(String.format("%s is not present in the FileTable", value));
        BufferedReader br = fileTable.lookup(fileName.getVal());
        try {
            br.close();
        } catch (IOException e) {
            throw new MyException(String.format("Unexpected error in closing %s", value));
        }
        fileTable.remove(fileName.getVal());
        state.setFileTable(fileTable);
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return null;
    }
}
