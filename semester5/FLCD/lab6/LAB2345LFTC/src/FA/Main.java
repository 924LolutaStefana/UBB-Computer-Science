package FA;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String fileName = "C:\\Users\\user\\Desktop\\lab23LFTC\\src\\FA\\FA.in";
        FiniteAutomata finiteAutomata = new FiniteAutomata(fileName);

        Scanner scanner = new Scanner(System.in);
        String inputSequence = "";

        while (true) {
            System.out.println("\n1. Show all the states.");
            System.out.println("2. Show the alphabet.");
            System.out.println("3. Show the initial state.");
            System.out.println("4. Show the final states.");
            System.out.println("5. Show the transitions.");
            System.out.println("6. Check if the FA is a deterministic FA.");
            System.out.println("7. Check is a sequence is accepted by the DFA.");
            System.out.println("0. Exit.");

            System.out.print(">");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> finiteAutomata.showStates();
                case 2 -> finiteAutomata.showAlphabet();
                case 3 -> finiteAutomata.showInitialState();
                case 4 -> finiteAutomata.showFinalStates();
                case 5 -> finiteAutomata.showTransitions();
                case 6 -> {
                    if (finiteAutomata.checkIfDFA()) {
                        System.out.println("The finite automata IS deterministic.");
                    } else {
                        System.out.println("The finite automata IS NOT deterministic.");
                    }
                }
                case 7 -> {
                    System.out.print("Enter the sequence: ");
                    inputSequence = scanner.nextLine();
                    if (finiteAutomata.checkAcceptanceOfSequence(inputSequence)) {
                        System.out.println("The sequence " + inputSequence + " IS accepted by the FA");
                    } else {
                        System.out.println("The sequence " + inputSequence + " IS NOT accepted by the FA");
                    }
                }

                case 0 -> {
                    System.out.println("Exiting the program.");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Please enter a valid option.");

            }
        }
    }

}
