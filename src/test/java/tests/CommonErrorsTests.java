package tests;

import models.ResponseModel;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static tests.TestData.RESULT_ERROR;
import static tests.TestData.X_API_KEY;

public class CommonErrorsTests extends TestBase {


    @Test
    public void errorWithNoActionTest() {
        String token = generateToken();
        String body = String.format("token=%s", token);

        ResponseModel response = api.query(body, X_API_KEY, 400);

        assertThat(response.getResult()).isEqualTo(RESULT_ERROR);
        String expectedMessage = "action: invalid action 'null'. Allowed: LOGIN, LOGOUT, ACTION";
        assertThat(response.getMessage()).isEqualTo(expectedMessage);
    }

    @Test
    public void errorWithNoActionAndNoTokenTest() {
        String body = "";

        ResponseModel response = api.query(body, X_API_KEY, 400);

        assertThat(response.getResult()).isEqualTo(RESULT_ERROR);
        String expectedTokenMessage = "token: не должно равняться null";
        String expectedActionMessage = "action: invalid action 'null'. Allowed: LOGIN, LOGOUT, ACTION";
        assertThat(response.getMessage()).contains(expectedTokenMessage);
        assertThat(response.getMessage()).contains(expectedActionMessage);
    }

    @Test
    public void errorWithWrongActionTest() {
        String token = generateToken();
        String body = String.format("token=%s&action=WRONG_ACTION", token);

        ResponseModel response = api.query(body, X_API_KEY, 400);

        assertThat(response.getResult()).isEqualTo(RESULT_ERROR);
        String expectedMessage = "action: invalid action 'WRONG_ACTION'. Allowed: LOGIN, LOGOUT, ACTION";
        assertThat(response.getMessage()).isEqualTo(expectedMessage);
    }

    @Test
    public void errorWithWrongApiKeyTest() {
        String token = generateToken();
        String body = String.format("token=%s&action=ACTION", token);

        ResponseModel response = api.query(body, "wrong_api_key", 401);

        assertThat(response.getResult()).isEqualTo(RESULT_ERROR);
        String expectedMessage = "Missing or invalid API Key";
        assertThat(response.getMessage()).isEqualTo(expectedMessage);
    }
}
