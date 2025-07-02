package chap09.autodebit;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

import com.example.chap09.autodebit.CardNumberValidator;
import com.example.chap09.autodebit.CardValidity;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CardNumberValidatorTest {

  private WireMockServer wireMockServer;

  @BeforeEach
  void setUp() {
    wireMockServer = new WireMockServer(options().port(8089));
    wireMockServer.start();
  }

  @AfterEach
  void tearDown() {
    wireMockServer.stop();
  }

  @Test
  void valid() {
    wireMockServer.stubFor(post(urlEqualTo("/card"))
        .withRequestBody(equalTo("1234567890"))
        .willReturn(aResponse()
            .withHeader("Content-Type", "text/plain")
            .withBody("ok")
        )
    );

    CardNumberValidator validator = new CardNumberValidator("http://localhost:8089");
    CardValidity validity = validator.validate("1234567890");
    Assertions.assertEquals(CardValidity.VALID, validity);
  }
}
