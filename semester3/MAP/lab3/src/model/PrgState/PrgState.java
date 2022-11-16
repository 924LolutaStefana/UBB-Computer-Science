package model.PrgState;


import model.ADT.Dictionary.MyIDictionary;
import model.ADT.List.MyIList;
import model.ADT.Stack.MyIStack;
import model.ADT.Stack.MyStack;
import model.Statement.IStmt;
import model.Value.Value;

import java.io.BufferedReader;

public class PrgState {
    private MyIStack<IStmt> exeStack;
    private MyIDictionary<String,Value> symTable;
    private MyIList<Value> out;
    private MyIDictionary<String, BufferedReader> fileTable;
    public PrgState(MyIStack <IStmt> exeS,MyIDictionary<String,Value> symT,MyIList<Value> ot, IStmt prg, MyIDictionary<String, BufferedReader> fileTable){
        exeStack=exeS;
        symTable=symT;
        this.fileTable = fileTable;
        out=ot;
        exeStack.push(prg);
    }

    public MyIStack<IStmt> getExeStack() {
        return exeStack;
    }

    public void setExeStack(MyIStack<IStmt> exeStack) {
        this.exeStack = exeStack;
    }

    public MyIDictionary<String, Value> getSymTable() {
        return symTable;
    }

    public void setSymTable(MyIDictionary<String, Value> symTable) {
        this.symTable = symTable;
    }

    public MyIList<Value> getOut() {
        return out;
    }

    public void setOut(MyIList<Value> out) {
        this.out = out;
    }
    public void setFileTable(MyIDictionary<String, BufferedReader> newFileTable) {
        this.fileTable = newFileTable;
    }
    public MyIDictionary<String, BufferedReader> getFileTable() {
        return fileTable;
    }

    @Override
    public String toString() {
        return
                "exeStack=" + exeStack.toString() +
                ", symTable=" + symTable.toString() +
                ", out=" + out.toString()+
                        ", fileTable=" +fileTable.toString()
                ;
    }
}
