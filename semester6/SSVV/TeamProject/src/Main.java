import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> animals = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 3, 2, 1, 1, 2, 3, 1));

        System.out.print("Original array: ");
        AnimalArray.printArray(animals);

        List<Integer> newAnimals = AnimalArray.beFriends(animals);

        System.out.print("New array: ");
        AnimalArray.printArray(newAnimals);
    }
}