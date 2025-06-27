package chap06;

import java.io.File;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class FileTest {

  @Test
  void readFile_And_ROW_SUM() throws IOException {
    ClassLoader classLoader = getClass().getClassLoader();
    File dataFile = new File(classLoader.getResource("sum1").getFile());

    int sumResult = MathUtils.sum(dataFile);
    assertEquals(6, sumResult);
  }

  @Test
  void noDataFile_Then_Exception() {
    File dadtaFile = new File("badpath.txt");
    assertThrows(IllegalArgumentException.class,
        () -> MathUtils.sum(dadtaFile));
  }

  @Test
  void dataFileSumTest2() {
    givenDataFile("target/dataFile.txt", "1", "2", "3", "4");
    File dataFile = new File("target/dataFile.txt");
    long sum = MathUtils.sum(dataFile);
    assertEquals(10, sum);
  }

  private void givenDataFile(String path, String... lines) {
    try {
      Path dataPath = Paths.get(path);
      Files.createDirectories(dataPath.getParent()); // 🔸 경로 없을 경우 생성
      Files.write(dataPath, Arrays.asList(lines));
    } catch (IOException e) {
      throw new RuntimeException("파일 생성 실패: " + path, e); // 원인 포함
    }
  }
}
