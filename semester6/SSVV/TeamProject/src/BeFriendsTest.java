import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class BeFriendsTest {

    @Test
    public void testOriginalExample() {
        List<Integer> animals = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 3, 2, 1, 1, 2, 3, 1));
        List<Integer> expected = Arrays.asList(1, 4, 2, 4, 3, 4, 3, 4, 2, 4, 1, 1, 4, 2, 4, 3, 1);
        assertEquals(expected, AnimalArray.beFriends(animals));
    }

    @Test
    public void testEmptyArray() {
        List<Integer> animals = new ArrayList<>();
        List<Integer> expected = List.of();
        assertEquals(expected, AnimalArray.beFriends(animals));
    }

    @Test
    public void testNoPairs() {
        List<Integer> animals = new ArrayList<>(Arrays.asList(4, 4, 4));
        List<Integer> expected = Arrays.asList(4, 4, 4);
        assertEquals(expected, AnimalArray.beFriends(animals));
    }

    
    @Test
    public void testMultipleMixedPairs() {
        List<Integer> animals = new ArrayList<>(Arrays.asList(1, 2, 1, 2, 3, 2, 3, 1, 2));
        List<Integer> expected = Arrays.asList(1, 4, 2, 4, 1, 4, 2, 4, 3, 4, 2, 4, 3, 1, 4, 2);
        assertEquals(expected, AnimalArray.beFriends(animals));
    }
}
