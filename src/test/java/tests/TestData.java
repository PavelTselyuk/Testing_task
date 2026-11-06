package tests;

import api.ApiClient;
import io.github.cdimascio.dotenv.Dotenv;

public class TestData {


    public static final String ENDPOINT = "/endpoint";
    public static final String RESULT_OK = "OK";
    public static final String RESULT_ERROR = "ERROR";
    public static final String WRONG_FORMATTED_TOKEN = "wrong_formatted_token";

    private static final Dotenv dotenv = Dotenv.load();
    public static final String X_API_KEY = dotenv.get("X_API_KEY");
}
