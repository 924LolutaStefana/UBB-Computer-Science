import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class MyScanner {

    private final ArrayList<String> operators = new ArrayList<>(
            List.of("+", "-", "*", "///", "<", ">", "isequal", "isequalorgreater", "isequalorless", "isequalto", "^", "modulo", "/%", "from", "to", "step", "+1", "*", "/", "+", "%", "===")
    );

    private final ArrayList<String> separators = new ArrayList<>(
            List.of("{", "}", "(", ")", "[", "]", ":", ";", " ", ",", "\t", "\n", "'", "\"", "output", "read", "^", "if", "otherwise", "then", "cond")
    );

    private final ArrayList<String> reservedWords = new ArrayList<>(
            List.of("isequal", "isequalorgreater", "isequalorless", "isequalto", "for", "step", "output", "if", "cond", "then", "otherwise", "read", "getout")
    );

    private final String filePath;

    private SymbolTable symbolTable;

    private ProgramInternalForm pif;

    public MyScanner(String filePath) {
        this.filePath = filePath;
        this.symbolTable = new SymbolTable(100);
        this.pif = new ProgramInternalForm();
    }

    private String readFile() throws FileNotFoundException {
        StringBuilder fileContent = new StringBuilder();
        Scanner scanner = new Scanner(new File(this.filePath));
        while(scanner.hasNextLine()){
            fileContent.append(scanner.nextLine()).append("\n");
        }

        return fileContent.toString().replace("\t", "");
    }

    private List<Pair<String, Pair<Integer, Integer>>> createListOfProgramsElems(){
        try{
            String content = this.readFile();
            String operatorsAndSeparators = this.operators.stream().collect(Collectors.joining()) +
                    this.separators.stream().collect(Collectors.joining());
            StringTokenizer tokenizer = new StringTokenizer(content, operatorsAndSeparators, true);

            List<String> tokens = Collections.list(tokenizer)
                    .stream()
                    .map(t->(String) t)
                    .collect(Collectors.toList());

            return tokenize(tokens);
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }

        return null;
    }

    private List<Pair<String, Pair<Integer, Integer>>> tokenize(List<String> tokensToBe){
        List<Pair<String, Pair<Integer, Integer>>> resultedTokens = new ArrayList<>();
        boolean isStringConstant = false;
        boolean isCharConstant = false;
        StringBuilder createdString = new StringBuilder();
        int numberLine = 1;
        int numberColumn = 1;

        for(String t: tokensToBe){
            switch (t) {
                case "\"":
                    if (isStringConstant) {
                        createdString.append(t);
                        resultedTokens.add(new Pair<>(createdString.toString(), new Pair<>(numberLine, numberColumn)));
                        createdString = new StringBuilder();
                    } else {
                        createdString.append(t);
                    }
                    isStringConstant = !isStringConstant;
                    break;
                case "'":
                    if (isCharConstant) {
                        createdString.append(t);
                        resultedTokens.add(new Pair<>(createdString.toString(), new Pair<>(numberLine, numberColumn)));
                        createdString = new StringBuilder();
                    } else {
                        createdString.append(t);
                    }
                    isCharConstant = !isCharConstant;
                    break;
                case "\n":
                    numberLine++;
                    numberColumn = 1;
                    break;
                default:
                    if (isStringConstant) {
                        createdString.append(t);
                    } else if (isCharConstant) {
                        createdString.append(t);
                    } else if (!t.equals(" ")) {
                        resultedTokens.add(new Pair<>(t, new Pair<>(numberLine, numberColumn)));
                        numberColumn++;
                    }
                    break;
            }
        }
        return resultedTokens;
    }

    public void scan() {
        List<Pair<String, Pair<Integer, Integer>>> tokens = createListOfProgramsElems();
        AtomicBoolean lexicalErrorExists = new AtomicBoolean(false);

        if (tokens == null) {
            return;
        }

        tokens.forEach(t -> {
            String token = t.getFirst();
            Pair<Integer, Integer> pairLineColumn = t.getSecond();

            if (this.reservedWords.contains(token)) {
                this.pif.add(new Pair<>(token, new Pair<>(-1, -1)), 2);
            } else if (this.operators.contains(token)) {
                this.pif.add(new Pair<>(token, new Pair<>(-1, -1)), 3);
            } else if (this.separators.contains(token)) {
                this.pif.add(new Pair<>(token, new Pair<>(-1, -1)), 4);
            } else if (Pattern.compile("^(0|[-|+]?[1-9][0-9]*)|'[^']'|'[a-zA-Z]'|\"[0-9]*[a-zA-Z ]*\"$").matcher(token).matches()) {
                this.symbolTable.add(token);
                this.pif.add(new Pair<>(token, symbolTable.findPositionOfTerm(token)), 0);
            } else if (Pattern.compile("^([a-zA-Z]|_)[a-zA-Z_0-9]*").matcher(token).matches()) {
                this.symbolTable.add(token);
                this.pif.add(new Pair<>(token, symbolTable.findPositionOfTerm(token)), 1);
            } else {
                System.out.println("Lexical error at line: " + pairLineColumn.getFirst() + " and column: " + pairLineColumn.getSecond() + ", invalid token: " + t.getFirst());
                lexicalErrorExists.set(true);
            }
        });

        if (!lexicalErrorExists.get()) {
            System.out.println("Lexically correct!");
        }




    }

    public ProgramInternalForm getPif() {
        return this.pif;
    }

    public SymbolTable getSymbolTable() {
        return this.symbolTable;
    }
}
