import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;

public class Dictionary {
    public HashSet<String> words = new HashSet<>();

    public Dictionary(int wordLength, HashMap<Character, Integer> charCount) {

        InputStream is = Main.class.getClassLoader()
                                   .getResourceAsStream("dictionary.txt");

        if (is == null) {
            throw new RuntimeException("File not found!");
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.length() == wordLength) {
                    if (charCount != null && isValidWord(line, charCount)) {
                        words.add(line);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }
    }

    public Dictionary(HashSet<String> words) {
        this.words = new HashSet<>(words);
    }

    private boolean isValidWord(String word, HashMap<Character, Integer> charCount) {
        HashMap<Character, Integer> tempCount = new HashMap<>(charCount);
        for (char c : word.toCharArray()) {
            if (!tempCount.containsKey(c) || tempCount.get(c) == 0) {
                return false;
            }
            tempCount.put(c, tempCount.get(c) - 1);
        }
        return true;
    }
}
