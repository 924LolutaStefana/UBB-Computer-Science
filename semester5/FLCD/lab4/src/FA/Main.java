package FA;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String fileName = "C:\\Users\\stefa\\Desktop\\Lab4\\src\\FA.in";
        FA.FiniteAutomata finiteAutomata = new FA.FiniteAutomata(fileName);

        Scanner scanner = new Scanner(System.in);
        String inputSequence = "";

        while (true) {
            System.out.println("\n1. Print all the states.");
            System.out.println("2. Print the alphabet.");
            System.out.println("3. Print the initial state.");
            System.out.println("4. Print the final states.");
            System.out.println("5. Print the transitions.");
            System.out.println("6. Check if a sequence is accepted by the DFA.");
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
                    System.out.print("Enter the sequence: ");
                    inputSequence = scanner.nextLine();
                    if (finiteAutomata.checkAcceptanceOfSequence(inputSequence)) {
                        System.out.println("The sequence " + inputSequence + " is accepted by the FA");
                    } else {
                        System.out.println("The sequence " + inputSequence + " is not accepted by the FA");
                    }
                }

                case 0 -> {
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Please enter a valid option.");

            }
        }
    }

}