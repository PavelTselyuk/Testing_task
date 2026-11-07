package tests;

import models.ResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static tests.TestData.*;

@DisplayName("ActionTests")
public class ActionTests extends TestBase {

    @Test
    @DisplayName("Успешный action тест")
    public void successfulActionTest() {
        String token = generateToken();
        api.login(token, X_API_KEY, 200);

        ResponseModel response = api.action(token, X_API_KEY, 200);

        checkResult(response, RESULT_OK);
    }

    @Test
    @DisplayName("Ошибка action при отсутствии токена в БД")
    public void errorActionWithNonExistedTokenTest() {
        String token = generateToken();

        ResponseModel response = api.action(token, X_API_KEY, 403);

        String expectedMessage = String.format("Token '%s' not found", token);
        checkResultAndMessage(response, RESULT_ERROR, expectedMessage);
    }

    @Test
    @DisplayName("Ошибка action при неправильным формате токена")
    public void errorActionWithWrongFormattedTokenTest() {
        ResponseModel response = api.action(WRONG_FORMATTED_TOKEN, X_API_KEY, 400);

        String expectedMessage = "token: должно соответствовать \"^[0-9A-F]{32}$\"";
        checkResultAndMessage(response, RESULT_ERROR, expectedMessage);
    }

    @Test
    @DisplayName("Ошибка action при отсутствии токена в запросе")
    public void errorActionWithNoTokenTest() {
        ResponseModel response = api.action(null, X_API_KEY, 400);

        String expectedMessage = "token: не должно равняться null";
        checkResultAndMessage(response, RESULT_ERROR, expectedMessage);
    }
}
