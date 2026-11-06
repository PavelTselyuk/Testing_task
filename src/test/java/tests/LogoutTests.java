package tests;

import models.ResponseModel;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static tests.TestData.*;
import static tests.TestData.RESULT_ERROR;

public class LogoutTests extends TestBase {

    @Test
    public void successfulLogoutTest() {
        String token = generateToken();
        api.login(token, X_API_KEY, 200);

        ResponseModel response = api.logout(token, X_API_KEY, 200);
        assertThat(response.getResult()).isEqualTo(RESULT_OK);
    }


    @Test
    public void errorLogoutWithNonExistedTokenTest() {
        String token = generateToken();

        ResponseModel response = api.logout(token, X_API_KEY, 403);

        assertThat(response.getResult()).isEqualTo(RESULT_ERROR);
        String expectedMessage = String.format("Token '%s' not found", token);
        assertThat(response.getMessage()).isEqualTo(expectedMessage);
    }

    @Test
    public void errorLogoutWithWrongFormattedTokenTest() {
        ResponseModel response = api.logout(WRONG_FORMATTED_TOKEN, X_API_KEY, 400);

        assertThat(response.getResult()).isEqualTo(RESULT_ERROR);
        String expectedMessage = "token: должно соответствовать \"^[0-9A-F]{32}$\"";
        assertThat(response.getMessage()).isEqualTo(expectedMessage);
    }

    @Test
    public void errorLogoutWithNoTokenTest() {
        ResponseModel response = api.logout(null, X_API_KEY, 400);

        assertThat(response.getResult()).isEqualTo(RESULT_ERROR);
        String expectedMessage = "token: не должно равняться null";
        assertThat(response.getMessage()).isEqualTo(expectedMessage);
    }
}
