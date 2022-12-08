package repository;

import model.PrgState.PrgState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import Exception.MyException;
public class Repository implements IRepository{
    private List<PrgState> programStates;
    private  String logFilePath;
    private int currentPosition;

    public Repository(PrgState progState,String logFilePath) {
        this.logFilePath = logFilePath;
        this.programStates = new ArrayList<>();
        this.currentPosition = 0;
        this.addProgram(progState);

    }

    @Override
    public PrgState getCrtPrg() {

            return this.programStates.get(this.currentPosition);

    }
    @Override
    public void PrgStateExec() throws MyException, IOException {
        PrintWriter logFile;
        logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
        logFile.println(this.programStates.get(0).toString());
        logFile.close();
    }
    @Override
    public void addProgram(PrgState progState) {
        this.programStates.add(progState);
    }

}
