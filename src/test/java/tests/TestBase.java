package tests;

import config.BaseConfig;
import helpers.FileReader;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import models.BaseRequestModel;
import models.BaseResponseModel;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;

import static helpers.Authorisation.getAuthToken;
import static io.restassured.RestAssured.given;
import static specs.Spec.*;

public class TestBase {

    static  BaseConfig config = ConfigFactory.create(BaseConfig.class, System.getProperties());
    public static BaseRequestModel bookingData = new BaseRequestModel();
    public Integer idBooking;

    @BeforeAll
    static void beforeAll(){
        RestAssured.baseURI = config.baseUrl();
        getAuthToken(config.username(), config.password());
        bookingData = FileReader.readJson();
    }

    @Step("Создать бронирование")
    public Integer createBooking(BaseRequestModel body){
        BaseResponseModel response =
                given(requestSpec)
                        .body(body)
                        .cookie("token",System.getProperty("token"))
                        .when()
                        .post("/booking")
                        .then()
                        .spec(responseSpec200)
                        .extract().as(BaseResponseModel.class);
        return response.getBookingid();
    }

    @Step("Удалить бронирование")
    public void deleteBooking(Integer id) {
        given(requestSpec)
                .cookie("token", System.getProperty("token"))
                .when()
                .delete("/booking/" + id)
                .then()
                .spec(responseSpec201);
    }

}
