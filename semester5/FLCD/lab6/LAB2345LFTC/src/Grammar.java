import FA.MySet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class Grammar {
    private  MySet<String> terminals;
    private  MySet<String> nonterminals;
    private String startingSymbol;
    private  MySet<Production> productions;
    private  String fileName;

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
            String[] productionsArray = productionsLine.split("\\$");
            for (String production : productionsArray) {
                String[] parts = production.split("->", 2);
                String lhs = parts[0].trim();
                String[] rhsArray = parts[1].split("\\|");
                MySet<List<String>> rhsSet = new MySet<>();
                for (String rhsProduction : rhsArray) {
                    List<String> rhsList = Arrays.asList(rhsProduction.trim().split("\\s+"));
                    rhsList.replaceAll(String::trim);
                    rhsSet.add(rhsList);
                }
                Production productionObj = new Production(lhs, rhsSet);
                productions.add(productionObj);
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

        for (Production production : productions.getElements()) {
            System.out.print(production.getLhs() + "->" +
                    printRHS(production.getRhs()));
            if (++i < productionsSize) {
                System.out.print(", ");
            } else {
                System.out.println(".");
            }
        }
    }

    @Override
    public String toString() {
        return "Grammar{" +
                "productions=" + productions +
                '}';
    }

    public void showProductionsForNonterminal(String nonterminal) {
        for (Production production : productions.getElements()) {
            if (Objects.equals(production.getLhs(), nonterminal)) {
                System.out.print(printRHS(production.getRhs()) + "\n");
            }
        }
    }

    private StringBuilder printRHS(MySet<List<String>> rhsProductions) {
        StringBuilder rhs = new StringBuilder();
        for (int i = 0; i < rhsProductions.size() - 1; i++) {
            List<String> rhsList = rhsProductions.getElementAtPosition(i);
            rhs.append(String.join("", rhsList)).append("|");
        }
        List<String> lastRhsList = rhsProductions.getElementAtPosition(rhsProductions.size() - 1);
        rhs.append(String.join("", lastRhsList));
        return rhs;
    }

    public boolean checkIfCFG() {
        if (!this.nonterminals.contains(this.startingSymbol)) {
            return false;
        }
        for (Production production : productions.getElements()) {
            if (production.getLhs().contains(" ")) {
                return false;
            }
        }
        return true;
    }

    public MySet<String> getTerminals() {
        return terminals;
    }

    public MySet<String> getNonterminals() {
        return nonterminals;
    }

    public String getStartingSymbol() {
        return startingSymbol;
    }

    public void setStartingSymbol(String startingSymbol) {
        this.startingSymbol = startingSymbol;
    }

    public MySet<Production> getProductions() {
        return productions;
    }

    public String getFileName() {
        return fileName;
    }

    public void setTerminals(MySet<String> terminals) {
        this.terminals = terminals;
    }

    public void setNonterminals(MySet<String> nonterminals) {
        this.nonterminals = nonterminals;
    }

    public void setProductions(MySet<Production> productions) {
        this.productions = productions;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public MySet<List<String>> getProductionsForNonterminal(String nonterminal) {
        MySet<List<String>> rhs = new MySet<>();
        for (Production production : productions.getElements()) {
            if (production.getLhs().equals(nonterminal)) {
                rhs = production.getRhs();
            }
        }
        return rhs;
    }
}
