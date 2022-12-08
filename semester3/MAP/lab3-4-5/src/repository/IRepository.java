package repository;

import model.PrgState.PrgState;
import Exception.MyException;

import java.io.IOException;
import java.util.List;

public interface IRepository {
    PrgState getCrtPrg();
    void addProgram(PrgState progState);
    void PrgStateExec() throws MyException, IOException;
}
