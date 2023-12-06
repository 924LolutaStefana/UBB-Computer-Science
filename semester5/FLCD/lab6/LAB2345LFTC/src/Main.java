import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String fileName1 = "C:\\Users\\user\\Desktop\\LAB2345LFTC\\src\\programs\\g1.txt";
        String fileName2 = "C:\\Users\\user\\Desktop\\LAB2345LFTC\\src\\programs\\g2.txt";
        String fileName3 = "C:\\Users\\user\\Desktop\\LAB2345LFTC\\src\\programs\\g3.txt";
        Grammar grammar = new Grammar(fileName3);
        Parser parser = new Parser(grammar);
        Scanner scanner = new Scanner(System.in);
        String nonterminal = "";

        while (true) {
            System.out.println("1. Print set of nonterminals.");
            System.out.println("2. Print set of terminals.");
            System.out.println("3. Print set of productions.");
            System.out.println("4. Print productions for given nonterminal.");
            System.out.println("5. CFG check.");
            System.out.println("6. Compute FIRST sets.");
            System.out.println("7. Compute FOLLOW sets.");
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
                case 6 -> {
                    parser.computeFirstSets();
                }
                case 7 -> {
                    parser.computeFollowSets();
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
