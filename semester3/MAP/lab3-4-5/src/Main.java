import view.View;
import Exception.MyException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws MyException, IOException {
        View ui = new View();
        ui.start();
    }
}