package helpers;

import io.qameta.allure.Step;
import models.auth.AuthRequestModel;
import models.auth.AuthResponseModel;

import static io.restassured.RestAssured.given;
import static specs.Spec.requestSpec;
import static specs.Spec.responseSpec200;

public class Authorization {

    @Step("Получить токен авторизации")
    public static String getAuthToken(String username, String password) {
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

        return authResponse.getToken();
    }
}
