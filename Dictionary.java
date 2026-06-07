import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class Dictionary {
    public HashSet<String> words = new HashSet<>();

    public Dictionary() {
    try (BufferedReader br = new BufferedReader(new FileReader("dictionary.txt"))) {
      String line;
      while ((line = br.readLine()) != null) {
        words.add(line);
      }
    } catch (IOException e) {
      System.out.println("Error reading file.");
    }
  }
}
