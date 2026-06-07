import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class Dictionary {
    public HashSet<String> words = new HashSet<>();

    public Dictionary(int wordLength) {
    try (BufferedReader br = new BufferedReader(new FileReader("dictionary.txt"))) {
      String line;
      while ((line = br.readLine()) != null) {
        if (line.length() == wordLength) {
          words.add(line);
        }
      }
    } catch (IOException e) {
      System.out.println("Error reading file.");
    }
  }
}
