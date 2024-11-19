package helpers;

import io.qameta.allure.Step;
import models.Auth.AuthRequestModel;
import models.Auth.AuthResponseModel;

import static io.restassured.RestAssured.given;
import static specs.Spec.requestSpec;
import static specs.Spec.responseSpec200;

public class Authorisation {

    @Step("Получить токен авторизации")
    public static void getAuthToken(String username, String password){
        AuthRequestModel authRequest = new AuthRequestModel();
        authRequest.setUsername(username);
        authRequest.setPassword(password);

        AuthResponseModel authResponse =
                given(requestSpec)
                        .body(authRequest)
                        .when()
                        .post("/auth")
                        .then()
                        .spec(responseSpec200)
                        .extract().as(AuthResponseModel.class);

        System.setProperty("token", authResponse.getToken());
    }
}
