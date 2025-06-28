package chap07;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.chap07.AutoDebitInfoRepository;
import com.example.chap07.AutoDebitRegister;
import com.example.chap07.AutoDebitReq;
import com.example.chap07.CardNumberValidator;
import com.example.chap07.CardValidity;
import com.example.chap07.RegisterResult;
import com.example.chap07.StubAutoDebitInfoRepository;
import com.example.chap07.StubCardNumberValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AutoDebitRegisterTest {
  private AutoDebitRegister register;
  private StubCardNumberValidator stubValidator;
  private StubAutoDebitInfoRepository stubRepository;

  @BeforeEach
  void setUp() {
    stubValidator = new StubCardNumberValidator();
    stubRepository = new StubAutoDebitInfoRepository();
    register = new AutoDebitRegister(stubValidator, stubRepository);
  }

  @Test
  void invalidCard() {
    stubValidator.setInvalidNo("1112223333");

    AutoDebitReq req = new AutoDebitReq("user1", "1112223333");
    RegisterResult result = register.register(req);

    assertEquals(CardValidity.INVALID, result.getValidity());
  }

  @Test
  void validCard() {
    // 업체에서 받은 테스트용 유효한 카드번호 사용
    AutoDebitReq req = new AutoDebitReq("user1", "1234123412341234");
    RegisterResult result = register.register(req);
    assertEquals(CardValidity.VALID, result.getValidity());
  }

  @Test
  void theftCard() {
    stubValidator.setTheftNo("1234567890123456");

    // 업체에서 받은 도난 테스트용 카드번호 사용
    AutoDebitReq req = new AutoDebitReq("user1", "1234567890123456");
    RegisterResult result = register.register(req);

    assertEquals(CardValidity.THEFT, result.getValidity());
  }
}
