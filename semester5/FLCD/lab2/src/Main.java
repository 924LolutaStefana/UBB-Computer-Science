public class Main {
    public static void main(String[] args) {
        SymbolTable symbolTable = new SymbolTable(6);

        symbolTable.add("4");
        System.out.println(symbolTable.containsTerm("4"));
        Pair position = symbolTable.findPositionOfTerm("4");
        System.out.println(position);

        symbolTable.add("1");
        System.out.println(symbolTable.containsTerm("1"));
        System.out.println(symbolTable.findPositionOfTerm("1"));


        symbolTable.add("3");
        System.out.println(symbolTable.containsTerm("3"));
        System.out.println(symbolTable.findPositionOfTerm("3"));



        System.out.println(symbolTable.getHashTable());
    }
}