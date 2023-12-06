import FA.MySet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class ParserTest {

    private Grammar grammar;
    private Parser parser;

    @Before
    public void setUp() {
        String grammarFile = "C:\\Users\\user\\Desktop\\LAB2345LFTC\\src\\programs\\g3.txt";
        grammar = new Grammar(grammarFile);
        parser = new Parser(grammar);
    }

    @Test
    public void testComputeFirstSets() {
        parser.computeFirstSets();

        MySet<String> firstS = parser.getFirstSets().get("S");
        Assert.assertEquals(set("(", "a"), firstS);

        MySet<String> firstA = parser.getFirstSets().get("A");
        Assert.assertEquals(set("+", "EPSILON"), firstA);

        MySet<String> firstB = parser.getFirstSets().get("B");
        Assert.assertEquals(set("(", "a"), firstB);

        MySet<String> firstC = parser.getFirstSets().get("C");
        Assert.assertEquals(set("*", "EPSILON"), firstC);

        MySet<String> firstD = parser.getFirstSets().get("D");
        Assert.assertEquals(set("(", "a"), firstD);
    }

    @Test
    public void testComputeFollowSets() {
        parser.computeFirstSets();
        parser.computeFollowSets();

        MySet<String> followS = parser.getFollowSets().get("S");
        Assert.assertEquals(set("EPSILON", ")"), followS);

        MySet<String> followA = parser.getFollowSets().get("A");
        Assert.assertEquals(set("EPSILON", ")"), followA);

        MySet<String> followB = parser.getFollowSets().get("B");
        Assert.assertEquals(set("+", "EPSILON", ")"), followB);

        MySet<String> followC = parser.getFollowSets().get("C");
        Assert.assertEquals(set("+", "EPSILON", ")"), followC);

        MySet<String> followD = parser.getFollowSets().get("D");
        Assert.assertEquals(set("*", "EPSILON", "+", ")"), followD);
    }
    @Test
    public void testAdditionalProduction() {
        Grammar newGrammar = createGrammarWithAdditionalProduction();
        Parser newParser = new Parser(newGrammar);

        newParser.computeFirstSets();
        newParser.computeFollowSets();

        MySet<String> firstS = newParser.getFirstSets().get("S");
        Assert.assertEquals(set("EPSILON", "*", "+"), firstS);

        MySet<String> followS = newParser.getFollowSets().get("S");
        Assert.assertEquals(set("EPSILON", ")"), followS);

        MySet<String> firstC = newParser.getFirstSets().get("C");
        Assert.assertEquals(set("*", "EPSILON"), firstC);

        MySet<String> followC = newParser.getFollowSets().get("C");
        Assert.assertEquals(set("+", "EPSILON", ")"), followC);

        MySet<String> firstNewProduction = newParser.getFirstSets().get("A");
        Assert.assertEquals(set("+", "EPSILON"), firstNewProduction);
    }

    private Grammar createGrammarWithAdditionalProduction() {
        Grammar newGrammar = new Grammar("C:\\Users\\user\\Desktop\\LAB2345LFTC\\src\\programs\\g3.txt");

        // Add an additional production S -> C A
        MySet<List<String>> rhsSet = new MySet<>();
        rhsSet.add(Arrays.asList("C", "A"));
        Production additionalProduction = new Production("S", rhsSet);
        newGrammar.getProductions().add(additionalProduction);

        return newGrammar;
    }
    private MySet<String> set(String... elements) {
        MySet<String> mySet = new MySet<>();
        for (String element : elements) {
            mySet.add(element);
        }
        return mySet;
    }
}
