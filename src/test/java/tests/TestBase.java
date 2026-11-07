package tests;


import api.ApiClient;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import models.ResponseModel;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeAll;

import static org.assertj.core.api.Assertions.assertThat;

public class TestBase {

    public Faker faker = new Faker();
    protected static final ApiClient api = new ApiClient();

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    public String generateToken() {
        return faker.regexify("[A-F0-9]{32}");
    }

    @Step("Check that result = \"{expectedResult}\" and expectedMessage = \"{expectedMessage}\"")
    public void checkResultAndMessage(ResponseModel response, String expectedResult, String expectedMessage) {
        assertThat(response.getResult()).isEqualTo(expectedResult);
        assertThat(response.getMessage()).isEqualTo(expectedMessage);
    }

    @Step("Check that result = \"{expectedResult}\"")
    public void checkResult(ResponseModel response, String expectedResult) {
        assertThat(response.getResult()).isEqualTo(expectedResult);
    }
}
