import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class CountWordsTest {

    CountWords countWords = new CountWords();
    @Test
    public void testEmptyAnalyze() {
        HashMap<String, Integer> checker = new HashMap<String, Integer>();
        assertEquals(checker, countWords.analyze(""));
    }

    @Test
    public void testOneAnalyze() {
        HashMap<String, Integer> checker = new HashMap<String, Integer>();
        checker.put("One", 1);
        assertEquals(checker, countWords.analyze("One"));
    }

    @Test
    public void testNoDupesAnalyze() {
        HashMap<String, Integer> checker = new HashMap<String, Integer>();
        checker.put("One", 1);
        checker.put("Two", 1);
        checker.put("Three", 1);
        checker.put("Four", 1);
        assertEquals(checker, countWords.analyze("One Two Three Four"));
    }

    @Test
    public void testDupesAnalyze() {
        HashMap<String, Integer> checker = new HashMap<String, Integer>();
        checker.put("One", 1);
        checker.put("Two", 2);
        checker.put("Three", 3);
        checker.put("Four", 4);
        assertEquals(checker, countWords.analyze("Two One Two Four Four Three Three Three Four Four"));
    }
}