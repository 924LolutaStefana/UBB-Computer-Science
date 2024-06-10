import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class InsertCowTest {

    @Test
    public void testNoPairs() {
        List<Integer> animals = new ArrayList<>(Arrays.asList(1, 4, 1, 4));
        List<Integer> pairs = List.of();
        List<Integer> expected = Arrays.asList(1, 4, 1, 4);
        assertEquals(expected, AnimalArray.insertCow(animals, pairs));
    }

    @Test
    public void testOnePair() {
        List<Integer> animals = new ArrayList<>(Arrays.asList(1, 2, 3));
        List<Integer> pairs = List.of(0);
        List<Integer> expected = Arrays.asList(1, 4, 2, 3);
        assertEquals(expected, AnimalArray.insertCow(animals, pairs));
    }

    @Test
    public void testMultiplePairs() {
        List<Integer> animals = new ArrayList<>(Arrays.asList(1, 2, 3, 2, 3));
        List<Integer> pairs = Arrays.asList(0, 2, 3);
        List<Integer> expected = Arrays.asList(1, 4, 2, 3, 4, 2, 4, 3);
        assertEquals(expected, AnimalArray.insertCow(animals, pairs));
    }

    @Test
    public void testLoopCoverage() {
        List<Integer> animals = new ArrayList<>(Arrays.asList(1, 2, 3, 2, 1));
        List<Integer> pairs = Arrays.asList(0, 2);
        List<Integer> expected = Arrays.asList(1, 4, 2, 3, 4, 2, 1);
        assertEquals(expected, AnimalArray.insertCow(animals, pairs));
    }
}
