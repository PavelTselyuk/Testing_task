package api;

import models.ResponseModel;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.URLENC;
import static tests.TestData.X_API_KEY;

public class ApiClient {

    public ResponseModel login(String token, String xApiKey, Integer expectedStatusCode) {
        String body = (token == null)
                ? "action=LOGIN"
                : String.format("token=%s&action=LOGIN", token);
        return given()
                .header("X-Api-Key", xApiKey)
                .log().all()
                .contentType(URLENC)
                .body(body)
                .when()
                .post("/endpoint")
                .then()
                .log().all()
                .statusCode(expectedStatusCode)
                .extract()
                .as(ResponseModel.class);
    }

    public ResponseModel logout(String token, String xApiKey, Integer expectedStatusCode) {
        String body = (token == null)
                ? "action=LOGOUT"
                : String.format("token=%s&action=LOGOUT", token);
        return given()
                .header("X-Api-Key", xApiKey)
                .log().all()
                .contentType(URLENC)
                .body(body)
                .when()
                .post("/endpoint")
                .then()
                .log().all()
                .statusCode(expectedStatusCode)
                .extract()
                .as(ResponseModel.class);
    }

    public ResponseModel action(String token, String xApiKey, Integer expectedStatusCode) {
        String body = (token == null)
                ? "action=ACTION"
                : String.format("token=%s&action=ACTION", token);
        return given()
                .header("X-Api-Key", xApiKey)
                .log().all()
                .contentType(URLENC)
                .body(body)
                .when()
                .post("/endpoint")
                .then()
                .log().all()
                .statusCode(expectedStatusCode)
                .extract()
                .as(ResponseModel.class);
    }

    public ResponseModel query(String body, String xApiKey, Integer expectedStatusCode) {
        return given()
                .header("X-Api-Key", xApiKey)
                .log().all()
                .contentType(URLENC)
                .body(body)
                .when()
                .post("/endpoint")
                .then()
                .log().all()
                .statusCode(expectedStatusCode)
                .extract()
                .as(ResponseModel.class);
    }
}
