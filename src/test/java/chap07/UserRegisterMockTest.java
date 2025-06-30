package chap07;

import com.example.chap07.AutoDebitRegister;
import com.example.chap07.AutoDebitReq;
import com.example.chap07.CardValidity;
import com.example.chap07.EmailNotifier;
import com.example.chap07.MemoryUserRepository;
import com.example.chap07.RegisterResult;
import com.example.chap07.UserRegister;
import com.example.chap07.WeakPasswordChecker;
import com.example.chap07.WeakPasswordException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

public class UserRegisterMockTest {

  private UserRegister userRegister;
  private WeakPasswordChecker mockPasswordChecker = Mockito.mock(WeakPasswordChecker.class);
  private MemoryUserRepository fakeRepository = new MemoryUserRepository();
  private EmailNotifier mockEmailNotifier = Mockito.mock(EmailNotifier.class);
  private AutoDebitRegister autoDebitRegister;

  @BeforeEach
  void setUp() {
    userRegister = new UserRegister(mockPasswordChecker, fakeRepository, mockEmailNotifier);
  }

  @DisplayName("약한 암호면 가입 실패")
  @Test
  void weakPassword() {
    BDDMockito.given(mockPasswordChecker.checkPasswordWeak("pw")).willReturn(true);

    Assertions.assertThrows(WeakPasswordException.class, () -> {
      userRegister.register("id", "pw", "email");
    });
  }

  @DisplayName("회원 가입시 암호 검사 수행함")
  @Test
  void checkPassword() {
    userRegister.register("id", "pw", "email");

    BDDMockito.then(mockPasswordChecker)
        .should()
        .checkPasswordWeak(BDDMockito.anyString());
  }

  @DisplayName("가입하면 메일을 전송함")
  @Test
  void whenRegisterThenSendMail() {
    userRegister.register("id", "pw", "email@email.com");

    ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
    BDDMockito.then(mockEmailNotifier)
        .should()
        .sendRegisterEmail(captor.capture());

    String realEmail = captor.getValue();
    Assertions.assertEquals("email@email.com", realEmail);
  }


  // private StubCardValidator stubValidator = new StubCardValidator();
  // private AutoDebitRegister register = new AutoDebitRegister(stubValidator);
//  @Test
  void invalidCardNumber() {
    // 상황: 유효하지 않은 카드번호
    // 실제로 카드 정보 API를 연동하면 테스트 코드에서 상황을 제어할 수 없음

    /**
     * 1. 제어하기 힘든 상황을 별도 타입으로 분리
     * 2. 테스트 코드는 별도로 분리한 타입의 대역을 생성
     * 3. 생성한 대역을 테스트 대상의 생성자 등을 이용해서 전달
     * 4. 대역을 이용해서 상황 구성
     */

    // stubValidator.setInvalidNo("11223344")
    // 실행
    AutoDebitReq req = new AutoDebitReq("user1", "카드번호 필요");
    RegisterResult result = autoDebitRegister.register(req);
    Assertions.assertEquals(CardValidity.VALID, result.getValidity());
  }
}
