package tests;

import models.ResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;
import static tests.TestData.RESULT_ERROR;
import static tests.TestData.X_API_KEY;


@DisplayName("CommonErrorsTests")
public class CommonErrorsTests extends TestBase {


    @Test
    @DisplayName("Ошибка при отсутствии action в запросе")
    public void errorWithNoActionTest() {
        String token = generateToken();
        String body = String.format("token=%s", token);

        ResponseModel response = api.query(body, X_API_KEY, 400);

        String expectedMessage = "action: invalid action 'null'. Allowed: LOGIN, LOGOUT, ACTION";
        checkResultAndMessage(response, RESULT_ERROR, expectedMessage);
    }

    @Test
    @DisplayName("Ошибка при отсутствии action и токена в запросе")
    public void errorWithNoActionAndNoTokenTest() {
        String body = "";

        ResponseModel response = api.query(body, X_API_KEY, 400);

        String expectedTokenMessage = "token: не должно равняться null";
        String expectedActionMessage = "action: invalid action 'null'. Allowed: LOGIN, LOGOUT, ACTION";
        step(
                String.format(
                        "Check that result = \"%s\" and message contains \"%s\" and \"%s\"",
                        RESULT_ERROR,
                        expectedTokenMessage,
                        expectedActionMessage),
                () -> {
                    assertThat(response.getResult()).isEqualTo(RESULT_ERROR);
                    assertThat(response.getMessage()).contains(expectedTokenMessage);
                    assertThat(response.getMessage()).contains(expectedActionMessage);
                }
        );
    }

    @Test
    @DisplayName("Ошибка при несуществующем action в запросе")
    public void errorWithWrongActionTest() {
        String token = generateToken();
        String body = String.format("token=%s&action=WRONG_ACTION", token);

        ResponseModel response = api.query(body, X_API_KEY, 400);

        String expectedMessage = "action: invalid action 'WRONG_ACTION'. Allowed: LOGIN, LOGOUT, ACTION";
        checkResultAndMessage(response, RESULT_ERROR, expectedMessage);
    }

    @Test
    @DisplayName("Ошибка при неверном apiKey в запросе")
    public void errorWithWrongApiKeyTest() {
        String token = generateToken();
        String body = String.format("token=%s&action=ACTION", token);

        ResponseModel response = api.query(body, "wrong_api_key", 401);

        String expectedMessage = "Missing or invalid API Key";
        checkResultAndMessage(response, RESULT_ERROR, expectedMessage);
    }
}
