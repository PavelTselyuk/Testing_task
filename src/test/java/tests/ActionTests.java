package tests;

import models.ResponseModel;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static tests.TestData.*;
import static tests.TestData.RESULT_ERROR;

public class ActionTests extends TestBase {

    @Test
    public void successfulActionTest() {
        String token = generateToken();
        api.login(token, X_API_KEY, 200);

        ResponseModel response = api.action(token, X_API_KEY, 200);

        assertThat(response.getResult()).isEqualTo(RESULT_OK);
    }

    @Test
    public void errorActionWithNonExistedTokenTest() {
        String token = generateToken();

        ResponseModel response = api.action(token, X_API_KEY, 403);

        assertThat(response.getResult()).isEqualTo(RESULT_ERROR);
        String expectedMessage = String.format("Token '%s' not found", token);
        assertThat(response.getMessage()).isEqualTo(expectedMessage);
    }

    @Test
    public void errorActionWithWrongFormattedTokenTest() {
        ResponseModel response = api.action(WRONG_FORMATTED_TOKEN, X_API_KEY, 400);

        assertThat(response.getResult()).isEqualTo(RESULT_ERROR);
        String expectedMessage = "token: должно соответствовать \"^[0-9A-F]{32}$\"";
        assertThat(response.getMessage()).isEqualTo(expectedMessage);
    }

    @Test
    public void errorActionWithNoTokenTest() {
        ResponseModel response = api.action(null, X_API_KEY, 400);

        assertThat(response.getResult()).isEqualTo(RESULT_ERROR);
        String expectedMessage = "token: не должно равняться null";
        assertThat(response.getMessage()).isEqualTo(expectedMessage);
    }
}
