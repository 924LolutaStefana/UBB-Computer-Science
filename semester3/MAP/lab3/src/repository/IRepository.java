package repository;

import model.PrgState.PrgState;

import java.util.List;

public interface IRepository {
    PrgState getCrtPrg();
    void addProgram(PrgState progState);
}
