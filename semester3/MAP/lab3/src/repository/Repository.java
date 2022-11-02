package repository;

import model.PrgState.PrgState;

import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository{
    private List<PrgState> programStates;

    private int currentPosition;

    public Repository(PrgState progState) {
        this.programStates = new ArrayList<>();
        this.currentPosition = 0;
        this.addProgram(progState);

    }

    @Override
    public PrgState getCrtPrg() {

            return this.programStates.get(this.currentPosition);

    }
    @Override
    public void addProgram(PrgState progState) {
        this.programStates.add(progState);
    }

}
