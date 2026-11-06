package tests;


import api.ApiClient;
import io.restassured.RestAssured;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    public Faker faker = new Faker();
    protected static final ApiClient api = new ApiClient();

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    public String generateToken(){
        return faker.regexify("[A-F0-9]{32}");
    }
}
