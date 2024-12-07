package tests;

import models.BookingBodyModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static specs.Spec.*;

public class UpdateBookingTests extends TestBase {

    @Test
    @DisplayName("Обновление текущего бронирования")
    void successfulUpdateBookingTest() {
        int idBooking = createBooking(bookingData);
        BookingBodyModel newBooking = updateBookingData();

        BookingBodyModel response = step("Отправить запрос на обновление бронирования", () ->
                given(requestSpec)
                        .body(newBooking)
                        .cookie("token", token)
                        .when()
                        .put("/booking/" + idBooking)
                        .then()
                        .spec(responseSpec200)
                        .extract().as(BookingBodyModel.class));

        step("Проверить, что данные бронирования обновлены и не соответствуют изначальным", () -> {
            assertThat(response.getLastname()).isNotEqualTo(bookingData.getLastname());
            assertThat(response.getFirstname()).isNotEqualTo(bookingData.getFirstname());
            assertThat(response.getTotalprice()).isNotEqualTo(bookingData.getTotalprice());
            assertThat(response.getDepositpaid()).isNotEqualTo(bookingData.getDepositpaid());
            assertThat(response.getBookingdates()).isNotEqualTo(bookingData.getBookingdates());
        });

        step("Удалить тестовые данные", () ->
                deleteBooking(idBooking));
    }

    @Test
    @DisplayName("Обновление текущего бронирования без токена авторизации")
    void updateBookingWithoutTokenTest() {
        int idBooking = createBooking(bookingData);
        BookingBodyModel newBooking = updateBookingData();

        step("Отправить запрос на обновление бронирования без токена. Проверить, что вернулся код ответа 403", () ->
                given(requestSpec)
                        .body(newBooking)
                        .when()
                        .put("/booking/" + idBooking)
                        .then()
                        .spec(responseSpec403));

        step("Удалить тестовые данные", () ->
                deleteBooking(idBooking));
    }
}
