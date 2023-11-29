import FA.FiniteAutomata;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class Scanner {
    private String wholeProgram;
    private List<String> operators;
    private List<String> separators;
    private List<String> reservedWords;
    private List<Pair<String, Pair<Integer, Integer>>> PIF;
    private SymbolTable symbolTable;
    private int currentIndexInProgram = 0;
    private int currentLine = 1;

    public Scanner() {
        this.operators = new ArrayList<>();
        this.separators = new ArrayList<>();
        this.reservedWords = new ArrayList<>();
        this.PIF = new ArrayList<>();
        this.symbolTable = new SymbolTable(29);
        try {
            readTokensFromFile();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void readTokensFromFile() throws IOException {
        File file = new File("C:\\Users\\user\\Desktop\\lab23LFTC\\src\\programs\\tokens.in");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] tokens = line.split(" ");
            switch (tokens[0]) {
                case "+", "-", "x", "/", "%", "//", "=", "!=", "<", ">", "<=", ">=", "==", "===", "++", "--": {
                    operators.add(tokens[0]);
                    break;
                }
                case "(", ")", "{", "}", "[", "]", ".", "->", "*", "'", ",", ";": {
                    separators.add(tokens[0]);
                    break;
                }
                default:
                    reservedWords.add(tokens[0]);
            }
        }
        reader.close();
    }

    public List<String> getOperators() {return operators;}
    public List<String> getSeparators() {return separators;}
    public List<String> getReservedWords() {return reservedWords;}
    public int getCurrentIndexInProgram() {
        return currentIndexInProgram;
    }
    public void setWholeProgram(String wholeProgram) {
        this.wholeProgram = wholeProgram;
    }
    public void scan(String fileName) {
        try {
            Path filePath = Path.of("C:\\Users\\user\\Desktop\\lab23LFTC\\src\\programs\\" + fileName);
            setWholeProgram(Files.readString(filePath));
            currentIndexInProgram = 0;
            currentLine = 1;
            PIF = new ArrayList<>();
            symbolTable = new SymbolTable(29);
            while (currentIndexInProgram < wholeProgram.length()) { ///while(not(eof))
                classifyTokens();
            }
            FileWriter fileWriter = new FileWriter("PIF" + fileName.replace(".txt", ".out"));
            for (Pair<String, Pair<Integer, Integer>> pair : PIF) {
                fileWriter.write(pair.getFirst() + "  ----> (" + pair.getSecond().getFirst() + "," + pair.getSecond().getSecond() + ")\n");
            }
            fileWriter.close();
            FileWriter fileWriter1 = new FileWriter("ST" + fileName.replace(".txt", ".out"));
            fileWriter1.write(symbolTable.toString());
            fileWriter1.close();
            System.out.println("Program " + fileName + " is lexically CORRECT!");

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }

    private void classifyTokens() throws Exception { //here is the place to catch (if it exists) a lexical error
        ignoreSpaces();
        if (currentIndexInProgram == wholeProgram.length()) {return;}
        if (classifyAsIdentifier()) {return;}
        if (classifyAsStringConstant()) {return;}
        if (classifyAsIntConstant()) {return;}
        if (classifyAsSeparatorOperatorReservedWord()) {return;}
        throw new Exception("Lexical ERROR at line " + currentLine + ", index" + currentIndexInProgram);
    }

    private void ignoreSpaces() {
        while (currentIndexInProgram < wholeProgram.length() && Character.isWhitespace(wholeProgram.charAt(currentIndexInProgram))) {
            if (wholeProgram.charAt(currentIndexInProgram) == '\n') {
                currentLine++;
            }
            currentIndexInProgram++;
        }
    }

    private boolean classifyAsSeparatorOperatorReservedWord() {
        String possibleToken = wholeProgram.substring(currentIndexInProgram).split(" ")[0];
        for (String reservedWord : reservedWords) {
            if (possibleToken.startsWith(reservedWord)) {
                String regex = "^" + "[a-zA-Z0-9]*" + reservedWord + "[a-zA-Z0-9]+";
                if (Pattern.compile(regex).matcher(possibleToken).find()) {
                    return false;
                }
                currentIndexInProgram += reservedWord.length();
                PIF.add(new Pair<>(reservedWord, new Pair<>(-1, -1)));
                return true;
            }
        }
        for (var separator : separators) {
            if (Objects.equals(separator, possibleToken)) {
                currentIndexInProgram += separator.length();
                PIF.add(new Pair<>(separator, new Pair<>(-1, -1)));
                return true;
            } else if (possibleToken.startsWith(separator)) {
                currentIndexInProgram += separator.length();
                PIF.add(new Pair<>(separator, new Pair<>(-1, -1)));
                return true;
            }
        }
        for (var operator : operators) {
            if (Objects.equals(operator, possibleToken)) {
                currentIndexInProgram += operator.length();
                PIF.add(new Pair<>(operator, new Pair<>(-1, -1)));
                return true;
            } else if (possibleToken.startsWith(operator)) {
                currentIndexInProgram += operator.length();
                PIF.add(new Pair<>(operator, new Pair<>(-1, -1)));
                return true;
            }
        }
        return false;
    }

    private boolean classifyAsIdentifier() {
        var regex = Pattern.compile("^([a-zA-Z]+)");
        var matcher = regex.matcher(wholeProgram.substring(currentIndexInProgram));
        if (!matcher.find()) {
            return false;
        }
        var possibleIdentifier = matcher.group(1);
        if (!isIdentifierValid(possibleIdentifier, wholeProgram.substring(currentIndexInProgram))) {
            return false;
        }
        currentIndexInProgram += possibleIdentifier.length();
        Position position;
        try {
            position = symbolTable.addIdentifier(possibleIdentifier);
        } catch (Exception e) {
            position = symbolTable.getPositionIdentifier(possibleIdentifier);
        }
        Pair<Integer, Integer> pos = new Pair<>(position.getFirstPos(), position.getSecondPos());
        PIF.add(new Pair<>("id", pos));
        return true;
    }

    private boolean isIdentifierValid(String identifier, String programSubstring) {
        if (reservedWords.contains(identifier)) {
            return false;
        }
        if (Pattern.compile("(LOCAL\\s+)*?(INT|FLOAT|LONG|DOUBLE|CHAR|STRING|BOOL|ARRAY|)+\\s[a-z]+").matcher(programSubstring).find()) {
            return true;
        }
        if (Pattern.compile("[a-zA-Z]+\\(").matcher(programSubstring).find()) { //for function names
            return true;
        }
        if (Pattern.compile("[a-zA-Z]+\\)").matcher(programSubstring).find()) { //for passing the arguments
            return true;
        }
        return symbolTable.containsIdentifier(identifier);
    }

    private boolean classifyAsStringConstant() throws Exception {
        var regex = Pattern.compile("^'[A-Za-z0-9 .?]*'");
        var matcher = regex.matcher(wholeProgram.substring(currentIndexInProgram));
        if (!matcher.find()) {
            if (Pattern.compile("'.+'.*'").matcher(wholeProgram.substring(+currentIndexInProgram)).find()) {
                throw new Exception("Invalid string at line: " + currentLine);
            }
            if (Pattern.compile("^'[^']*").matcher(wholeProgram.substring(+currentIndexInProgram)).find()) {
                throw new Exception("Missing ' at line: " + currentLine);
            }
            return false;
        }
        var stringConstant = matcher.group(0);
        currentIndexInProgram += stringConstant.length();
        Position position;
        try {
            position = symbolTable.addConstant(stringConstant);
        } catch (Exception e) {
            position = symbolTable.getPositionConstant(stringConstant);
        }
        Pair<Integer, Integer> pos = new Pair<>(position.getFirstPos(), position.getSecondPos());
        PIF.add(new Pair<>("string constant", pos));
        return true;
    }

    private boolean classifyAsIntConstant() {

        var regex = Pattern.compile("^([+-]?[1-9][0-9]*|0)");
        var matcher = regex.matcher(wholeProgram.substring(currentIndexInProgram));
        if (!matcher.find()) {
            return false;
        }
        if (Pattern.compile("^([+-]?[1-9][0-9]*|0)[a-zA-Z_]").matcher(wholeProgram.substring(currentIndexInProgram)).find()) {
            return false;
        }
        var intConstant = matcher.group(1);
        currentIndexInProgram += intConstant.length();
        Position position;
        try {
            position = symbolTable.addConstant(intConstant);
        } catch (Exception e) {
            position = symbolTable.getPositionConstant(intConstant);
        }
        Pair<Integer, Integer> pos = new Pair<>(position.getFirstPos(), position.getSecondPos());
        PIF.add(new Pair<>("integer constant", pos));
        return true;
    }
}
