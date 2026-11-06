package tests;

import models.ResponseModel;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static tests.TestData.*;

public class LoginTests extends TestBase {

    @Test
    public void successfulLoginTest() {
        String token = generateToken();
        ResponseModel response = api.login(token, X_API_KEY, 200);

        assertThat(response.getResult()).isEqualTo(RESULT_OK);
    }

    @Test
    public void errorLoginWithExistedTokenTest() {
        String token = generateToken();
        api.login(token, X_API_KEY, 200);

        ResponseModel response = api.login(token, X_API_KEY, 409);

        assertThat(response.getResult()).isEqualTo(RESULT_ERROR);
        String expectedMessage = String.format("Token '%s' already exists", token);
        assertThat(response.getMessage()).isEqualTo(expectedMessage);
    }

    @Test
    public void errorLoginWithWrongFormattedTokenTest() {
        ResponseModel response = api.login(WRONG_FORMATTED_TOKEN, X_API_KEY, 400);

        assertThat(response.getResult()).isEqualTo(RESULT_ERROR);
        String expectedMessage = "token: должно соответствовать \"^[0-9A-F]{32}$\"";
        assertThat(response.getMessage()).isEqualTo(expectedMessage);
    }

    @Test
    public void errorLoginWithNoTokenTest() {
        ResponseModel response = api.login(null, X_API_KEY, 400);

        assertThat(response.getResult()).isEqualTo(RESULT_ERROR);
        String expectedMessage = "token: не должно равняться null";
        assertThat(response.getMessage()).isEqualTo(expectedMessage);
    }
}
