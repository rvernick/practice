import java.util.*;

public class EquationsSolver {

    public Map<String, Integer> solve(String equationsString) {
        HashMap<String, Integer> result = new HashMap<String, Integer>();
        String[] equations = equationsString.split("\n");
        boolean solved = false;
        for (int i = 0; i < equations.length; i++) {
            solved = true;
            for (String equation : equations) {
                if (!solveSingle(equation, result)) {
                    solved = false;
                }
            }
            if (solved) return result;
        }
        return null;
    }

    private boolean solveSingle(String equation, Map<String, Integer> results) {
        if (equation.isEmpty()) return false;
        Integer left = 0;
        Integer right = 0;
        Set<String> leftTerms = new HashSet<>();
        Set<String> rightTerms = new HashSet<>();
        boolean solvable = false;

        String[] parsed = equation.split("\\s+");
        boolean isLeft = true;
        for (String term : parsed) {
            if (term.isEmpty()) continue;
            try {
                int val = Integer.parseInt(term);
                if (isLeft) {
                    left += val;
                } else {
                    right += val;
                }
            } catch (NumberFormatException nfe) {
                if ("+".equals(term)) continue;
                if ("=".equals(term)) {
                    isLeft = false;
                    continue;
                }
                if (results.containsKey(term)) {
                    if (isLeft) {
                        left += results.get(term);
                    } else {
                        right += results.get(term);
                    }
                    continue;
                }
                solvable = false;
                if (isLeft) {
                    leftTerms.add(term);
                } else {
                    rightTerms.add(term);
                }
            }
        }
        if (leftTerms.size() == 0 && rightTerms.size() == 1) {
            Optional<String> term = rightTerms.stream().findAny();
            results.put(term.get(), left - right);
            solvable = true;
        }
        if (leftTerms.size() == 1 && rightTerms.size() == 0) {
            Optional<String> term = leftTerms.stream().findAny();
            results.put(term.get(), right - left);
            solvable = true;
        }
        if (leftTerms.isEmpty()
                && rightTerms.isEmpty()
                && left == right) {
            solvable = true;
        }
        return solvable;
    }
}
