import java.util.ArrayList;
import java.util.List;

public class AnimalArray {

    // Method to find pairs of mouse-cat (1, 2) or cat-dog (2, 3) or cat-mouse (2, 1) or dog-cat (3, 2)
    public static List<Integer> findPair(List<Integer> animals) {
        List<Integer> pairs = new ArrayList<>();
        for (int i = 0; i < animals.size() - 1; i++) {
            if ((animals.get(i) == 1 && animals.get(i + 1) == 2) ||
                    (animals.get(i) == 2 && animals.get(i + 1) == 3) ||
                    (animals.get(i) == 2 && animals.get(i + 1) == 1) ||
                    (animals.get(i) == 3 && animals.get(i + 1) == 2)) {
                pairs.add(i);
            }
        }
        return pairs;
    }

    // Method to insert a cow (4) between pairs of mouse-cat (1, 2) or cat-dog (2, 3)
    public static List<Integer> insertCow(List<Integer> animals, List<Integer> pairs) {
        for (int i = pairs.size() - 1; i >= 0; i--) {
            int index = pairs.get(i);
            animals.add(index + 1, 4);
        }
        return animals;
    }

    // Method to solve the given problem
    public static List<Integer> beFriends(List<Integer> animals) {
        List<Integer> pairs = findPair(animals);
        return insertCow(animals, pairs);
    }

    // Helper method to print the array
    public static void printArray(List<Integer> animals) {
        for (int animal : animals) {
            System.out.print(animal + " ");
        }
        System.out.println();
    }


}
