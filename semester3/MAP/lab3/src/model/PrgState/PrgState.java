package model.PrgState;


import model.ADT.Dictionary.MyIDictionary;
import model.ADT.List.MyIList;
import model.ADT.Stack.MyIStack;
import model.ADT.Stack.MyStack;
import model.Statement.IStmt;
import model.Value.Value;

public class PrgState {
    private MyIStack<IStmt> exeStack;
    private MyIDictionary<String,Value> symTable;
    private MyIList<Value> out;
    public PrgState(MyIStack <IStmt> exeS,MyIDictionary<String,Value> symT,MyIList<Value> ot, IStmt prg){
        exeStack=exeS;
        symTable=symT;
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

    @Override
    public String toString() {
        return
                "exeStack=" + exeStack.toString() +
                ", symTable=" + symTable.toString() +
                ", out=" + out.toString()
                ;
    }
}
