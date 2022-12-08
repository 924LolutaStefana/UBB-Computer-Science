package model.PrgState;


import model.ADT.Dictionary.MyIDictionary;
import model.ADT.Heap.MyIHeap;
import model.ADT.List.MyIList;
import model.ADT.Stack.MyIStack;
import model.ADT.Stack.MyStack;
import model.Statement.IStmt;
import model.Value.Value;

import java.io.BufferedReader;
import java.util.List;

import Exception.MyException;
public class PrgState {
    private MyIStack<IStmt> exeStack;
    private MyIDictionary<String,Value> symTable;
    private MyIList<Value> out;
    private MyIHeap heap;
    private IStmt originalProgram;
    private MyIDictionary<String, BufferedReader> fileTable;
    public PrgState(MyIStack <IStmt> exeS,MyIDictionary<String,Value> symT,MyIList<Value> ot, IStmt prg, MyIDictionary<String, BufferedReader> fileTable,MyIHeap heap){
        exeStack=exeS;
        symTable=symT;
        this.heap = heap;
        this.fileTable = fileTable;
        out=ot;
        this.originalProgram = prg.deepCopy();
        exeStack.push(this.originalProgram);
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
    public void setHeap(MyIHeap newHeap) {
        this.heap = newHeap;
    }
    public MyIDictionary<String, BufferedReader> getFileTable() {
        return fileTable;
    }
    public MyIHeap getHeap() {
        return heap;
    }
    public String exeStackToString() {
        StringBuilder exeStackStringBuilder = new StringBuilder();
        List<IStmt> stack = exeStack.getReversed();
        for (IStmt statement: stack) {
            exeStackStringBuilder.append(statement.toString()).append("\n");
        }
        return exeStackStringBuilder.toString();
    }
    public String symTableToString() throws MyException {
        StringBuilder symTableStringBuilder = new StringBuilder();
        for (String key: symTable.keySet()) {
            symTableStringBuilder.append(String.format("%s -> %s\n", key, symTable.lookup(key).toString()));
        }
        return symTableStringBuilder.toString();
    }

    public String outToString() {
        StringBuilder outStringBuilder = new StringBuilder();
        for (Value elem: out.getList()) {
            outStringBuilder.append(String.format("%s\n", elem.toString()));
        }
        return outStringBuilder.toString();
    }

    public String fileTableToString() {
        StringBuilder fileTableStringBuilder = new StringBuilder();
        for (String key: fileTable.keySet()) {
            fileTableStringBuilder.append(String.format("%s\n", key));
        }
        return fileTableStringBuilder.toString();
    }
    public String heapToString() throws MyException {
        StringBuilder heapStringBuilder = new StringBuilder();
        for (int key: heap.keySet()) {
            heapStringBuilder.append(String.format("%d -> %s\n", key, heap.get(key)));
        }
        return heapStringBuilder.toString();
    }
    @Override
    public String toString() {
        return "Execution stack: \n" + exeStack.getReversed() + "\nSymbol table: \n" + symTable.toString() + "\nOutput list: \n" + out.toString() + "\nFile table:\n" + fileTable.toString() + "\nHeap memory:\n" + heap.toString() + "\n";
    }

    public String programStateToString() throws MyException {
        return "Execution stack: \n" + exeStackToString() + "Symbol table: \n" + symTableToString() + "Output list: \n" + outToString() + "File table:\n" + fileTableToString() + "Heap memory:\n" + heapToString();
    }
}
