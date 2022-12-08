package controller;

import model.ADT.Stack.MyIStack;
import model.PrgState.PrgState;
import model.Statement.IStmt;
import repository.IRepository;
import Exception.MyException;

import java.io.IOException;

public class Controller {
    IRepository repository;
    boolean displayFlag = false;

    public void setDisplayFlag(boolean value) {
        this.displayFlag = value;
    }

    public Controller(IRepository repository) {
        this.repository = repository;
    }
    public PrgState oneStep(PrgState state) throws MyException {

        MyIStack<IStmt> stk=state.getExeStack();
        if(stk.isEmpty())
            throw  new MyException("prgstate stack is empty");
        IStmt  crtStmt = stk.pop();
        return crtStmt.execute(state);
    }
    public void allStep() throws MyException, IOException {
        PrgState prg = repository.getCrtPrg();
        repository.PrgStateExec();
        while (!prg.getExeStack().isEmpty()){
            oneStep(prg);
            repository.PrgStateExec();
        }}
    private void display() {
        if (displayFlag) {
            System.out.println(this.repository.getCrtPrg().toString());
        }
    }


}
