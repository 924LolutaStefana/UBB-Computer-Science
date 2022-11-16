package model.Statement;

import model.ADT.Dictionary.MyIDictionary;
import model.Expression.Exp;
import model.PrgState.PrgState;
import Exception.MyException;
import model.Type.IntType;
import model.Type.StringType;
import model.Value.IntValue;
import model.Value.StringValue;
import model.Value.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFile implements IStmt{
    private  Exp exp;
    private  String varName;

    public ReadFile(Exp exp, String varName) {
        this.exp = exp;
        this.varName = varName;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIDictionary<String, BufferedReader> fileTable = state.getFileTable();

        if (symTable.isVarDef(varName)) {
            Value value = symTable.lookup(varName);
            if (value.getType().equals(new IntType())) {
                value = exp.eval(state.getSymTable());
                if (value.getType().equals(new StringType())) {
                    StringValue castValue = (StringValue) value;
                    if (fileTable.isVarDef(castValue.getVal())) {
                        BufferedReader br = fileTable.lookup(castValue.getVal());
                        try {
                            String line = br.readLine();
                            if (line == null)
                                line = "0";
                            symTable.put(varName, new IntValue(Integer.parseInt(line)));
                        } catch (IOException e) {
                            throw new MyException(String.format("Could not read from file %s", castValue));
                        }
                    } else {
                        throw new MyException(String.format("The file table does not contain %s", castValue));
                    }
                } else {
                    throw new MyException(String.format("%s does not evaluate to StringType", value));
                }
            } else {
                throw new MyException(String.format("%s is not of type IntType", value));
            }
        } else {
            throw new MyException(String.format("%s is not present in the symTable.", varName));
        }
        return state;
    }

    @Override
    public IStmt deepCopy() {
        return new ReadFile(exp.deepCopy(), varName);
    }

    @Override
    public String toString() {
        return String.format("ReadFile(%s, %s)", exp.toString(), varName);
    }
}
