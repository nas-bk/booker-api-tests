package tests;

import models.BaseResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static specs.Spec.requestSpec;
import static specs.Spec.responseSpec200;

public class CreateBookingTests extends TestBase {

    @Test
    @DisplayName("Создание бронирования")
    void successfulCreateBookingTest() {

        BaseResponseModel response =
                step("Отправить запрос на создание бронирования", () ->
                        given(requestSpec)
                                .body(bookingData)
                                .cookie("token", System.getProperty("token"))
                                .when()
                                .post("/booking")
                                .then()
                                .spec(responseSpec200)
                                .extract().as(BaseResponseModel.class));

        step("Проверить, что данные бронирования соответствуют переданным в запросе", () ->
                assertThat(response.getBooking()).isEqualTo(bookingData));
        step("Проверить, что бронированию присвоен id", () ->
                assertThat(response.getBookingid()).isNotNull());

        step("Удалить тестовые данные", () ->
                deleteBooking(response.getBookingid()));
    }
}
