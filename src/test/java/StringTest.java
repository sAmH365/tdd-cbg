import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StringTest {

  @Test
  void substring_test() {
    String str = "abcde";
    Assertions.assertEquals("bc", str.substring(1,3));
  }
}
