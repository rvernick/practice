import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

class EquationsSolverTest {

    @Test
    public void testUnsolvable() {
        String empty = "";
        assertEquals(null, new EquationsSolver().solve(empty) );
    }

    @Test
    public void singleRightSetting() {
        String xIs0 = "2 = two";
        HashMap<String, Integer> expected = new HashMap<>();
        expected.put("two", 2);
        assertEquals(expected, new EquationsSolver().solve(xIs0));
    }

    @Test
    public void singleLeftSetting() {
        String xIs0 = "zero = 0";
        HashMap<String, Integer> expected = new HashMap<>();
        expected.put("zero", 0);
        assertEquals(expected, new EquationsSolver().solve(xIs0));
    }

    @Test
    public void twoEquationsSolvable() {
        String equations = "zero = 0 \n 2 = two";
        HashMap<String, Integer> expected = new HashMap<>();
        expected.put("zero", 0);
        expected.put("two", 2);
        assertEquals(expected, new EquationsSolver().solve(equations));
    }

    @Test
    public void twoEquationsRelatedSolvable() {
        String equations = "five = two + 3 \n 2 = two";
        HashMap<String, Integer> expected = new HashMap<>();
        expected.put("five", 5);
        expected.put("two", 2);
        assertEquals(expected, new EquationsSolver().solve(equations));
    }
}