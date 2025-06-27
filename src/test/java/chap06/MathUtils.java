package chap06;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MathUtils {

  public static int sum(File dataFile) {
    try (BufferedReader br = new BufferedReader(new FileReader(dataFile))) {
      int count = 0;
      String line;
      while ((line = br.readLine()) != null && !line.equals("")) {
        count += Integer.parseInt(line);
      }

      return count;
    } catch (IOException e) {
      throw new IllegalArgumentException();
    }
  }
}
