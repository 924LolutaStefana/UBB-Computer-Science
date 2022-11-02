package model.Statement;
import Exception.MyException;
import model.PrgState.PrgState;

public class NopStmt implements IStmt{
    @Override
    public PrgState execute(PrgState state) throws MyException {
        return null;
    }

    @Override
    public String toString() {
        return "NopStmt";
    }
    @Override
    public IStmt deepCopy(){
        return new NopStmt();
    }
}
