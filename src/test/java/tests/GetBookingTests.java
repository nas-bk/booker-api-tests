package tests;

import models.BaseRequestModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static specs.Spec.requestSpec;
import static specs.Spec.responseSpec200;

public class GetBookingTests extends TestBase {

    @Test
    @DisplayName("Получение информации существующего бронирования")
    void successfulGetBookingTest() {

        step("Подготовить тестовые данные", () ->
                idBooking = createBooking(bookingData));

        BaseRequestModel response =
                step("Отправить запрос на получение данных бронирования по его id", () ->
                        given(requestSpec)
                                .cookie("token", System.getProperty("token"))
                                .when()
                                .get("/booking/" + idBooking)
                                .then()
                                .spec(responseSpec200)
                                .extract().as(BaseRequestModel.class));

        step("Проверить, что запрос вернул данные соответсвющие созданному броннированию.", () ->
                assertThat(response).isEqualTo(bookingData));

        step("Удалить тестовые данные", () ->
                deleteBooking(idBooking));
    }

}
