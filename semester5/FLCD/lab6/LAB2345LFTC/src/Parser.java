import FA.MySet;

import java.util.*;

public class Parser {

    private final Grammar grammar;
    private Map<String, MySet<String>> firstSets;
    private Map<String, MySet<String>> followSets;

    public Parser(Grammar grammar) {
        this.grammar = grammar;
        this.firstSets = new HashMap<>();
        this.followSets = new HashMap<>();
    }

    public void computeFirstSets() {
        for (String nonterminal : grammar.getNonterminals().getElements()) {
            MySet<String> f0Set = computeF0(nonterminal);
            firstSets.put(nonterminal, f0Set);
        }
        var isChanged = true;
        while (isChanged) {
            isChanged = false;
            Map<String, MySet<String>> newColumn = new HashMap<>();
            for (String nonterminal : grammar.getNonterminals().getElements()) {
                MySet<List<String>> productionForNonterminal = grammar.getProductionsForNonterminal(nonterminal);
                MySet<String> toAdd = new MySet<>();
                toAdd.addAll(firstSets.get(nonterminal));
                for (List<String> production : productionForNonterminal.getElements()) {
                    List<String> rhsNonterminals = new ArrayList<>();
                    String rhsTerminal = null;
                    for (String symbol : production) {
                        if (this.grammar.getNonterminals().getElements().contains(symbol))
                            rhsNonterminals.add(symbol);
                        else {
                            rhsTerminal = symbol;
                            break;
                        }
                    }
                    var resultConcatenation = concatenationOfLengthOne(rhsNonterminals, rhsTerminal);
                    toAdd.addAll(resultConcatenation);
                }
                newColumn.put(nonterminal, toAdd);
            }
            if (!newColumn.entrySet().equals(firstSets.entrySet()))
                isChanged = true;
            firstSets = newColumn;
        }
        for (String terminal : grammar.getTerminals().getElements()) {
            MySet<String> terminalSet = new MySet<>();
            terminalSet.add(terminal);
            firstSets.put(terminal, terminalSet);
        }

        System.out.println("FIRST Sets:");
        for (String nonterminal : grammar.getNonterminals().getElements()) {
            System.out.println("FIRST(" + nonterminal + ") = " + firstSets.get(nonterminal));
        }
    }

    private MySet<String> concatenationOfLengthOne(List<String> rhsNonterminals, String rhsTerminal) {
        if (rhsNonterminals.size() == 0)
            return new MySet<>();
        if (rhsNonterminals.size() == 1) {
            return firstSets.get(rhsNonterminals.iterator().next());
        }
        MySet<String> concatenation = new MySet<>();
        int step = 0;
        boolean allEpsilon = true;
        for (String nonterminal : rhsNonterminals) {
            if (!firstSets.get(nonterminal).contains("EPSILON")) {
                allEpsilon = false;
            }
        }
        if (allEpsilon) {
            concatenation.add(Objects.requireNonNullElse(rhsTerminal, "EPSILON"));
        }
        while (step < rhsNonterminals.size()) {
            boolean thereIsOneEpsilon = false;
            for (String s : firstSets.get(rhsNonterminals.get(step)).getElements()) {
                if (s.equals("EPSILON"))
                    thereIsOneEpsilon = true;
                else
                    concatenation.add(s);
            }
            if (thereIsOneEpsilon)
                step++;
            else break;
        }
        return concatenation;
    }

    public MySet<String> computeF0(String nonterminal) {
        MySet<String> f0Set = new MySet<>();
        for (Production production : grammar.getProductions().getElements()) {
            if (production.getLhs().equals(nonterminal)) {
                for (List<String> rhsList : production.getRhs().getElements()) {
                    String firstSymbol = rhsList.get(0);
                    if (grammar.getTerminals().contains(firstSymbol) || "EPSILON".equals(firstSymbol)) {
                        f0Set.add(firstSymbol);
                    }
                }
            }
        }
        return f0Set;
    }

    public void computeFollowSets() {
        for (String nonterminal : grammar.getNonterminals().getElements()) {
            followSets.put(nonterminal, new MySet<>());
        }
        followSets.get(grammar.getStartingSymbol()).add("EPSILON");
        boolean isChanged = true;
        while (isChanged) {
            isChanged = false;
            HashMap<String, MySet<String>> newColumn = new HashMap<>();

            for (String nonterminal : grammar.getNonterminals().getElements()) {
                newColumn.put(nonterminal, new MySet<>());
                var productionsWithNonterminalInRhs = new HashMap<String, Set<List<String>>>();
                var allProductions = grammar.getProductions();
                for (Production currentProduction : allProductions.getElements()) {
                    for (List<String> smallProduction : currentProduction.getRhs().getElements()) {
                        if (smallProduction.contains(nonterminal)) {
                            String lhs = currentProduction.getLhs();
                            if (!productionsWithNonterminalInRhs.containsKey(lhs))
                                productionsWithNonterminalInRhs.put(lhs, new HashSet<>());
                            productionsWithNonterminalInRhs.get(lhs).add(smallProduction);
                        }
                    }
                }
                MySet<String> toAdd = new MySet<>();
                toAdd.addAll(followSets.get(nonterminal));
                productionsWithNonterminalInRhs.forEach((k, v) -> {
                    for (List<String> production : v) {
                        for (int indexOfNonterminal = 0; indexOfNonterminal < production.size(); ++indexOfNonterminal) {
                            if (production.get(indexOfNonterminal).equals(nonterminal)) {
                                if (indexOfNonterminal + 1 == production.size()) {
                                    toAdd.addAll(followSets.get(k));
                                } else {
                                    String followSymbol = production.get(indexOfNonterminal + 1);
                                    if (grammar.getTerminals().getElements().contains(followSymbol))
                                        toAdd.add(followSymbol);
                                    else {
                                        for (String symbol : firstSets.get(followSymbol).getElements()) {
                                            if (symbol.equals("EPSILON"))
                                                toAdd.addAll(followSets.get(k));
                                            else
                                                toAdd.addAll(firstSets.get(followSymbol));
                                        }
                                    }
                                }
                            }
                        }
                    }

                });

                newColumn.put(nonterminal, toAdd);
            }
            if (!newColumn.entrySet().equals(followSets.entrySet()))
                isChanged = true;
            followSets = newColumn;
        }
        System.out.println("FOLLOW Sets:");
        for (String nonterminal : grammar.getNonterminals().getElements()) {
            System.out.println("FOLLOW(" + nonterminal + ") = " + followSets.get(nonterminal));
        }
    }
    public Map<String, MySet<String>> getFirstSets() {
        return firstSets;
    }

    public Map<String, MySet<String>> getFollowSets() {
        return followSets;
    }
}
