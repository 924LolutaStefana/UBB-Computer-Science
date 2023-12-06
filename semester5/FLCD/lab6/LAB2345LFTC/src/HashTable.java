import java.util.ArrayList;

public class HashTable {
    private ArrayList<ArrayList<String>> elements;
    private int size;

    public HashTable(int size) {
        this.size = size;
        this.elements = new ArrayList<>();
        for (int i = 0; i < this.size; i++) {
            this.elements.add(new ArrayList<>());
        }
    }

    public int getSize() {
        return this.size;
    }

    public int hashFunction(String element) {
        int sum = 0;
        for (int i = 0; i < element.length(); i++) {
            sum += element.charAt(i);
        }
        return sum % this.size;
    }

    public boolean containsElement(String element) {
        int hashValue = hashFunction(element);
        return this.elements.get(hashValue).contains(element);
    }

    public Position addElement(String element) throws Exception {
        int hashValue = hashFunction(element);
        if (!containsElement(element)) {
            this.elements.get(hashValue).add(element);
            int positionInList = this.elements.get(hashValue).indexOf(element);
            return new Position(hashValue, positionInList);
        }
        throw new Exception("Element " + element + " is already in the Symbol Table!\n");
    }

    public Position getPosition(String element) {
        if (containsElement(element)) {
            int hashValue = hashFunction(element);
            int positionInList = this.elements.get(hashValue).indexOf(element);
            return new Position(hashValue, positionInList);
        }
        return new Position(-1, -1);
    }

    public boolean deleteElement(String element) throws Exception {
        Position position = getPosition(element);
        this.elements.get(position.getFirstPos()).remove(position.getSecondPos());
        return true;
    }

    @Override
    public String toString() {
        return "{" + elements + "}";

    }
}
