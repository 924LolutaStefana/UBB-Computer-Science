package view;

import controller.Controller;
import model.ADT.Dictionary.MyDictionary;
import model.ADT.Heap.MyHeap;
import model.ADT.List.MyList;
import model.ADT.Stack.MyStack;
import model.Expression.ArithExp;
import model.Expression.RelationalExp;
import model.Expression.ValueExp;
import model.Expression.VarExp;
import model.PrgState.PrgState;
import model.Statement.*;
import model.Type.BoolType;
import model.Type.IntType;
import model.Type.StringType;
import model.Value.BoolValue;
import model.Value.IntValue;
import model.Value.StringValue;
import model.Value.Value;
import repository.IRepository;
import repository.Repository;

import java.io.BufferedReader;

public class Interpreter {
    public static void main(String[] args){
        IStmt ex1 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))),
                        new PrintStmt(new VarExp("v"))));
        PrgState prg1 = new   PrgState( new MyStack <>() , new MyDictionary<>(),new MyList<>(), ex1 , new MyDictionary<>(),new MyHeap() );
        IRepository repo1 = new Repository(prg1, "log1.txt");
        Controller controller1 = new Controller(repo1);

        IStmt ex2 = new CompStmt(new VarDeclStmt("a",new IntType()),
                new CompStmt(new VarDeclStmt("b",new IntType()),
                        new CompStmt(new AssignStmt("a", new ArithExp('+',new ValueExp(new IntValue(2)),new
                                ArithExp('*',new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),
                                new CompStmt(new AssignStmt("b",new ArithExp('+',new VarExp("a"), new ValueExp(new
                                        IntValue(1)))), new PrintStmt(new VarExp("b"))))));
        PrgState prg2 = new  PrgState(new MyStack <>() , new MyDictionary<>(),new MyList<>(), ex2 , new MyDictionary<>(),new MyHeap());
        IRepository repo2 = new Repository(prg2, "log2.txt");
        Controller controller2 = new Controller(repo2);



        IStmt ex4 = new CompStmt(new VarDeclStmt ("varf", new StringType()),
                new CompStmt(new AssignStmt("varf", new ValueExp(new StringValue("test.in"))),
                        new CompStmt(new OpenRFile(new VarExp("varf")),
                                new CompStmt(new VarDeclStmt("varc", new IntType()),
                                        new CompStmt(new ReadFile(new VarExp("varf"), "varc"),
                                                new CompStmt(new PrintStmt(new VarExp("varc")),
                                                        new CompStmt(new ReadFile(new VarExp("varf"), "varc"),
                                                                new CompStmt(new PrintStmt(new VarExp("varc")),
                                                                        new CloseRFile(new VarExp("varf"))))))))));

        PrgState prg4 = new PrgState(new MyStack <>() , new MyDictionary<>(),new MyList<>(), ex4 , new MyDictionary<>(),new MyHeap());
        IRepository repo4 = new Repository(prg4, "log4.txt");
        Controller controller4 = new Controller(repo4);

        IStmt ex3 = new CompStmt(new VarDeclStmt("a", new IntType()),
                new CompStmt(new VarDeclStmt("b", new IntType()),
                        new CompStmt(new AssignStmt("a", new ValueExp(new IntValue(5))),
                                new CompStmt(new AssignStmt("b", new ValueExp(new IntValue(7))),
                                        new IfStmt(new RelationalExp(">", new VarExp("a"),
                                                new VarExp("b")),new PrintStmt(new VarExp("a")),
                                                new PrintStmt(new VarExp("b")))))));

        PrgState prg3 = new  PrgState(new MyStack <>() , new MyDictionary<>(),new MyList<>(), ex3 , new MyDictionary<>(),new MyHeap());
        IRepository repo3 = new Repository(prg3, "log3.txt");
        Controller controller3 = new Controller(repo3);
        IStmt ex5 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(4))),
                        new CompStmt(new WhileStmt(new RelationalExp(">", new VarExp("v"), new ValueExp(new IntValue(0))),
                                new CompStmt(new PrintStmt(new VarExp("v")), new AssignStmt("v",new ArithExp('-', new VarExp("v"), new ValueExp(new IntValue(1)))))),
                                new PrintStmt(new VarExp("v")))));

        PrgState prg5 = new  PrgState(new MyStack <>() , new MyDictionary<>(),new MyList<>(), ex5 , new MyDictionary<>(),new MyHeap());
        IRepository repo5 = new Repository(prg5, "log5.txt");
        Controller controller5 = new Controller(repo5);

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExemple("1", ex1.toString(), controller1));
        menu.addCommand(new RunExemple("2", ex2.toString(), controller2));
        menu.addCommand(new RunExemple("3", ex3.toString(), controller3));
        menu.addCommand(new RunExemple("4", ex4.toString(), controller4));
        menu.addCommand(new RunExemple("5", ex5.toString(), controller5));

        menu.show();
    }
}
