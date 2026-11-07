package tests;

import models.ResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static tests.TestData.*;

@DisplayName("LogoutTests")
public class LogoutTests extends TestBase {

    @Test
    @DisplayName("Успешный логаут")
    public void successfulLogoutTest() {
        String token = generateToken();
        api.login(token, X_API_KEY, 200);

        ResponseModel response = api.logout(token, X_API_KEY, 200);
        checkResult(response, RESULT_OK);
    }


    @Test
    @DisplayName("Ошибка логаута при отсутствии токена в БД")
    public void errorLogoutWithNonExistedTokenTest() {
        String token = generateToken();

        ResponseModel response = api.logout(token, X_API_KEY, 403);

        String expectedMessage = String.format("Token '%s' not found", token);
        checkResultAndMessage(response, RESULT_ERROR, expectedMessage);
    }

    @Test
    @DisplayName("Ошибка логаута при неправильным формате токена")
    public void errorLogoutWithWrongFormattedTokenTest() {
        ResponseModel response = api.logout(WRONG_FORMATTED_TOKEN, X_API_KEY, 400);

        String expectedMessage = "token: должно соответствовать \"^[0-9A-F]{32}$\"";
        checkResultAndMessage(response, RESULT_ERROR, expectedMessage);
    }

    @Test
    @DisplayName("Ошибка логаута при отсутствии токена в запросе")
    public void errorLogoutWithNoTokenTest() {
        ResponseModel response = api.logout(null, X_API_KEY, 400);

        String expectedMessage = "token: не должно равняться null";
        checkResultAndMessage(response, RESULT_ERROR, expectedMessage);
    }
}
