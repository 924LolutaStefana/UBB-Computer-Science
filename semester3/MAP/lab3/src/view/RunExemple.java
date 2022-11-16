package view;

import controller.Controller;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;
import Exception.MyException;
public class RunExemple extends Command{
    private Controller controller;
    public RunExemple(String key, String description,Controller controller) {
        super(key, description);
        this.controller = controller;
    }

    @Override
    public void execute() {
        try {
            //System.out.println("Do you want to display the steps?[Y/n]");
            //Scanner readOption = new Scanner(System.in);
            //String option = readOption.next();

            controller.setDisplayFlag(true);
            controller.allStep();
        } catch (MyException | IOException exception) {
            System.out.println("\u001B[31m" + exception.getMessage() + "\u001B[0m");
        }
    }
    }

