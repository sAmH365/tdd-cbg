package chap08;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.chap08.testable.DailyBatchLoader;
import com.example.chap08.testable.Times;
import java.time.LocalDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

public class DailyBatchLoaderTest {

  private Times mockTimes = Mockito.mock(Times.class);
  private final DailyBatchLoader loader = new DailyBatchLoader();

  @BeforeEach
  void setUp() {
    loader.setBasePath("src/test/resources");
    loader.setTimes(mockTimes);
  }

  @Test
  void loadCount() {
    BDDMockito.given(mockTimes.today()).willReturn(LocalDate.of(2025, 5, 5));
    int ret = loader.load();
    assertEquals(3, ret);
  }
}
