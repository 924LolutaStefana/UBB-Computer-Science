package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import Exception.MyException;
import controller.Controller;
import model.ADT.Dictionary.MyDictionary;
import model.ADT.Dictionary.MyIDictionary;
import model.ADT.List.MyIList;
import model.ADT.List.MyList;
import model.ADT.Stack.MyIStack;
import model.ADT.Stack.MyStack;
import model.Expression.ArithExp;
import model.Expression.ValueExp;
import model.Expression.VarExp;
import model.PrgState.PrgState;
import model.Statement.*;
import model.Type.BoolType;
import model.Type.IntType;
import model.Value.BoolValue;
import model.Value.IntValue;
import model.Value.Value;
import repository.IRepository;
import repository.Repository;

public class View {
    public void start() throws MyException, IOException {
        boolean done = false;
        while (!done) {

                showMenu();
                Scanner readOption = new Scanner(System.in);
                int option = readOption.nextInt();
                if (option == 0) {
                    done = true;
                } else if (option == 1) {
                    runProgram1();
                } else if (option == 2) {
                    runProgram2();
                } else if (option == 3) {
                    runProgram3();
                } else {
                    System.out.println("Invalid input!");
                }

        }
    }

    private void showMenu() {

        System.out.println("0. Exit.");
        System.out.println("1. Run first: \n\tint v;\n\tv=2;\n\tPrint(v)");
        System.out.println("2. Run second: \n\tint a;\n\tint b;\n\ta=2+3*5;\n\tb=a+1;\n\tPrint(b)");
        System.out.println("3. Run third \n\tbool a;\n\tint v;\n\ta=true;\n\t(If a Then v=2 Else v=3);\n\tPrint(v)");
        System.out.println("Choose an option> ");
    }

    private void runProgram1() throws MyException, IOException {
        IStmt ex1 = new CompStmt(new VarDeclStmt(" v", new IntType()),
                new CompStmt(new AssignStmt(" v", new ValueExp(new IntValue(2))),
                        new PrintStmt(new VarExp(" v"))));
        runStatement(ex1);
    }

    private void runProgram2() throws MyException, IOException {
        IStmt ex2 = new CompStmt( new VarDeclStmt(" a",new IntType()),
                new CompStmt(new VarDeclStmt(" b",new IntType()),
                        new CompStmt(new AssignStmt(" a", new ArithExp('+',new ValueExp(new IntValue(2)),
                                new ArithExp('*',new ValueExp(new IntValue(3))
                                        , new ValueExp(new IntValue(5))))),  new CompStmt(new AssignStmt(" b",
                                new ArithExp('+',new VarExp(" a"), new ValueExp(new IntValue(1)))), new PrintStmt(new VarExp(" b"))))));
        runStatement(ex2);
    }

    private void runProgram3() throws MyException, IOException {
        IStmt ex3 = new CompStmt(new VarDeclStmt(" a", new BoolType()),
                new CompStmt(new VarDeclStmt(" v", new IntType()),
                        new CompStmt(new AssignStmt(" a", new ValueExp(new BoolValue(true))),
                                new CompStmt(new IfStmt(new VarExp(" a"),
                                        new AssignStmt(" v", new ValueExp(new IntValue(2))),
                                        new AssignStmt(" v", new ValueExp(new IntValue(3)))),
                                        new PrintStmt(new VarExp(" v"))))));
        runStatement(ex3);
    }

    private void runStatement(IStmt statement) throws MyException, IOException {
        MyIStack<IStmt> executionStack = new MyStack<>();
        MyIDictionary<String, Value> symbolTable = new MyDictionary<>();
        MyIList<Value> output = new MyList<>();
        MyIDictionary<String, BufferedReader> fileTable = new MyDictionary<>();

        PrgState state = new PrgState(executionStack, symbolTable, output,  statement,fileTable);

        IRepository repository = new Repository(state,"");
        Controller controller = new Controller(repository);

        System.out.println("Do you want to display the steps?Yes or No?");
        Scanner readOption = new Scanner(System.in);
        String option = readOption.next();
        controller.setDisplayFlag(Objects.equals(option, "Yes"));

        controller.allStep();
        System.out.println("Result is" + state.getOut().toString().replace('[', ' ').replace(']', ' '));
    }

}
