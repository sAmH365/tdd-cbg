package chap5;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("@DisplayName 테스트")
public class LifeCycleTest {
  public LifeCycleTest() {
    System.out.println("new LifeCycleTest");
  }

  @BeforeEach
  void setUp() {
    System.out.println("SetUp");
  }

  @Test
  @DisplayName("A 출력테스트")
  void a() {
    System.out.println("A");
  }

  @Test
  @DisplayName("B 출력테스트")
  void b() {
    System.out.println("B");
  }


  @Test
  @DisplayName("C 출력테스트")
  @Disabled
  void c() {
    System.out.println("C");
//    throw new IllegalArgumentException();
  }

  @AfterEach
  void tearDown() {
    System.out.println("TearDown");
  }
}
