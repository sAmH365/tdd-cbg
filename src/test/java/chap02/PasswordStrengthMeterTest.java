package chap02;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.chap02.PasswordStrength;
import com.example.chap02.PasswordStrengthMeter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PasswordStrengthMeterTest {

  /**
   * 검사 규칙
   * 1. 길이가 8글자 이상
   * 2. 0부터 9 사이의 숫자를 포함
   * 3. 대문자 포함
   *
   * 세 규칙을 모두 충족하면 암호강도는 강함
   * 2개의 규칙을 충족하면 암호강도는 보통
   * 1개 이하의 규칙을 충족하면 암호강도는 약함
   */
  
  
  private PasswordStrengthMeter meter = new PasswordStrengthMeter();

  private void assertStrength(String password, PasswordStrength expStr) {
    PasswordStrength result = meter.meter(password);
    assertEquals(expStr, result);
  }

  @Test
  @DisplayName("암호가 모든 조건을 충족하면 암호 강도는 강함이어야함")
  void meetsAllCriteria_Then_Strong() {
    assertStrength("ab12!@AB", PasswordStrength.STRONG);
    assertStrength("abc1!Add", PasswordStrength.STRONG);
  }

  @Test
  @DisplayName("암호가 8글자 미만, 나머지 조건은 충족하면 암호 강도는 보통이어야함")
  void meetsOtherCriteria_except_for_Length_Then_Normal() {
    assertStrength("ab12!@A", PasswordStrength.NORMAL);
    assertStrength("Ab12!@A", PasswordStrength.NORMAL);
  }

  @Test
  @DisplayName("숫자를 포함하지 않고 나머지 조건은 충족하면 암호 강도는 보통이어야함")
  void meetsOtherCriteria_except_for_Number_Then_Normal() {
    assertStrength("ab!@ABqwer", PasswordStrength.NORMAL);
    assertStrength("Abbb!@Ad", PasswordStrength.NORMAL);
  }

  @Test
  @DisplayName("값이 없는 경우 암호강도는 INVALID이다")
  void nullInput_Then_Invalid() {
    assertStrength(null, PasswordStrength.INVALID);
  }

  @Test
  @DisplayName("빈 문자열인 경우 암호강도는 INVALID이다")
  void emptyInput_Then_Invalid() {
    assertStrength("", PasswordStrength.INVALID);
    assertStrength("          ", PasswordStrength.INVALID);
  }

  @Test
  @DisplayName("대문자를 포함하지 않고 나머지 조건을 충족하면 암호 강도는 보통이다")
  void meetsOtherCriteria_except_for_Uppercase_Then_Normal() {
    assertStrength("abc!@#24", PasswordStrength.NORMAL);
    assertStrength("ccd@@#24", PasswordStrength.NORMAL);
  }

  @Test
  @DisplayName("길이가 8글자 이상인 조건만 충족하면 암호 강도는 약함이다")
  void meetsOnlyLengthCriteria_Then_Weak() {
    assertStrength("abcdabcd#", PasswordStrength.WEAK);
  }

  @Test
  @DisplayName("숫자 포함 조건만 충족하면 암호 강도는 약함이다")
  void meetsOnlyNumCriteria_Then_Weak() {
    assertStrength("12345", PasswordStrength.WEAK);
  }

  @Test
  @DisplayName("대문자 포함 조건만 충족하면 암호 강도는 약함이다")
  void meetsOnlyUpperCriteria_Then_Weak() {
    assertStrength("ABCDABC", PasswordStrength.WEAK);
  }

  @Test
  @DisplayName("아무조건도 충족하지 않으면 암호 강도는 약함이다")
  void meetsNoCriteria_Then_Weak() {
    assertStrength("abc", PasswordStrength.WEAK);
  }
}
