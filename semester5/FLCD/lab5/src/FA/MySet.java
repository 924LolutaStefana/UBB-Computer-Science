package FA;

import java.util.ArrayList;
import java.util.List;

public class MySet<T> {
    private final List<T> elements;

    public MySet() {
        this.elements = new ArrayList<>();
    }

    public void add(T element) {
        if (!contains(element)) {
            elements.add(element);
        }
    }

    public boolean contains(T element) {
        return elements.contains(element);
    }

    public int size() {
        return elements.size();
    }

    @Override
    public String toString() {
        return elements.toString();
    }

    public T getElementAtPosition(int position) {
        return elements.get(position);
    }

    public List<T> getElements() {
        return elements;
    }
}
