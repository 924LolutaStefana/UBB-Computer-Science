package FA;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FiniteAutomata {

    private final MySet<String> states;
    private String initialState;
    private final MySet<String> finalStates;
    private final MySet<Transition> transitions;
    private final MySet<String> alphabet;


    private String fileName;

    public FiniteAutomata(String fileName) {
        this.fileName = fileName;
        this.states = new MySet<>();
        this.initialState = "";
        this.finalStates = new MySet<>();
        this.transitions = new MySet<>();
        this.alphabet = new MySet<>();
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
        if (line.startsWith("states=")) {
            String statesLine = line.substring("states=".length());
            statesLine = statesLine.substring(1, statesLine.length() - 1); //eliminate { and }
            String[] stateArray = statesLine.split(",");
            for (String state : stateArray) {
                states.add(state.trim());
            }
        } else if (line.startsWith("initialState=")) {
            initialState = line.substring("initialState=".length()).trim();
        } else if (line.startsWith("finalStates=")) {
            String finalStatesLine = line.substring("finalStates=".length());
            finalStatesLine = finalStatesLine.substring(1, finalStatesLine.length() - 1);
            String[] finalStateArray = finalStatesLine.split(",");
            for (String finalState : finalStateArray) {
                finalStates.add(finalState.trim());
            }
        } else if (line.startsWith("transitions=")) {
            String transitionsLine = line.substring("transitions=".length());
            transitionsLine = transitionsLine.substring(1, transitionsLine.length() - 1);
            String[] transitionArray = transitionsLine.split(";");
            for (String transitionStr : transitionArray) {
                String[] parts = transitionStr.replaceAll("[()]", "").split(",");
                if (parts.length == 3) {
                    transitions.add(new Transition(parts[0].trim(), parts[1].trim(), parts[2].trim()));
                }
            }
        } else if (line.startsWith("alphabet=")) {
            String alphabetLine = line.substring("alphabet=".length());
            alphabetLine = alphabetLine.substring(1, alphabetLine.length() - 1);
            String[] symbolArray = alphabetLine.split(",");
            for (String symbol : symbolArray) {
                alphabet.add(symbol.trim());
            }
        }
    }


    public boolean checkIfDFA() {
        for (String state : states.getElements()) {
            for (String symbol : alphabet.getElements()) {
                // Check if there is exactly one transition for each symbol from each state
                if (getTransitionsFromStartStateAndSymbol(state, symbol).size() > 1) {
                    return false;
                }
            }
        }
        return true;
    }

    private MySet<Transition> getTransitionsFromStartStateAndSymbol(String state, String symbol) {
        MySet<Transition> result = new MySet<>();
        for (Transition transition : transitions.getElements()) {
            if (transition.getStart_state().equals(state) && transition.getLabeled_symbol().equals(symbol)) {
                result.add(transition);
            }
        }
        return result;
    }

    public void showStates() {
        System.out.print("The states are: ");
        int statesSize = states.size();
        int i = 0;
        for (String state : states.getElements()) {
            System.out.print(state);
            if (++i < statesSize) {
                System.out.print(", ");
            } else {
                System.out.println(".");
            }
        }
    }

    public void showAlphabet() {
        System.out.print("The elements of the alphabet are: ");
        int alphabetSize = alphabet.size();
        int i = 0;
        for (String symbol : alphabet.getElements()) {
            System.out.print(symbol);
            if (++i < alphabetSize) {
                System.out.print(", ");
            } else {
                System.out.println(".");
            }
        }
    }

    public void showInitialState() {
        System.out.println("The initial state is: " + initialState + ".");
    }

    public void showFinalStates() {
        int finalStatesSize = finalStates.size();
        if (finalStatesSize == 1) {
            System.out.println("The final state is: " + finalStates.getElements().get(0) + ".");
        } else if (finalStatesSize > 1) {
            System.out.print("The final states are: ");
            int i = 0;
            for (String finalState : finalStates.getElements()) {
                System.out.print(finalState);
                if (++i < finalStatesSize) {
                    System.out.print(", ");
                } else {
                    System.out.println(".");
                }
            }
        }
    }

    public void showTransitions() {
        System.out.print("The set of transitions is: ");
        int transitionsSize = transitions.size();
        int i = 0;
        for (Transition transition : transitions.getElements()) {
            System.out.print("(" + transition.getStart_state() + ", " +
                    transition.getLabeled_symbol() + ", " +
                    transition.getTarget_state() + ")");
            if (++i < transitionsSize) {
                System.out.print(", ");
            } else {
                System.out.println(".");
            }
        }
    }

    public boolean checkAcceptanceOfSequence(String inputSequence) {
        if (!checkIfDFA()) {
            return false;
        }
        String currentState = initialState;
        for (int i = 0; i < inputSequence.length(); i++) {
            String symbol = String.valueOf(inputSequence.charAt(i));
            MySet<Transition> transitionsFromStateAndSymbol = getTransitionsFromStartStateAndSymbol(currentState, symbol);
            if (transitionsFromStateAndSymbol.size() == 1) {
                currentState = transitionsFromStateAndSymbol.getElements().get(0).getTarget_state();
            } else {
                return false;
            }
        }
        return finalStates.contains(currentState);
    }
}
