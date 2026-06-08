import java.util.HashMap;
import java.util.List;

public class Main {
    

    public static void main(String args[]) {
        int rowSize;
        String inputString;

        if (args.length != 2) {
            System.out.println("Usage: java Main <rowSize> <inputString>");
            return;
        }

        try {
            rowSize = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("First argument must be an integer.");
            return;
        }

        if (rowSize <= 0) {
            System.out.println("Row size must be greater than 0.");
            return;
        }

        inputString = args[1];

        if (inputString.length() != rowSize * rowSize) {
            System.out.println("Input string length must match the square of the row size.");
            return;
        }

        System.out.println("Row Size: " + rowSize);
        System.out.println("Input String: " + inputString);

        HashMap<Character, Integer> charCount = new HashMap<>();
        for (char c : inputString.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }
        
        Dictionary dict = new Dictionary(rowSize, charCount);
        WordSquareBuilder builder = new WordSquareBuilder(dict, rowSize);
        List<String> wordSquare = builder.buildWordSquare(charCount);

        if (wordSquare.isEmpty()) {
            System.out.println("No valid word square found.");
        } else {
            System.out.println("Word Square:");
            for (String word : wordSquare) {
                System.out.println(word);
            }
        }

        return;
    }
}
