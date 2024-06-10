import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FindPairTest {

    @Test
    public void testNoPairs() {
        List<Integer> animals = Arrays.asList(4, 4, 4, 4);
        List<Integer> expected = Collections.emptyList();
        assertEquals(expected, AnimalArray.findPair(animals));
    }

    @Test
    public void testOneMouseCatPair() {
        List<Integer> animals = Arrays.asList(1, 2);
        List<Integer> expected = List.of(0);
        assertEquals(expected, AnimalArray.findPair(animals));
    }

    @Test
    public void testOneCatDogPair() {
        List<Integer> animals = Arrays.asList(2, 3);
        List<Integer> expected = List.of(0);
        assertEquals(expected, AnimalArray.findPair(animals));
    }

    @Test
    public void testMultipleMouseCatPairs() {
        List<Integer> animals = Arrays.asList(1, 2, 1, 2);
        List<Integer> expected = Arrays.asList(0, 1, 2);
        assertEquals(expected, AnimalArray.findPair(animals));
    }

    @Test
    public void testMultipleCatDogPairs() {
        List<Integer> animals = Arrays.asList(2, 3, 2, 3);
        List<Integer> expected = Arrays.asList(0, 1, 2);
        assertEquals(expected, AnimalArray.findPair(animals));
    }

    @Test
    public void testMixedPairs() {
        List<Integer> animals = Arrays.asList(1, 2, 3, 2, 3);
        List<Integer> expected = Arrays.asList(0, 1, 2, 3);
        assertEquals(expected, AnimalArray.findPair(animals));
    }

    @Test
    public void testEmptyArray() {
        List<Integer> animals = Collections.emptyList();
        List<Integer> expected = Collections.emptyList();
        assertEquals(expected, AnimalArray.findPair(animals));
    }

    @Test
    public void testSingleElementArray() {
        List<Integer> animals = List.of(1);
        List<Integer> expected = Collections.emptyList();
        assertEquals(expected, AnimalArray.findPair(animals));
    }

    @Test
    public void testTwoElementArray() {
        List<Integer> animals = Arrays.asList(1, 3);
        List<Integer> expected = Collections.emptyList();
        assertEquals(expected, AnimalArray.findPair(animals));
    }

    @Test
    public void testCatMousePair() {
        List<Integer> animals = Arrays.asList(2, 1);
        List<Integer> expected = List.of(0);
        assertEquals(expected, AnimalArray.findPair(animals));
    }

    @Test
    public void testDogCatPair() {
        List<Integer> animals = Arrays.asList(3, 2);
        List<Integer> expected = List.of(0);
        assertEquals(expected, AnimalArray.findPair(animals));
    }

    @Test
    public void testAllPairs() {
        List<Integer> animals = Arrays.asList(1, 2, 3, 2, 1, 2, 3, 2, 1);
        List<Integer> expected = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7);
        assertEquals(expected, AnimalArray.findPair(animals));
    }

    @Test
    public void testPairsWithOtherAnimals() {
        List<Integer> animals = Arrays.asList(1, 2, 4, 3, 2, 1, 4, 2, 3, 1);
        List<Integer> expected = Arrays.asList(0, 3, 4, 7);
        assertEquals(expected, AnimalArray.findPair(animals));
    }
}
