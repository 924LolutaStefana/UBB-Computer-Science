import FA.MySet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;


public class Grammar {
    private final MySet<String> terminals;
    private final MySet<String> nonterminals;
    private String startingSymbol;
    private final MySet<Pair<String, MySet<String>>> productions;
    private final String fileName;

    public Grammar(String fileName) {
        this.fileName = fileName;
        this.terminals = new MySet<>();
        this.nonterminals = new MySet<>();
        this.startingSymbol = "";
        this.productions = new MySet<>();
        try {
            readFromFile();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void readFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                processLine(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processLine(String line) {
        if (line.startsWith("nonterminals=")) {
            String nonterminalsLine = line.substring("nonterminals=".length());
            nonterminalsLine = nonterminalsLine.substring(1, nonterminalsLine.length() - 1);
            String[] nonterminalsArray = nonterminalsLine.split(",");
            for (String nonterminal : nonterminalsArray) {
                nonterminals.add(nonterminal.trim());
            }
        } else if (line.startsWith("terminals=")) {
            String terminalsLine = line.substring("terminals=".length());
            terminalsLine = terminalsLine.substring(1, terminalsLine.length() - 1);
            String[] terminalsArray = terminalsLine.split(",");
            for (String terminal : terminalsArray) {
                terminals.add(terminal.trim());
            }
        } else if (line.startsWith("startingSymbol=")) {
            startingSymbol = line.substring("startingSymbol=".length()).trim();
        } else if (line.startsWith("productions=")) {
            String productionsLine = line.substring("productions=".length());
            productionsLine = productionsLine.substring(1, productionsLine.length() - 1);
            String[] productionsArray = productionsLine.split("\\$"); //it was ; at first but ; is also in the grammar

            for (String production : productionsArray) {
                String[] parts = production.split("->",2); //to split after the first ->, because -> is also a terminal in my grammar
                if (parts.length == 2) {
                    String nonterminal = parts[0].trim(); //nonterminal part
                    String[] productionArray = parts[1].split("\\|");

                    MySet<String> productionList = new MySet<>();
                    for (String prod : productionArray) {
                        productionList.add(prod.trim());
                    }
                    Pair<String, MySet<String>> pair = new Pair<>(nonterminal, productionList);
                    productions.add(pair);
                }
            }
        }
    }

    public void showNonterminals() {
        System.out.print("The nonterminals are: ");
        int nonterminalsSize = nonterminals.size();
        int i = 0;
        for (String nonterminal : nonterminals.getElements()) {
            System.out.print(nonterminal);
            if (++i < nonterminalsSize) {
                System.out.print(", ");
            } else {
                System.out.println(".");
            }
        }
        System.out.println("The starting symbol is: " + startingSymbol);
    }

    public void showTerminals() {
        System.out.print("The terminals are: ");
        int terminalsSize = terminals.size();
        int i = 0;
        for (String terminal : terminals.getElements()) {
            System.out.print(terminal);
            if (++i < terminalsSize) {
                System.out.print(", ");
            } else {
                System.out.println(".");
            }
        }
    }

    public void showProductions() {
        System.out.print("The set of productions is: ");
        int productionsSize = productions.size();
        int i = 0;
        for (Pair<String, MySet<String>> production : productions.getElements()) {
            System.out.print(production.getFirst() + "->" +
                    printRHS(production.getSecond()));
            if (++i < productionsSize) {
                System.out.print(", ");
            } else {
                System.out.println(".");
            }
        }
    }

    public void showProductionsForNonterminal(String nonterminal) {
        for (Pair<String, MySet<String>> production : productions.getElements()) {
            if (Objects.equals(production.getFirst(), nonterminal)) {
                System.out.print(printRHS(production.getSecond()) + "\n");

            }
        }
    }

    private StringBuilder printRHS(MySet<String> rhsProductions) {
        StringBuilder rhs = new StringBuilder();
        for (int i = 0; i < rhsProductions.size() - 1; i++) {
            rhs.append(rhsProductions.getElementAtPosition(i)).append("|");
        }
        rhs.append(rhsProductions.getElementAtPosition(rhsProductions.size() - 1));
        return rhs;
    }

    public boolean checkIfCFG() {
        if (!this.nonterminals.contains(this.startingSymbol)) { //verify if the starting symbols is found in the nonterminals set
            return false;
        }
        for (Pair<String, MySet<String>> production : productions.getElements()) { //verify if each production has a single nonterminal in the lhs
            if(production.getFirst().contains(" ")){
                return false;
            }
        }
        for (String nonterminal : nonterminals.getElements()) { //verify if each nonterminal has a production
            boolean hasProductions = false;
            for (Pair<String, MySet<String>> production : productions.getElements()) {
                if (production.getFirst().equals(nonterminal)) {
                    hasProductions = true;
                    break;
                }
            }
            if (!hasProductions) {
                System.out.println("Nonterminal '" + nonterminal + "' does not have any productions.");
                return false;
            }
        }
        return true;

    }
}
