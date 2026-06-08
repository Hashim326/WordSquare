import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WordSquareBuilder {

    private Dictionary dictionary;
    private int rowSize;
    private List<String> wordSquare = new ArrayList<>();

    public WordSquareBuilder(Dictionary dictionary, int rowSize) {
        this.dictionary = dictionary;
        this.rowSize = rowSize;
    }

    public List<String> buildWordSquare(HashMap<Character, Integer> charCountMap) {
        if (charCountMap == null || charCountMap.isEmpty()) {
            throw new IllegalArgumentException("Character count map cannot be null or empty.");
        }

        int numOfChars = charCountMap.values().stream().mapToInt(Integer::intValue).sum();
        if (numOfChars < rowSize * rowSize) {
            throw new IllegalArgumentException("Not enough characters to fill the word square.");
        }

        List<String> availableWords = new ArrayList<>(dictionary.words);
        wordSquare = backtrack(new ArrayList<>(), new HashMap<>(charCountMap), availableWords);
        return wordSquare;
    }

    private List<String> backtrack(List<String> currentSquare, HashMap<Character, Integer> charCountMap, List<String> availableWords) {

        if (currentSquare.size() == rowSize) {
            return new ArrayList<>(currentSquare);
        }

        for (String word : availableWords) {
            if (currentSquare.contains(word)) {
                continue;
            }

            if (!isValidWord(word, currentSquare)) {
                continue;
            }

            if (!charsAreAvailable(word, charCountMap)) {
                continue;
            }

            useCharacters(word, charCountMap, -1);
            currentSquare.add(word);
            List<String> result = backtrack(currentSquare, charCountMap, availableWords);
            if (result != null) {
                return result;
            }
            currentSquare.remove(currentSquare.size() - 1);
            useCharacters(word, charCountMap, 1);
        }
        return null;
    }

    private boolean isValidWord(String word, List<String> currentSquare) {
        int rowIndex = currentSquare.size();
        for (int i = 0; i < rowIndex; i++) {
            if (currentSquare.get(i).charAt(rowIndex) != word.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    private boolean charsAreAvailable(String word, HashMap<Character, Integer> charCountMap) {
        HashMap<Character, Integer> tempCount = new HashMap<>(charCountMap);
        for (char c : word.toCharArray()) {
            int count = tempCount.getOrDefault(c, 0);
            if (count <= 0) {
                return false;
            }
            tempCount.put(c, count - 1);
        }
        return true;
    }

    private void useCharacters(String word, HashMap<Character, Integer> charCountMap, int difference) {
        for (char c : word.toCharArray()) {
            charCountMap.put(c, charCountMap.get(c) + difference);
        }
    }
}