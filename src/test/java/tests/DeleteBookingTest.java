package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static specs.Spec.*;

public class DeleteBookingTest extends TestBase {

    @Test
    @DisplayName("Удаление бронирования")
    void successfulDeleteBookingTest() {

        step("Подготовить тестовые данные", () ->
                idBooking = createBooking(bookingData));

        step("Отправить запрос на удаление бронирования. Проверить, что вернулся код ответа 201", () ->
                given(requestSpec)
                        .cookie("token", System.getProperty("token"))
                        .when()
                        .delete("/booking/" + idBooking)
                        .then()
                        .spec(responseSpec201));

    }

    @Test
    @DisplayName("Удаление бронирования без токена авторизации")
    void deleteBookingWithoutTokenTest() {

        step("Подготовить тестовые данные", () ->
                idBooking = createBooking(bookingData));

        step("Отправить запрос на удаление бронирования без токена. Проверить, что вернулся код ответа 403", () ->
                given(requestSpec)
                        .when()
                        .delete("/booking/" + idBooking)
                        .then()
                        .spec(responseSpec403));
    }
}
