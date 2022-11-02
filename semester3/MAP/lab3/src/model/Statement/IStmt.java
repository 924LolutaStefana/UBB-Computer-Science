package model.Statement;
import Exception.MyException;
import model.PrgState.PrgState;

public interface IStmt {
    PrgState execute(PrgState state) throws MyException;
    IStmt deepCopy();
}
