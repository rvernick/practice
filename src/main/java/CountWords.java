import java.util.HashMap;
import java.util.Map;

class CountWords {

    Map<String, Integer> analyze(String input) {
        HashMap<String, Integer> result = new HashMap<String, Integer>();
        String[] words = input.split("\\s+");
        for (String word : words) {
            if (word.isBlank()) continue;
            if (result.containsKey(word)) {
                result.put(word, result.get(word) + 1);
            } else {
                result.put(word, 1);
            }
        }
        return result;
    }
}