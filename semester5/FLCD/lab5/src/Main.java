import FA.FiniteAutomata;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String fileName1 = "C:\\Users\\user\\Desktop\\LAB23LFTC\\src\\programs\\g1.txt";
        String fileName2 = "C:\\Users\\user\\Desktop\\LAB23LFTC\\src\\programs\\g2.txt";
        Grammar grammar = new Grammar(fileName2);
        Scanner scanner = new Scanner(System.in);
        String nonterminal = "";

        while (true) {
            System.out.println("1. Print set of nonterminals.");
            System.out.println("2. Print set of terminals.");
            System.out.println("3. Print set of productions.");
            System.out.println("4. Print productions for given nonterminal.");
            System.out.println("5. CFG check.");
            System.out.println("0. Exit.");

            System.out.print(">");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> grammar.showNonterminals();
                case 2 -> grammar.showTerminals();
                case 3 -> grammar.showProductions();
                case 4 -> {
                    System.out.print("Enter the nonterminal: ");
                    nonterminal = scanner.nextLine();
                    grammar.showProductionsForNonterminal(nonterminal);
                }
                case 5 -> {
                    if (grammar.checkIfCFG()) {
                        System.out.println("Grammar is context free grammar.");
                    } else {
                        System.out.println("Grammar is NOT context free grammar.");
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
