package api;

import io.qameta.allure.Param;
import io.qameta.allure.Step;
import io.qameta.allure.model.Parameter;
import models.ResponseModel;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.URLENC;
import static tests.TestData.ENDPOINT;

public class ApiClient {

    @Step("Login with token={token}")
    public ResponseModel login(String token, @Param(mode = Parameter.Mode.MASKED)String xApiKey, Integer expectedStatusCode) {
        String body = (token == null)
                ? "action=LOGIN"
                : String.format("token=%s&action=LOGIN", token);
        return given()
                .header("X-Api-Key", xApiKey)
                .log().all()
                .contentType(URLENC)
                .body(body)
                .when()
                .post(ENDPOINT)
                .then()
                .log().all()
                .statusCode(expectedStatusCode)
                .extract()
                .as(ResponseModel.class);
    }

    @Step("Logout with token={token}")
    public ResponseModel logout(String token, @Param(mode = Parameter.Mode.MASKED)String xApiKey, Integer expectedStatusCode) {
        String body = (token == null)
                ? "action=LOGOUT"
                : String.format("token=%s&action=LOGOUT", token);
        return given()
                .header("X-Api-Key", xApiKey)
                .log().all()
                .contentType(URLENC)
                .body(body)
                .when()
                .post(ENDPOINT)
                .then()
                .log().all()
                .statusCode(expectedStatusCode)
                .extract()
                .as(ResponseModel.class);
    }

    @Step("Action with token={token}")
    public ResponseModel action(String token, @Param(mode = Parameter.Mode.MASKED)String xApiKey, Integer expectedStatusCode) {
        String body = (token == null)
                ? "action=ACTION"
                : String.format("token=%s&action=ACTION", token);
        return given()
                .header("X-Api-Key", xApiKey)
                .log().all()
                .contentType(URLENC)
                .body(body)
                .when()
                .post(ENDPOINT)
                .then()
                .log().all()
                .statusCode(expectedStatusCode)
                .extract()
                .as(ResponseModel.class);
    }

    @Step("Query with body = {body}")
    public ResponseModel query(String body, @Param(mode = Parameter.Mode.MASKED)String xApiKey, Integer expectedStatusCode) {
        return given()
                .header("X-Api-Key", xApiKey)
                .log().all()
                .contentType(URLENC)
                .body(body)
                .when()
                .post(ENDPOINT)
                .then()
                .log().all()
                .statusCode(expectedStatusCode)
                .extract()
                .as(ResponseModel.class);
    }
}
