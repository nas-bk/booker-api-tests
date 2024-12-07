package tests;

import models.BookingIdsResponseModel;
import models.BookingResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static helpers.CustomAllureListener.withCustomTemplates;
import static helpers.UtilMath.getBookingWithMaxId;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;
import static specs.Spec.requestSpec;
import static specs.Spec.responseSpec200;

public class CreateBookingTests extends TestBase {

    @Test
    @DisplayName("Создание бронирования")
    void successfulCreateBookingTest() {
        BookingIdsResponseModel[] bookingIdArray =
                step("Получить массив id существующих бронирований", () ->
                        given()
                                .cookie("token", token)
                                .filter(withCustomTemplates())
                                .contentType(JSON)
                                .when()
                                .get("/booking")
                                .then()
                                .statusCode(200)
                                .extract().as(BookingIdsResponseModel[].class));

        var maxId = step("Найти бронирование с максимальным id", () -> getBookingWithMaxId(bookingIdArray));

        BookingResponseModel response =
                step("Отправить запрос на создание бронирования", () ->
                        given(requestSpec)
                                .body(bookingData)
                                .cookie("token", token)
                                .when()
                                .post("/booking")
                                .then()
                                .spec(responseSpec200)
                                .extract().as(BookingResponseModel.class));


        step("Проверить, что данные бронирования соответствуют переданным в запросе", () ->
                assertThat(response.getBooking()).isEqualTo(bookingData));
        step("Проверить, что бронированию присвоен id", () ->
                assertThat(response.getBookingid()).isNotNull().isGreaterThan(0));
        step("Проверить, что id созданного бронирования больше существующих id", () ->
                assertThat(response.getBookingid()).isGreaterThan(maxId.getBookingid()));

        step("Удалить тестовые данные", () ->
                deleteBooking(response.getBookingid()));
    }
}
