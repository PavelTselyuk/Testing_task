package tests;

import models.ResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static tests.TestData.*;


@DisplayName("LoginTests")
public class LoginTests extends TestBase {

    @Test
    @DisplayName("Успешный логин")
    public void successfulLoginTest() {
        String token = generateToken();

        ResponseModel response = api.login(token, X_API_KEY, 200);

        checkResult(response, RESULT_OK);
    }

    @Test
    @DisplayName("Ошибка логина с уже существующим токеном в БД")
    public void errorLoginWithExistedTokenTest() {
        String token = generateToken();
        api.login(token, X_API_KEY, 200);

        ResponseModel response = api.login(token, X_API_KEY, 409);

        String expectedMessage = String.format("Token '%s' already exists", token);
        checkResultAndMessage(response, RESULT_ERROR, expectedMessage);
    }

    @Test
    @DisplayName("Ошибка логина при неправильным формате токена в запросе")
    public void errorLoginWithWrongFormattedTokenTest() {
        ResponseModel response = api.login(WRONG_FORMATTED_TOKEN, X_API_KEY, 400);

        String expectedMessage = "token: должно соответствовать \"^[0-9A-F]{32}$\"";
        checkResultAndMessage(response, RESULT_ERROR, expectedMessage);
    }

    @Test
    @DisplayName("Ошибка логина с отсутствующим токеном")
    public void errorLoginWithNoTokenTest() {
        ResponseModel response = api.login(null, X_API_KEY, 400);

        String expectedMessage = "token: не должно равняться null";
        checkResultAndMessage(response, RESULT_ERROR, expectedMessage);
    }
}
