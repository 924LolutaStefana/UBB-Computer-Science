public class SymbolTable {
    private int size;
    private HashTable identifiersTable;
    private HashTable constantsTable;

    public SymbolTable(int size) {
        this.size = size;
        this.identifiersTable = new HashTable(size);
        this.constantsTable = new HashTable(size);
    }

    public int getSize() {
        return this.size;
    }

    public boolean containsIdentifier(String identifier) {
        return this.identifiersTable.containsElement(identifier);
    }

    public boolean containsConstant(String constant) {
        return this.constantsTable.containsElement(constant);
    }

    public Position getPositionIdentifier(String identifier) {
        return this.identifiersTable.getPosition(identifier);
    }

    public Position getPositionConstant(String constant) {
        return this.constantsTable.getPosition(constant);
    }

    public Position addIdentifier(String identifier) throws Exception {
        return this.identifiersTable.addElement(identifier);
    }

    public Position addConstant(String constant) throws Exception {
        return this.constantsTable.addElement(constant);
    }

    public boolean deleteIdentifier(String identifier) throws Exception {
        return this.identifiersTable.deleteElement(identifier);
    }

    public boolean deleteConstant(String constant) throws Exception {
        return this.constantsTable.deleteElement(constant);
    }

    @Override
    public String toString() {
        return "SymbolTable: " +
                "\nIDENTIFIERS=" + identifiersTable +
                "\nCONSTANTS=" + constantsTable;
    }
}
